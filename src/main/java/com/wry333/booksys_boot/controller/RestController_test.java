package com.wry333.booksys_boot.controller;

import com.wry333.booksys_boot.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//rust风格的控制器，可以配合axios或者jquery实现前后端分离
//此为测试接口，返回一个名为test的用户对象
@RestController
@RequestMapping("/rest_c")
public class RestController_test {
    @GetMapping
    public User getUser() {
        User user = new User();
        user.setUsername("test");
        return user;
    }
}
