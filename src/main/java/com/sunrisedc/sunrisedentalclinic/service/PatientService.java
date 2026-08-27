package com.sunrisedc.sunrisedentalclinic.service;

import com.sunrisedc.sunrisedentalclinic.dao.PatientDAO;
import com.sunrisedc.sunrisedentalclinic.model.Patient;
import java.util.List;

public class PatientService {
    private static PatientService instance;
    private PatientDAO patientDAO;

    //private constructor
    public PatientService () {
        this.patientDAO = new PatientDAO();
    }

    //inject constructor
    public PatientService (PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    public static PatientService getInstance() {
        if (instance == null) {
            synchronized (PatientService.class) {
                if (instance == null) {
                    instance = new PatientService();
                }
            }
        }
        return instance;
    }

    public void registerPatient(Patient patient) { patientDAO.insert(patient); }
    public List<Patient> getAllPatients() { return patientDAO.findAll(); }
    public List<Patient> searchPatient(String name) { return patientDAO.searchByName(name); }
    public Patient findPatient(int patientId) { return  patientDAO.findById(patientId); }
    public void updatePatient(Patient patient) { patientDAO.update(patient); }
    public void deletePatient(int patientId) { patientDAO.deleteById(patientId); }
}
