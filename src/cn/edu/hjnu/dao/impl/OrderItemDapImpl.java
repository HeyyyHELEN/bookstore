package cn.edu.hjnu.dao.impl;

import cn.edu.hjnu.dao.OrderItemDao;
import cn.edu.hjnu.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderItemDapImpl implements OrderItemDao {
    @Override
    public void addOrderItems(Object[][] orderItemParams) throws Exception{
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            new QueryRunner().batch(
                    connection,
                    "insert into t_order_item values(null,?,?,?,?,?,?)",
                    orderItemParams
            );
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null);
        }
    }
}
