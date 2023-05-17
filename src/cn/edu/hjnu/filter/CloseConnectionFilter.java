package cn.edu.hjnu.filter;

import cn.edu.hjnu.util.JDBCUtils;

import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;

public class CloseConnectionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
