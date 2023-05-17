package cn.edu.hjnu.service.impl;

import cn.edu.hjnu.dao.BookDao;
import cn.edu.hjnu.dao.impl.BookDaoImpl;
import cn.edu.hjnu.pojo.Book;
import cn.edu.hjnu.service.Bookservice;

import java.util.List;

public class BookServiceImpl implements Bookservice {
    @Override
    public List<Book> selectBookList() {
        BookDao bookDao = new BookDaoImpl();
        return bookDao.selectBookList();

    }

    @Override
    public int addBook(Book book) {
        book.setImgPath("static/uploads/Javabianchengsixiang.jpg");
        BookDao bookDao = new BookDaoImpl();

        return bookDao.addBook(book);
    }

    @Override
    public int deleteBookById(Integer bookId) {
        BookDao bookDao = new BookDaoImpl();
        return bookDao.deleteBookById(bookId);
    }

    @Override
    public Book selectBookById(Integer bookId) {
        BookDao bookDao = new BookDaoImpl();
        return bookDao.selectBookById(bookId);
    }

    @Override
    public int updateBookById(Book book){
        BookDao bookDao = new BookDaoImpl();
        return bookDao.updateBookById(book);
    }
}
