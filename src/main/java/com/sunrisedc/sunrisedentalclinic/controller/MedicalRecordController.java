package com.sunrisedc.sunrisedentalclinic.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.sunrisedc.sunrisedentalclinic.model.MedicalRecord;
import com.sunrisedc.sunrisedentalclinic.service.MedicalRecordService;

@WebServlet("/dentist/records")
public class MedicalRecordController extends HttpServlet {

    private MedicalRecordService medicalRecordService;

    public MedicalRecordController() {}

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @Override
    public void init() throws ServletException {
        medicalRecordService = MedicalRecordService.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String patientId = request.getParameter("patientId");
        if (patientId != null && !patientId.isEmpty()) {
            MedicalRecord medicalRecord = medicalRecordService.getRecordForPatient(Integer.parseInt(patientId));
            request.setAttribute("medicalRecord", medicalRecord);
        }
        request.getRequestDispatcher("/WEB-INF/view/dentist/records.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int patientId = Integer.parseInt(request.getParameter("patientId"));
        String notes = request.getParameter("notes");

        // Check if this patient already has a record.
        MedicalRecord existing = medicalRecordService.getRecordForPatient(patientId);

        if (existing != null) {
            // Update the existing record.
            existing.setNotes(notes);
            medicalRecordService.updateRecord(existing);
        } else {
            // Create a new record for this patient.
            MedicalRecord record = new MedicalRecord();
            record.setPatientId(patientId);
            record.setNotes(notes);
            record.setCreatedDate(java.time.LocalDate.now().toString());
            medicalRecordService.addRecord(record);
        }

        response.sendRedirect(request.getContextPath() + "/dentist/records?patientId=" + patientId);
    }
}
