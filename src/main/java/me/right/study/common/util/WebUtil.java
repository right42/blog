package me.right.study.common.util;

import io.undertow.servlet.handlers.ServletRequestContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class WebUtil {

    public static HttpServletRequest getRequest(){
        var req = (HttpServletRequest) ServletRequestContext.current().getServletRequest();
        return req;
    }

    public static void setSessionAttribute(String key, Object value){
        getRequest().getSession().setAttribute(key, value);
    }

    public static <T> T getSessionAttribute(String key){
        return (T) getRequest().getSession().getAttribute(key);
    }

    public static void removeSession(String key){
        getRequest().getSession().removeAttribute(key);
    }

}
