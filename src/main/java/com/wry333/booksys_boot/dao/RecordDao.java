package com.wry333.booksys_boot.dao;


import com.wry333.booksys_boot.domain.Record;
import com.wry333.booksys_boot.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RecordDao {

    @Select("select count(*) from tbl_list where id = #{id}")
    int getNumByUid(User user);

    @Select("select * from tbl_list where id = #{id}")
    List<Record> getRecordByUid(User user);

    @Select("select count(*) from tbl_list where id = #{id} and rel_date = null")
    int getShouldReturn(User user);

    @Select("select return_date from tbl_list where id = #{id}")
    List<String> getReturnDate(User user);
}
