package com.sunrisedc.sunrisedentalclinic.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.sunrisedc.sunrisedentalclinic.model.Bill;
import com.sunrisedc.sunrisedentalclinic.service.BillService;

@WebServlet("/receptionist/billing")
public class BillController extends HttpServlet {
    private BillService billService;

    private BillController() {}

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @Override
    public void init() throws ServletException {
        billService = BillService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //view an existing bill for appointment
        String appId = request.getParameter("appointmentId");
        if (appId != null && !appId.isEmpty()) {
            Bill bill = billService.getBillForAppointment(Integer.parseInt(appId));
            request.setAttribute("bills", bill);
        }
        request.getRequestDispatcher("/WEB-INF/view/receptionist/billing.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //builds the bill and let service calculate and save it
        Bill bill = new Bill();
        bill.setAppointmentId(Integer.parseInt(request.getParameter("appointmentId")));
        bill.setConsultationFee(Integer.parseInt(request.getParameter("consultationFee")));
        bill.setTreatmentFee(Integer.parseInt(request.getParameter("treatmentFee")));
        bill.setDiscount(Integer.parseInt(request.getParameter("discount")));
        bill.setBillId(Integer.parseInt(request.getParameter("billId")));

        billService.createBill(bill);
        response.sendRedirect(request.getContextPath() + "/receptionist/billing?appointmentId=" + bill.getAppointmentId());
    }
}
