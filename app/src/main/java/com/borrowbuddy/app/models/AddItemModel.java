package com.borrowbuddy.app.models;

public class AddItemModel {

    private String itemId;
    private String ownerId;
    private String ownerName;
    private String itemName;
    private String category;
    private String description;
    private String price;
    private String deposit;
    private String location;
    private String availability;
    private String imageUrl;
    private String status;
    private String reason;

    public AddItemModel() {
    }

    public AddItemModel(String itemId, String ownerId, String ownerName,
                        String itemName, String category, String description,
                        String price, String deposit, String location,
                        String availability, String imageUrl,
                        String status, String reason) {

        this.itemId = itemId;
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.itemName = itemName;
        this.category = category;
        this.description = description;
        this.price = price;
        this.deposit = deposit;
        this.location = location;
        this.availability = availability;
        this.imageUrl = imageUrl;
        this.status = status;
        this.reason = reason;
    }

    public String getItemId() { return itemId; }
    public void setItemId(String itemId) { this.itemId = itemId; }

    public String getOwnerId() { return ownerId; }
    public void setOwnerId(String ownerId) { this.ownerId = ownerId; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }

    public String getDeposit() { return deposit; }
    public void setDeposit(String deposit) { this.deposit = deposit; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}