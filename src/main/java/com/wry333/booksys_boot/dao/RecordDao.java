package com.wry333.booksys_boot.dao;


import com.wry333.booksys_boot.domain.Record;
import com.wry333.booksys_boot.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RecordDao {


    //通过uid查询借阅总数
    @Select("select count(*) from tbl_list where id = #{id}")
    int getNumByUid(User user);


    //获得传入用户对象的所有借阅记录
    @Select("select * from tbl_list where id = #{id} ")
    List<Record> getRecordByUid(User user);


    //查询所有未归还书籍数
    @Select("select count(*) from tbl_list where id = #{id} and rel_date IS null")
    int getShouldReturn(User user);


    //获得传入用户所有真实归还书籍时间
    @Select("select return_date from tbl_list where id = #{id}")
    List<String> getReturnDate(User user);


    //获得所有订单应该归还时间的数组（用于统计)
    @Select("select return_date from tbl_list")
    String[] getAllReturnDate();

    //获得所有借阅记录的数量(用于统计)
    @Select("select count(*) from tbl_list")
    int getAllNum();


    @Select("select * from tbl_list")
    List<Record> findAll();
}
