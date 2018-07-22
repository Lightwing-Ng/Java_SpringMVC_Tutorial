package com.lightwing.springmvc.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理器
 *
 * @author Lightwing Ng
 */
public class CustomerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) {
        String result = "The System is failed, please contact<br>administrator: lightwing" +
                ".ng@gmail.com";
        // 自定义异常处理
        if (ex instanceof MyException)
            result = ((MyException) ex).getMsg();
        ModelAndView mav = new ModelAndView();
        mav.addObject("msg", result);
        mav.setViewName("msg");

        return mav;
    }
}
