package com.borrowbuddy.app;

public class UserModel {

    private String userid;
    private String fullname;
    private String email;
    private String phone;
    private String trustscore;
    private String status;

    public UserModel() {
        // Required for Firestore
    }

    public String getUserid() {
        return userid;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getTrustscore() {
        return trustscore;
    }

    public String getStatus() {
        return status;
    }
}