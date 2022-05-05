package com.wry333.booksys_boot.dao;

import com.wry333.booksys_boot.domain.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


@Mapper
public interface BookDao {

    //通过b_id查询书名
    @Select("select name from tbl_book where b_id = #{b_id}")
    String findNameById(Long b_id);

    @Select("select count(*) from tbl_book ")
    int findAllBookNum();


@Update("delete from tbl_book where b_id = #{b_id}")
    void deleteBooById(Book book);
@Insert("insert into tbl_book values(null,#{name},#{publisher},#{author},#{date},#{info},#{context},#{c_id})")
    void addBookInfo(Book book);
@Select("select * from tbl_book where c_id=#{c_id}")
    List<Book> getBookByClass(Book book);
@Select("select * from tbl_book where name=#{name}")
    List<Book> getBookByName(Book book);
@Select("select * from tbl_book")
    List<Book> getAllBook();
}
