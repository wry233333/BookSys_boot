package com.mcm.booksys_boot.controller;

import com.github.pagehelper.PageInfo;
import com.mcm.booksys_boot.service.BookService;
import com.wry333.booksys_boot.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class VueBookController {
@Autowired
    BookService bookService;

@GetMapping("/allBookInfo/{p}")
public PageInfo<Book> pageSpear(@PathVariable String p)
{
    PageInfo<Book> list = bookService.getAllBook(Integer.parseInt(p));
    return list;
}



}
