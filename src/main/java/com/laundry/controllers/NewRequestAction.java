package com.laundry.controllers;

import com.laundry.enums.LaundryOrderStatus;
import com.laundry.model.LaundryOrder;
import com.laundry.repo.LaundryOrderRepo;
import com.laundry.repo.PriceChartRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/new-request")
public class NewRequestAction extends HttpServlet {
    private static final PriceChartRepo priceChartRepo = new PriceChartRepo();
    private static final LaundryOrderRepo laundryOrderRepo = new LaundryOrderRepo();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        Integer shirt = Integer.parseInt(req.getParameter("shirt"));
        Integer tShirt = Integer.parseInt(req.getParameter("tShirt"));
        Integer bottomWear = Integer.parseInt(req.getParameter("bottomWear"));
        Integer woolenWear = Integer.parseInt(req.getParameter("woolenWear"));
        Integer bedsheets = Integer.parseInt(req.getParameter("bedsheets"));
        Integer others = Integer.parseInt(req.getParameter("others"));

        int totalNumberOfCloths = shirt + tShirt + bottomWear + woolenWear + bedsheets + others;
        if(totalNumberOfCloths < 1){
            req.getSession().setAttribute("error", "Please select number of cloths");
            req.getRequestDispatcher("new-requests.jsp").forward(req, resp);
        }
        else {
            Integer totalAmount = shirt * priceChartRepo.getPriceByTypeOfCloth("Shirt")
                    + tShirt * priceChartRepo.getPriceByTypeOfCloth("T Shirt")
                    + bottomWear * priceChartRepo.getPriceByTypeOfCloth("Bottom Wear")
                    + woolenWear * priceChartRepo.getPriceByTypeOfCloth("Woolen Wear")
                    + bedsheets * priceChartRepo.getPriceByTypeOfCloth("Blanket/Bed sheets")
                    + others * priceChartRepo.getPriceByTypeOfCloth("others");

            List<String> clothItems = new ArrayList<>();
            if (shirt > 0) clothItems.add("Shirt");
            if (tShirt > 0) clothItems.add("T Shirt");
            if (bottomWear > 0) clothItems.add("Bottom Wear");
            if (woolenWear > 0) clothItems.add("Woolen Wear");
            if (bedsheets > 0) clothItems.add("Blanket/Bed sheets");
            if (others > 0) clothItems.add("Others");

            LaundryOrder laundryOrder = new LaundryOrder();
            laundryOrder.setOrderDate(LocalDate.now());
            laundryOrder.setStatus(LaundryOrderStatus.NEW);
            laundryOrder.setUserId(id);
            laundryOrder.setUserName(name);
            laundryOrder.setNumberOfCloths(totalNumberOfCloths);
            laundryOrder.setAmount(totalAmount);
            laundryOrder.setClothItems(clothItems.toString());
            laundryOrder = laundryOrderRepo.create(laundryOrder);
            if (laundryOrder != null) {
                req.getSession().setAttribute("success", "Laundry order created successfully");
            } else {
                req.getSession().setAttribute("error", "Laundry order could not be created");
            }
            resp.sendRedirect("user-dashboard.jsp");
        }
    }
}
