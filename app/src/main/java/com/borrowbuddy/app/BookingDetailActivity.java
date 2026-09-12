package com.borrowbuddy.app;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class BookingDetailActivity extends AppCompatActivity {

    // =========================================================
    // TOOLBAR
    // =========================================================

    private MaterialToolbar toolbar;

    // =========================================================
    // MAIN LAYOUT
    // =========================================================

    private ScrollView bookingDetailScrollView;
    private LinearLayout bookingDetailMainLayout;

    // =========================================================
    // IMAGE SECTION
    // =========================================================

    private MaterialCardView itemImageCard;
    private ViewPager2 imageViewPager;

    private TextView dot1;
    private TextView dot2;
    private TextView dot3;
    private TextView dot4;
    private TextView dot5;

    private ItemImageAdapter imageAdapter;

    private ArrayList<Integer> images =
            new ArrayList<>();

    // =========================================================
    // BOOKING DETAILS
    // =========================================================

    private TextView itemNameText;
    private TextView priceText;
    private TextView durationText;
    private TextView startDateText;
    private TextView endDateText;
    private TextView statusText;
    private TextView bookingIdText;

    // =========================================================
    // OWNER DETAILS
    // =========================================================

    private TextView ownerNameText;
    private TextView ownerContactText;

    // =========================================================
    // CARDS
    // =========================================================

    private MaterialCardView bookingInfoCard;
    private MaterialCardView ownerDetailsCard;

    // =========================================================
    // BUTTON
    // =========================================================

    private Button cancelBookingButton;

    // =========================================================
    // COLORS
    // =========================================================

    private static final int PURPLE =
            Color.rgb(106, 27, 154);

    private static final int DARK_CARD =
            Color.rgb(43, 41, 50);

    private static final int LIGHT_BACKGROUND =
            Color.rgb(245, 247, 250);

    // =========================================================
    // PREFERENCES
    // =========================================================

    private static final String PREF_NAME =
            "BorrowBuddy";

    private static final String DARK_MODE =
            "darkMode";

    // =========================================================
    // BOOKING DATA
    // =========================================================

    private String bookingItemName =
            "Canon Camera";

    private String bookingPrice =
            "₹300 / day";

    private String bookingDuration =
            "3 Days";

    private String bookingStartDate =
            "05 Aug 2026";

    private String bookingEndDate =
            "07 Aug 2026";

    private String bookingStatus =
            "● Completed";

    private String bookingId =
            "BB1025";

    private String ownerName =
            "Rahul Patel";

    private String ownerContact =
            "9876543210";

    // =========================================================
    // ON CREATE
    // =========================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_booking_detail
        );

        // =====================================================
        // TOOLBAR
        // =====================================================

        toolbar =
                findViewById(R.id.toolbar);

        toolbar.setNavigationIconTint(
                Color.WHITE
        );

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {

            getSupportActionBar().setTitle(
                    "Booking Detail"
            );

            getSupportActionBar()
                    .setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(
                v -> finish()
        );

        // =====================================================
        // INITIALIZE VIEWS
        // =====================================================

        bookingDetailScrollView =
                findViewById(
                        R.id.bookingDetailScrollView
                );

        bookingDetailMainLayout =
                findViewById(
                        R.id.bookingDetailMainLayout
                );

        itemImageCard =
                findViewById(
                        R.id.itemImageCard
                );

        imageViewPager =
                findViewById(
                        R.id.imageViewPager
                );

        dot1 = findViewById(R.id.dot1);
        dot2 = findViewById(R.id.dot2);
        dot3 = findViewById(R.id.dot3);
        dot4 = findViewById(R.id.dot4);
        dot5 = findViewById(R.id.dot5);

        itemNameText =
                findViewById(
                        R.id.itemName
                );

        priceText =
                findViewById(
                        R.id.priceText
                );

        durationText =
                findViewById(
                        R.id.durationText
                );

        startDateText =
                findViewById(
                        R.id.startDateText
                );

        endDateText =
                findViewById(
                        R.id.endDateText
                );

        statusText =
                findViewById(
                        R.id.statusText
                );

        bookingIdText =
                findViewById(
                        R.id.bookingIdText
                );

        ownerNameText =
                findViewById(
                        R.id.ownerNameText
                );

        ownerContactText =
                findViewById(
                        R.id.ownerContactText
                );

        bookingInfoCard =
                findViewById(
                        R.id.bookingInfoCard
                );

        ownerDetailsCard =
                findViewById(
                        R.id.ownerDetailsCard
                );

        cancelBookingButton =
                findViewById(
                        R.id.cancelBookingButton
                );

        // =====================================================
        // GET DATA FROM INTENT
        // =====================================================

        Intent intent =
                getIntent();

        if (intent != null) {

            String value;

            value =
                    intent.getStringExtra(
                            "itemName"
                    );

            if (value != null &&
                    !value.isEmpty()) {

                bookingItemName = value;
            }

            value =
                    intent.getStringExtra(
                            "price"
                    );

            if (value != null &&
                    !value.isEmpty()) {

                bookingPrice = value;
            }

            value =
                    intent.getStringExtra(
                            "duration"
                    );

            if (value != null &&
                    !value.isEmpty()) {

                bookingDuration = value;
            }

            value =
                    intent.getStringExtra(
                            "startDate"
                    );

            if (value != null &&
                    !value.isEmpty()) {

                bookingStartDate = value;
            }

            value =
                    intent.getStringExtra(
                            "endDate"
                    );

            if (value != null &&
                    !value.isEmpty()) {

                bookingEndDate = value;
            }

            value =
                    intent.getStringExtra(
                            "status"
                    );

            if (value != null &&
                    !value.isEmpty()) {

                bookingStatus = value;
            }

            value =
                    intent.getStringExtra(
                            "bookingId"
                    );

            if (value != null &&
                    !value.isEmpty()) {

                bookingId = value;
            }

            value =
                    intent.getStringExtra(
                            "ownerName"
                    );

            if (value != null &&
                    !value.isEmpty()) {

                ownerName = value;
            }

            value =
                    intent.getStringExtra(
                            "ownerContact"
                    );

            if (value != null &&
                    !value.isEmpty()) {

                ownerContact = value;
            }
        }

        // =====================================================
        // SET BOOKING INFORMATION
        // =====================================================

        itemNameText.setText(
                bookingItemName
        );

        priceText.setText(
                bookingPrice
        );

        durationText.setText(
                bookingDuration
        );

        startDateText.setText(
                bookingStartDate
        );

        endDateText.setText(
                bookingEndDate
        );

        statusText.setText(
                bookingStatus
        );

        bookingIdText.setText(
                "Booking ID: " + bookingId
        );

        ownerNameText.setText(
                ownerName
        );

        ownerContactText.setText(
                ownerContact
        );

        // =====================================================
        // SET UP IMAGES
        // =====================================================

        setupImages();

        // =====================================================
        // VIEWPAGER PAGE CHANGE
        // =====================================================

        imageViewPager.registerOnPageChangeCallback(
                new ViewPager2.OnPageChangeCallback() {

                    @Override
                    public void onPageSelected(
                            int position
                    ) {

                        super.onPageSelected(
                                position
                        );

                        updateDots(position);
                    }
                }
        );

        // =====================================================
        // CANCEL BOOKING
        // =====================================================

        cancelBookingButton.setOnClickListener(
                v -> showCancelDialog()
        );

        // =====================================================
        // DARK MODE
        // =====================================================

        applyDarkMode();
    }

    // =========================================================
    // SETUP IMAGES
    // =========================================================

    private void setupImages() {

        /*
         * Currently BorrowBuddy is using the demo
         * ic_launcher_foreground image.
         *
         * We keep 5 positions just like Item Details.
         * Later these can be replaced with actual
         * uploaded item images.
         */

        images.clear();

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

        imageAdapter =
                new ItemImageAdapter(
                        images
                );

        imageViewPager.setAdapter(
                imageAdapter
        );

        imageViewPager.setCurrentItem(
                0,
                false
        );

        updateDots(0);
    }

    // =========================================================
    // UPDATE DOTS
    // =========================================================

    private void updateDots(
            int position
    ) {

        TextView[] dots = {
                dot1,
                dot2,
                dot3,
                dot4,
                dot5
        };

        for (int i = 0;
             i < dots.length;
             i++) {

            if (i < images.size()) {

                dots[i].setVisibility(
                        View.VISIBLE
                );

                if (i == position) {

                    dots[i].setText(
                            "●"
                    );

                } else {

                    dots[i].setText(
                            "○"
                    );
                }

            } else {

                dots[i].setVisibility(
                        View.GONE
                );
            }
        }
    }

    // =========================================================
    // CANCEL BOOKING DIALOG
    // =========================================================

    private void showCancelDialog() {

        new AlertDialog.Builder(this)

                .setTitle(
                        "Cancel Booking"
                )

                .setMessage(
                        "Are you sure you want to cancel this booking?"
                )

                .setNegativeButton(
                        "NO",
                        null
                )

                .setPositiveButton(
                        "YES",
                        (dialog, which) -> {

                            Intent intent =
                                    new Intent(
                                            BookingDetailActivity.this,
                                            BookingCancelledActivity.class
                                    );

                            intent.putExtra(
                                    "itemName",
                                    bookingItemName
                            );

                            intent.putExtra(
                                    "bookingId",
                                    bookingId
                            );

                            intent.putExtra(
                                    "ownerName",
                                    ownerName
                            );

                            startActivity(intent);

                            finish();
                        }
                )

                .show();
    }

    // =========================================================
    // DARK / LIGHT MODE
    // =========================================================

    private void applyDarkMode() {

        boolean darkMode =
                getSharedPreferences(
                        PREF_NAME,
                        MODE_PRIVATE
                ).getBoolean(
                        DARK_MODE,
                        false
                );

        if (darkMode) {

            // Main background
            bookingDetailScrollView
                    .setBackgroundColor(
                            Color.BLACK
                    );

            bookingDetailMainLayout
                    .setBackgroundColor(
                            Color.BLACK
                    );

            // Cards
            bookingInfoCard
                    .setCardBackgroundColor(
                            DARK_CARD
                    );

            ownerDetailsCard
                    .setCardBackgroundColor(
                            DARK_CARD
                    );

            itemImageCard
                    .setCardBackgroundColor(
                            Color.BLACK
                    );

            // Text
            changeAllTextColor(
                    bookingDetailMainLayout,
                    Color.WHITE
            );

            // Status
            statusText.setTextColor(
                    Color.rgb(
                            129,
                            199,
                            132
                    )
            );

            // Booking ID
            bookingIdText.setTextColor(
                    Color.LTGRAY
            );

        } else {

            // Main background
            bookingDetailScrollView
                    .setBackgroundColor(
                            LIGHT_BACKGROUND
                    );

            bookingDetailMainLayout
                    .setBackgroundColor(
                            LIGHT_BACKGROUND
                    );

            // Cards
            bookingInfoCard
                    .setCardBackgroundColor(
                            Color.WHITE
                    );

            ownerDetailsCard
                    .setCardBackgroundColor(
                            Color.WHITE
                    );

            itemImageCard
                    .setCardBackgroundColor(
                            Color.WHITE
                    );

            // Text
            changeAllTextColor(
                    bookingDetailMainLayout,
                    Color.BLACK
            );

            cancelBookingButton.setTextColor(
                    Color.WHITE
            );

            // Status
            statusText.setTextColor(
                    Color.rgb(
                            46,
                            125,
                            50
                    )
            );

            // Booking ID
            bookingIdText.setTextColor(
                    Color.DKGRAY
            );
        }

        // Always keep toolbar purple
        // and toolbar title/arrow white.

        toolbar.setBackgroundColor(
                Color.rgb(108, 74, 182)
        );

        toolbar.setTitleTextColor(
                Color.WHITE
        );

        getWindow().setStatusBarColor(
                Color.rgb(108, 74, 182)
        );

        toolbar.setNavigationIconTint(
                Color.WHITE
        );
    }

    // =========================================================
    // CHANGE TEXT COLOR
    // =========================================================

    private void changeAllTextColor(
            View view,
            int color
    ) {

        if (view == toolbar) {
            return;
        }

        if (view instanceof TextView) {

            ((TextView) view)
                    .setTextColor(color);

            return;
        }

        if (view instanceof ViewGroup) {

            ViewGroup group =
                    (ViewGroup) view;

            for (int i = 0;
                 i < group.getChildCount();
                 i++) {

                changeAllTextColor(
                        group.getChildAt(i),
                        color
                );
            }
        }
    }

    // =========================================================
    // RESUME
    // =========================================================

    @Override
    protected void onResume() {

        super.onResume();

        applyDarkMode();
    }

    // =========================================================
    // BACK
    // =========================================================

    @Override
    public boolean onSupportNavigateUp() {

        finish();

        return true;
    }

    // =========================================================
    // FULL SCREEN IMAGE
    // =========================================================

    public void showFullImage(
            int imageResId
    ) {

        final Dialog dialog =
                new Dialog(this);

        dialog.requestWindowFeature(
                Window.FEATURE_NO_TITLE
        );

        ImageView fullImageView =
                new ImageView(this);

        fullImageView.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
        );

        fullImageView.setScaleType(
                ImageView.ScaleType.FIT_CENTER
        );

        fullImageView.setBackgroundColor(
                Color.BLACK
        );

        fullImageView.setImageResource(
                imageResId
        );

        fullImageView.setOnClickListener(
                v -> dialog.dismiss()
        );

        dialog.setContentView(
                fullImageView
        );

        dialog.show();

        Window window =
                dialog.getWindow();

        if (window != null) {

            window.setBackgroundDrawableResource(
                    android.R.color.black
            );

            window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            );

            window.setLayout(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT
            );
        }
    }
}
