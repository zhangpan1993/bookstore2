package com.bookstore.web.servlet;

import com.bookstore.domain.User;
import com.bookstore.domain.UserForm;
import com.bookstore.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        User user = new User();

        UserForm uf = new UserForm();

        try {
            BeanUtils.populate(uf,req.getParameterMap());

            if (uf.validate()){

                req.setAttribute("uf",uf);

                req.getRequestDispatcher("/register.jsp").forward(req,resp);

                return;
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }



        try {
            if (req.getSession().getAttribute("checkcode_session").equals(req.getParameter("checkcode"))){
                BeanUtils.populate(user,req.getParameterMap());
                user.setActiveCode(UUID.randomUUID().toString());
                user.setRole("普通用户");
                UserService us = new UserService();
                us.regist(user);
                req.getRequestDispatcher("/registersuccess.jsp").forward(req,resp);
                System.out.println(user);
            }else{
                req.setAttribute("checkerr","验证码错误");
                req.getRequestDispatcher("/register.jsp").forward(req,resp);
            }

        } catch (IllegalAccessException | InvocationTargetException | SQLException e) {
            req.setAttribute("user_msg",e.getMessage()+"用户名存在");
            req.getRequestDispatcher("/register.jsp").forward(req,resp);
            e.printStackTrace();
        }


    }
}
