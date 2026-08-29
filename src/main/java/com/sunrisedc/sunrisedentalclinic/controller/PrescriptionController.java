package com.sunrisedc.sunrisedentalclinic.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.sunrisedc.sunrisedentalclinic.model.Prescription;
import com.sunrisedc.sunrisedentalclinic.service.PrescriptionService;

@WebServlet("/dentist/prescriptions")
public class PrescriptionController extends HttpServlet {
    private PrescriptionService prescriptionService;

    public PrescriptionController() {}

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @Override
    public void init() throws ServletException {
        prescriptionService = PrescriptionService.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String patientId = request.getParameter("patientId");
        if (patientId == null && !patientId.isEmpty()) {
            request.setAttribute("prescriptions", prescriptionService.getPatientPrescriptions(Integer.parseInt(patientId)));
        }
        request.getRequestDispatcher("/WEB-INF/view/dentist/prescriptions.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("update".equals(action)) {
            Prescription p = new Prescription();
            p.setPrescriptionId(Integer.parseInt(request.getParameter("Id")));
            p.setMedication(request.getParameter("medication"));
            p.setDosage(request.getParameter("dosage"));
            p.setNotes(request.getParameter("notes"));
            prescriptionService.updatePrescription(p);
            response.sendRedirect(request.getContextPath() + "/dentist/prescriptions?patientId=" + request.getParameter("patientId"));
            return;
        }

        Prescription p = new Prescription();
        p.setPatientId(Integer.parseInt(request.getParameter("patientId")));
        p.setDentistId(Integer.parseInt(request.getParameter("dentistId")));
        p.setDate(request.getParameter("date"));
        p.setMedication(request.getParameter("medication"));
        p.setDosage(request.getParameter("dosage"));
        p.setNotes(request.getParameter("notes"));

        prescriptionService.addPrescription(p);
        response.sendRedirect(request.getContextPath() + "/dentist/prescriptions");
    }
}
