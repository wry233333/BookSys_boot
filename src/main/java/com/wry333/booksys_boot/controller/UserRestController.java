package com.wry333.booksys_boot.controller;

import com.wry333.booksys_boot.domain.User;
import com.wry333.booksys_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


//rust风格的控制器，可以配合axios或者jquery实现前后端分离
//此为测试接口，返回一个名为test的用户对象
@RestController
@RequestMapping("/findPwd")
public class UserRestController {

    @Autowired
    UserService userService;

    //测试接口仅供测试使用
    @PostMapping
    @ResponseBody
    public boolean sendEmail(@RequestBody Map map, HttpSession session) {
        String email = (String) map.get("data");
        System.out.println(email);
        String nums = userService.sendEmail(email);
        if (nums != null) {
            session.setAttribute("code", nums);
            return true;
        } else return false;
    }

}
