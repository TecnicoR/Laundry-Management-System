package com.laundry.controllers;

import com.laundry.repo.PriceChartRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/delete-price")
public class DeletePriceAction extends HttpServlet {

    private static final PriceChartRepo priceChartRepo = new PriceChartRepo();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Boolean delete = priceChartRepo.delete(id);
        if(Boolean.TRUE.equals(delete)){
            req.getSession().setAttribute("success","Deleted Successfully");
        }
        else{
            req.getSession().setAttribute("error", "Could not delete");
        }
        resp.sendRedirect("admin-dashboard.jsp");

    }
}
