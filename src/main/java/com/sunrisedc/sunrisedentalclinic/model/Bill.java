package com.sunrisedc.sunrisedentalclinic.model;

public class Bill {

    private int billId;
    private int appointmentId;
    private Double consultationFee;
    private Double treatmentFee;
    private Double discount;
    private Double totalAmount;
    private String billDate;

    //No arg constructor
    public Bill () {}

    //full constructor

    public Bill(int billId, int appointmentId, Double consultationFee, Double treatmentFee, Double discount, Double totalAmount, String billDate) {
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

    public Double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(Double consultationFee) {
        this.consultationFee = consultationFee;
    }

    public Double getTreatmentFee() {
        return treatmentFee;
    }

    public void setTreatmentFee(Double treatmentFee) {
        this.treatmentFee = treatmentFee;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }
}
