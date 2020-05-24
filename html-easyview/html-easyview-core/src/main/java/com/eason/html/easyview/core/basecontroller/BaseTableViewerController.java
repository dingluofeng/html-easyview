package com.eason.html.easyview.core.basecontroller;

import java.lang.annotation.Annotation;
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
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.eason.html.easyview.core.IAction;
import com.eason.html.easyview.core.IconStyle;
import com.eason.html.easyview.core.PageHolder;
import com.eason.html.easyview.core.QueryAction;
import com.eason.html.easyview.core.ToolBarAction;
import com.eason.html.easyview.core.WidgetStyle;
import com.eason.html.easyview.core.annotations.CustomQueryAction;
import com.eason.html.easyview.core.annotations.CustomTableViewAction;
import com.eason.html.easyview.core.annotations.TableItemAction;
import com.eason.html.easyview.core.annotations.TableViewController;
import com.eason.html.easyview.core.annotations.ToolItemAction;
import com.eason.html.easyview.core.form.CustomButton;
import com.eason.html.easyview.core.form.ToolItemButton;
import com.eason.html.easyview.core.form.table.TableItemLink;
import com.eason.html.easyview.core.form.table.formatter.TableColMappingFormatter;
import com.eason.html.easyview.core.form.table.formatter.TableColMappingFormatterManager;
import com.eason.html.easyview.core.form.table.model.TableColumnBuilder.TableColumn;
import com.eason.html.easyview.core.form.table.model.TableViewResult;
import com.eason.html.easyview.core.logging.Log;
import com.eason.html.easyview.core.logging.LogFactory;
import com.eason.html.easyview.core.page.SingleTableViewPage;
import com.eason.html.easyview.core.utils.BeanRefectUtils;
import com.eason.html.easyview.core.utils.CollectionUtils;
import com.eason.html.easyview.core.utils.HttpRequestHolder;
import com.eason.html.easyview.core.utils.ServiceFinder;
import com.eason.html.easyview.core.utils.StringUtils;

public abstract class BaseTableViewerController<Co, Vo> extends ServiceFinder implements InitializingBean {

	protected Log logger = LogFactory.getLog(this.getClass());

	protected int toolbarStyle = WidgetStyle.NONE /* WidgetStyle.ADD |WidgetStyle.REFLUSH */ ;

	private boolean onlineResource = false;
	
	private int pageSize = 10;

	private String baseUrl;

	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	private List<QueryAction> customActions = new ArrayList<>();

	private List<ToolBarAction> toolBarActions = new ArrayList<>();

	private List<TableItemLink> tableItemsLinks = new ArrayList<>();

	private final String titleName;

	@Autowired
	private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

	private TableColMappingFormatterManager colMappingFormatterManager = new TableColMappingFormatterManager();

	private Comparator<IAction> actionComparator = new Comparator<IAction>() {
		@Override
		public int compare(IAction queryAction1, IAction queryAction2) {
			return queryAction1.id().compareTo(queryAction2.id());
		}
	};

	/**
	 * @param tableTitle 表格展示标题
	 */
	public BaseTableViewerController(String titleName, int toolbarStyle) {
		super();
		this.titleName = titleName;
		this.toolbarStyle = toolbarStyle;
		enableDefaultTableItemOpt();
	}

	protected void enableDefaultTableItemOpt() {
		if ((WidgetStyle.ADD & toolbarStyle) != 0) {
			tableItemsLinks.add(TableItemLink.of("edit", "编辑", IconStyle.ICON_PENCIL, ""));
		}
		if ((WidgetStyle.DEL & toolbarStyle) != 0) {
			tableItemsLinks.add(TableItemLink.of("remove", "删除", IconStyle.ICON_TRASH, ""));
		}
	}

	private final void buildCustomQueryAction() {
		Class<?> clazz = getClass();
		Method[] declaredMethods = clazz.getDeclaredMethods();
		TableViewController tableViewController = AnnotationUtils.findAnnotation(clazz, TableViewController.class);
		if (tableViewController != null) {
			baseUrl = tableViewController.value()[0];
			pageSize = tableViewController.pageSize();
		} else {
			pageSize = 10;
			RequestMapping mapping = AnnotationUtils.findAnnotation(clazz, RequestMapping.class);
			if (mapping != null) {
				baseUrl = mapping.value()[0];
			}
		}
		for (Method method : declaredMethods) {
			Annotation[] customQueryActions = AnnotationUtils.getAnnotations(method);
			for (Annotation annotation : customQueryActions) {
				if (annotation instanceof TableItemAction) {
					TableItemAction tableItemAction = (TableItemAction) annotation;
					// buildTableItemAction
					buildTableItemAction(method, tableItemAction);
				}
				if (annotation instanceof ToolItemAction) {
					ToolItemAction toolItemAction = (ToolItemAction) annotation;
					// buildToolbarAction
					buildToolbarAction(method, toolItemAction);
				}
				if (annotation instanceof CustomQueryAction || annotation instanceof CustomTableViewAction) {
					CustomQueryAction customQueryAction = AnnotatedElementUtils.findMergedAnnotation(method, CustomQueryAction.class);
					// buildCustomAction
					buildCustomAction(method, customQueryAction);
				}
			}
		}
		Collections.sort(customActions, actionComparator);
		Collections.sort(toolBarActions, actionComparator);
	}

