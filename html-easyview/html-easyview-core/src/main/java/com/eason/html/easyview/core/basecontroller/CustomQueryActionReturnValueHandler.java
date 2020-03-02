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

import com.eason.html.easyview.core.annotations.CustomQueryAction;
import com.eason.html.easyview.core.annotations.TableItemAction;
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

	private Log logger=LogFactory.getLog(CustomQueryActionReturnValueHandler.class);
	
	public CustomQueryActionReturnValueHandler(DateFormat dateFormat) {
		super();
		if (dateFormat!=null) {
			JacksonUtils.getMapper().setDateFormat(dateFormat);
		}
	}

	@Override
	public boolean supportsReturnType(MethodParameter returnType) {
		return returnType.hasMethodAnnotation(CustomQueryAction.class)||returnType.hasMethodAnnotation(TableItemAction.class);
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
        	TableViewResult tableViewResult = new TableViewResult();
        	TableItemAction tableItemAction = returnType.getMethodAnnotation(TableItemAction.class);
        	if (tableItemAction!=null) {
        		tableViewResult.setData(returnValue);
			}else {
				CustomQueryAction customQueryAction = returnType.getMethodAnnotation(CustomQueryAction.class);
				TableInfo tableInfo=null;
				if (List.class.isAssignableFrom(returnType.getParameterType())) {
					tableInfo = new TableInfo((List<?>) returnValue);
				}else{
					List<Object> ret=new ArrayList<>();
					ret.add(returnValue);
					tableInfo = new TableInfo(ret);
				}
				Class<?> conditionForm = customQueryAction.conditionForm();
				if (conditionForm!=Object.class) {
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
