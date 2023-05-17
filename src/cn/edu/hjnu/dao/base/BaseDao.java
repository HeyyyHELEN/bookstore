package cn.edu.hjnu.dao.base;

import cn.edu.hjnu.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BaseDao<T> {


    /**
     * DML语句
     * @param sql sql语句
     * @param args 传入参数
     * @return 影响的行数
     */
    public int update(String sql, Object...args){


        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            return new QueryRunner().update(
                    connection,
                    sql,
                    args

            );
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null);
        }


        return -1;

    }


    /**
     * 查询单条语句
     * @param sql
     * @param clazz
     * @param args
     * @return
     */
    public T getBean(String sql, Class<T> clazz, Object... args){
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            return new QueryRunner().query(
                    connection,
                    sql,
                    new BeanHandler<>(clazz),
                    args
            );

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null);
        }


        return null;
    }


    /**
     * 查询多条语句
     * @param sql
     * @param clazz
     * @param args
     * @return
     */
    public List<T> getBeanList(String sql, Class<T> clazz, Object... args){

        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            return new QueryRunner().query(
                    connection,
                    sql,
                    new BeanListHandler<>(clazz),
                    args
            );

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null);
        }


        return null;

    }


}
