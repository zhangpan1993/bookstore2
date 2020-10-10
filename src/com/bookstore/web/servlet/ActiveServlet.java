package com.bookstore.web.servlet;

import com.bookstore.domain.User;
import com.bookstore.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/active")
public class ActiveServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String activeCode = req.getParameter("activeCode");

        System.out.println("收到了激活码："+activeCode);
        try {
            UserService userService = new UserService();

            userService.activeUser(activeCode);

            req.getRequestDispatcher("/activesuccess.jsp").forward(req, resp);
        }catch (Exception e){

            resp.getWriter().write("激活失败");
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        }
    }
}
