package cn.edu.hjnu.controller;

import cn.edu.hjnu.constant.BookstoreConstant;
import cn.edu.hjnu.controller.base.ModelBaseServlet;
import cn.edu.hjnu.pojo.Cart;
import cn.edu.hjnu.pojo.User;
import cn.edu.hjnu.service.OrderService;
import cn.edu.hjnu.service.impl.OrderServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/protected/orderClient")
public class ProtectedOrderClient extends ModelBaseServlet {

    public void toCheckoutPage(HttpServletRequest request, HttpServletResponse response){

        try {
            processTemplate("pages/cart/checkout",request,response);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void checkout(HttpServletRequest request, HttpServletResponse response){
        //获取购物车
        Cart existCart = (Cart) request.getSession().getAttribute(BookstoreConstant.SESSION_KEY_CART);
        //获取用户信息
        User existUser = (User) request.getSession().getAttribute(BookstoreConstant.SESSION_KEY_USER);
        //调用业务层
        OrderService orderService = new OrderServiceImpl();
        try {
             String orderSequence = orderService.checkout(existCart,existUser);
             request.getSession().removeAttribute(BookstoreConstant.SESSION_KEY_CART);
             request.getSession().setAttribute("orderSequence",orderSequence);
             response.sendRedirect(request.getContextPath() + "/protected/orderClient?method=toCheckoutPage");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
