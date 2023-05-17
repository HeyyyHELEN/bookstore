package cn.edu.hjnu.dao;

import cn.edu.hjnu.pojo.User;

import java.util.List;

public interface UserDao {

    public User login(String username, String password);

    int addUser(User user) throws Exception;

    int deleteUserById(Integer userId) throws Exception;

    int updateUserById(User user) throws Exception;

    User selectUserById(Integer userId) throws Exception;

    List<User> selectUserList() throws Exception;

    User selectUserByUsername(String userName);
}
