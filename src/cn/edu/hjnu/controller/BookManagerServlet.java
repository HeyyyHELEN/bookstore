package cn.edu.hjnu.controller;

import cn.edu.hjnu.controller.base.ModelBaseServlet;
import cn.edu.hjnu.pojo.Book;
import cn.edu.hjnu.service.Bookservice;
import cn.edu.hjnu.service.impl.BookServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/protected/bookManager")
public class BookManagerServlet extends ModelBaseServlet {


    public void toBookManagerPage(HttpServletRequest request, HttpServletResponse response){

        try {
            Bookservice bookservice = new BookServiceImpl();
            List<Book> bookList = bookservice.selectBookList();
            request.setAttribute("bookList",bookList);
            processTemplate("pages/manager/book_manager",request,response);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void toBookAddPage(HttpServletRequest request, HttpServletResponse response){

        try {
            processTemplate("/pages/manager/book_add",request,response);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void addBook(HttpServletRequest request, HttpServletResponse response){

        Book book = new Book();
        try {
            BeanUtils.populate(book,request.getParameterMap());
            Bookservice bookservice = new BookServiceImpl();
            int addBook = bookservice.addBook(book);

            System.out.println(1 / 0);

            if (addBook > 0 ){

                response.sendRedirect(request.getContextPath() + "/protected/bookManager?method=toBookManagerPage");
            } else {
                request.setAttribute("book",book);
                toBookAddPage(request, response);
            }

        } catch (Exception e) {

            e.printStackTrace();
            request.setAttribute("book",book);
            toBookAddPage(request, response);



        }


    }


    public void deleteBookById(HttpServletRequest request, HttpServletResponse response){

        try {
            Integer bookId = Integer.parseInt(request.getParameter("bookId"));

            Bookservice bookservice = new BookServiceImpl();
            int deleteBookById = bookservice.deleteBookById(bookId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            response.sendRedirect(request.getContextPath() + "/protected/bookManager?method=toBookManagerPage");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void toBookEditPage(HttpServletRequest request, HttpServletResponse response){

        try {

            Integer bookId = Integer.parseInt(request.getParameter("bookId"));

            Bookservice bookservice = new BookServiceImpl();
            Book book = bookservice.selectBookById(bookId);
            request.setAttribute("book",book);
            processTemplate("/pages/manager/book_edit",request,response);



        } catch (IOException e) {
            e.printStackTrace();
        }

    }





    public void updateBookById(HttpServletRequest request, HttpServletResponse response){

        Book book = new Book();
        try {
            BeanUtils.populate(book,request.getParameterMap());
            Bookservice bookservice = new BookServiceImpl();
            int updateBookById = bookservice.updateBookById(book);
            if(updateBookById > 0){
                response.sendRedirect(request.getContextPath() + "/protected/bookManager?method=toBookManagerPage");
            } else {
                toBookEditPage(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            toBookEditPage(request, response);
        }


    }






}
