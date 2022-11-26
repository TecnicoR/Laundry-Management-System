package com.laundry.controllers;

import com.laundry.model.User;
import com.laundry.repo.UserRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/user-login")
public class UserLoginAction extends HttpServlet {
    private final UserRepo userRepo = new UserRepo();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = userRepo.getByEmail(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                req.getSession().setAttribute("user", user);
                resp.sendRedirect("user-dashboard.jsp");
            } else {
                req.getSession().setAttribute("error", "Wrong password, try again");
                resp.sendRedirect("login.jsp");
            }

        } else {
            req.getSession().setAttribute("error", "User does not exist, try registering");
            resp.sendRedirect("login.jsp");
        }
    }
}
