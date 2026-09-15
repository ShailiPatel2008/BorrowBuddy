package com.borrowbuddy.app;

public class UserModel {

    private String userid;
    private String full_name;
    private String email;
    private String phone;
    private double rate;
    private boolean is_active;

    public UserModel() {
        // Required by Firestore
    }

    // -------------------------
    // Getters
    // -------------------------

    public String getUserid() {
        return userid;
    }

    public String getFullname() {
        return full_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getTrustscore() {
        return String.valueOf(rate);
    }

    public String getStatus() {
        return is_active ? "Active" : "Blocked";
    }

    // -------------------------
    // Setters
    // -------------------------

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public void setStatus(String status) {
        this.is_active = status.equalsIgnoreCase("Active");
    }
}