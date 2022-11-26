package com.laundry.controllers;


import com.laundry.model.User;
import com.laundry.repo.UserRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user-signup")
public class UserSignupAction extends HttpServlet {

    private final UserRepo userRepo = new UserRepo();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phoneNumber");
        String password = req.getParameter("password");
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        user = userRepo.create(user);
        if(user != null){
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("user-dashboard.jsp");
        }
        else{
            req.getSession().setAttribute("error", "Could not register, try again");
            resp.sendRedirect("signup.jsp");
        }
    }
}
