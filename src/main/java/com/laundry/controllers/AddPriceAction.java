package com.laundry.controllers;


import com.laundry.model.PriceChart;
import com.laundry.repo.PriceChartRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/add-price")
public class AddPriceAction extends HttpServlet {

    private static final PriceChartRepo priceChartRepo = new PriceChartRepo();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String typeOfCloth = req.getParameter("typeOfCloth");
        Integer price = Integer.parseInt(req.getParameter("price"));
        PriceChart priceChart = new PriceChart();
        priceChart.setTypeOfCloth(typeOfCloth);
        priceChart.setPrice(price);
        priceChart = priceChartRepo.create(priceChart);
        if(priceChart!=null){
            resp.sendRedirect("admin-dashboard.jsp");
        }
        else{
            req.getSession().setAttribute("error", "Could not save price details");
            resp.sendRedirect("add-price.jsp");
        }

    }
}
