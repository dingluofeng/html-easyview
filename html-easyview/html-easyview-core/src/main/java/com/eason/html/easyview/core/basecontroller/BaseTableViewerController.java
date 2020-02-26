package com.eason.html.easyview.core.basecontroller;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.eason.html.easyview.core.QueryAction;
import com.eason.html.easyview.core.annotations.CustomQueryAction;
import com.eason.html.easyview.core.form.CustomButton;
import com.eason.html.easyview.core.form.table.formatter.TableColMappingFormatter;
import com.eason.html.easyview.core.form.table.formatter.TableColMappingFormatterManager;
import com.eason.html.easyview.core.form.table.model.TableViewResult;
import com.eason.html.easyview.core.logging.Log;
import com.eason.html.easyview.core.logging.LogFactory;
import com.eason.html.easyview.core.page.SingleTableViewPage;
import com.eason.html.easyview.core.utils.CollectionUtils;
import com.eason.html.easyview.core.utils.StringUtils;

public abstract class BaseTableViewerController<Co, Vo> implements InitializingBean{

	protected Log logger = LogFactory.getLog(this.getClass());

	protected int toolbarStyle = 0 /* WiggetStyle.ADD |WiggetStyle.REFLUSH */ ;
	
	private boolean onlineResource=true;
	
	private String baseUrl;
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	private List<QueryAction> customActions=new ArrayList<>();
	
	private final String titleName;
	
	@Autowired
    RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    private TableColMappingFormatterManager colMappingFormatterManager = new TableColMappingFormatterManager();

    private Comparator<QueryAction> actionComparator = new Comparator<QueryAction>() {
        @Override
        public int compare(QueryAction queryAction1, QueryAction queryAction2) {
            return queryAction1.id().compareTo(queryAction2.id());
        }
    };

	/**
	 * @param tableTitle  表格展示标题
	 */
    public BaseTableViewerController(String titleName, int toolbarStyle) {
		super();
		this.titleName=titleName;
        this.toolbarStyle = toolbarStyle;
        // buildCustomQueryAction
        buildCustomQueryAction();
    }

    private final void buildCustomQueryAction() {
        Class<?> clazz = getClass();
		Method[] declaredMethods = clazz.getDeclaredMethods();
		RequestMapping mapping = clazz.getAnnotation(RequestMapping.class);
		if (mapping!=null) {
			baseUrl = mapping.value()[0];
		}
		for (Method method : declaredMethods) {
			CustomQueryAction customQueryAction = method.getAnnotation(CustomQueryAction.class);
			if (customQueryAction==null) {
				continue;
			}
			String id = customQueryAction.id();
			if (StringUtils.isBlank(id)) {
				id=method.getName();
			}
			QueryAction queryAction=new QueryAction(id,customQueryAction.title(),baseUrl+customQueryAction.path()[0]); 
			Class<?> conditionForm = customQueryAction.conditionForm();
			if (conditionForm!=Object.class) {
				queryAction.setSearchCondition(conditionForm);
			}
			addQueryAction(queryAction);
		}
    }

