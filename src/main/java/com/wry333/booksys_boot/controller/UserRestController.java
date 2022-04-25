package com.wry333.booksys_boot.controller;


import com.wry333.booksys_boot.domain.User;
import com.wry333.booksys_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseBody
    public List<User> getAllUser() {
        List<User> list = userService.getAllUser();
        return list;
    }

}
