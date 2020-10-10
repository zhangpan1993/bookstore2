package com.bookstore.domain;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.HashMap;
import java.util.Map;

public class UserForm {

    private String username;
    private String password;
    private String repassword;
    private String email;
    private String telephone;
    private String registTime;

    Map<String,String> err = new HashMap<String,String>();

    public boolean validate(){

        if (username == null || "".equals(username)){

            err.put("username","用户名密码不能为空");

        }else if (!username.matches("\\w{3,12}")){

            err.put("username","用户名密码必须为2-12位");
        }

        if (password == null || "".equals(password.trim())){

            err.put("password","密码不能为空");
        }else if (!password.matches("\\w{6,12}")){

            err.put("password","密码必须为6-12位");
        }

        if (!email.matches("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$")){

            err.put("email","邮箱格式不正确");
        }


        return !err.isEmpty();

    }

    public UserForm(){

        super();
    }

    public UserForm(String username, String password, String repassword, String email, String telephone) {
        this.username = username;
        this.password = password;
        this.repassword = repassword;
        this.email = email;
        this.telephone = telephone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRegistTime() {
        return registTime;
    }

    public void setRegistTime(String registTime) {
        this.registTime = registTime;
    }

    public Map<String, String> getErr() {
        return err;
    }

    public void setErr(Map<String, String> err) {
        this.err = err;
    }
}
