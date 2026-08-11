package com.sunrisedc.sunrisedentalclinic.model;

public class Dentist extends Staff{

    private String specialization;

    public Dentist(){
        super(Role.DENTIST);
    }

    public Dentist(int id, String username, String passwordHash, String fullName, String email, String phone, String specialization) {
        super(id, username, passwordHash, fullName, email, phone, Role.DENTIST);
        this.specialization = specialization;
    }

    @Override
    public String getDashboard() {
        return "/dentist/Ddashboard";
    }

    public String getSpecializationDetails() {
        return specialization; }

    public void setSpecialization(String specialization) {
        this.specialization = specialization; }
}
