package com.laundry.controllers;

import com.laundry.enums.LaundryOrderStatus;
import com.laundry.model.LaundryOrder;
import com.laundry.repo.LaundryOrderRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@WebServlet("/next-step-of-laundry-order")
public class NextStepLaundryOrderAction extends HttpServlet {
    private static final LaundryOrderRepo laundryOrderRepo = new LaundryOrderRepo();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        String jspPageName = null;
        try {
            String[] uriNames = new URI(req.getHeader("referer")).getPath().split("/");
            jspPageName = uriNames[uriNames.length - 1];
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        LaundryOrder laundryOrder = laundryOrderRepo.getById(id);
        if (laundryOrder.getStatus().equals(LaundryOrderStatus.FINISHED)) {
            req.getSession().setAttribute("error", "Order already finished");
        } else {
            List<LaundryOrderStatus> laundryOrderStatuses = Arrays.asList(LaundryOrderStatus.values());
            int i = laundryOrderStatuses.indexOf(laundryOrder.getStatus());
            if (laundryOrderStatuses.get(i + 1).equals(LaundryOrderStatus.FINISHED)) {
                laundryOrder = laundryOrderRepo.updateFinishDate(id, LocalDate.now());
            }
            laundryOrder = laundryOrderRepo.updateStatus(id, laundryOrderStatuses.get(i + 1));
        }
        resp.sendRedirect("./" + jspPageName + "?orderStatus=" + laundryOrder.getStatus());
    }
}
