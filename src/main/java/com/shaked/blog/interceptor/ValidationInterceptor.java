package com.shaked.blog.interceptor;

import com.shaked.blog.exception.ServiceForbiddenException;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by USER1 on 1/28/2018.
 */
public class ValidationInterceptor implements HandlerInterceptor {

    private static final String USER = "user";

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String user = httpServletRequest.getHeader(USER);
        if (StringUtils.isEmpty(user)) {
            throw new ServiceForbiddenException("user header is missing");
        }
        httpServletRequest.setAttribute(USER, user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