	private final void buildTableItemAction(Method method, TableItemAction tableItemAction) {
		if (tableItemAction != null) {
			String title = tableItemAction.title();
			if (StringUtils.isBlank(title)) {
				title = method.getName();
			}
			TableItemLink itemLink = TableItemLink.of(method.getName(), title, tableItemAction.styleClass(),
					baseUrl + tableItemAction.path()[0]).msgType(tableItemAction.msgType()).hideScript(tableItemAction.hideScript());
			checkedUniqId(tableItemsLinks, itemLink);
			tableItemsLinks.add(itemLink);
		}
	}

	private final void buildCustomAction(Method method, CustomQueryAction customQueryAction) {
		if (customQueryAction == null) {
			return;
		}
		String id = customQueryAction.id();
		if (StringUtils.isBlank(id)) {
			id = method.getName();
		}
		List<TableColumn> viewColumns = BeanRefectUtils.parseColumns(method, colMappingFormatterManager);
		if (CollectionUtils.isEmpty(viewColumns)) {
			throw new IllegalArgumentException(method.getDeclaringClass().getSimpleName() + "." + method.getName()
					+ "找不到表格类定义，请使用@TableColumns标注展示列");
		}
		
		QueryAction queryAction = new QueryAction(id, customQueryAction.title(), baseUrl + customQueryAction.path()[0]);
		queryAction.setViewColumns(viewColumns);
		queryAction.setPagesize(customQueryAction.pageSize());
		Class<?> conditionForm = customQueryAction.conditionForm();
		if (conditionForm != Object.class) {
			queryAction.setSearchCondition(conditionForm);
		}
		checkedUniqId(customActions, queryAction);
		this.customActions.add(queryAction);
	}

