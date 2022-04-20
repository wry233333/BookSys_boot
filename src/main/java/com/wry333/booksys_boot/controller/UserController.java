package com.wry333.booksys_boot.controller;


import com.wry333.booksys_boot.domian.User;
import com.wry333.booksys_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping("/sign_in")
    public String sign_in(){
        return "login";
    }

    @RequestMapping("/sign_up")
    public String sign_up(){
        return "register";
    }

    @RequestMapping("user_login")
    public ModelAndView user_login(User user, ModelAndView mav, HttpSession session){
        User user_login = userService.login(user);
        if(user_login != null){
            session.setAttribute("user",user_login);
            mav.setViewName("user_index");
        }
        else {
            mav.setViewName("login");
            mav.addObject("log_msg","邮箱或密码错误");
        }
        return mav;
    }

    @RequestMapping("/register")
    public ModelAndView user_register(User user,ModelAndView mav){
        mav.setViewName("register");
        if(userService.register(user)){
            mav.setViewName("login");
            mav.addObject("log_msg","注册成功，请登录");
        }
        else{
            mav.addObject("reg_msg","邮箱地址重复，请重试");
        }
        return mav;
    }

}
