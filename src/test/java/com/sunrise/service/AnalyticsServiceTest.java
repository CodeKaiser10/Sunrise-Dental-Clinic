package com.sunrise.service;

import com.sunrisedc.sunrisedentalclinic.dao.AnalyticsDAO;
import com.sunrisedc.sunrisedentalclinic.service.AnalyticsService;
import com.sunrisedc.sunrisedentalclinic.model.AnalyticsSummary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AnalyticsServiceTest {

    @Mock
    private AnalyticsDAO analyticsDAO;

    private AnalyticsService analyticsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        analyticsService = new AnalyticsService(analyticsDAO);
    }

    @Test
    void summaryFromDAO() {
        when(analyticsDAO.countPatients()).thenReturn(1);
        when(analyticsDAO.countAppointments()).thenReturn(1);
        when(analyticsDAO.countAppointmentsByStatus("SCHEDULED")).thenReturn(1);
        when(analyticsDAO.countAppointmentsByStatus("COMPLETED")).thenReturn(1);
        when(analyticsDAO.countAppointmentsByStatus("CANCELLED")).thenReturn(1);
        when(analyticsDAO.sumRevenue()).thenReturn(250000.0);

        AnalyticsSummary summary = analyticsService.getSummary();

        assertEquals(1, summary.getTotalPatients());
        assertEquals(1, summary.getTotalAppointments());
        assertEquals(1, summary.getScheduledAppointments());
        assertEquals(1, summary.getCompletedAppointments());
        assertEquals(1, summary.getCancelledAppointments());
        assertEquals(250000.0, summary.getTotalRevenue());
    }
}
