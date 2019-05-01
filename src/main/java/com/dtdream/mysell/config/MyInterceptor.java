package com.dtdream.mysell.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author yxiumei
 * @Data 2019/5/1 23:05
 */
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object userInfo = session.getAttribute("userInfo");
        if (null == userInfo) {
            response.sendRedirect(request.getContextPath()+"/index");
            return false;
        }
        return true;
    }
}
