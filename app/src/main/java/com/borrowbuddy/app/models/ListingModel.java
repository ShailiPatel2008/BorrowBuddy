package com.borrowbuddy.app.models;

import java.util.ArrayList;
import java.util.List;

public class ListingModel {

    private String itemName;
    private String category;
    private String description;
    private String price;
    private String location;
    private String mobileNumber;
    private String securityDeposit;
    private String status;

    // Quantity
    private int quantity;

    // Multiple item images
    private List<String> imageUrls = new ArrayList<>();

    public ListingModel() {
        // Required empty constructor
    }

    public ListingModel(
            String itemName,
            String category,
            String description,
            String price,
            String location,
            String mobileNumber,
            String securityDeposit,
            String status,
            int quantity
    ) {

        this.itemName = itemName;
        this.category = category;
        this.description = description;
        this.price = price;
        this.location = location;
        this.mobileNumber = mobileNumber;
        this.securityDeposit = securityDeposit;
        this.status = status;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getSecurityDeposit() {
        return securityDeposit;
    }

    public void setSecurityDeposit(String securityDeposit) {
        this.securityDeposit = securityDeposit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Quantity getter
    public int getQuantity() {
        return quantity;
    }

    // Quantity setter
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Image URLs getter
    public List<String> getImageUrls() {
        return imageUrls;
    }

    // Image URLs setter
    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}