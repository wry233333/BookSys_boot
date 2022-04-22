package com.wry333.booksys_boot.dao;


import com.wry333.booksys_boot.domain.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDao {

    @Select("select * from tbl_user where email = #{email} and password = #{password}")
    User login(User user);


    @Select("select * from tbl_user where email = #{email}")
    User findByEmail(User user);

    @Insert("insert into tbl_user values (null,#{username},#{password},#{email})")
    void saveUser(User user);


    @Update("update tbl_user set password = #{new_pwd} where id = #{user.id}")
    void resetPwd(User user, @Param("new_pwd") String new_pwd);

    @Update("update tbl_user set username = #{username} where id = #{id}")
    void rename(long id, String username);
}
