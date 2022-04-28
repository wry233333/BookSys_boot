package com.wry333.booksys_boot.controller;


import com.github.pagehelper.PageInfo;
import com.wry333.booksys_boot.domain.Record;
import com.wry333.booksys_boot.domain.User;
import com.wry333.booksys_boot.service.AdminService;
import com.wry333.booksys_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/user")
public class UserAdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    /**
     * 首页用户分页显示功能
     *
     * @param page
     * @return
     */

    @GetMapping("/page/{page}")
    @ResponseBody
    public PageInfo<User> getAllUser(@PathVariable String page) {
        List<User> list = userService.getAllUser(Integer.parseInt(page));
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


    /**
     * 首页显示借阅记录
     *
     * @return
     */

    @GetMapping("/list")
    @ResponseBody
    public List<Record> getAllRecord() {
        List<Record> list = adminService.findAllRecord();
        return list;
    }

    /**
     * 处理删除用户的请求
     *
     * @param list_id
     * @return
     */
    @DeleteMapping("/delete")
    @ResponseBody
    public boolean deleteUser(@RequestBody List<String> list_id) {
        userService.deleteUser(list_id);
        return true;
    }

    @PostMapping("/search")
    @ResponseBody
    public List<User> searchUser(@RequestBody Map map) {
        System.out.println(map.get("data"));
        List<User> list = userService.searchUser((String) map.get("data"));
        return list;
    }


}
