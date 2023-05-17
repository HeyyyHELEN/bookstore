package cn.edu.hjnu.dao;

import cn.edu.hjnu.pojo.Book;

import java.util.List;

public interface BookDao {
    List<Book> selectBookList();

    int addBook(Book book);


    int deleteBookById(Integer bookId);


    Book selectBookById(Integer bookId);


    int updateBookById(Book book);

    void updateBooks(Object[][] bookParams);

}
