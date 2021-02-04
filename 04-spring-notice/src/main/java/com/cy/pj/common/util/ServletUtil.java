package com.cy.pj.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class ServletUtil {

    /**获取请求对象*/
    public static HttpServletRequest getRequest(){
        return getServletRequestAttributes().getRequest();
    }

    /**
     * 通过RequestContextHolder类型获取请求属性
     */
    public static ServletRequestAttributes getServletRequestAttributes(){
        //取信息是从当前线程中取出
        return (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
    }
}
