/**
 * 
 */
package com.eason.html.easyview.core.basecontroller;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.eason.html.easyview.core.IMessageForm;
import com.eason.html.easyview.core.annotations.CustomQueryAction;
import com.eason.html.easyview.core.annotations.TableItemAction;
import com.eason.html.easyview.core.annotations.ToolItemAction;
import com.eason.html.easyview.core.form.table.model.TableInfo;
import com.eason.html.easyview.core.form.table.model.TableViewResult;
import com.eason.html.easyview.core.logging.Log;
import com.eason.html.easyview.core.logging.LogFactory;
import com.eason.html.easyview.core.utils.JacksonUtils;

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
				|| returnType.hasMethodAnnotation(TableItemAction.class)
				|| returnType.hasMethodAnnotation(ToolItemAction.class);
	}

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
			TableItemAction tableItemAction = returnType.getMethodAnnotation(TableItemAction.class);
			ToolItemAction toolItemAction = returnType.getMethodAnnotation(ToolItemAction.class);
			if (tableItemAction != null || toolItemAction != null) {
				int type = toolItemAction != null ? toolItemAction.msgType() : IMessageForm.MSG_FORM;
				String title = toolItemAction != null ? toolItemAction.title() : "信息";
				String area = toolItemAction != null ? toolItemAction.area() : "['auto','auto']";
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
				tableViewResult.setMsgType(type);
				tableViewResult.setMsgTitle(title);
				tableViewResult.setArea(area);
			} else {
				tableViewResult = new TableViewResult();
				CustomQueryAction customQueryAction = returnType.getMethodAnnotation(CustomQueryAction.class);
				TableInfo tableInfo = null;
				if (List.class.isAssignableFrom(returnType.getParameterType())) {
					tableInfo = new TableInfo((List<?>) returnValue);
				} else {
					List<Object> ret = new ArrayList<>();
					if (returnValue != null) {
						ret.add(returnValue);
					}
					tableInfo = new TableInfo(ret);
				}
				Class<?> conditionForm = customQueryAction.conditionForm();
				if (conditionForm != Object.class) {
					tableInfo.setConditionClass(conditionForm);
				}
				tableViewResult.setData(tableInfo);
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

}
