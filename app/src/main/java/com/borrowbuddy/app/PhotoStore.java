package com.borrowbuddy.app;

import android.net.Uri;

import java.util.ArrayList;

public class PhotoStore {

    private static final ArrayList<Uri> photos =
            new ArrayList<>();


    // =========================
    // ADD PHOTO
    // =========================

    public static void addPhoto(Uri uri) {

        if (uri != null) {
            photos.add(uri);
        }
    }


    // =========================
    // GET ALL PHOTOS
    // =========================

    public static ArrayList<Uri> getPhotos() {

        return photos;
    }


    // =========================
    // REMOVE PHOTO
    // =========================

    public static void removePhoto(Uri uri) {

        photos.remove(uri);
    }


    // =========================
    // CLEAR ALL
    // =========================

    public static void clearPhotos() {

        photos.clear();
    }
}