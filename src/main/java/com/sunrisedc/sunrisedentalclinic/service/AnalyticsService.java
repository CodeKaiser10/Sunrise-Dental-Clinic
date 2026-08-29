package com.sunrisedc.sunrisedentalclinic.service;

import com.sunrisedc.sunrisedentalclinic.dao.AnalyticsDAO;
import com.sunrisedc.sunrisedentalclinic.model.AnalyticsSummary;

// Business layer for analytics — assembles the summary
public class AnalyticsService {

    private static AnalyticsService instance;
    private AnalyticsDAO analyticsDAO;

    private AnalyticsService() {
        this.analyticsDAO = new AnalyticsDAO();
    }

    public AnalyticsService(AnalyticsDAO analyticsDAO) {
        this.analyticsDAO = analyticsDAO;
    }

    public static AnalyticsService getInstance() {
        if (instance == null) {
            synchronized (AnalyticsService.class) {
                if (instance == null) {
                    instance = new AnalyticsService();
                }
            }
        }
        return instance;
    }


    public AnalyticsSummary getSummary() {
        AnalyticsSummary summary = new AnalyticsSummary();
        summary.setTotalPatients(analyticsDAO.countPatients());
        summary.setTotalAppointments(analyticsDAO.countAppointments());
        summary.setScheduledAppointments(analyticsDAO.countAppointmentsByStatus("SCHEDULED"));
        summary.setCompletedAppointments(analyticsDAO.countAppointmentsByStatus("COMPLETED"));
        summary.setCancelledAppointments(analyticsDAO.countAppointmentsByStatus("CANCELLED"));
        summary.setTotalRevenue(analyticsDAO.sumRevenue());
        return summary;
    }
}