package cn.edu.hjnu.pojo;

import java.math.BigDecimal;

public class CartItem {

    private Integer bookId;
    private String imgPath;
    private String bookName;
    private Integer count;
    private Double price;
    private Double amount;

    public CartItem() {
    }

    public CartItem(Integer bookId, String imgPath, String bookName, Integer count, Double price, Double amount) {
        this.bookId = bookId;
        this.imgPath = imgPath;
        this.bookName = bookName;
        this.count = count;
        this.price = price;
        this.amount = amount;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmount() {
        BigDecimal countBig = new BigDecimal(this.count + "");
        BigDecimal priceBig = new BigDecimal(this.price + "");
        this.amount = countBig.multiply(priceBig).doubleValue();
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


    public void countIncrease(){
        this.count++;
    }


    public void countDecrease(){
        this.count--;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "bookId=" + bookId +
                ", imgPath='" + imgPath + '\'' +
                ", bookName='" + bookName + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
