package com.mcm.booksys_boot.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wry333.booksys_boot.dao.BookDao;
import com.mcm.booksys_boot.service.BookService;
import com.wry333.booksys_boot.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookDao bookDao;
    @Override
 public PageInfo<Book> getAllBook(int p)
    {
        PageHelper.startPage(p, 5);
        List<Book> list = bookDao.getAllBook();
        PageInfo<Book> pageInfo = new PageInfo<Book>(list);
        return pageInfo;
    }
    @Override
    public List<Book> showBookByClass (Book book)
    {
        List <Book> bookClassList =new ArrayList<>();
        bookClassList = bookDao.getBookByClass(book);
                return bookClassList;
    }
    @Override
    public List<Book> showBookByName (Book book)
    {
        List <Book> bookNameList = new ArrayList<>();
        bookNameList = bookDao.getBookByName(book);
        return bookNameList;
    }
@Override
    public  boolean AddBook(Book book)
{
    bookDao.addBookInfo(book);
   return true;

}
@Override
    public boolean deleteBook (Book book)
{
    bookDao.deleteBooById(book);
    return  true;


}

}
