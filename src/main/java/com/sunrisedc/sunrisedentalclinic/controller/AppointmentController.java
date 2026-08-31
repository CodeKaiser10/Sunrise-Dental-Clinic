package com.sunrisedc.sunrisedentalclinic.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.sunrisedc.sunrisedentalclinic.model.Appointment;
import com.sunrisedc.sunrisedentalclinic.service.AppointmentService;

@WebServlet({"/receptionist/appointments", "/manager/appointments"})
public class AppointmentController extends HttpServlet {

    private AppointmentService appointmentService;

    public AppointmentController() {}

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Override
    public void init() throws ServletException {
        appointmentService = AppointmentService.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Show the booking form page.
        String action = request.getParameter("action");
        if ("new".equals(action)) {
            request.getRequestDispatcher("/WEB-INF/view/receptionist/appointment-form.jsp").forward(request, response);
            return;
        }

        // Search by appointment number if given (null-safe).
        String appNo = request.getParameter("appointmentNumber");
        if (appNo != null && !appNo.isEmpty()) {
            request.setAttribute("searchResult", appointmentService.findByNumber(appNo));
        }

        request.setAttribute("appointments", appointmentService.getAllAppointments());

        // Manager gets read-only view; receptionist gets full manage view.
        if (request.getServletPath().startsWith("/manager")) {
            request.getRequestDispatcher("/WEB-INF/view/manager/appointments.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/view/receptionist/appointments.jsp").forward(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action  = request.getParameter("action");

        //cancel an appointment
        if ("cancel".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            appointmentService.cancelAppointment(id);
            response.sendRedirect(request.getContextPath() + "/receptionist/appointments");
            return;
        }

        //update status
        if ("status".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            appointmentService.updateStatus(id, request.getParameter("status"));
            response.sendRedirect(request.getContextPath() + "/receptionist/appointments");
            return;
        }

        //otherwise books an appointment
        Appointment appointment = new Appointment();
        appointment.setPatientId(Integer.parseInt(request.getParameter("patientId")));
        appointment.setDentistId(Integer.parseInt(request.getParameter("dentistId")));
        appointment.setAppointmentDateTime(request.getParameter("appointmentDateTime"));

        appointmentService.bookAppointment(appointment);
        response.sendRedirect(request.getContextPath() + "/receptionist/appointments");
    }
}
