package com.sunrisedc.sunrisedentalclinic.model;

//clinics static that shown on managers dashboard
public class AnalyticsSummary {

    private int totalPatients;
    private int totalAppointments;
    private int scheduledAppointments;
    private int completedAppointments;
    private int cancelledAppointments;
    private double totalRevenue;

    public AnalyticsSummary() {}

    public int getTotalPatients() {
        return totalPatients;
    }

    public void setTotalPatients(int totalPatients) {
        this.totalPatients = totalPatients;
    }

    public int getTotalAppointments() {
        return totalAppointments;
    }

    public void setTotalAppointments(int totalAppointments) {
        this.totalAppointments = totalAppointments;
    }

    public int getScheduledAppointments() {
        return scheduledAppointments;
    }

    public void setScheduledAppointments(int scheduledAppointments) {
        this.scheduledAppointments = scheduledAppointments;
    }

    public int getCompletedAppointments() {
        return completedAppointments;
    }

    public void setCompletedAppointments(int completedAppointments) {
        this.completedAppointments = completedAppointments;
    }

    public int getCancelledAppointments() {
        return cancelledAppointments;
    }

    public void setCancelledAppointments(int cancelledAppointments) {
        this.cancelledAppointments = cancelledAppointments;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
