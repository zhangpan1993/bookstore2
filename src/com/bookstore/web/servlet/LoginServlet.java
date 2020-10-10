package com.bookstore.web.servlet;

import com.bookstore.domain.User;
import com.bookstore.exception.UserException;
import com.bookstore.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserService userService = new UserService();
        try {
            User user = userService.login(username,password);
            System.out.println(user);
            if (user != null && "1".equals(user.getState())){
                req.getRequestDispatcher("/admin/login/home.jsp").forward(req,resp);
            }else if("0".equals(user.getState())){

                req.setAttribute("loginerr","账号未激活激活，请先激活！");
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }else{

                req.setAttribute("loginerr","用户名或密码错误");
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }
        } catch (UserException e) {
            e.printStackTrace();
        }


    }
}
