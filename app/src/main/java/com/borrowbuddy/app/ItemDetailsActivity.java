package com.borrowbuddy.app;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ItemDetailsActivity extends AppCompatActivity {

    LinearLayout mainLayout;
    LinearLayout photoContainer;

    TextView txtItemName;
    TextView txtOwner;
    TextView txtCategory;
    TextView txtPickup;
    TextView txtPrice;
    TextView txtDeposit;
    TextView txtCondition;
    TextView txtDescription;

    TextView lblCategory;
    TextView lblPickup;
    TextView lblPrice;
    TextView lblDeposit;
    TextView lblCondition;
    TextView lblDescription;

    TextView btnBack;

    final int PURPLE = Color.rgb(171, 71, 188);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_item_details);

        mainLayout = findViewById(R.id.mainLayout);
        photoContainer = findViewById(R.id.photoContainer);

        txtItemName = findViewById(R.id.txtItemName);
        txtOwner = findViewById(R.id.txtOwner);
        txtCategory = findViewById(R.id.txtCategory);
        txtPickup = findViewById(R.id.txtPickup);
        txtPrice = findViewById(R.id.txtPrice);
        txtDeposit = findViewById(R.id.txtDeposit);
        txtCondition = findViewById(R.id.txtCondition);
        txtDescription = findViewById(R.id.txtDescription);

        lblCategory = findViewById(R.id.lblCategory);
        lblPickup = findViewById(R.id.lblPickup);
        lblPrice = findViewById(R.id.lblPrice);
        lblDeposit = findViewById(R.id.lblDeposit);
        lblCondition = findViewById(R.id.lblCondition);
        lblDescription = findViewById(R.id.lblDescription);

        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> finish());

        String itemName = getIntent().getStringExtra("itemName");
        String ownerName = getIntent().getStringExtra("ownerName");
        String category = getIntent().getStringExtra("category");
        String pickup = getIntent().getStringExtra("pickup");
        String price = getIntent().getStringExtra("price");
        String deposit = getIntent().getStringExtra("deposit");
        String description = getIntent().getStringExtra("description");
        String condition = getIntent().getStringExtra("condition");

        if (itemName == null || itemName.isEmpty())
            itemName = "Canon Camera";

        if (ownerName == null || ownerName.isEmpty())
            ownerName = "Rahul";

        if (category == null || category.isEmpty())
            category = "Electronics";

        if (pickup == null || pickup.isEmpty())
            pickup = "Hazira, Surat";

        if (price == null || price.isEmpty())
            price = "₹300/day";

        if (deposit == null || deposit.isEmpty())
            deposit = "₹1,000";

        if (description == null || description.isEmpty())
            description = "Good quality item available for borrowing/renting.";

        if (condition == null || condition.isEmpty())
            condition = "Good";

        txtItemName.setText(itemName);
        txtOwner.setText("Owner: " + ownerName);
        txtCategory.setText(category);
        txtPickup.setText(pickup);
        txtPrice.setText(price);
        txtDeposit.setText(deposit);
        txtCondition.setText(condition);
        txtDescription.setText(description);

        showPhotos();

        applyDarkMode();
    }

    @Override
    protected void onResume() {
        super.onResume();

        applyDarkMode();
    }

    // =====================================================
    // SHOW PHOTOS
    // =====================================================

    private void showPhotos() {

        photoContainer.removeAllViews();

        ArrayList<String> photoUris =
                getIntent().getStringArrayListExtra("photoUris");

        if (photoUris != null && !photoUris.isEmpty()) {

            int maxPhotos = Math.min(photoUris.size(), 5);

            for (int i = 0; i < maxPhotos; i++) {

                ImageView imageView =
                        new ImageView(this);

                LinearLayout.LayoutParams params =
                        new LinearLayout.LayoutParams(
                                dp(280),
                                dp(220)
                        );

                params.setMargins(
                        dp(6),
                        dp(6),
                        dp(6),
                        dp(6)
                );

                imageView.setLayoutParams(params);

                imageView.setScaleType(
                        ImageView.ScaleType.CENTER_CROP
                );

                GradientDrawable box =
                        new GradientDrawable();

                box.setShape(
                        GradientDrawable.RECTANGLE
                );

                box.setColor(
                        Color.rgb(95, 95, 95)
                );

                box.setCornerRadius(
                        dp(10)
                );

                imageView.setBackground(box);

                try {

                    imageView.setImageURI(
                            Uri.parse(photoUris.get(i))
                    );

                } catch (Exception e) {

                    imageView.setImageResource(
                            android.R.drawable.ic_menu_camera
                    );
                }

                photoContainer.addView(imageView);
            }

        } else {

            ImageView imageView =
                    new ImageView(this);

            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(
                            dp(280),
                            dp(220)
                    );

            params.setMargins(
                    dp(6),
                    dp(6),
                    dp(6),
                    dp(6)
            );

            imageView.setLayoutParams(params);

            imageView.setScaleType(
                    ImageView.ScaleType.CENTER
            );

            imageView.setImageResource(
                    android.R.drawable.ic_menu_camera
            );

            GradientDrawable box =
                    new GradientDrawable();

            box.setShape(
                    GradientDrawable.RECTANGLE
            );

            box.setColor(
                    Color.rgb(95, 95, 95)
            );

            box.setCornerRadius(
                    dp(10)
            );

            imageView.setBackground(box);

            photoContainer.addView(imageView);
        }
    }

    // =====================================================
    // DARK MODE
    // =====================================================

    private void applyDarkMode() {

        boolean darkMode =
                getSharedPreferences(
                        "Settings",
                        MODE_PRIVATE
                ).getBoolean(
                        "dark_mode",
                        false
                );

        if (darkMode) {

            mainLayout.setBackgroundColor(
                    Color.rgb(16, 16, 16)
            );

            setAllTextColor(Color.WHITE);

        } else {

            mainLayout.setBackgroundColor(
                    Color.rgb(246, 243, 248)
            );

            setAllTextColor(Color.BLACK);
        }

        // Toolbar back arrow always white
        if (btnBack != null) {
            btnBack.setTextColor(Color.WHITE);
        }
    }

    // =====================================================
    // ALL TEXT COLOR
    // =====================================================

    private void setAllTextColor(int color) {

        // Main item information
        if (txtItemName != null)
            txtItemName.setTextColor(color);

        if (txtOwner != null)
            txtOwner.setTextColor(color);

        if (txtCategory != null)
            txtCategory.setTextColor(color);

        if (txtPickup != null)
            txtPickup.setTextColor(color);

        if (txtPrice != null)
            txtPrice.setTextColor(color);

        if (txtDeposit != null)
            txtDeposit.setTextColor(color);

        if (txtCondition != null)
            txtCondition.setTextColor(color);

        if (txtDescription != null)
            txtDescription.setTextColor(color);

        // IMPORTANT:
        // Section TITLES
        if (lblCategory != null)
            lblCategory.setTextColor(color);

        if (lblPickup != null)
            lblPickup.setTextColor(color);

        if (lblPrice != null)
            lblPrice.setTextColor(color);

        if (lblDeposit != null)
            lblDeposit.setTextColor(color);

        if (lblCondition != null)
            lblCondition.setTextColor(color);

        // THIS FIXES DESCRIPTION TITLE
        if (lblDescription != null)
            lblDescription.setTextColor(color);
    }

    // =====================================================
    // DP
    // =====================================================

    private int dp(int value) {

        return (int) (
                value *
                        getResources()
                                .getDisplayMetrics()
                                .density
                        + 0.5f
        );
    }
}