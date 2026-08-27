package com.sunrise.service;

import com.sunrisedc.sunrisedentalclinic.service.PatientService;
import com.sunrisedc.sunrisedentalclinic.dao.PatientDAO;
import com.sunrisedc.sunrisedentalclinic.model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

//unit test for patient service using mocked Dao
public class PatientServiceTest {

    @Mock
    private PatientDAO patientDAO;

    private PatientService patientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        patientService = new PatientService(patientDAO);
    }

    @Test
    void registerPatientThroughDAO() {
        Patient patients = new Patient(0, "Sha Lak", "Kandy", "0711111111", "2002-10-15", "MALE");
        patientService.registerPatient(patients);
        verify(patientDAO).insert(patients);
    }

    @Test
    void returnAllPatientsFromDAO() {
        patientService.getAllPatients();
        verify(patientDAO).findAll();
    }

    @Test
    void searchPatientsThroughDAO() {
        patientService.searchPatient("Sha Lak");
        verify(patientDAO).searchByName("Sha Lak");
    }

    @Test
    void findPatientThroughDAO() {
        patientService.findPatient(1);
        verify(patientDAO).findById(1);
    }

    @Test
    void updatePatientThroughDAO() {
        Patient patients = new Patient(1,"Shahindu Lakshan", "Colombo", "0700000000", "1999-10-15", "MALE");
        patientService.updatePatient(patients);
        verify(patientDAO).update(patients);
    }

    @Test
    void deletePatientThroughDAO() {
        patientService.deletePatient(5);
        verify(patientDAO).deleteById(5);
    }
}
