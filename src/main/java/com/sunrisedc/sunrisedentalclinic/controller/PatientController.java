package com.sunrisedc.sunrisedentalclinic.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.sunrisedc.sunrisedentalclinic.model.Patient;
import com.sunrisedc.sunrisedentalclinic.service.PatientService;

//handles patient registrations, search, list, edit and delete
@WebServlet({"/receptionist/patients", "/manager/patients"})
public class PatientController extends HttpServlet {

    private PatientService patientService;

    public PatientController() {}

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @Override
    public void init() throws ServletException {
        patientService = PatientService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if("new".equals(action)){
            request.getRequestDispatcher("/WEB-INF/view/receptionist/patient-form.jsp").forward(request, response);
            return;
        }

        //Show the edit form with patient's data
        if ("edit".equals(action)) {
            int id  = Integer.parseInt(request.getParameter("id"));
            Patient patients = patientService.findPatient(id);
            request.setAttribute("editPatient", patients);
            request.getRequestDispatcher("/WEB-INF/view/receptionist/editPatient.jsp").forward(request, response);
            return;
        }

        //Search by name if a term is given, otherwise list all
        String search = request.getParameter("search");
        if (search != null && !search.isEmpty()) {
            request.setAttribute("patients", patientService.searchPatient(search));
        }

        // (no else — if no search, patients is null, so the page shows an empty state)
        if (request.getServletPath().startsWith("/manager")) {
            request.getRequestDispatcher("/WEB-INF/view/manager/patients.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/view/receptionist/patients.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        //deletes a patient
        if ("delete".equals(action)) {
            int id  = Integer.parseInt(request.getParameter("id"));
            patientService.deletePatient(id);
            response.sendRedirect(request.getContextPath() + "/receptionist/patients");
            return;
        }

        //read the form fields
        Patient patient = new Patient();
        patient.setName(request.getParameter("name"));
        patient.setAddress(request.getParameter("address"));
        patient.setContactNumber(request.getParameter("contactNumber"));
        patient.setDateOfBirth(request.getParameter("dateOfBirth"));
        patient.setGender(request.getParameter("gender"));

        //update if an id is present, otherwise insert a new patient
        if ("update".equals(action)) {
            patient.setPatientId(Integer.parseInt(request.getParameter("id")));
            patientService.updatePatient(patient);
        } else {
            patientService.registerPatient(patient);
        }
        response.sendRedirect(request.getContextPath() + "/receptionist/patients");
    }
}
