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
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setPatientId(Integer.parseInt(request.getParameter("patientId")));
        medicalRecord.setNotes(request.getParameter("notes"));

        if ("update".equals(action)) {
            medicalRecordService.updateRecord(medicalRecord);
        } else {
            medicalRecord.setCreatedDate(request.getParameter("createdDate"));
            medicalRecordService.addRecord(medicalRecord);
        }

        response.sendRedirect(request.getContextPath() + "/dentist/records?patientId=" + medicalRecord.getPatientId());
    }
}
