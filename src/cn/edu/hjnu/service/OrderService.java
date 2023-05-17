package cn.edu.hjnu.service;

import cn.edu.hjnu.pojo.Cart;
import cn.edu.hjnu.pojo.User;

public interface OrderService {
    public String checkout(Cart existCart, User existUser) throws Exception;

}
