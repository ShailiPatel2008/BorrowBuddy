package com.borrowbuddy.app;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListingRequestDetailsActivity extends AppCompatActivity {

    LinearLayout mainLayout;
    LinearLayout photoContainer;

    TextView txtItemName;
    TextView txtOwner;
    TextView txtCategory;
    TextView txtDescription;
    TextView txtPrice;
    TextView txtDeposit;
    TextView txtPickup;
    TextView txtAvailableFrom;
    TextView txtContact;
    TextView txtCondition;

    TextView lblDescription;
    TextView lblPrice;
    TextView lblDeposit;
    TextView lblPickup;
    TextView lblAvailableFrom;
    TextView lblContact;
    TextView lblCondition;

    TextView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_request_details);

        mainLayout = findViewById(R.id.mainLayout);
        photoContainer = findViewById(R.id.photoContainer);

        txtItemName = findViewById(R.id.txtItemName);
        txtOwner = findViewById(R.id.txtOwner);
        txtCategory = findViewById(R.id.txtCategory);
        txtDescription = findViewById(R.id.txtDescription);
        txtPrice = findViewById(R.id.txtPrice);
        txtDeposit = findViewById(R.id.txtDeposit);
        txtPickup = findViewById(R.id.txtPickup);
        txtAvailableFrom = findViewById(R.id.txtAvailableFrom);
        txtContact = findViewById(R.id.txtContact);
        txtCondition = findViewById(R.id.txtCondition);

        lblDescription = findViewById(R.id.lblDescription);
        lblPrice = findViewById(R.id.lblPrice);
        lblDeposit = findViewById(R.id.lblDeposit);
        lblPickup = findViewById(R.id.lblPickup);
        lblAvailableFrom = findViewById(R.id.lblAvailableFrom);
        lblContact = findViewById(R.id.lblContact);
        lblCondition = findViewById(R.id.lblCondition);

        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> finish());

        // -----------------------------
        // GET DATA
        // -----------------------------

        String itemName = getIntent().getStringExtra("itemName");
        String owner = getIntent().getStringExtra("ownerName");
        String category = getIntent().getStringExtra("category");
        String description = getIntent().getStringExtra("description");
        String price = getIntent().getStringExtra("price");
        String deposit = getIntent().getStringExtra("deposit");
        String pickup = getIntent().getStringExtra("pickup");
        String availableFrom = getIntent().getStringExtra("availableFrom");
        String contact = getIntent().getStringExtra("contact");
        String condition = getIntent().getStringExtra("condition");

        if (itemName != null)
            txtItemName.setText(itemName);

        if (owner != null)
            txtOwner.setText("Owner: " + owner);

        if (category != null)
            txtCategory.setText("Category: " + category);

        if (description != null)
            txtDescription.setText(description);

        if (price != null)
            txtPrice.setText("₹ " + price.replace("₹", ""));

        if (deposit != null)
            txtDeposit.setText(deposit);

        if (pickup != null)
            txtPickup.setText(pickup);

        if (availableFrom != null)
            txtAvailableFrom.setText(availableFrom);

        if (contact != null)
            txtContact.setText(contact);

        if (condition != null)
            txtCondition.setText(condition);

        // Show maximum 5 photos
        showPhotos();

        applyDarkMode();
    }

    @Override
    protected void onResume() {
        super.onResume();
        applyDarkMode();
    }

    // =====================================================
    // SHOW MAXIMUM 5 PHOTOS IN BOX
    // =====================================================

    private void showPhotos() {

        photoContainer.removeAllViews();

        ArrayList<String> photoUris =
                getIntent().getStringArrayListExtra("photoUris");

        if (photoUris != null && !photoUris.isEmpty()) {

            int maxPhotos = Math.min(photoUris.size(), 5);

            for (int i = 0; i < maxPhotos; i++) {

                addPhotoBox(photoUris.get(i), i + 1);
            }

        } else {

            // Temporary camera placeholder
            addPhotoBox(null, 1);
        }
    }

    // =====================================================
    // CREATE PHOTO BOX
    // =====================================================

    private void addPhotoBox(String uriString, int photoNumber) {

        // Outer box
        FrameLayout photoBox = new FrameLayout(this);

        LinearLayout.LayoutParams boxParams =
                new LinearLayout.LayoutParams(
                        dp(300),
                        dp(300)
                );

        boxParams.setMargins(
                dp(6),
                dp(10),
                dp(6),
                dp(10)
        );

        photoBox.setLayoutParams(boxParams);

        // Box background
        photoBox.setBackgroundResource(R.drawable.photo_box);

        // Image
        ImageView imageView = new ImageView(this);

        FrameLayout.LayoutParams imageParams =
                new FrameLayout.LayoutParams(
                        dp(270),
                        dp(270)
                );

        imageParams.gravity = Gravity.CENTER;

        imageView.setLayoutParams(imageParams);

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        // Real photo
        if (uriString != null && !uriString.isEmpty()) {

            try {

                imageView.setImageURI(
                        Uri.parse(uriString)
                );

            } catch (Exception e) {

                imageView.setImageResource(
                        android.R.drawable.ic_menu_camera
                );
            }

        } else {

            // Temporary placeholder
            imageView.setImageResource(
                    android.R.drawable.ic_menu_camera
            );
        }

        photoBox.addView(imageView);

        photoContainer.addView(photoBox);
    }

    // =====================================================
    // DARK MODE
    // =====================================================

    private void applyDarkMode() {

        boolean darkMode = getSharedPreferences(
                "Settings",
                MODE_PRIVATE
        ).getBoolean("dark_mode", false);

        if (darkMode) {

            mainLayout.setBackgroundColor(
                    Color.rgb(16, 16, 16)
            );

            setTextColor(Color.WHITE);

            setLabelColor(Color.rgb(186, 150, 230));

        } else {

            mainLayout.setBackgroundColor(
                    Color.rgb(246, 243, 248)
            );

            setTextColor(Color.BLACK);

            setLabelColor(Color.rgb(126, 87, 194));
        }

        // Toolbar back arrow always white
        btnBack.setTextColor(Color.WHITE);
    }

    private void setTextColor(int color) {

        txtItemName.setTextColor(color);
        txtOwner.setTextColor(color);
        txtCategory.setTextColor(color);
        txtDescription.setTextColor(color);
        txtPrice.setTextColor(color);
        txtDeposit.setTextColor(color);
        txtPickup.setTextColor(color);
        txtAvailableFrom.setTextColor(color);
        txtContact.setTextColor(color);
        txtCondition.setTextColor(color);
    }

    private void setLabelColor(int color) {

        lblDescription.setTextColor(color);
        lblPrice.setTextColor(color);
        lblDeposit.setTextColor(color);
        lblPickup.setTextColor(color);
        lblAvailableFrom.setTextColor(color);
        lblContact.setTextColor(color);
        lblCondition.setTextColor(color);
    }

    private int dp(int value) {

        return (int) (
                value *
                        getResources()
                                .getDisplayMetrics()
                                .density
        );
    }
}