package cn.edu.hjnu.controller;

import cn.edu.hjnu.constant.BookstoreConstant;
import cn.edu.hjnu.controller.base.ModelBaseServlet;
import cn.edu.hjnu.pojo.ResultVO;
import cn.edu.hjnu.pojo.User;
import cn.edu.hjnu.service.UserService;
import cn.edu.hjnu.service.impl.UserServiceImpl;
import cn.edu.hjnu.util.JSONUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/user")
public class UserServlet extends ModelBaseServlet {

    public void  toLoginPage(HttpServletRequest request, HttpServletResponse  response){

        try {
            processTemplate("pages/user/login", request,response);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void  toLoginSuccessPage(HttpServletRequest request, HttpServletResponse  response){

        try {
            System.out.println("toLoginSuccessPage执行了");
            processTemplate("pages/user/login_success", request,response);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public  void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ResultVO resultVO = null;

        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            UserService userService = new UserServiceImpl();
            User user = userService.login(username, password);

            if (null != user){
                //设置保持登录状态
                //告诉服务器已经登录
                request.getSession().setAttribute(BookstoreConstant.SESSION_KEY_USER,user);

                //response.sendRedirect(request.getContextPath() + "/user?method=toLoginSuccessPage");
                //告诉客户端已经登录
                resultVO = new ResultVO(true,BookstoreConstant.LOGIN_SUCCESS,user);

            }else {
                //登录失败，跳转回login.html
                //String errotMsg = "账户或密码错误!";
                //request.setAttribute("errorMsg",errotMsg);
                //toLoginPage(request, response);

                resultVO = new ResultVO(false,BookstoreConstant.LOGIN_FAIL,null);


            }
        } catch (Exception e) {
            e.printStackTrace();
            resultVO = new ResultVO(false,BookstoreConstant.LOGIN_FAIL,null);

        }

        try {
            JSONUtils.writeJavaBean2ResponseText(response,resultVO);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void  toRegistPage(HttpServletRequest request, HttpServletResponse  response){

        try {
            processTemplate("pages/user/regist", request,response);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void regist(HttpServletRequest request, HttpServletResponse  response){

        System.out.println("regist");
        User inputUser = new User();

        String inputCode = request.getParameter("code");
        Object existCode = request.getSession().getAttribute("KAPTCHA_SESSION_KEY");
        if (!inputCode.equals(existCode)){

            String codeErrorMsg = "验证码错误!";
            request.setAttribute("codeErrorMsg",codeErrorMsg);
            toRegistPage(request, response);
            return;

        }


        try {
            BeanUtils.populate(inputUser,request.getParameterMap());
            UserService userService = new UserServiceImpl();

            User dbUser = userService.selectUserByUsername(inputUser.getUserName());
            if (null != dbUser){

                response.setContentType("text/html;charset=utf-8");

                String errorMsg = "用户名已经存在!";
                request.setAttribute("errorMsg",errorMsg);

                toRegistPage(request, response);

                return;
            }

            int addUser = userService.addUser(inputUser);
            if (addUser > 0){
                response.sendRedirect(request.getContextPath() + "/user?method=toRegistSuccessPage");

            } else {
                toRegistPage(request, response);
            }


        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void toRegistSuccessPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("toRegistSuccessPage执行了");
            processTemplate("pages/user/regist_success", request,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*public void loginToRegist(HttpServletRequest request, HttpServletResponse response){
        System.out.println("loginToRegist执行了");

        try {
            response.sendRedirect(request.getContextPath() + "/user?method=toRegistPage");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/



    public void logout(HttpServletRequest request, HttpServletResponse response){

        //移除session的用户信息
        request.getSession().removeAttribute(BookstoreConstant.SESSION_KEY_USER);

        /*try {
            response.sendRedirect(request.getContextPath() + "/index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        ResultVO resultVO = new ResultVO(true,"注销登录成功!",null);
        try {
            JSONUtils.writeJavaBean2ResponseText(response,resultVO);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