	@RequestMapping(value = "/tableview", produces = { "text/html; charset=UTF-8" })
	@ResponseBody
	public final String tableView(HttpServletRequest request) throws Throwable {
		String requestURI = request.getRequestURI();
		if (StringUtils.isBlank(baseUrl)) {
			baseUrl = requestURI + "/..";
		}
        SingleTableViewPage tableViewPage = new SingleTableViewPage(titleName);
		if (CollectionUtils.isNotEmpty(customActions)) {
            Collections.sort(customActions, actionComparator);
			for (QueryAction queryAction : customActions) {
				CustomButton queryBtn = CustomButton.of(queryAction);
				tableViewPage.addCustomButton(queryBtn);
			}
		}
        tableViewPage.setColMappingFormatterManager(colMappingFormatterManager);
		tableViewPage.setToolbarStyle(toolbarStyle);
		tableViewPage.setOnlineResource(onlineResource);
        return tableViewPage.html(baseUrl, getCoClass(), getVoClass());
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public final TableViewResult listInfos(PageParams pageParams, Co co) {
		try {
			logger.infof("列表查询请求，pageParams:%s,condition:%s", pageParams, co);
			PageHolder<Vo> page = list(pageParams, co);
			int total = page.total;
			return new TableViewResult(total, page.records);
		} catch (Throwable e) {
			logger.error("获取列表信息失败,page:" + pageParams + ",co:" + co, e);
			return null;
		}
	}

	/**
	 * 列表分页查询结果
	 * 
	 * @param pageParams 分页查询参数
	 * @param co         查询条件对象
	 * @return
	 */
	protected abstract PageHolder<Vo> list(PageParams pageParams, Co co);

	@RequestMapping("/add")
	@ResponseBody
	public final TableViewResult addInfo(Vo vo) {
		try {
			logger.infof("添加请求，vo:%s", vo);
			int result = add(vo);
			return new TableViewResult(result, "success");
		} catch (Exception e) {
			logger.error("添加信息失败,vo:" + vo, e);
			return new TableViewResult(-1, e.getMessage());
		}
	}

	/**
	 * 新增对象后台处理逻辑，如果没有新增功能，可以默认空实现
	 * 
	 * @param vo 新增对象
	 * @return
	 */
	protected int add(Vo vo) {
		return 0;
	}

	@RequestMapping("/update")
	@ResponseBody
	public final TableViewResult updateInfo(Vo vo) {
		try {
			logger.infof("更新请求，vo:%s", vo);
			int result = update(vo);
			return new TableViewResult(result, "success");
		} catch (Exception e) {
			logger.error("更新信息失败,vo" + vo, e);
			return new TableViewResult(-1, e.getMessage());
		}
	}

	/**
	 * 更新对象后台处理逻辑，如果没有更新功能，可以默认空实现
	 * 
	 * @param vo 更新对象
	 * @return
	 */
	protected int update(Vo vo) {
		return 0;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public final TableViewResult delInfo(String id, String type) {
		try {
			logger.infof("删除请求，id:%s,type:%s", id, type);
			String[] ids = StringUtils.split(id, ",");
			int result = delete(ids);
			return new TableViewResult(result, "success");
		} catch (Exception e) {
			logger.error("删除失败,id:" + id + ",type:" + type, e);
			return new TableViewResult(-1, e.getMessage());
		}
	}

	/**
	 * 根据主键ID列表删除表格对象
	 * 
	 * @param ids 设置的主键值，没有设置默认对象第一个属性作为主键
	 * @return
	 */
	protected int delete(String... ids) {
		return 0;
	}
	
	@RequestMapping("/upload")
    @ResponseBody
    public final TableViewResult uploadFile(MultipartHttpServletRequest request) {
        MultipartFile multipartFile = request.getFile("file");
        try {
            logger.infof("收到上传请求，file:%s", multipartFile.getOriginalFilename());
            int result = upload(multipartFile);
            return new TableViewResult(result, "success");
        } catch (Exception e) {
            logger.error("上传信息失败,file:" + multipartFile.getOriginalFilename(), e);
            return new TableViewResult(-1, e.getMessage());
        }
    }

    protected int upload(MultipartFile multipartFile) {
        return 0;
    }

    protected final void addTableColMappingFormatter(TableColMappingFormatter mappingFormatter) {
        colMappingFormatterManager.addTableColMappingFormatter(mappingFormatter);
    }

    private final Class<?> getCoClass() {
        Class<?> genericClass = getGenericClass(getClass().getGenericSuperclass(), 0);
        return genericClass;
    }

	private final Class<?> getVoClass() {
        Class<?> genericClass = getGenericClass(getClass().getGenericSuperclass(), 1);
		return genericClass;
	}

	public final Class<?> getGenericClass(Type cls, int i) {
		try {
			ParameterizedType parameterizedType = (ParameterizedType) cls;
			Object genericClass = parameterizedType.getActualTypeArguments()[i];
			if (genericClass instanceof ParameterizedType) { // handle nested generic type
				return (Class<?>) ((ParameterizedType) genericClass).getRawType();
			} else if (genericClass instanceof GenericArrayType) { // handle array generic type
				return (Class<?>) ((GenericArrayType) genericClass).getGenericComponentType();
			} else if (((Class<?>) genericClass).isArray()) {
				// Requires JDK 7 or higher, Foo<int[]> is no longer GenericArrayType
				return ((Class<?>) genericClass).getComponentType();
			} else {
				return (Class<?>) genericClass;
			}
		} catch (Throwable e) {
			throw new IllegalArgumentException(cls + " generic type undefined!", e);
		}
	}

	private final void addQueryAction(QueryAction action) {
    	if (!checkedUniqId(action)) {
			throw new IllegalArgumentException("CustomQueryAction id() "+action.id()+" is already exsit! please config another uniqid ");
		}
        this.customActions.add(action);
    }
    
    private final boolean checkedUniqId(QueryAction action) {
    	for (QueryAction queryAction : customActions) {
			if (StringUtils.equalsIgnoreCase(queryAction.id(), action.id())) {
				return false;
			}
		}
    	return true;
    }

    @Override
    public void afterPropertiesSet() {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = requestMappingHandlerAdapter
                .getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> list = new ArrayList<>();
        list.add(new CustomQueryActionReturnValueHandler(dateFormat));//自定义returnHandler
        list.addAll(returnValueHandlers);
        requestMappingHandlerAdapter.setReturnValueHandlers(list);
    }

	public void setOnlineResource(boolean onlineResource) {
		this.onlineResource = onlineResource;
	}

	public static class PageHolder<T> {

		public final int total;

		public final List<T> records;

		public PageHolder(int total, List<T> records) {
			super();
			this.total = total;
			this.records = records;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Page [total=");
			builder.append(total);
			builder.append(", records=");
			builder.append(records);
			builder.append("]");
			return builder.toString();
		}
	}
}