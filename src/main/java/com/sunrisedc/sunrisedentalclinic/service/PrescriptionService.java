package com.sunrisedc.sunrisedentalclinic.service;

import com.sunrisedc.sunrisedentalclinic.dao.PrescriptionDAO;
import com.sunrisedc.sunrisedentalclinic.model.Prescription;
import java.util.List;

public class PrescriptionService {

    private static PrescriptionService instance;
    private PrescriptionDAO prescriptionDAO;

    private PrescriptionService() {
        this.prescriptionDAO = new PrescriptionDAO();
    }

    public  PrescriptionService(PrescriptionDAO prescriptionDAO) {
        this.prescriptionDAO = prescriptionDAO;
    }

    public static PrescriptionService getInstance() {
        if (instance == null) {
            synchronized (PrescriptionService.class) {
                if (instance == null) {
                    instance = new PrescriptionService();
                }
            }
        }
        return instance;
    }

    public void addPrescription(Prescription prescription) { prescriptionDAO.insert(prescription); }
    public List<Prescription> getPatientPrescriptions(int patientId) { return prescriptionDAO.findByPatient(patientId); }
    public void updatePrescription(Prescription prescription) { prescriptionDAO.update(prescription); }
}
