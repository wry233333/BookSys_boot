package com.mcm.booksys_boot.controller;
import com.github.pagehelper.PageInfo;
import com.wry333.booksys_boot.domain.Book;
import com.mcm.booksys_boot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BookController {
    @Autowired
    BookService bookService;

    @RequestMapping("/allBookInfo")
    public ModelAndView book_info(Book book, ModelAndView mav)
    {
        mav.setViewName("/admin/allBookInfo");
        PageInfo<Book> bookList = bookService.getAllBook(1);
        mav.addObject("list",bookList.getList());
        return mav;
    }
    @RequestMapping("/addBook")
        public ModelAndView book_add(Book book,ModelAndView mav)
    {
        mav.setViewName("/admin/addBookInfo");
        bookService.AddBook(book);
        return mav;

    }

    @RequestMapping("addnewbook")
    public String add_book()
    {
        return "/admin/addBookInfo";
    }


    @RequestMapping("/deleteBook")
    public ModelAndView book_delete(Book book,ModelAndView mav)
    {
        mav.setViewName("forward:/admin/allBookInfo");
        bookService.deleteBook(book);
        return  mav;
    }

    @RequestMapping("/searchBook")
    public ModelAndView book_search(Book book,ModelAndView mav)
    {
        mav.setViewName("/admin/sortByBookClass");
        List<Book> bookClassList = bookService.showBookByClass(book);
        mav.addObject("classList",bookClassList);
        return mav;

    }
    @RequestMapping("/searchBookByName")
    public ModelAndView book_searchTwo(Book book,ModelAndView mav)
    {
        mav.setViewName("/admin/searchForBookByName");
        List<Book> bookNameList = bookService.showBookByName(book);
        mav.addObject("nameList",bookNameList);
        return mav;

    }
    @RequestMapping("/searchForBookByName")
    public String transform_1()
    {

        return "/admin/searchForBookByName";
    }

    @RequestMapping("/sortByBookClass")
    public String transform()
    {
        return "/admin/sortByBookClass";

    }


}
