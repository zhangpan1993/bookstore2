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
            String path = null;
            if ("管理员".equals(user.getRole()) ){
                path = "/admin/login/home.jsp";

            }else if ("普通用户".equals(user.getRole())){
                path = "/myAccount.jsp";
            }

            req.getSession().setAttribute("user",user);
            req.getRequestDispatcher(path).forward(req,resp);
        } catch (UserException e) {
            e.printStackTrace();
            req.setAttribute("loginerr",e.getMessage());
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }


    }
}
