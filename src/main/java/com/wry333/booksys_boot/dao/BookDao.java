package com.wry333.booksys_boot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface BookDao {
    @Select("select name from tbl_book where b_id = #{b_id}")
    String findNameById(Long b_id);
}
