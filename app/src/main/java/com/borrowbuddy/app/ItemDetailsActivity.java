package com.borrowbuddy.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class ItemDetailsActivity extends AppCompatActivity {

    // ================= TOOLBAR =================

    MaterialToolbar toolbar;


    TextView itemName;
    TextView itemOwner;
    TextView itemCategory;
    TextView itemPrice;
    TextView itemCity;
    TextView itemRating;
    TextView itemDescription;
    TextView itemLocation;

    TextView descriptionTitle;
    TextView locationTitle;

    TextView dot1;
    TextView dot2;
    TextView dot3;
    TextView dot4;
    TextView dot5;

    Button btnRequestBorrow;

    View itemDetailsLayout;

    MaterialCardView itemImageCard;
    MaterialCardView detailsCard;

    ViewPager2 imageViewPager;

    private static final int PURPLE =
            Color.rgb(106, 27, 154);

    private static final int DARK_CARD =
            Color.rgb(43, 41, 50);


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_item_details);


        // ================= TOOLBAR =================

        toolbar =
                findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {

            getSupportActionBar().setTitle(
                    "Item Details"
            );

            getSupportActionBar()
                    .setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(
                v -> finish()
        );


        // ================= STATUS BAR =================

        Window window = getWindow();

        window.setStatusBarColor(PURPLE);

        window.getDecorView().setSystemUiVisibility(0);


        // ================= FIND VIEWS =================

        itemDetailsLayout =
                findViewById(R.id.itemDetailsLayout);

        itemImageCard =
                findViewById(R.id.itemImageCard);

        detailsCard =
                findViewById(R.id.detailsCard);

        imageViewPager =
                findViewById(R.id.imageViewPager);


        itemName =
                findViewById(R.id.itemName);

        itemOwner =
                findViewById(R.id.itemOwner);

        itemCategory =
                findViewById(R.id.itemCategory);

        itemPrice =
                findViewById(R.id.itemPrice);

        itemCity =
                findViewById(R.id.itemCity);

        itemRating =
                findViewById(R.id.itemRating);

        itemDescription =
                findViewById(R.id.itemDescription);

        itemLocation =
                findViewById(R.id.itemLocation);

        descriptionTitle =
                findViewById(R.id.descriptionTitle);

        locationTitle =
                findViewById(R.id.locationTitle);

        btnRequestBorrow =
                findViewById(R.id.btnRequestBorrow);

        dot1 =
                findViewById(R.id.dot1);

        dot2 =
                findViewById(R.id.dot2);

        dot3 =
                findViewById(R.id.dot3);

        dot4 =
                findViewById(R.id.dot4);

        dot5 =
                findViewById(R.id.dot5);


        // ================= GET DATA =================
        // HomeActivity sends "itemName"

        String name =
                getIntent().getStringExtra("itemName");

        String price =
                getIntent().getStringExtra("price");

        String location =
                getIntent().getStringExtra("location");

        String rating =
                getIntent().getStringExtra("rating");

        String category =
                getIntent().getStringExtra("category");


        // ================= ITEM NAME =================

        if (name != null && !name.isEmpty()) {

            itemName.setText(name);

        } else {

            itemName.setText("Item");
        }


        // ================= OWNER =================

        if ("Camera".equalsIgnoreCase(name) ||
                "Canon Camera".equalsIgnoreCase(name)) {

            itemOwner.setText(
                    "Owner: Rahul Patel"
            );

        } else if ("Laptop".equalsIgnoreCase(name)) {

            itemOwner.setText(
                    "Owner: Amit Shah"
            );

        } else if ("Bicycle".equalsIgnoreCase(name)) {

            itemOwner.setText(
                    "Owner: Karan Patel"
            );

        } else if ("Gaming Console".equalsIgnoreCase(name)) {

            itemOwner.setText(
                    "Owner: Neha Patel"
            );

        } else if (name != null && !name.isEmpty()) {

            itemOwner.setText(
                    "Owner: Neha Patel"
            );

        } else {

            itemOwner.setText(
                    "Owner: Not Available"
            );
        }


        // ================= CATEGORY =================

        if (category != null &&
                !category.isEmpty()) {

            itemCategory.setText(
                    "Category: " + category
            );

        } else if ("Bicycle".equalsIgnoreCase(name)) {

            itemCategory.setText(
                    "Category: Sports"
            );

        } else if ("Gaming Console".equalsIgnoreCase(name)) {

            itemCategory.setText(
                    "Category: Electronics"
            );

        } else if ("Laptop".equalsIgnoreCase(name)) {

            itemCategory.setText(
                    "Category: Electronics"
            );

        } else if ("Camera".equalsIgnoreCase(name) ||
                "Canon Camera".equalsIgnoreCase(name)) {

            itemCategory.setText(
                    "Category: Electronics"
            );

        } else {

            itemCategory.setText(
                    "Category: General"
            );
        }


        // ================= PRICE =================

        if (price != null &&
                !price.isEmpty()) {

            itemPrice.setText(
                    "Price: " + price
            );

        } else if ("Bicycle".equalsIgnoreCase(name)) {

            itemPrice.setText(
                    "Price: ₹100/day"
            );

        } else if ("Gaming Console".equalsIgnoreCase(name)) {

            itemPrice.setText(
                    "Price: ₹500/day"
            );

        } else if ("Laptop".equalsIgnoreCase(name)) {

            itemPrice.setText(
                    "Price: ₹300/day"
            );

        } else if ("Camera".equalsIgnoreCase(name) ||
                "Canon Camera".equalsIgnoreCase(name)) {

            itemPrice.setText(
                    "Price: ₹300/day"
            );

        } else {

            itemPrice.setText(
                    "Price: ₹150/day"
            );
        }


        // ================= LOCATION =================

        if (location != null &&
                !location.isEmpty()) {

            itemCity.setText(location);

            itemLocation.setText(
                    location + ", Gujarat"
            );

        } else if ("Bicycle".equalsIgnoreCase(name)) {

            itemCity.setText("Ahmedabad");

            itemLocation.setText(
                    "Ahmedabad, Gujarat"
            );

        } else if ("Gaming Console".equalsIgnoreCase(name)) {

            itemCity.setText("Surat");

            itemLocation.setText(
                    "Surat, Gujarat"
            );

        } else if ("Laptop".equalsIgnoreCase(name)) {

            itemCity.setText("Vadodara");

            itemLocation.setText(
                    "Vadodara, Gujarat"
            );

        } else {

            itemCity.setText("Ahmedabad");

            itemLocation.setText(
                    "Ahmedabad, Gujarat"
            );
        }


        // ================= RATING =================

        if (rating != null &&
                !rating.isEmpty()) {

            itemRating.setText(rating);

        } else if ("Bicycle".equalsIgnoreCase(name)) {

            itemRating.setText("★★★★★");

        } else if ("Gaming Console".equalsIgnoreCase(name)) {

            itemRating.setText("★★★★☆");

        } else if ("Laptop".equalsIgnoreCase(name)) {

            itemRating.setText("★★★★☆");

        } else {

            itemRating.setText("★★★★☆");
        }


        // ================= DESCRIPTION =================

        if ("Bicycle".equalsIgnoreCase(name)) {

            itemDescription.setText(
                    "Good quality bicycle available " +
                            "for borrowing. It is suitable " +
                            "for daily rides and outdoor use. " +
                            "Please handle it carefully and " +
                            "return it on time."
            );

        } else if ("Gaming Console".equalsIgnoreCase(name)) {

            itemDescription.setText(
                    "Gaming console available for " +
                            "borrowing. Please use it carefully " +
                            "and return it on time."
            );

        } else if ("Laptop".equalsIgnoreCase(name)) {

            itemDescription.setText(
                    "Good quality laptop available for " +
                            "borrowing. Please handle it " +
                            "carefully and return it on time."
            );

        } else if ("Camera".equalsIgnoreCase(name) ||
                "Canon Camera".equalsIgnoreCase(name)) {

            itemDescription.setText(
                    "Good quality camera available for " +
                            "borrowing. Please handle it " +
                            "carefully and return it on time."
            );

        } else {

            itemDescription.setText(
                    "Good quality item available for " +
                            "borrowing. Please handle the item " +
                            "carefully and return it on time."
            );
        }


        // ================= 5 IMAGES =================

        List<Integer> images =
                new ArrayList<>();

        /*
         * Existing image system is kept unchanged.
         * Firebase images can be connected here later.
         */

        images.add(
                R.drawable.ic_launcher_foreground
        );

        images.add(
                R.drawable.ic_launcher_foreground
        );

        images.add(
                R.drawable.ic_launcher_foreground
        );

        images.add(
                R.drawable.ic_launcher_foreground
        );

        images.add(
                R.drawable.ic_launcher_foreground
        );


        ItemImageAdapter adapter =
                new ItemImageAdapter(images);

        imageViewPager.setAdapter(adapter);


        // ================= IMAGE SWIPE =================

        imageViewPager.registerOnPageChangeCallback(
                new ViewPager2.OnPageChangeCallback() {

                    @Override
                    public void onPageSelected(
                            int position) {

                        super.onPageSelected(position);

                        showDot(position);
                    }
                }
        );


        showDot(0);


        // ================= DARK MODE =================

        applyDarkMode();


        // ================= REQUEST BORROW =================

        btnRequestBorrow.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            ItemDetailsActivity.this,
                            BookingActivity.class
                    );

            intent.putExtra(
                    "name",
                    itemName.getText().toString()
            );

            intent.putExtra(
                    "price",
                    itemPrice.getText().toString()
            );

            intent.putExtra(
                    "location",
                    itemLocation.getText().toString()
            );

            intent.putExtra(
                    "rating",
                    itemRating.getText().toString()
            );

            intent.putExtra(
                    "category",
                    itemCategory.getText().toString()
            );

            startActivity(intent);
        });
    }


    // ==================================================
    // 5 DOTS
    // ==================================================

    private void showDot(int position) {

        dot1.setTextColor(Color.GRAY);
        dot2.setTextColor(Color.GRAY);
        dot3.setTextColor(Color.GRAY);
        dot4.setTextColor(Color.GRAY);
        dot5.setTextColor(Color.GRAY);


        if (position == 0) {

            dot1.setTextColor(PURPLE);

        } else if (position == 1) {

            dot2.setTextColor(PURPLE);

        } else if (position == 2) {

            dot3.setTextColor(PURPLE);

        } else if (position == 3) {

            dot4.setTextColor(PURPLE);

        } else if (position == 4) {

            dot5.setTextColor(PURPLE);
        }
    }


    // ==================================================
    // DARK MODE
    // ==================================================

    private void applyDarkMode() {

        SharedPreferences preferences =
                getSharedPreferences(
                        "BorrowBuddy",
                        MODE_PRIVATE
                );


        boolean darkMode =
                preferences.getBoolean(
                        "darkMode",
                        false
                );


        // ================= STATUS BAR =================

        getWindow().setStatusBarColor(PURPLE);

        getWindow()
                .getDecorView()
                .setSystemUiVisibility(0);


        // ================= HEADER =================

        toolbar.setBackgroundColor(PURPLE);

        toolbar.setTitleTextColor(
                Color.WHITE
        );


        // ================= DARK / LIGHT =================

        if (darkMode) {

            itemDetailsLayout.setBackgroundColor(
                    Color.BLACK
            );

            itemImageCard.setCardBackgroundColor(
                    DARK_CARD
            );

            detailsCard.setCardBackgroundColor(
                    DARK_CARD
            );

            setMainTextColor(
                    Color.WHITE
            );

        } else {

            itemDetailsLayout.setBackgroundColor(
                    Color.rgb(248, 249, 250)
            );

            itemImageCard.setCardBackgroundColor(
                    Color.WHITE
            );

            detailsCard.setCardBackgroundColor(
                    Color.WHITE
            );

            setMainTextColor(
                    Color.BLACK
            );
        }


        // ================= RATING =================

        itemRating.setTextColor(
                Color.rgb(255, 193, 7)
        );


        // ================= REQUEST BUTTON =================

        btnRequestBorrow.setBackgroundTintList(
                ContextCompat.getColorStateList(
                        this,
                        android.R.color.transparent
                )
        );

        btnRequestBorrow.setBackgroundColor(
                PURPLE
        );

        btnRequestBorrow.setTextColor(
                Color.WHITE
        );


        // ================= DOT =================

        showDot(
                imageViewPager.getCurrentItem()
        );
    }


    // ==================================================
    // TEXT COLOR
    // ==================================================

    private void setMainTextColor(int color) {

        itemName.setTextColor(color);

        itemOwner.setTextColor(color);

        itemCategory.setTextColor(color);

        itemPrice.setTextColor(color);

        itemCity.setTextColor(color);

        itemDescription.setTextColor(color);

        itemLocation.setTextColor(color);

        descriptionTitle.setTextColor(color);

        locationTitle.setTextColor(color);
    }


    // ==================================================
    // RESUME
    // ==================================================

    @Override
    protected void onResume() {

        super.onResume();

        applyDarkMode();
    }
}