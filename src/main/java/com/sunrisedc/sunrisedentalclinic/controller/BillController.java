package com.sunrisedc.sunrisedentalclinic.controller;

import java.io.IOException;

import com.sunrisedc.sunrisedentalclinic.model.Appointment;
import com.sunrisedc.sunrisedentalclinic.service.AppointmentService;
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

    public BillController() {}

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
            request.setAttribute("bill", bill);
        }
        request.getRequestDispatcher("/WEB-INF/view/receptionist/billing.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //builds the bill and let service calculate and save it
        Bill bill = new Bill();
        bill.setAppointmentId(Integer.parseInt(request.getParameter("appointmentId")));
        bill.setConsultationFee(Double.parseDouble(request.getParameter("consultationFee")));
        bill.setTreatmentFee(Double.parseDouble(request.getParameter("treatmentFee")));
        bill.setDiscount(Double.parseDouble(request.getParameter("discount")));
        bill.setBillDate(request.getParameter("billDate"));

        boolean saved = billService.createBill(bill);

        if (saved) {
            request.setAttribute("bill", bill);
        } else {
            request.setAttribute("billError", "Bill could not be saved. check the appointment ID");
        }
        request.getRequestDispatcher("/WEB-INF/view/receptionist/billing.jsp").forward(request, response);
    }
}
