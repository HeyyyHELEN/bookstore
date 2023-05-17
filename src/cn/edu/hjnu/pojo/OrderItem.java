package cn.edu.hjnu.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderItem {

    private Integer itemId;
    private String bookName;
    private double price;
    private String imgPath;
    private Integer itemCount;
    private double itemAmount;
    private String orderId;

}
