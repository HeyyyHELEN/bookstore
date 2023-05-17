package cn.edu.hjnu.dao.impl;

import cn.edu.hjnu.constant.BookstoreConstant;
import cn.edu.hjnu.dao.OrderDao;
import cn.edu.hjnu.pojo.Order;
import cn.edu.hjnu.util.JDBCUtils;

import java.sql.*;

public class OrderDaoImpl implements OrderDao {
    @Override
    public void addOrder(Order order) throws Exception{

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            //开启事务
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);

            //获取连接
             connection = JDBCUtils.getConnection();
            //创建sql
            String sql = "insert into t_order values(null,?,?,?,?,?,?)";
            //获取主键
             statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //设置订单序列单号
            statement.setString(1, order.getOrderSequence());
            //设置创建订单时间
            statement.setString(2, order.getCreateTime());
            //设置订单总数量
            statement.setInt(3,order.getTotalCount());
            //设置订单总金额
            statement.setDouble(4,order.getTotalAmount());
            //设置订单状态
            statement.setInt(5, BookstoreConstant.ORDER_UNCOMPLETED);
            //设置用户id
            statement.setInt(6,order.getUserId());
            //执行sql语句
            int addOrder = statement.executeUpdate();
            if (addOrder >= 1){
                 resultSet = statement.getGeneratedKeys();
                while (resultSet.next()){
                    int orderId = resultSet.getInt(1);
                    order.setOrderId(orderId);
                }
            }

            //没有错误，提交事务
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            //有错，回滚事务
            connection.rollback();

        }finally {
            //JDBCUtils.release(connection,statement,resultSet);
        }



    }
}
