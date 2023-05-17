package cn.edu.hjnu.controller.base;

import annotation.MyResponseBody;
import cn.edu.hjnu.util.JSONUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class ModelBaseServlet extends ViewBaseServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String methodName = req.getParameter("method");

        try {

            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

            Object result = method.invoke(this, req, resp);
            boolean present = method.isAnnotationPresent(MyResponseBody.class);
            if (present){
                if (null != result){
                    //方法执行之后返回的是javabean对象
                    JSONUtils.writeJavaBean2ResponseText(resp,result);
                } else {
                    //没有使用注解，转发/重定向
                    String newResult = (String) result;
                    if (newResult != null){
                        if (newResult.startsWith("forward:")){
                            String path = newResult.substring("forward:".length());
                            processTemplate(path,req,resp);
                        } else if (newResult.startsWith("redirect:")){
                            //  /项目访问路径/资源访问路径
                            String path = newResult.substring("redirect:".length());
                            resp.sendRedirect(req.getContextPath() + "/" + path);

                        } else {
                            processTemplate(newResult,req,resp);
                        }

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
