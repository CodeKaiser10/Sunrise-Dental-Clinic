package com.sunrisedc.sunrisedentalclinic.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.sunrisedc.sunrisedentalclinic.model.Staff;
import com.sunrisedc.sunrisedentalclinic.service.ReportService;

@WebServlet("/manager/reports")
public class ReportController extends HttpServlet {

    private ReportService reportService;

    public ReportController() {}

    @Override
    public void init() throws ServletException {
        reportService = ReportService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("reports", reportService.getAllReports());
        request.getRequestDispatcher("/WEB-INF/view/manager/reports.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        HttpSession session = request.getSession();
        Staff user = (Staff) session.getAttribute("user");

        reportService.generateReport(type, user.getId());
        response.sendRedirect(request.getContextPath() + "/manager/reports");
    }
}
