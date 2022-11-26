package com.laundry.controllers;

import com.laundry.model.Admin;
import com.laundry.repo.AdminRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin-login")
public class AdminLoginAction extends HttpServlet {

    private final AdminRepo adminRepo = new AdminRepo();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Admin admin = adminRepo.getAdmin(email, password);
        if (admin != null) {
            req.getSession().setAttribute("user", admin);
            resp.sendRedirect("admin-dashboard.jsp");
        }
        else{
            req.getSession().setAttribute("error", "Wrong Credentials, try again");
            resp.sendRedirect("login-staff.jsp");
        }
    }
}
