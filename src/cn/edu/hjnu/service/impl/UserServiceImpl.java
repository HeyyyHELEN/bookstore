package cn.edu.hjnu.service.impl;

import cn.edu.hjnu.dao.UserDao;
import cn.edu.hjnu.dao.impl.UserDaoImpl;
import cn.edu.hjnu.pojo.User;
import cn.edu.hjnu.service.UserService;
import cn.edu.hjnu.util.MD5Utils;

public class UserServiceImpl implements UserService {
    @Override
    public User login(String username, String password) {

        UserDao userDao = new UserDaoImpl();
        User dbuser = userDao.selectUserByUsername(username);
        if (null == dbuser){
            return null;
        }

        boolean verify = MD5Utils.verify(password, dbuser.getUserPwd());
        return verify ? dbuser : null;
    }



    @Override
    public int addUser(User inputUser) throws Exception {

        String salt = MD5Utils.generateSalt();
        String md5password = MD5Utils.generateMD5AndSalt(inputUser.getUserPwd(), salt);
        inputUser.setUserPwd(md5password);

        UserDao userDao = new UserDaoImpl();
        return userDao.addUser(inputUser);

    }

    @Override
    public User selectUserByUsername(String userName) {
        UserDao userDao = new UserDaoImpl();
        return userDao.selectUserByUsername(userName);
    }
}
