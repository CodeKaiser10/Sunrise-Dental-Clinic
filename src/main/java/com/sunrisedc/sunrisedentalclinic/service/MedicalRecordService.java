package com.sunrisedc.sunrisedentalclinic.service;

import com.sunrisedc.sunrisedentalclinic.dao.MedicalRecordDAO;
import com.sunrisedc.sunrisedentalclinic.model.MedicalRecord;

public class MedicalRecordService {

    private static MedicalRecordService instance;
    private MedicalRecordDAO medicalRecordDAO;

    private MedicalRecordService() {
        this.medicalRecordDAO = new MedicalRecordDAO();
    }

    public MedicalRecordService(MedicalRecordDAO medicalRecordDAO) {
        this.medicalRecordDAO = medicalRecordDAO;
    }

    public static MedicalRecordService getInstance() {
        if (instance == null) {
            synchronized (MedicalRecordService.class) {
                if (instance == null) {
                    instance = new MedicalRecordService();
                }
            }
        }
        return instance;
    }

    public MedicalRecord getRecordForPatient(int patientId) {return medicalRecordDAO.findByPatient(patientId); }
    public void addRecord(MedicalRecord medicalRecord) {medicalRecordDAO.insert(medicalRecord);}
    public void updateRecord(MedicalRecord medicalRecord) {medicalRecordDAO.update(medicalRecord);}
}
