package com.wry333.booksys_boot.controller;


import com.wry333.booksys_boot.domain.Record;
import com.wry333.booksys_boot.domain.User;
import com.wry333.booksys_boot.service.AdminService;
import com.wry333.booksys_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class UserAdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @GetMapping("/page/{page}")
    @ResponseBody
    public List<User> getAllUser(@PathVariable String page) {
        List<User> list = userService.getAllUser(Integer.parseInt(page));
        return list;
    }


    @GetMapping("/list")
    @ResponseBody
    public List<Record> getAllRecord() {
        List<Record> list = adminService.findAllRecord();
        return list;
    }
}
