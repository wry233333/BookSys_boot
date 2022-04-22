package com.wry333.booksys_boot.dao;


import com.wry333.booksys_boot.domain.User;
import org.apache.ibatis.annotations.*;

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
}
