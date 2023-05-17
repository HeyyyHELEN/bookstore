package cn.edu.hjnu.service;

import cn.edu.hjnu.pojo.User;

public interface UserService {

    public User login(String username, String password);

    int addUser(User inputUser) throws Exception;

    User selectUserByUsername(String userName);
}