	private final void buildToolbarAction(Method method, ToolItemAction toolItemAction) {
		if (toolItemAction != null) {
			String title = toolItemAction.title();
			String styleClass = toolItemAction.styleClass();
			String url = baseUrl + toolItemAction.path()[0];
			ToolBarAction toolBarAction = new ToolBarAction(method.getName(), title, url);
			toolBarAction.setClassStyle(styleClass);
			checkedUniqId(toolBarActions, toolBarAction);
			toolBarActions.add(toolBarAction);
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
		tableViewPage.setPageSize(pageSize);
		if (CollectionUtils.isNotEmpty(customActions)) {
			for (QueryAction queryAction : customActions) {
				CustomButton queryBtn = CustomButton.of(queryAction);
				tableViewPage.addCustomButton(queryBtn);
			}
		}
		if (CollectionUtils.isNotEmpty(this.toolBarActions)) {
			for (ToolBarAction toolBarAction : toolBarActions) {
				ToolItemButton toolItemButton = ToolItemButton.of(toolBarAction);
				tableViewPage.addToolItemButton(toolItemButton);
			}
		}
		if (CollectionUtils.isNotEmpty(tableItemsLinks)) {
			for (TableItemLink itemLink : tableItemsLinks) {
				tableViewPage.addTableItemsLink(itemLink);
			}
		}
		colMappingFormatterManager.initInjectExtension();
		tableViewPage.setColMappingFormatterManager(colMappingFormatterManager);
		tableViewPage.setToolbarStyle(toolbarStyle);
		tableViewPage.setOnlineResource(onlineResource);
		return tableViewPage.html(baseUrl, getCoClass(), getVoClass());
	}

	@RequestMapping("/list")
	@ResponseBody
    public final TableViewResult listInfos(PageParams pageParams, Co co, HttpServletRequest servletRequest) {
        HttpRequestHolder.setRequest(servletRequest);
		try {
			logger.infof("列表查询请求，pageParams:%s,condition:%s", pageParams, co);
			PageHolder<Vo> page = list(pageParams, co);
			int total =0;
			List<Vo> records =null;
			if (page!=null) {
				total = page.total;
				records = page.records;
			}
			return new TableViewResult(0, total, records);
		} catch (Throwable e) {
			logger.error("获取列表信息失败,page:" + pageParams + ",co:" + co, e);
			return null;
        } finally {
            HttpRequestHolder.resetRequestContext();
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
    public final TableViewResult addInfo(Vo vo, HttpServletRequest servletRequest) {
        HttpRequestHolder.setRequest(servletRequest);
		try {
			logger.infof("添加请求，vo:%s", vo);
			ResponseResult result = add(vo);
			return new TableViewResult(result);
		} catch (Exception e) {
			logger.error("添加信息失败,vo:" + vo, e);
			return new TableViewResult(-1, e.getMessage());
        } finally {
            HttpRequestHolder.resetRequestContext();
		}
	}

	/**
	 * 新增对象后台处理逻辑，如果没有新增功能，可以默认空实现
	 * 
	 * @param vo 新增对象
	 * @return
	 */
	protected ResponseResult add(Vo vo) {
		return ResponseResult.ok();
	}

	@RequestMapping("/update")
	@ResponseBody
    public final TableViewResult updateInfo(Vo vo, HttpServletRequest servletRequest) {
        HttpRequestHolder.setRequest(servletRequest);
		try {
			logger.infof("更新请求，vo:%s", vo);
			ResponseResult result = update(vo);
			return new TableViewResult(result);
		} catch (Exception e) {
			logger.error("更新信息失败,vo" + vo, e);
			return new TableViewResult(-1, e.getMessage());
        } finally {
            HttpRequestHolder.resetRequestContext();
        }
	}

	/**
	 * 更新对象后台处理逻辑，如果没有更新功能，可以默认空实现
	 * 
	 * @param vo 更新对象
	 * @return
	 */
	protected ResponseResult update(Vo vo) {
		return ResponseResult.ok();
	}

	@RequestMapping("/delete")
	@ResponseBody
    public final TableViewResult delInfo(String id, String type, HttpServletRequest servletRequest) {
        HttpRequestHolder.setRequest(servletRequest);
		try {
			logger.infof("删除请求，id:%s,type:%s", id, type);
			String[] ids = StringUtils.split(id, ",");
			ResponseResult result = delete(ids);
			return new TableViewResult(result);
		} catch (Exception e) {
			logger.error("删除失败,id:" + id + ",type:" + type, e);
			return new TableViewResult(-1, e.getMessage());
        } finally {
            HttpRequestHolder.resetRequestContext();
        }
	}

	/**
	 * 根据主键ID列表删除表格对象
	 * 
	 * @param ids 设置的主键值，没有设置默认对象第一个属性作为主键
	 * @return
	 */
	protected ResponseResult delete(String... ids) {
		return ResponseResult.ok();
	}

	@RequestMapping("/fileimport")
	@ResponseBody
    public final TableViewResult fileImport_(MultipartHttpServletRequest request, HttpServletRequest servletRequest) {
        HttpRequestHolder.setRequest(servletRequest);
		MultipartFile multipartFile = request.getFile("file");
		try {
			logger.infof("收到上传请求，file:%s", multipartFile.getOriginalFilename());
			ResponseResult result = fileImport(multipartFile);
			return new TableViewResult(result);
		} catch (Exception e) {
			logger.error("上传信息失败,file:" + multipartFile.getOriginalFilename(), e);
			return new TableViewResult(-1, e.getMessage());
        } finally {
            HttpRequestHolder.resetRequestContext();
        }
	}

	protected ResponseResult fileImport(MultipartFile multipartFile) {
		return ResponseResult.ok();
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

	protected final <Action extends IAction> void checkedUniqId(List<Action> actions, IAction action) {
		for (IAction queryAction : actions) {
			if (StringUtils.equalsIgnoreCase(queryAction.id(), action.id())) {
				throw new IllegalArgumentException(
						"Action id() " + action.id() + " is already exsit! please config another uniqid ");
			}
		}
	}

	@Override
	public void afterPropertiesSet() {
		// HandlerMethodReturnValueHandler
		List<HandlerMethodReturnValueHandler> returnValueHandlers = requestMappingHandlerAdapter
				.getReturnValueHandlers();
		List<HandlerMethodReturnValueHandler> allReturnValueHandlers = new ArrayList<>();
		// 自定义returnHandler
		allReturnValueHandlers.add(new CustomQueryActionReturnValueHandler(dateFormat));
		allReturnValueHandlers.addAll(returnValueHandlers);
		requestMappingHandlerAdapter.setReturnValueHandlers(allReturnValueHandlers);
		// HandlerMethodArgumentResolver
		List<HandlerMethodArgumentResolver> allArgumentResolvers = new ArrayList<>();
		List<HandlerMethodArgumentResolver> argumentResolvers = requestMappingHandlerAdapter.getArgumentResolvers();
		// 自定义argumentResolvers
		for (HandlerMethodArgumentResolver handlerMethodArgumentResolver : argumentResolvers) {
			if (handlerMethodArgumentResolver instanceof org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor) {
				allArgumentResolvers.add(new TableItemActionMethodArgumentResolver(requestMappingHandlerAdapter.getMessageConverters()));
			}
			allArgumentResolvers.add(handlerMethodArgumentResolver);
		}
		// argumentResolvers
		requestMappingHandlerAdapter.setArgumentResolvers(allArgumentResolvers);
		colMappingFormatterManager.setServiceFinder(this);
		// buildCustomQueryAction
		buildCustomQueryAction();
	}

	public void setOnlineResource(boolean onlineResource) {
		this.onlineResource = onlineResource;
	}

}