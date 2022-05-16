package com.wry333.booksys_boot.dao;


import com.wry333.booksys_boot.domain.User;
import com.wry333.booksys_boot.domain.User_Level;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {

    //登录
    @Select("select * from tbl_user where email = #{email} and password = #{password}")
    User login(User user);

    //通过邮箱查询用户
    @Select("select * from tbl_user where email = #{email}")
    User findByEmail(User user);

    //新增用户
    @Insert("insert into tbl_user values (null,#{username},#{password},#{email})")
    void saveUser(User user);

    //更新用户密码
    @Update("update tbl_user set password = #{new_pwd} where id = #{user.id}")
    void resetPwd(User user, @Param("new_pwd") String new_pwd);

    //更新用户名
    @Update("update tbl_user set username = #{username} where id = #{id}")
    void rename(long id, String username);

    //查询所有用户的数量
    @Select("select count(*) from tbl_user")
    Integer findAllUserNum();

    //查询所有用户
    @Select("SELECT * FROM tbl_user")
    List<User> findAllUsers();

    //通过用户id查询用户
    @Select("select username from tbl_user where id=#{id}")
    String findUserById(long id);

    //通过用户id删除用户
    @Update("delete from tbl_user where id = #{user_id}")
    int deleteUserById(String user_id);

    //通过用户名查找用户
    @Select("select * from tbl_user where username = #{username}")
    List<User> findUserByName(String username);

    //通过用户id查询用户
    @Select("select * from tbl_user where id=#{id}")
    User findUserById2(long id);


    //更新用户信息
    @Update("update tbl_user set username = #{username},password = #{password},email = #{email} where id = #{id}")
    void updateUser(User user);

    //查询admin表获得用户的类别等级
    @Select("select * from tbl_admin where id = #{id}")
    User_Level getUserLevel(User u);


    //添加管员
    @Insert("insert into tbl_admin values(#{id},'图书管理员',1)")
    int addAdmin(String id);

    //删除管理员
    @Delete("delete from tbl_admin where id = #{id}")
    int deleteAdmin(String id);


    //查找管理员
    @Select("select * from tbl_admin where id = #{id}")
    User findAdmin(User user);
}
