package com.sunrisedc.sunrisedentalclinic.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.sunrisedc.sunrisedentalclinic.service.AnalyticsService;

//shows the manager the clinics statistics summary
@WebServlet("/manager/analytics")
public class AnalyticsController extends HttpServlet {
    private AnalyticsService analyticsService;

    public AnalyticsController() {}

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @Override
    public void init() throws ServletException {
        analyticsService = AnalyticsService.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("summary", analyticsService.getSummary());
        request.getRequestDispatcher("/WEB-INF/view/manager/analytics.jsp").forward(request, response);
    }
}
