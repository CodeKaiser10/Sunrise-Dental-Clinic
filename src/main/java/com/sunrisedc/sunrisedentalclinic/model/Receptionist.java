package com.sunrisedc.sunrisedentalclinic.model;

public class Receptionist extends Staff{

    public Receptionist(){
        super(Role.RECEPTIONIST);
    }

    public Receptionist(int id, String username, String passwordHash, String fullName, String email, String phone) {
        super(id, username, passwordHash, fullName, email, phone, Role.RECEPTIONIST);
    }

    @Override
    public String getDashboard() {
        return "/receptionist/Rdashboard";
    }
}
