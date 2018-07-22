package com.lightwing.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 用户请求处理器
 *
 * @author Lightwing Ng
 */
@Controller
@RequestMapping("user")
public class UserController {
    @RequestMapping("toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("login")
    public String login(String username, String password, HttpSession session) {
        if (username.equals("admin") && password.equals("admin")) {
            session.setAttribute("username", username);
            return "redirect:/itemList.action";
        } else {
            System.out.println("Password: " + password + " is wrong");
        }
        return "login";
    }
}
