package com.borrowbuddy.app.models;

public class EarningModel {

    private String earningId;
    private String ownerId;
    private String itemName;
    private String rent;
    private String paymentStatus;
    private String bookingDate;

    public EarningModel() {
    }

    public EarningModel(String earningId, String ownerId,
                        String itemName, String rent,
                        String paymentStatus, String bookingDate) {

        this.earningId = earningId;
        this.ownerId = ownerId;
        this.itemName = itemName;
        this.rent = rent;
        this.paymentStatus = paymentStatus;
        this.bookingDate = bookingDate;
    }

    public String getEarningId() { return earningId; }
    public void setEarningId(String earningId) { this.earningId = earningId; }

    public String getOwnerId() { return ownerId; }
    public void setOwnerId(String ownerId) { this.ownerId = ownerId; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public String getRent() { return rent; }
    public void setRent(String rent) { this.rent = rent; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public String getBookingDate() { return bookingDate; }
    public void setBookingDate(String bookingDate) { this.bookingDate = bookingDate; }
}