package com.laundry.controllers;

import com.laundry.model.PriceChart;
import com.laundry.repo.PriceChartRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/edit-price")
public class EditPriceAction extends HttpServlet {

    private static final PriceChartRepo priceChartRepo = new PriceChartRepo();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        String typeOfCloth = req.getParameter("typeOfCloth");
        Integer price = Integer.parseInt(req.getParameter("price"));
        PriceChart priceChart = new PriceChart();
        priceChart.setId(id);
        priceChart.setTypeOfCloth(typeOfCloth);
        priceChart.setPrice(price);
        priceChart = priceChartRepo.update(priceChart);
        if (priceChart != null) {
            req.getSession().setAttribute("success", priceChart.getTypeOfCloth() +"'s Price updated successfully");
        } else {
            req.getSession().setAttribute("error", "Could not update, try again");
        }
        resp.sendRedirect("admin-dashboard.jsp");
    }
}
