package com.sunrisedc.sunrisedentalclinic.model;

public class Bill {

    private int billId;
    private int appointmentId;
    private double consultationFee;
    private double treatmentFee;
    private double discount;
    private double totalAmount;
    private String billDate;

    //No arg constructor
    public Bill () {}

    //full constructor

    public Bill(int billId, int appointmentId, double consultationFee, double treatmentFee, double discount, double totalAmount, String billDate) {
        this.billId = billId;
        this.appointmentId = appointmentId;
        this.consultationFee = consultationFee;
        this.treatmentFee = treatmentFee;
        this.discount = discount;
        this.totalAmount = totalAmount;
        this.billDate = billDate;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(int consultationFee) {
        this.consultationFee = consultationFee;
    }

    public double getTreatmentFee() {
        return treatmentFee;
    }

    public void setTreatmentFee(double treatmentFee) {
        this.treatmentFee = treatmentFee;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }
}
