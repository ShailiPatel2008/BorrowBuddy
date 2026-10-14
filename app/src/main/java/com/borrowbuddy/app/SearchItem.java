package com.borrowbuddy.app;



public class SearchItem {

    String id;
    String name;
    String price;
    String location;
    String rating;
    int image;

    public SearchItem(
            String id,
            String name,
            String price,
            String location,
            String rating,
            int image) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.location = location;
        this.rating = rating;
        this.image = image;
    }
}