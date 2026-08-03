package com.borrowbuddy.app.models;

public class ListingModel {

    private String itemName;
    private String category;
    private String description;
    private String price;
    private String location;
    private String mobileNumber;
    private String imageUrl;
    private String status;

    private String securityDeposit;

    // Empty Constructor
    public ListingModel() {
    }

    // Parameterized Constructor
    public ListingModel(String itemName, String category, String description,
                        String price, String location, String mobileNumber,
                        String imageUrl, String status) {

        this.itemName = itemName;
        this.category = category;
        this.description = description;
        this.price = price;
        this.location = location;
        this.mobileNumber = mobileNumber;
        this.imageUrl = imageUrl;
        this.status = status;
    }

    // Getters
    public String getItemName() {
        return itemName;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getLocation() {
        return location;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSecurityDeposit() {
        return securityDeposit;
    }

    public void setSecurityDeposit(String securityDeposit) {
        this.securityDeposit = securityDeposit;
    }
}