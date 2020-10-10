package com.bookstore.service;

import com.bookstore.dao.userDao;
import com.bookstore.domain.User;
import com.bookstore.exception.UserException;
import com.bookstore.utils.SendJMail;
import org.junit.Test;

import java.sql.SQLException;

public class UserService {

    userDao userDao = new userDao();
    public void regist(User user) throws SQLException {

        userDao.addUser(user);
        //发送激活邮件
        String activeLink = "http://localhost:8080/active?activeCode=" + user.getActiveCode();
        String html = "欢迎注册网上书城，请 <a href=\"" + activeLink+ "\">点击</a> 激活帐号";
        System.out.println("激活:" + html);
        SendJMail.sendMail(user.getEmail(), html);

    }


    public User login(String username,String password) throws UserException {

        try {
            User user = userDao.findUserByUsernameAndPassword(username,password);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("登录失败");

        }



    }

    public void activeUser(String activeCode){

        User user = null;
        try {
            user = userDao.findUserByActiveCode(activeCode);
            if (user != null){

                userDao.updateActiveState(activeCode);
            }else{

                throw new UserException("激活失败");
            }
        } catch (SQLException | UserException e) {
            e.printStackTrace();
        }

    }
}
