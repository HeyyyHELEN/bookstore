package cn.edu.hjnu.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResultVO<T> {

    private Boolean flag;
    private String msg;
    private T data;

}
