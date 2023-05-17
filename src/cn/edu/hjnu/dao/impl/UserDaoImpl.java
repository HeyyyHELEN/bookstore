package cn.edu.hjnu.dao.impl;

import cn.edu.hjnu.dao.UserDao;
import cn.edu.hjnu.dao.base.BaseDao;
import cn.edu.hjnu.pojo.User;

import java.util.List;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

    @Override
    public User login(String username, String password) {

        return getBean(
                "select user_id userId, user_name userName, user_pwd userPwd, email from t_user where user_name = ? and user_pwd = ?",
                User.class,
                username,
                password
        );

    }

    @Override
    public int addUser(User user) throws Exception {

        return update(
                "insert into t_user values(null,?,?,?)",
                user.getUserName(),
                user.getUserPwd(),
                user.getEmail()
        );



    }

    @Override
    public int deleteUserById(Integer userId) throws Exception {

        return update(
                "delete from t_user where user_id = ?",
                userId
        );


    }


    @Override
    public int updateUserById(User user) throws Exception {

        return update(
                "update t_user set user_name = ? , user_pwd = ? , email = ? where user_id = ?",
                user.getUserName(),
                user.getUserPwd(),
                user.getEmail(),
                user.getUserId()
        );


    }

    @Override
    public User selectUserById(Integer userId) throws Exception {

        return getBean(
                "select user_id userId, user_name userName, user_pwd uswePwd, email from t_user where user_id = ?",
                User.class,
                userId
                );


    }

    @Override
    public List<User> selectUserList() throws Exception {

        return getBeanList(
                "select user_id userId, user_name userName, user_pwd uswePwd, email from t_user",
                User.class
                );


    }

    @Override
    public User selectUserByUsername(String userName) {
        return getBean(
                "select user_id usetId, user_name userName, user_pwd userPwd, email from t_user where user_name = ?",
                User.class,
                userName

        );
    }
}
