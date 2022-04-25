package com.wry333.booksys_boot.dao;


import com.wry333.booksys_boot.domain.User;
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
}
