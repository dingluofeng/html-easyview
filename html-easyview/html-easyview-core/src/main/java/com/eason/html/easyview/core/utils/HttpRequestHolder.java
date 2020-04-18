package com.eason.html.easyview.core.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.NamedThreadLocal;

public abstract class HttpRequestHolder  {

    private static final ThreadLocal<HttpServletRequest> requestAttributesHolder = new NamedThreadLocal<>(
            "Request context");

    public static void resetRequestContext() {
		requestAttributesHolder.remove();
	}

    public static void setRequest(HttpServletRequest servletRequest) {
        if (servletRequest == null) {
            requestAttributesHolder.remove();
        } else {
            requestAttributesHolder.set(servletRequest);
        }
	}

    public static HttpServletRequest getRequest() {
        return requestAttributesHolder.get();
	}

}
