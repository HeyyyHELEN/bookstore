package cn.edu.hjnu.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {

    private static DataSource dataSource;

    static {
        Properties properties = new Properties();
        try {
            properties.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //开启事务
    public static void startTransaction() throws SQLException {
        getConnection().setAutoCommit(false);
    }

    //回滚事务
    public static void rollback() throws SQLException {
        getConnection().rollback();
    }

    //提交事务
    public static void commit() throws SQLException {
        getConnection().commit();
    }

    //放流，关闭连接
    public static void closeConnection() throws SQLException {
        Connection connection = getConnection();

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection = null;
        //重置状态
        threadLocal.remove();


    }



    public static DataSource getDataSource() {
        return dataSource;
    }


    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static Connection getConnection() throws SQLException {
        Connection connection = threadLocal.get();
        if (connection == null){
            connection = dataSource.getConnection();
            threadLocal.set(connection);
        }
        return connection;
    }

    public static void release(Connection connection, Statement statement, ResultSet resultSet){

        if (null != connection){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (null != statement){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (null != resultSet){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void release(Connection connection, Statement statement){
        release(connection, statement,null);
    }

    public static void release(Connection connection){
        release(connection,null);
    }

}
