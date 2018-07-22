package com.lightwing.springmvc.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 *
 * @author Lightwing Ng
 */
public class MyInterceptor implements HandlerInterceptor {
    // 方法执行后被执行
    // 处理异常，清资源，记录日志等等
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) {
        System.out.println(" > MyInterceptor.afterCompletion");
    }

    // 方法执行之后，返回ModelAndView之前被执行
    // 设置页面的共用参数等等
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) {
        System.out.println(" > MyInterceptor.postHandle");
    }

    // 进入方法前被执行
    // 登录拦截，权限验证等等
    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) {
        System.out.println(" > MyInterceptor.preHandle");
        // true放行, false拦截
        return true;
    }
}
