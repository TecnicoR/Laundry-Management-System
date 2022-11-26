package com.laundry.controllers;

import com.laundry.model.User;
import com.laundry.repo.UserRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/update")
public class UpdateProfileAction extends HttpServlet {
    private final UserRepo userRepo = new UserRepo();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phoneNumber");
        Integer id = Integer.parseInt(req.getParameter("id"));
        if (!req.getParameter("password").isEmpty()){
            user.setPassword(req.getParameter("password"));
        }
        else {
            user.setPassword(null);
        }
        user.setName(name);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setId(id);
        User user1 = userRepo.updateById(id, user);
        if (user1 != null) {
            req.getSession().setAttribute("success", "Profile updated successfully");
            req.getSession().setAttribute("user", user1);
            resp.sendRedirect("user-dashboard.jsp");
        }
        else{
            req.getSession().setAttribute("error", "Profile could not be updated");
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("update-profile.jsp");
        }
    }
}
