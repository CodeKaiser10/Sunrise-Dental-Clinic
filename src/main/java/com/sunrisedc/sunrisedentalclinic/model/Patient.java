package com.sunrisedc.sunrisedentalclinic.model;

public class Patient {

    private int patientId;
    private String name;
    private String address;
    private String contactNumber;
    private String dateOfBirth;
    private String gender;

    //no-arg constructor
    public Patient() {}

    //Full constructor
    public Patient(int patientId, String name, String address, String contactNumber,  String dateOfBirth, String gender) {
        this.patientId = patientId;
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
