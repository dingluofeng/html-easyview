/**
 * 
 */
package com.eason.html.easyview.core.basecontroller;

import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.eason.html.easyview.core.IMessageForm;
import com.eason.html.easyview.core.PageHolder;
import com.eason.html.easyview.core.annotations.CustomQueryAction;
import com.eason.html.easyview.core.annotations.CustomTableViewAction;
import com.eason.html.easyview.core.annotations.TableItemAction;
import com.eason.html.easyview.core.annotations.ToolItemAction;
import com.eason.html.easyview.core.form.table.model.TableColumnBuilder.TableColumn;
import com.eason.html.easyview.core.form.table.model.TableInfo;
import com.eason.html.easyview.core.form.table.model.TableViewResult;
import com.eason.html.easyview.core.logging.Log;
import com.eason.html.easyview.core.logging.LogFactory;
import com.eason.html.easyview.core.utils.BeanRefectUtils;
import com.eason.html.easyview.core.utils.JacksonUtils;
import com.google.common.base.Objects;

/**
 * @author dingluofeng
 *
 */
public class CustomQueryActionReturnValueHandler implements HandlerMethodReturnValueHandler {

	private Log logger = LogFactory.getLog(CustomQueryActionReturnValueHandler.class);

	public CustomQueryActionReturnValueHandler(DateFormat dateFormat) {
		super();
		if (dateFormat != null) {
			JacksonUtils.getMapper().setDateFormat(dateFormat);
		}
	}

	@Override
	public boolean supportsReturnType(MethodParameter returnType) {
		return returnType.hasMethodAnnotation(CustomQueryAction.class)
				|| returnType.hasMethodAnnotation(CustomTableViewAction.class)
				|| returnType.hasMethodAnnotation(TableItemAction.class)
				|| returnType.hasMethodAnnotation(ToolItemAction.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest) throws Exception {
		mavContainer.setRequestHandled(true);
		HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			TableViewResult tableViewResult = null;
			if (returnValue instanceof TableViewResult) {
				tableViewResult=(TableViewResult) returnValue;
			}else{
				Annotation[] methodAnnotations = returnType.getMethodAnnotations();
				for (Annotation annotation : methodAnnotations) {
					if (annotation instanceof TableItemAction || annotation instanceof ToolItemAction) {
						int msgType = getAnnotationValue(annotation, "msgType", IMessageForm.MSG_FORM);
						String title = getAnnotationValue(annotation, "title", "信息");
						String area = getAnnotationValue(annotation, "area", "['auto','auto']");
						if (returnValue instanceof TableViewResult) {
							tableViewResult = (TableViewResult) returnValue;
						} else if (returnValue instanceof String) {
							tableViewResult = new TableViewResult(0, (String) returnValue);
						} else if (returnValue instanceof ResponseResult) {
							tableViewResult = new TableViewResult((ResponseResult) returnValue);
						} else {
							tableViewResult = new TableViewResult();
							tableViewResult.setData(returnValue);
						}
						tableViewResult.setMsgType(msgType);
						tableViewResult.setMsgTitle(title);
						tableViewResult.setArea(area);
					} else {
						tableViewResult = new TableViewResult();
						Method method = returnType.getMethod();
						TableInfo tableInfo = null;
						// 判断返回原始数据类型
						Class<?> returnClass = BeanRefectUtils.getReturnClassFromMethod(method);
						List<TableColumn> columns = new ArrayList<>();
						if (Map.class.isAssignableFrom(returnClass)) {
							columns = BeanRefectUtils.parseColumnsfromMap(method, (Map<Object, Object>) returnValue);
						} else {
							columns = BeanRefectUtils.parseColumns(method, null);
						}
						if (List.class.isAssignableFrom(returnType.getParameterType())) {
							List<?> listValues = (List<?>) returnValue;
							tableInfo = new TableInfo(listValues, columns);
							tableViewResult.setTotal(listValues.size());
						}else if(PageHolder.class.isAssignableFrom(returnType.getParameterType())){
							PageHolder<?> value=(PageHolder<?>) returnValue;
							tableViewResult.setTotal(value.total);
							tableInfo = new TableInfo((List<?>) value.records, columns);
						} else {
							List<Object> ret = new ArrayList<>();
							if (returnValue != null) {
								ret.add(returnValue);
							}
							tableInfo = new TableInfo(ret, columns);
							tableViewResult.setTotal(1);
						}
						Class<?> conditionForm = getAnnotationValue(annotation, "conditionForm", Object.class);
						if (conditionForm != Object.class) {
							tableInfo.setConditionClass(conditionForm);
						}
						tableViewResult.setData(tableInfo);
					}
				}
			}
			writer.print(JacksonUtils.toJsonString(tableViewResult));
			writer.flush();
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	private final <T> T getAnnotationValue(Annotation annotation, String attributeName, T defaultValue) {
		Object value = AnnotationUtils.getValue(annotation, attributeName);
		Object defaultValue_ = AnnotationUtils.getDefaultValue(annotation, attributeName);
		if (value == null) {
			return defaultValue;
		} else if (Objects.equal(value, defaultValue_)) {
			return defaultValue;
		}
		return (T) value;
	}

}
