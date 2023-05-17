package cn.edu.hjnu.filter;

import javax.servlet.*;
import java.io.IOException;


public class Encoding_Filter implements Filter {

    private String encoding;

    public void init(FilterConfig config) throws ServletException {

        encoding = config.getInitParameter("encoding");

    }



    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }
}
