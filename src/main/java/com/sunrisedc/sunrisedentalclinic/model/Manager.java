package com.sunrisedc.sunrisedentalclinic.model;

public class Manager extends Staff{

    public Manager(){
        super(Role.MANAGER);
    }

    public Manager(int id, String username, String passwordHash, String fullName, String email, String phone) {
        super(id, username, passwordHash, fullName, email, phone, Role.MANAGER);
    }

    @Override
    public String getDashboard() {
        return "Manager";
    }
}
