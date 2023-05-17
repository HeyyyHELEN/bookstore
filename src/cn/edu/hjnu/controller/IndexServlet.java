package cn.edu.hjnu.controller;

import cn.edu.hjnu.controller.base.ViewBaseServlet;
import cn.edu.hjnu.pojo.Book;
import cn.edu.hjnu.service.Bookservice;
import cn.edu.hjnu.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/index.html")
public class IndexServlet extends ViewBaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Bookservice bookservice = new BookServiceImpl();
        List<Book> bookList = bookservice.selectBookList();
        req.setAttribute("bookList",bookList);

        processTemplate("index",req,resp);



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
