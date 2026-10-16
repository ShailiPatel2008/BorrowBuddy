package com.borrowbuddy.app.models;

public class NotificationModel {

    private String notificationId;
    private String userId;
    private String title;
    private String message;
    private String timestamp;
    private boolean isRead;

    // Empty constructor (Required for Firebase)
    public NotificationModel() {
    }

    public NotificationModel(String notificationId,
                             String userId,
                             String title,
                             String message,
                             String timestamp,
                             boolean isRead) {

        this.notificationId = notificationId;
        this.userId = userId;
        this.title = title;
        this.message = message;
        this.timestamp = timestamp;
        this.isRead = isRead;
    }
    public NotificationModel(String title,
                             String message,
                             String timestamp) {

        this.title = title;
        this.message = message;
        this.timestamp = timestamp;
    }
    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}