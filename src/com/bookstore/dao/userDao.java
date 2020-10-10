package com.bookstore.dao;

import com.bookstore.domain.User;
import com.bookstore.utils.C3P0Utils;
import com.sun.deploy.security.BadCertificateDialog;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class userDao {

    QueryRunner qr = new QueryRunner(C3P0Utils.getDs());

    public void addUser(User user) throws SQLException {

        String sql = "INSERT INTO user (username,password,gender,email,telephone,introduce,activeCode,state,role,registTime)" +
        "VALUES(?,?,?,?,?,?,?,?,?,?)";
        qr.update(sql,user.getUsername(),user.getPassword(),user.getGender(),user.getEmail(),user.getTelephone(),user.getIntroduce(),
                user.getActiveCode(),user.getState(),user.getRole(),user.getRegistTime());

    }

    public User findUserByUsernameAndPassword(String username,String password) throws SQLException {


        String sql = "select * from user where username=? and password=?";

        return qr.query(sql,new BeanHandler<User>(User.class),username,password);
    }

    public User findUserByActiveCode(String activeCode) throws SQLException {

        String sql = "select * from user where activeCode=?";

        return qr.query(sql,new BeanHandler<User>(User.class),activeCode);
    }

    public void updateActiveState(String activeCode) throws SQLException {

        String sql = "update user set state = 1 where activeCode = ?";
        qr.update(sql,activeCode);
    }




}
