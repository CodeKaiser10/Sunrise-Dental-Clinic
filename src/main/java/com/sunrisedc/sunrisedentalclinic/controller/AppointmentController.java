package com.sunrisedc.sunrisedentalclinic.controller;

import java.io.IOException;

import com.sunrisedc.sunrisedentalclinic.model.Patient;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.sunrisedc.sunrisedentalclinic.model.Appointment;
import com.sunrisedc.sunrisedentalclinic.service.AppointmentService;
import com.sunrisedc.sunrisedentalclinic.service.PatientService;
import com.sunrisedc.sunrisedentalclinic.service.UserService;

@WebServlet({"/receptionist/appointments", "/manager/appointments"})
public class AppointmentController extends HttpServlet {

    private AppointmentService appointmentService;
    private UserService userService;
    private PatientService patientService;

    public AppointmentController() {}


    @Override
    public void init() throws ServletException {
        appointmentService = AppointmentService.getInstance();
        patientService = PatientService.getInstance();
        userService = UserService.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        //register new appointment
        if ("new".equals(action)) {
            request.setAttribute("dentists", userService.getUsersByRole("DENTIST"));
            request.getRequestDispatcher("/WEB-INF/view/receptionist/appointment-form.jsp").forward(request, response);
            return;
        }

        if ("search".equals(action)) {
            String appNo = request.getParameter("appointmentNumber");
            if (appNo != null && !appNo.isEmpty()) {
                request.setAttribute("searchResult", appointmentService.findByNumber(appNo));
            }
            request.getRequestDispatcher("/WEB-INF/view/receptionist/appointment-search.jsp").forward(request, response);
            return;
        }

        if ("manage".equals(action)) {
            request.setAttribute("appointments", appointmentService.getAllAppointments());
            if (request.getServletPath().startsWith("/manager")) {
                request.getRequestDispatcher("/WEB-INF/view/manager/appointments.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/WEB-INF/view/receptionist/appointment-manage.jsp").forward(request, response);
            }
            return;
        }

        request.getRequestDispatcher("/WEB-INF/view/receptionist/appointments.jsp").forward(request, response);
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

        // Read the patient details from the form.
        String contact = request.getParameter("contactNumber");
        Patient existing = patientService.findByContact(contact);

        int patientId;
        if (existing != null) {
            // Patient already exists — reuse their record.
            patientId = existing.getPatientId();
        } else {
            // New patient — create the record and use the new id.
            Patient patient = new Patient();
            patient.setName(request.getParameter("patientName"));
            patient.setAddress(request.getParameter("address"));
            patient.setContactNumber(contact);
            patient.setDateOfBirth(request.getParameter("dateOfBirth"));
            patient.setGender(request.getParameter("gender"));
            patientId = patientService.registerAndGetId(patient);
        }


        //otherwise books an appointment
        Appointment appointment = new Appointment();
        appointment.setPatientId(patientId);
        appointment.setDentistId(Integer.parseInt(request.getParameter("dentistId")));
        appointment.setTreatmentType(request.getParameter("treatmentType"));
        appointment.setAppointmentDateTime(request.getParameter("appointmentDateTime"));

        appointmentService.bookAppointment(appointment);
        response.sendRedirect(request.getContextPath() + "/receptionist/appointments");
    }
}
