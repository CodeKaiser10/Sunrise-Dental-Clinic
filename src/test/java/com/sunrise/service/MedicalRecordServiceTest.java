package com.sunrise.service;

import com.sunrisedc.sunrisedentalclinic.service.MedicalRecordService;
import com.sunrisedc.sunrisedentalclinic.dao.MedicalRecordDAO;
import com.sunrisedc.sunrisedentalclinic.model.MedicalRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class MedicalRecordServiceTest {

    @Mock
    private MedicalRecordDAO medicalRecordDAO;

    private MedicalRecordService medicalRecordService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        medicalRecordService = new MedicalRecordService(medicalRecordDAO);
    }

    @Test
    void getRecordForPatientThroughDAO() {
        medicalRecordService.getRecordForPatient(1);
        verify(medicalRecordDAO).findByPatient(1);
    }

    @Test
    void addRecordThroughDAO() {
        MedicalRecord record = new MedicalRecord(0, 1, "2026-09-05", "Checkup");
        medicalRecordService.addRecord(record);
        verify(medicalRecordDAO).insert(record);
    }

    @Test
    void updateRecordThroughDAO() {
        MedicalRecord record = new MedicalRecord(0, 1, "2026-09-05", "updated Checkup");
        medicalRecordService.updateRecord(record);
        verify(medicalRecordDAO).update(record);
    }
}
