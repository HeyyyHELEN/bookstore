package cn.edu.hjnu.filter;

import cn.edu.hjnu.constant.BookstoreConstant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Object existUser = request.getSession().getAttribute(BookstoreConstant.SESSION_KEY_USER);
        if (existUser == null) {
            //没有登录,跳转到login.html
            response.sendRedirect(request.getContextPath() + "/user?method=toLoginPage");
        } else {
            //有登录，直接放行
            filterChain.doFilter(request,response);
        }



    }
}
