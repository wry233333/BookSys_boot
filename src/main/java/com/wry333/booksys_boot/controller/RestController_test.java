package com.wry333.booksys_boot.controller;

import com.wry333.booksys_boot.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


//rust风格的控制器，可以配合axios或者jquery实现前后端分离
//此为测试接口，返回一个名为test的用户对象
@CrossOrigin
@RestController
@RequestMapping("/rest_c")
public class RestController_test {


    //测试接口仅供测试使用
    @GetMapping
    @ResponseBody
    public List<User> getUser() {
        List list = new ArrayList();
        User user = new User();
        list.add(user);
        User user1 = new User();
        list.add(user1);
        user.setUsername("test");
        return list;
    }

}
