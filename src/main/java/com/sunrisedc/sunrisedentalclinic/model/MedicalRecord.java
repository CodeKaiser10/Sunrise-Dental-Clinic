package com.sunrisedc.sunrisedentalclinic.model;

public class MedicalRecord {

    private int recordId;
    private int patientId;
    private String createdDate;
    private String notes;

    public MedicalRecord() {}

    public MedicalRecord(int recordId, int patientId, String createdDate, String notes) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.createdDate = createdDate;
        this.notes = notes;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
