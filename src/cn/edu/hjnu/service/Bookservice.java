package cn.edu.hjnu.service;

import cn.edu.hjnu.pojo.Book;

import java.util.List;

public interface Bookservice {


    List<Book> selectBookList();

    int addBook(Book book);

    int deleteBookById(Integer bookId);


    Book selectBookById(Integer bookId);

    int updateBookById(Book book);
}
