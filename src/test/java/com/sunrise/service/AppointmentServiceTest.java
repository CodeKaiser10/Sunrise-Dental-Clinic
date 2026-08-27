package com.sunrise.service;

import com.sunrisedc.sunrisedentalclinic.service.AppointmentService;
import com.sunrisedc.sunrisedentalclinic.dao.AppointmentDAO;
import com.sunrisedc.sunrisedentalclinic.model.Appointment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AppointmentServiceTest {

    @Mock
    private AppointmentDAO appointmentDAO;

    @Mock
    private AppointmentService appointmentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        appointmentService = new AppointmentService(appointmentDAO);
    }

    @Test
    void bookAppointmentThroughDAO() {
        Appointment a = new Appointment();
        a.setPatientId(1);
        a.setDentistId(2);
        appointmentService.bookAppointment(a);
        verify(appointmentDAO).insert(a);
    }

    @Test
    void GenerateNumber() {
        Appointment a = new Appointment();
        a.setPatientId(1);
        a.setDentistId(2);
        appointmentService.bookAppointment(a);
        verify(appointmentDAO).insert(a);
    }

    @Test
    void findByNumberThroughDAO() {
        appointmentService.findByNumber("AP-1");
        verify(appointmentDAO).findByNumber("AP-1");
    }

    @Test
    void cancelAppointmentThroughDAO() {
        appointmentService.cancelAppointment(1);
        verify(appointmentDAO).updateStatus(1,  "CANCELLED");
    }

    @Test
    void updateStatusThroughDAO() {
        appointmentService.updateStatus(1, "Completed");
        verify(appointmentDAO).updateStatus(1, "Completed");
    }
}
