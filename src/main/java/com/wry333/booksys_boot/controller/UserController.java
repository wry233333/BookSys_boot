package com.wry333.booksys_boot.controller;


import com.wry333.booksys_boot.domain.Record;
import com.wry333.booksys_boot.domain.User;
import com.wry333.booksys_boot.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping("/sign_in")
    public ModelAndView sign_in(ModelAndView mav) {
        mav.setViewName("login");
        return mav;
    }

    @RequestMapping("/sign_up")
    public ModelAndView sign_up(ModelAndView mav) {
        mav.setViewName("register");
        return mav;
    }



    @RequestMapping("user_login")
    public ModelAndView user_login(User user, ModelAndView mav, HttpSession session){
        User user_login = userService.login(user);
        if(user_login != null){
            session.setAttribute("user",user_login);
            mav.setViewName("forward:/user_admin");
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
            mav.setViewName("forward:login");
            mav.addObject("log_msg", "注册成功，请登录");
        }
        else{
            mav.addObject("reg_msg","邮箱地址重复，请重试");
        }
        return mav;
    }


    @RequestMapping("/user_admin")
    public ModelAndView user_admin(ModelAndView mav, @SessionAttribute User user) throws Exception {
        mav.setViewName("user_index");
        List<Integer> list = userService.get_index_data(user);
        List<Record> records = userService.getAllRecord(user);
        mav.addObject("num", list);
        mav.addObject("records", records);
        return mav;
    }


    @RequestMapping("log_out")
    public String log_out(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:index.html";
    }

    @RequestMapping("/test")
    public String test() {
        return "whitepage";
    }


    @RequestMapping("/reset")
    public String reset() {
        return "resetPassword";
    }

    @RequestMapping("/resetPwd")
    public ModelAndView reset_pwd(@RequestParam("password") String pwd, @RequestParam("new") String new_pwd, HttpSession session, ModelAndView mav) {
        User user = (User) session.getAttribute("user");
        if (user.getPassword().equals(pwd)) {
            userService.resetPwd(user, new_pwd);
            session.removeAttribute("user");
            mav.setViewName("redirect:sign_in");
        } else {
            mav.setViewName("resetPassword");
            mav.addObject("msg", "原密码错误，请重试");
        }
        return mav;
    }

    @RequestMapping("/rename")
    public String rename() {
        return "rename";
    }

    @RequestMapping("/user_rename")
    public ModelAndView user_rename(ModelAndView mav, @RequestParam String username, @SessionAttribute User user, HttpSession session) {
        mav.setViewName("rename");
        user.setUsername(username);
        session.setAttribute("user", user);
        userService.rename(user, username);
        mav.addObject("msg", "修改成功");
        return mav;
    }


    @RequestMapping("/admin")
    public String admin() {
        return "/admin/admin_index";
    }


}
