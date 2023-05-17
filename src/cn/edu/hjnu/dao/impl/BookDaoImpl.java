package cn.edu.hjnu.dao.impl;

import cn.edu.hjnu.dao.BookDao;
import cn.edu.hjnu.dao.base.BaseDao;
import cn.edu.hjnu.pojo.Book;
import cn.edu.hjnu.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {
    @Override
    public List<Book> selectBookList() {
        return getBeanList(
                "select book_id bookId, book_name bookName, author, price, sales, stock, img_path imgPath from t_book",
                Book.class
        );
    }

    @Override
    public int addBook(Book book) {
        return update(
                "insert into t_book values(null,?,?,?,?,?,?)",
                book.getBookName(),
                book.getAuthor(),
                book.getPrice(),
                book.getSales(),
                book.getStock(),
                book.getImgPath()
        );
    }

    @Override
    public int deleteBookById(Integer bookId) {
        return update(
                "delete from t_book where book_id = ?",
                bookId
        );
    }

    @Override
    public Book selectBookById(Integer bookId) {
        return getBean(
                "select book_id bookId, book_name bookName, author, price, sales, stock, img_path imgPath from t_book where book_id = ?",
                Book.class,
                bookId

                );
    }

    @Override
    public int updateBookById(Book book) {
        return update(
                "update t_book set book_name = ?, author = ?, price = ?, sales = ?, stock = ? where book_id = ?",
                book.getBookName(),
                book.getAuthor(),
                book.getPrice(),
                book.getSales(),
                book.getStock(),
                book.getBookId()

        );
    }

    @Override
    public void updateBooks(Object[][] bookParams) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            new QueryRunner().batch(
                    connection,
                    "update t_book set sales = sales + ?, stock = stock - ?, where book_id = ?",
                    bookParams
            );
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null);
        }
    }
}
