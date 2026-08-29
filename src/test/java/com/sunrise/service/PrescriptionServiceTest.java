package com.sunrise.service;

import com.sunrisedc.sunrisedentalclinic.service.PrescriptionService;
import com.sunrisedc.sunrisedentalclinic.dao.PrescriptionDAO;
import com.sunrisedc.sunrisedentalclinic.model.Prescription;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class PrescriptionServiceTest {

    @Mock
    private PrescriptionDAO prescriptionDAO;

    private PrescriptionService prescriptionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        prescriptionService = new PrescriptionService(prescriptionDAO);
    }

    @Test
    void addPrescriptionThroughDAO() {
        Prescription p = new Prescription(0, 1, 2, "2026-09-01","Amoxicillin", "250g", "After meals");
        prescriptionService.addPrescription(p);
        verify(prescriptionDAO).insert(p);
    }

    @Test
    void getPatientPrescriptionThroughDAO() {
        prescriptionService.getPatientPrescriptions(1);
        verify(prescriptionDAO).findByPatient(1);
    }

    @Test
    void updatePrescriptionThroughDAO() {
        Prescription p = new Prescription(1,1,2,"2026-09-01","Amoxicillin", "200g", "After meals");
        prescriptionService.updatePrescription(p);
        verify(prescriptionDAO).update(p);
    }
}
