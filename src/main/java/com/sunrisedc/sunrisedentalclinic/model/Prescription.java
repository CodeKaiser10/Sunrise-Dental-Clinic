package com.sunrisedc.sunrisedentalclinic.model;

public class Prescription {

    private int prescriptionId;
    private int patientId;
    private int dentistId;
    private String date;
    private String medication;
    private String dosage;
    private String notes;

    public Prescription() {}

     public Prescription(int prescriptionId, int patientId, int dentistId, String date, String medication, String dosage, String notes) {
        this.prescriptionId = prescriptionId;
        this.patientId = patientId;
        this.dentistId = dentistId;
        this.date = date;
        this.medication = medication;
        this.dosage = dosage;
        this.notes = notes;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDentistId() {
        return dentistId;
    }

    public void setDentistId(int dentistId) {
        this.dentistId = dentistId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
