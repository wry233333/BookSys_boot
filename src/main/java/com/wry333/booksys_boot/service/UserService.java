package com.wry333.booksys_boot.service;

import com.github.pagehelper.PageInfo;
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

    //删除用户
    void deleteUser(List<String> list_id);

    //按用户名搜索用户
    List<User> searchUser(String username);

    //通过uid获得用户
    User getGetUser(long id);

    //更新用户信息
    void updateUser(User user);

    //发送邮件
    String sendEmail(String email);

    //找回密码
    void finPwd(String email, String password);

    //查找全部用户包含类的数据
    PageInfo<User> getUserClasses(int p);

    //查询输入用户名的信息
    List<User> findUser_Class(String data);

    boolean addAdmin(String id);

    boolean deleteAdmin(String id);
}
