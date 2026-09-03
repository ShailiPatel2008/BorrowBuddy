package com.borrowbuddy.app.activities;

import android.app.Dialog;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.borrowbuddy.app.R;
import com.borrowbuddy.app.adapters.RequestImageAdapter;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class RequestDetailsActivity extends AppCompatActivity {

    private ViewPager2 viewPagerImages;
    private TextView tvImageIndicator;
    private TextView tvItemName;
    private TextView tvCustomerName;
    private TextView tvCustomerMobile;
    private TextView tvQuantity;
    private TextView tvRentalPeriod;
    private TextView tvRent;
    private TextView tvStatus;
    private TextView tvItemStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_request_details);

        // =====================================================
        // TOOLBAR
        // =====================================================

        MaterialToolbar toolbar =
                findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {

            getSupportActionBar().setTitle(
                    "Request Details"
            );

            getSupportActionBar()
                    .setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIconTint(Color.WHITE);

        // =====================================================
        // FIND VIEWS
        // =====================================================

        viewPagerImages =
                findViewById(R.id.viewPagerImages);

        tvImageIndicator =
                findViewById(R.id.tvImageIndicator);

        tvItemName =
                findViewById(R.id.tvItemName);

        tvCustomerName =
                findViewById(R.id.tvCustomerName);

        tvCustomerMobile =
                findViewById(R.id.tvCustomerMobile);

        tvQuantity =
                findViewById(R.id.tvQuantity);

        tvRentalPeriod =
                findViewById(R.id.tvRentalPeriod);

        tvRent =
                findViewById(R.id.tvRent);

        tvStatus =
                findViewById(R.id.tvStatus);

        tvItemStatus =
                findViewById(R.id.tvItemStatus);

        // =====================================================
        // GET REQUEST DATA
        // =====================================================

        String itemName =
                getIntent().getStringExtra("itemName");

        String customerName =
                getIntent().getStringExtra("customerName");

        String customerMobile =
                getIntent().getStringExtra("customerMobile");

        String rentalPeriod =
                getIntent().getStringExtra("rentalPeriod");

        String rent =
                getIntent().getStringExtra("rent");

        int quantity =
                getIntent().getIntExtra(
                        "quantity",
                        1
                );

        String status =
                getIntent().getStringExtra("status");

        String itemStatus =
                getIntent().getStringExtra("itemStatus");

        ArrayList<String> imageUrls =
                getIntent().getStringArrayListExtra(
                        "imageUrls"
                );

        // =====================================================
        // SET IMAGES
        // =====================================================

        if (imageUrls != null &&
                !imageUrls.isEmpty()) {

            RequestImageAdapter imageAdapter =
                    new RequestImageAdapter(
                            imageUrls,
                            imageUri -> showFullImage(imageUri)
                    );

            viewPagerImages.setAdapter(
                    imageAdapter
            );

            updateImageIndicator(
                    0,
                    imageUrls.size()
            );

            viewPagerImages.registerOnPageChangeCallback(
                    new ViewPager2.OnPageChangeCallback() {

                        @Override
                        public void onPageSelected(
                                int position) {

                            updateImageIndicator(
                                    position,
                                    imageUrls.size()
                            );
                        }
                    }
            );

        } else {

            tvImageIndicator.setText("●");
        }

        // =====================================================
        // DISPLAY ITEM INFORMATION
        // =====================================================

        if (itemName != null &&
                !itemName.isEmpty()) {

            tvItemName.setText(itemName);
        }

        // =====================================================
        // DISPLAY CUSTOMER
        // =====================================================

        if (customerName != null &&
                !customerName.isEmpty()) {

            tvCustomerName.setText(
                    "Customer: " + customerName
            );
        }

        if (customerMobile != null &&
                !customerMobile.isEmpty()) {

            tvCustomerMobile.setText(
                    "Mobile: " + customerMobile
            );
        }

        // =====================================================
        // DISPLAY RENTAL INFORMATION
        // =====================================================

        tvQuantity.setText(
                "Quantity: " + quantity
        );

        if (rentalPeriod != null &&
                !rentalPeriod.isEmpty()) {

            tvRentalPeriod.setText(
                    "Rental Period: " + rentalPeriod
            );
        }

        if (rent != null &&
                !rent.isEmpty()) {

            tvRent.setText(
                    "Rent: ₹" + rent
            );
        }

        // =====================================================
        // DISPLAY REQUEST STATUS
        // =====================================================

        if (status != null &&
                !status.isEmpty()) {

            tvStatus.setText(status);
        }

        // =====================================================
        // DISPLAY ITEM STATUS
        // =====================================================

        if (itemStatus != null &&
                !itemStatus.isEmpty()) {

            tvItemStatus.setText(
                    "Item Status: " + itemStatus
            );
        }
    }

    // =========================================================
    // FULL SCREEN IMAGE
    // =========================================================

    private void showFullImage(String imageUriString) {

        final Dialog dialog =
                new Dialog(this);

        dialog.requestWindowFeature(
                Window.FEATURE_NO_TITLE
        );

        ImageView imageView =
                new ImageView(this);

        imageView.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
        );

        imageView.setScaleType(
                ImageView.ScaleType.FIT_CENTER
        );

        imageView.setBackgroundColor(
                Color.BLACK
        );

        // =====================================================
        // LOAD IMAGE
        // =====================================================

        try {

            Uri imageUri =
                    Uri.parse(imageUriString);

            imageView.setImageURI(
                    imageUri
            );

        } catch (Exception e) {

            imageView.setImageResource(
                    android.R.drawable.ic_menu_gallery
            );
        }

        dialog.setContentView(
                imageView
        );

        // =====================================================
        // TAP IMAGE TO CLOSE
        // =====================================================

        imageView.setOnClickListener(
                v -> dialog.dismiss()
        );

        dialog.show();

        // =====================================================
        // FULL SCREEN WINDOW
        // =====================================================

        Window window =
                dialog.getWindow();

        if (window != null) {

            window.setBackgroundDrawableResource(
                    android.R.color.black
            );

            window.setLayout(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT
            );
        }
    }

    // =========================================================
    // BACK BUTTON
    // =========================================================

    @Override
    public boolean onSupportNavigateUp() {

        finish();

        return true;
    }

    // =========================================================
    // IMAGE INDICATOR
    // =========================================================

    private void updateImageIndicator(
            int selectedPosition,
            int totalImages) {

        StringBuilder indicator =
                new StringBuilder();

        for (int i = 0;
             i < totalImages;
             i++) {

            if (i == selectedPosition) {

                indicator.append("●");

            } else {

                indicator.append("○");
            }

            if (i < totalImages - 1) {

                indicator.append(" ");
            }
        }

        tvImageIndicator.setText(
                indicator.toString()
        );
    }
}