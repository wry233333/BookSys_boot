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

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/admin/user")
public class UserAdminController {

    private final AdminService adminService;

    private final UserService userService;

    @Autowired
    public UserAdminController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    /**
     * 首页用户分页显示功能
     *
     * @param page 页数
     * @return 包含数据的页面信息
     */

    @GetMapping("/page/{page}")
    @ResponseBody
    public PageInfo<User> getAllUser(@PathVariable String page) {
        List<User> list = userService.getAllUser(Integer.parseInt(page));
        return new PageInfo<>(list);
    }


    /**
     * 首页显示借阅记录
     *
     * @return 获得所有的借阅记录
     */

    @GetMapping("/list")
    @ResponseBody
    public List<Record> getAllRecord() {
        return adminService.findAllRecord();
    }

    /**
     * 处理删除用户的请求
     *
     * @param map 路径变量
     * @return 返回删除是否成功
     */
    @PostMapping("/delete")
    @ResponseBody
    public boolean deleteUser(@RequestBody Map map) {
        List<String> list_id = (List<String>) map.get("data");
        userService.deleteUser(list_id);
        return true;
    }

    @PostMapping("/search")
    @ResponseBody
    public List<User> searchUser(@RequestBody Map map) {
        System.out.println(map.get("data"));
        return userService.searchUser((String) map.get("data"));
    }

    @GetMapping("/classes/page/{page}")
    @ResponseBody
    public PageInfo<User> getUserClasses(@PathVariable String page) {
        int p = Integer.parseInt(page);
        return userService.getUserClasses(p);
    }

    @PostMapping("/classes/search/")
    @ResponseBody
    public List<User> class_searchUser(@RequestBody Map map) {
        return userService.findUser_Class((String) map.get("data"));
    }

    @PostMapping("/classes/")
    @ResponseBody
    public boolean update_userClass(@RequestBody Map map) {
        return userService.addAdmin((String) map.get("data"));
    }

    @PostMapping("/classes/delete")
    @ResponseBody
    public boolean delete_userClass(@RequestBody Map map) {
        return userService.deleteAdmin((String) map.get("data"));
    }

    @GetMapping("/reset_user_pwd/{id}")
    @ResponseBody
    public boolean reset_user_pwd(@PathVariable String id) {
        long uid = Long.parseLong(id);
        userService.admin_reset_pwd(uid);
        return true;
    }
}
