package com.sunrise.model;

import com.sunrisedc.sunrisedentalclinic.model.Prescription;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//verifies the prescription model stores and returns its fields
public class PrescriptionModelTest {

    @Test
    void createPrescription() {
        Prescription p = new Prescription(1,3,4, "2026-09-02", "Amoxicillin", "200mg", "before meals");
        assertEquals(1, p.getPrescriptionId());
        assertEquals(3, p.getPatientId());
        assertEquals(4, p.getDentistId());
        assertEquals("2026-09-02", p.getDate());
        assertEquals("Amoxicillin", p.getMedication());
        assertEquals("200mg", p.getDosage());
        assertEquals("before meals", p.getNotes());
    }

    @Test
    void setPrescription() {
        Prescription p = new Prescription();
        p.setMedication("Ibuprofen");
        assertEquals("Ibuprofen", p.getMedication());
    }
}
