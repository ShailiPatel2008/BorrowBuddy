package com.borrowbuddy.app.models;

public class RequestModel {

    private String itemName;
    private String customerName;
    private String customerMobile;
    private String rentalPeriod;
    private String rent;
    private String status;
    private String imageUrl;


    public RequestModel() {
        // Required empty constructor for Firebase later
    }


    public RequestModel(String itemName,
                        String customerName,
                        String customerMobile,
                        String rentalPeriod,
                        String rent,
                        String status,
                        String imageUrl) {

        this.itemName = itemName;
        this.customerName = customerName;
        this.customerMobile = customerMobile;
        this.rentalPeriod = rentalPeriod;
        this.rent = rent;
        this.status = status;
        this.imageUrl = imageUrl;

    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }


    public String getRentalPeriod() {
        return rentalPeriod;
    }

    public void setRentalPeriod(String rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }


    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}