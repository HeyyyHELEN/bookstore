package cn.edu.hjnu.service.impl;

import cn.edu.hjnu.dao.BookDao;
import cn.edu.hjnu.dao.OrderDao;
import cn.edu.hjnu.dao.OrderItemDao;
import cn.edu.hjnu.dao.impl.BookDaoImpl;
import cn.edu.hjnu.dao.impl.OrderDaoImpl;
import cn.edu.hjnu.dao.impl.OrderItemDapImpl;
import cn.edu.hjnu.pojo.Cart;
import cn.edu.hjnu.pojo.CartItem;
import cn.edu.hjnu.pojo.Order;
import cn.edu.hjnu.pojo.User;
import cn.edu.hjnu.service.OrderService;

import java.text.SimpleDateFormat;
import java.util.*;

public class OrderServiceImpl implements OrderService {

    @Override
    public String checkout(Cart existCart, User existUser) throws Exception {

        //--------------------①生成订单-------------------
        //调用持久层
        OrderDao orderDao = new OrderDaoImpl();
        //创建订单对象
        Order order = new Order();
        //1.1.订单id自增
        order.setOrderId(null);
        //1.2.设置订单序列号
        String orderSequence = UUID.randomUUID().toString().replace("-", "");
        order.setOrderSequence(orderSequence);
        //1.3.设置订单创建时间
        order.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //1.4.设置订单总金额
        order.setTotalAmount(existCart.getTotalAmount());
        //1.5.设置订单总数量
        order.setTotalCount(existCart.getTotalCount());
        //1.6.设置订单的用户id
        order.setUserId(existUser.getUserId());
        //调用持久层的addOrder方法
        orderDao.addOrder(order);
        System.out.println("order = " + order);

        //--------------------②生成订单项-------------------

        OrderItemDao orderItemDao = new OrderItemDapImpl();
        //2.1获取所有购物车项
        Map<Integer, CartItem> cartItemMap = existCart.getCartItemMap();
        //2.2批量添加
        Object[][] orderItemParams = new Object[cartItemMap.size()][6];
        List<CartItem> cartItems = new ArrayList<>(cartItemMap.values());
        //2.3获取每一个购物车项
        for (int i = 0; i < cartItems.size(); i++) {
            CartItem cartItem = cartItems.get(i);
            orderItemParams[i][0] = cartItem.getBookName();
            orderItemParams[i][1] = cartItem.getPrice();
            orderItemParams[i][2] = cartItem.getImgPath();
            orderItemParams[i][3] = cartItem.getCount();
            orderItemParams[i][4] = cartItem.getAmount();
            orderItemParams[i][5] = order.getOrderId();

        }

        orderItemDao.addOrderItems(orderItemParams);

        //--------------------③修改图书销量和库存-------------------

        BookDao bookDao = new BookDaoImpl();
        Object[][] bookParams = new Object[cartItemMap.size()][3];
        for (int i = 0; i < cartItems.size(); i++) {
            CartItem cartItem = cartItems.get(i);
            bookParams[i][0] = cartItem.getCount();
            bookParams[i][1] = cartItem.getCount();
            bookParams[i][2] = cartItem.getBookId();

        }
        bookDao.updateBooks(bookParams);


        return orderSequence;
    }
}
