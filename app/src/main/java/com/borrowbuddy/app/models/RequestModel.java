package com.borrowbuddy.app.models;

import java.util.List;

public class RequestModel {

    private String itemName;
    private String customerName;
    private String customerMobile;
    private String rentalPeriod;
    private String rent;
    private String status;
    private String itemStatus;
    private List<String> imageUrls;

    private int quantity;


    public RequestModel() {
        // Required empty constructor for Firebase later
    }


    public RequestModel(String itemName,
                        String customerName,
                        String customerMobile,
                        String rentalPeriod,
                        String rent,
                        int quantity,
                        String status,
                        String itemStatus,
                        List<String> imageUrls) {

        this.itemName = itemName;
        this.customerName = customerName;
        this.customerMobile = customerMobile;
        this.rentalPeriod = rentalPeriod;
        this.rent = rent;
        this.quantity = quantity;
        this.status = status;
        this.itemStatus = itemStatus;
        this.imageUrls = imageUrls;

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


    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }
}