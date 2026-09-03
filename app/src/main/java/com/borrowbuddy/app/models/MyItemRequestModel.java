package com.borrowbuddy.app.models;

public class MyItemRequestModel {

    private String itemName;
    private String category;
    private String price;
    private String status;
    private String reason;
    private int quantity;

    public MyItemRequestModel() {
    }

    public MyItemRequestModel(
            String itemName,
            String category,
            String price,
            int quantity,
            String status,
            String reason
    ) {
        this.itemName = itemName;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.reason = reason;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}