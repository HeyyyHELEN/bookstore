package cn.edu.hjnu.controller;

import cn.edu.hjnu.constant.BookstoreConstant;
import cn.edu.hjnu.controller.base.ModelBaseServlet;
import cn.edu.hjnu.pojo.Book;
import cn.edu.hjnu.pojo.Cart;
import cn.edu.hjnu.pojo.CartItem;
import cn.edu.hjnu.service.Bookservice;
import cn.edu.hjnu.service.impl.BookServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/protected/cart")
public class ProtectedCartServlet extends ModelBaseServlet {

    public void toCartPage(HttpServletRequest request, HttpServletResponse response){

        try {
            Object existCart = request.getSession().getAttribute(BookstoreConstant.SESSION_KEY_CART);
            processTemplate("pages/cart/cart",request,response);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void addBook2Cart(HttpServletRequest request, HttpServletResponse response){


        try {
            Integer bookId = Integer.parseInt(request.getParameter("bookId"));
            System.out.println("bookId = " + bookId);
            Bookservice bookservice = new BookServiceImpl();
            Book book = bookservice.selectBookById(bookId);
            Cart cart = (Cart) request.getSession().getAttribute(BookstoreConstant.SESSION_KEY_CART);
            if (null == cart){

                cart = new Cart();

            } else {

                cart.addBook2Cart(book);

            }

            request.getSession().setAttribute(BookstoreConstant.SESSION_KEY_CART,cart);
            response.sendRedirect(request.getContextPath() + "/index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void clearCart(HttpServletRequest request, HttpServletResponse response){

        request.getSession().removeAttribute(BookstoreConstant.SESSION_KEY_CART);

        try {
            response.sendRedirect(request.getContextPath() + request.getServletPath() + "?method=toCartPage");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void cartItemCountDecrease(HttpServletRequest request, HttpServletResponse response)  {

        Integer bookId = Integer.parseInt(request.getParameter("bookId"));
        Cart cart = (Cart) request.getSession().getAttribute(BookstoreConstant.SESSION_KEY_CART);
        CartItem cartItem = cart.getCartItemMap().get(bookId);
        if (cartItem.getCount() <= 1){
            cart.removeCartItem(bookId);
        } else {

            cart.cartItemCountDecrease(bookId);
        }

        try {
            response.sendRedirect(request.getContextPath() + request.getServletPath() + "?method=toCartPage");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void cartItemCountIncrease(HttpServletRequest request, HttpServletResponse response){
        Integer bookId = Integer.parseInt(request.getParameter("bookId"));
        Cart cart = (Cart) request.getSession().getAttribute(BookstoreConstant.SESSION_KEY_CART);

        cart.cartItemCountIncrease(bookId);

        try {
            response.sendRedirect(request.getContextPath() + request.getServletPath() + "?method=toCartPage");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void removeCartItem(HttpServletRequest request, HttpServletResponse response){
        Integer bookId = Integer.parseInt(request.getParameter("bookId"));
        Cart cart = (Cart) request.getSession().getAttribute(BookstoreConstant.SESSION_KEY_CART);

        cart.removeCartItem(bookId);

        try {
            response.sendRedirect(request.getContextPath() + request.getServletPath() + "?method=toCartPage");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void updateCartItemCount(HttpServletRequest request, HttpServletResponse response){

        Integer bookId = Integer.parseInt(request.getParameter("bookId"));
        Integer newCount = Integer.parseInt(request.getParameter("newCount"));
        Cart cart = (Cart) request.getSession().getAttribute(BookstoreConstant.SESSION_KEY_CART);

        if(newCount <= 0){

            cart.removeCartItem(bookId);

        } else {

            cart.updateCartItemCount(bookId,newCount);

        }

        try {
            response.sendRedirect(request.getContextPath() + request.getServletPath() + "?method=toCartPage");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
