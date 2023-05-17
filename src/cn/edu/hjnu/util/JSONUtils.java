package cn.edu.hjnu.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class JSONUtils {

    //将java对象转换成json字符串
    public static String javaBean2JsonStr(Object object) throws JsonProcessingException {

        return new ObjectMapper().writeValueAsString(object);

    }

    //将json字符串转换成java对象
    public static <T> T jsonStr2JavaBean(String jsonStr, Class<T> clazz) throws IOException {

        return new ObjectMapper().readValue(jsonStr, clazz);

    }


    //获取请求正文json，将json字符串转换为java对象
    public static <T> T readRequest2JavaBean(HttpServletRequest request, Class<T> clazz) throws IOException {
        BufferedReader bufferedReader = request.getReader();
        String content = null;
        StringBuilder sb = new StringBuilder();
        while ((content = bufferedReader.readLine()) != null){
            sb.append(content);

        }
        String inputJsonStr = sb.toString();
        System.out.println("inputJsonStr = " + inputJsonStr);
        T t = JSONUtils.jsonStr2JavaBean(inputJsonStr, clazz);

        return t;
    }


    //将java对象转换为json字符串，将json字符换作为响应返回给浏览器
    public static void writeJavaBean2ResponseText(HttpServletResponse response, Object obj) throws IOException {

        //将resultVO转换为json字符串
        String jsonStr = JSONUtils.javaBean2JsonStr(obj);
        //将jsonStr作为响应正文返回给浏览器
        response.setContentType("application/json;charset=utf-8");
        //响应到浏览器页面
        response.getWriter().write(jsonStr);

    }

}
