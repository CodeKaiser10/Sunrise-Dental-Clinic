package com.sunrisedc.sunrisedentalclinic.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.sunrisedc.sunrisedentalclinic.model.Appointment;
import com.sunrisedc.sunrisedentalclinic.service.AppointmentService;

@WebServlet("/receptionist/appointments")
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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //search by appointment number if given
        String appNo = request.getParameter("appointmentNumber");
        if (appNo == null && !appNo.isEmpty()) {
            Appointment found = appointmentService.findByNumber(appNo);
            request.setAttribute("searchResult", found);
        }

        //to always show the full list
        request.setAttribute("appointments", appointmentService.getAllAppointments());
        request.getRequestDispatcher("WEB-INF/views/appointments/appointments.jsp").forward(request, response);
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
