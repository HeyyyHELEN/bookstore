package cn.edu.hjnu.pojo;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Integer totalCount;

    private Double totalAmount;

    private Map<Integer,CartItem> cartItemMap = new HashMap<>();

    //获取购物车总数量
    public Integer getTotalCount(){

        Collection<CartItem> cartItems = cartItemMap.values();
        Integer totalCount = 0;
        for (CartItem cartItem : cartItems) {

            totalCount = totalCount + cartItem.getCount();

        }
        this.totalCount = totalCount;
        return this.totalCount;

    }

//获取商品总金额
    public Double getTotalAmount() {
        Collection<CartItem> cartItems = cartItemMap.values();

        BigDecimal totalAmountBig = new BigDecimal(0.0 + "");
        for (CartItem cartItem : cartItems) {
            BigDecimal amountBig = new BigDecimal(cartItem.getAmount() + "");
            totalAmountBig = totalAmountBig.add(amountBig);
        }
        this.totalAmount = totalAmountBig.doubleValue();
        return this.totalAmount;

    }


    public Map<Integer, CartItem> getCartItemMap(){
        return cartItemMap;
    }


    //指定购物车数量减一
    public void cartItemCountDecrease(Integer bookId){

        CartItem cartItem = cartItemMap.get(bookId);
        cartItem.countDecrease();

    }



    //指定购物车数量加一
    public void cartItemCountIncrease(Integer bookId){

        CartItem cartItem = cartItemMap.get(bookId);
        cartItem.countIncrease();


    }

    //修改购物车指定项的数量
    public void updateCartItemCount(Integer bookId, Integer newCount){

        CartItem cartItem = cartItemMap.get(bookId);
        cartItem.setCount(newCount);

    }



    //删除指定购物车项
    public void removeCartItem(Integer bookId){

        cartItemMap.remove(bookId);

    }


    public void addBook2Cart(Book book){

        Integer bookId = book.getBookId();
        if (cartItemMap.containsKey(bookId)){

            cartItemCountIncrease(bookId);

        } else {

            CartItem cartItem = new CartItem(
                    bookId,
                    book.getImgPath(),
                    book.getBookName(),
                    1,
                    book.getPrice(),
                    1 * book.getPrice()
            );
            cartItemMap.put(bookId,cartItem);

        }

    }


}
