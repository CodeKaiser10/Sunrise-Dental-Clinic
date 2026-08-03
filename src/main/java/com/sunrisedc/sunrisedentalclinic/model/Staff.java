package com.sunrisedc.sunrisedentalclinic.model;

// Base class for all staff users.
public abstract class Staff {

    private int id;
    private String username;
    private String passwordHash;
    private String fullName;
    private String email;
    private String phone;
    private final Role role;

    // Default constructor
    protected Staff(Role role) {
        this.role = role;
    }

    // Constructor to set all staff details.
    protected Staff(int id, String username, String passwordHash, String fullName, String email, String phone, Role role) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    // Returns the home page for each role after login.
    public abstract String getDashboard();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }
}
