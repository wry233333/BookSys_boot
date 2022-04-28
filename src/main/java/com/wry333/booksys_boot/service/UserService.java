package com.wry333.booksys_boot.service;

import com.wry333.booksys_boot.domain.Record;
import com.wry333.booksys_boot.domain.User;

import java.util.List;

public interface UserService {

    //登录用户
    User login(User user);

    //注册用户
    boolean register(User user);

    //获取用户主页视图数据
    List<Integer> get_index_data(User user) throws Exception;

    //获得当前登录用户的订单
    List<Record> getAllRecord(User user);

    //重置用户密码
    boolean resetPwd(User user, String new_pwd);

    //重置用户名称
    void rename(User user, String username);


    //获得管理员界面数据
    List<Integer> get_admin_data() throws Exception;


    //获得所有用户
    List<User> getAllUser(int i);

    void deleteUser(List<String> list_id);

    List<User> searchUser(String username);
}
