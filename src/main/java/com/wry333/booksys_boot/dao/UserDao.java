package com.wry333.booksys_boot.dao;


import com.wry333.booksys_boot.domian.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

    @Select("select * from tbl_user where email = #{email} and password = #{password}")
    User login(User user);


    @Select("select * from tbl_user where email = #{email}")
    User findByEmail(User user);

    @Insert("insert into tbl_user values (null,#{username},#{password},#{email})")
    void saveUser(User user);
}
