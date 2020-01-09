package me.right.study.common.util;

import io.undertow.servlet.handlers.ServletRequestContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class WebUtil {

    public static HttpServletRequest getRequest(){
        var req = (HttpServletRequest) ServletRequestContext.current().getServletRequest();
        return req;
    }
}
