package cn.edu.hjnu.demo;

import cn.edu.hjnu.pojo.Book;
import cn.edu.hjnu.pojo.Cart;

public class Demo01 {

    public static void main(String[] args) {

        Book book1 = new Book(
                1,
                "java",
                "helen",
                10.0,
                1000,
                10000,
                "gril.jpg"
        );

        Book book2 = new Book(
                2,
                "javase",
                "helen",
                10.0,
                1000,
                10000,
                "gril.jpg"
        );

        Book book3 = new Book(
                3,
                "javaee",
                "helen",
                10.0,
                1000,
                10000,
                "gril.jpg"
        );


        Cart cart = new Cart();
        cart.addBook2Cart(book1);
        cart.addBook2Cart(book1);
        cart.addBook2Cart(book1);
        cart.addBook2Cart(book2);
        cart.addBook2Cart(book3);


        System.out.println("总数量:" + cart.getTotalCount());
        System.out.println("总金额:" + cart.getTotalAmount());
        System.out.println("购物车项列表:" + cart.getCartItemMap());


    }

}
