package com.mcm.booksys_boot.service;

import com.github.pagehelper.PageInfo;
import com.wry333.booksys_boot.domain.Book;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookService {
    PageInfo<Book> getAllBook(int p);
    List<Book> showBookByClass (Book book);
    List<Book> showBookByName (Book book);
    boolean AddBook(Book book);
    boolean deleteBook (Book book);

}
