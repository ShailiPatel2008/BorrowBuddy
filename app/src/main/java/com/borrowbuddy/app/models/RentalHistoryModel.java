package com.borrowbuddy.app.models;

public class RentalHistoryModel {

    private String itemName;
    private String borrowerName;
    private String rentalPeriod;
    private String totalEarned;
    private String status;
    private float rating;
    private String imageUrl;

    private String ownerReview;

    public RentalHistoryModel() {
    }

    public RentalHistoryModel(String itemName,
                              String borrowerName,
                              String rentalPeriod,
                              String totalEarned,
                              String status,
                              float rating,
                              String imageUrl) {

        this.itemName = itemName;
        this.borrowerName = borrowerName;
        this.rentalPeriod = rentalPeriod;
        this.totalEarned = totalEarned;
        this.status = status;
        this.rating = rating;
        this.imageUrl = imageUrl;
    }

    public String getItemName() {
        return itemName;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public String getRentalPeriod() {
        return rentalPeriod;
    }

    public String getTotalEarned() {
        return totalEarned;
    }

    public String getStatus() {
        return status;
    }

    public float getRating() {
        return rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getOwnerReview() {
        return ownerReview;
    }
}