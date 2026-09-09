package com.borrowbuddy.app;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

public class RentalReviewActivity extends AppCompatActivity {

    // ================= HEADER =================

    MaterialToolbar toolbar;


    // ================= MAIN =================

    ScrollView reviewScroll;
    LinearLayout reviewLayout;

    // ================= ITEM =================

    MaterialCardView itemInfoCard;
    TextView itemName;
    TextView ownerName;
    TextView bookingStatus;

    // ================= RATING =================

    MaterialCardView ratingCard;
    TextView ratingTitle;
    RatingBar ratingBar;
    TextView ratingHint;

    // ================= REVIEW =================

    MaterialCardView reviewCard;
    TextView writeReviewTitle;
    EditText reviewText;

    // ================= BUTTON =================

    Button submitReviewButton;

    // ================= DARK MODE =================

    private static final String PREF_NAME = "BorrowBuddy";
    private static final String DARK_MODE = "darkMode";

    // ================= COLORS =================

    private static final int PURPLE =
            Color.rgb(106, 27, 154);

    private static final int DARK_BACKGROUND =
            Color.BLACK;

    private static final int DARK_CARD =
            Color.rgb(43, 41, 50);

    private static final int LIGHT_BACKGROUND =
            Color.rgb(248, 249, 250);


    // ==================================================
    // ON CREATE
    // ==================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rental_review);

        // =========================
// MATERIAL TOOLBAR
// =========================

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {

            getSupportActionBar().setTitle("Rental Review");

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            getSupportActionBar().setHomeButtonEnabled(true);
        }

// Toolbar title and back arrow
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIconTint(Color.WHITE);

        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });
        // =========================
        // STATUS BAR
        // =========================

        getWindow().setStatusBarColor(PURPLE);

        WindowCompat.getInsetsController(
                getWindow(),
                getWindow().getDecorView()
        ).setAppearanceLightStatusBars(false);


        // =========================
        // FIND VIEWS
        // =========================


        reviewScroll =
                findViewById(R.id.reviewScroll);

        reviewLayout =
                findViewById(R.id.reviewLayout);

        itemInfoCard =
                findViewById(R.id.itemInfoCard);

        itemName =
                findViewById(R.id.itemName);

        ownerName =
                findViewById(R.id.ownerName);

        bookingStatus =
                findViewById(R.id.bookingStatus);

        ratingCard =
                findViewById(R.id.ratingCard);

        ratingTitle =
                findViewById(R.id.ratingTitle);

        ratingBar =
                findViewById(R.id.ratingBar);

        ratingHint =
                findViewById(R.id.ratingHint);

        reviewCard =
                findViewById(R.id.reviewCard);

        writeReviewTitle =
                findViewById(R.id.writeReviewTitle);

        reviewText =
                findViewById(R.id.reviewText);

        submitReviewButton =
                findViewById(R.id.submitReviewButton);


        // ==================================================
        // RECEIVE ITEM DATA FROM MY BOOKINGS
        // ==================================================

        String receivedItemName =
                getIntent().getStringExtra("itemName");

        String receivedOwnerName =
                getIntent().getStringExtra("ownerName");

        String receivedBookingStatus =
                getIntent().getStringExtra("bookingStatus");


        // =========================
        // ITEM NAME
        // =========================

        if (receivedItemName != null &&
                !receivedItemName.trim().isEmpty()) {

            itemName.setText(
                    receivedItemName
            );

        } else {

            itemName.setText(
                    "Unknown Item"
            );
        }


        // =========================
        // OWNER NAME
        // =========================

        if (receivedOwnerName != null &&
                !receivedOwnerName.trim().isEmpty()) {

            ownerName.setText(
                    "Owner: " + receivedOwnerName
            );

        } else {

            ownerName.setText(
                    "Owner: Unknown Owner"
            );
        }


        // =========================
        // BOOKING STATUS
        // =========================

        if (receivedBookingStatus != null &&
                !receivedBookingStatus.trim().isEmpty()) {

            bookingStatus.setText(
                    "✓ Booking " +
                            receivedBookingStatus
            );

        } else {

            bookingStatus.setText(
                    "✓ Booking Completed"
            );
        }


        // ==================================================
        // RATING BAR
        // ==================================================

        ratingBar.setOnRatingBarChangeListener(
                (ratingBar, rating, fromUser) -> {

                    if (rating == 1) {

                        ratingHint.setText(
                                "Poor"
                        );

                    } else if (rating == 2) {

                        ratingHint.setText(
                                "Fair"
                        );

                    } else if (rating == 3) {

                        ratingHint.setText(
                                "Good"
                        );

                    } else if (rating == 4) {

                        ratingHint.setText(
                                "Very Good"
                        );

                    } else if (rating == 5) {

                        ratingHint.setText(
                                "Excellent"
                        );

                    } else {

                        ratingHint.setText(
                                "Tap a star to give your rating"
                        );
                    }

                }
        );


        // ==================================================
        // SUBMIT REVIEW
        // ==================================================

        submitReviewButton.setOnClickListener(v -> {

            float rating =
                    ratingBar.getRating();

            String review =
                    reviewText
                            .getText()
                            .toString()
                            .trim();


            // =========================
            // CHECK RATING
            // =========================

            if (rating == 0) {

                Toast.makeText(
                        RentalReviewActivity.this,
                        "Please select a rating",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }


            // =========================
            // CHECK REVIEW
            // =========================

            if (review.isEmpty()) {

                Toast.makeText(
                        RentalReviewActivity.this,
                        "Please write a review",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }


            // =========================
            // REVIEW SUBMITTED
            // =========================

            Toast.makeText(
                    RentalReviewActivity.this,
                    "Review submitted successfully",
                    Toast.LENGTH_SHORT
            ).show();


            finish();

        });


        // ==================================================
        // APPLY DARK MODE
        // ==================================================

        applyDarkMode();
    }


    // ==================================================
    // ON RESUME
    // ==================================================

    @Override
    protected void onResume() {

        super.onResume();

        if (reviewLayout != null) {

            applyDarkMode();

        }
    }


    // ==================================================
    // APPLY DARK MODE
    // ==================================================

    private void applyDarkMode() {

        SharedPreferences preferences =
                getSharedPreferences(
                        PREF_NAME,
                        MODE_PRIVATE
                );

        boolean isDarkMode =
                preferences.getBoolean(
                        DARK_MODE,
                        false
                );


        if (isDarkMode) {

            darkMode();

        } else {

            lightMode();

        }
    }


    // ==================================================
    // DARK MODE
    // ==================================================

    private void darkMode() {

        // Background
        reviewScroll.setBackgroundColor(
                DARK_BACKGROUND
        );

        reviewLayout.setBackgroundColor(
                DARK_BACKGROUND
        );


        // Cards
        itemInfoCard.setCardBackgroundColor(
                DARK_CARD
        );

        ratingCard.setCardBackgroundColor(
                DARK_CARD
        );

        reviewCard.setCardBackgroundColor(
                DARK_CARD
        );


        // Header
        toolbar.setBackgroundColor(
                PURPLE
        );

        toolbar.setTitleTextColor(
                Color.WHITE
        );


        // Main text
        reviewTitleColor(
                reviewLayout,
                Color.WHITE
        );


        // Specific text
        itemName.setTextColor(
                Color.WHITE
        );

        ownerName.setTextColor(
                Color.LTGRAY
        );

        bookingStatus.setTextColor(
                Color.rgb(129, 199, 132)
        );

        ratingTitle.setTextColor(
                Color.WHITE
        );

        ratingHint.setTextColor(
                Color.LTGRAY
        );

        writeReviewTitle.setTextColor(
                Color.WHITE
        );


        // EditText
        reviewText.setTextColor(
                Color.WHITE
        );

        reviewText.setHintTextColor(
                Color.LTGRAY
        );

        reviewText.setBackgroundTintList(
                ColorStateList.valueOf(
                        Color.GRAY
                )
        );


        // Rating
        ratingBar.setProgressTintList(
                ColorStateList.valueOf(
                        Color.rgb(255, 193, 7)
                )
        );

        ratingBar.setSecondaryProgressTintList(
                ColorStateList.valueOf(
                        Color.rgb(255, 193, 7)
                )
        );


        // Submit button
        submitReviewButton.setBackgroundTintList(
                ColorStateList.valueOf(
                        PURPLE
                )
        );

        submitReviewButton.setTextColor(
                Color.WHITE
        );


        getWindow().setStatusBarColor(
                PURPLE
        );

        WindowCompat.getInsetsController(
                getWindow(),
                getWindow().getDecorView()
        ).setAppearanceLightStatusBars(false);
    }


    // ==================================================
    // LIGHT MODE
    // ==================================================

    private void lightMode() {

        // Background
        reviewScroll.setBackgroundColor(
                LIGHT_BACKGROUND
        );

        reviewLayout.setBackgroundColor(
                LIGHT_BACKGROUND
        );


        // Cards
        itemInfoCard.setCardBackgroundColor(
                Color.WHITE
        );

        ratingCard.setCardBackgroundColor(
                Color.WHITE
        );

        reviewCard.setCardBackgroundColor(
                Color.WHITE
        );

        // Header
        toolbar.setBackgroundColor(
                PURPLE
        );

        toolbar.setTitleTextColor(
                Color.WHITE
        );

        toolbar.setNavigationIconTint(
                Color.WHITE
        );

        // Main text
        reviewTitleColor(
                reviewLayout,
                Color.BLACK
        );


        // Specific text
        itemName.setTextColor(
                Color.BLACK
        );

        ownerName.setTextColor(
                Color.rgb(85, 85, 85)
        );

        bookingStatus.setTextColor(
                Color.rgb(46, 125, 50)
        );

        ratingTitle.setTextColor(
                Color.BLACK
        );

        ratingHint.setTextColor(
                Color.rgb(119, 119, 119)
        );

        writeReviewTitle.setTextColor(
                Color.BLACK
        );


        // EditText
        reviewText.setTextColor(
                Color.BLACK
        );

        reviewText.setHintTextColor(
                Color.rgb(119, 119, 119)
        );


        // Rating
        ratingBar.setProgressTintList(
                ColorStateList.valueOf(
                        Color.rgb(255, 193, 7)
                )
        );

        ratingBar.setSecondaryProgressTintList(
                ColorStateList.valueOf(
                        Color.rgb(255, 193, 7)
                )
        );


        // Submit button
        submitReviewButton.setBackgroundTintList(
                ColorStateList.valueOf(
                        PURPLE
                )
        );

        submitReviewButton.setTextColor(
                Color.WHITE
        );


        getWindow().setStatusBarColor(
                PURPLE
        );

        WindowCompat.getInsetsController(
                getWindow(),
                getWindow().getDecorView()
        ).setAppearanceLightStatusBars(false);
    }


    // ==================================================
    // CHANGE MAIN TEXT
    // ==================================================

    private void reviewTitleColor(
            View view,
            int color
    ) {

        // Do not change toolbar title color
        if (view == toolbar) {
            return;
        }

        if (view instanceof TextView) {

            TextView textView =
                    (TextView) view;

            int id =
                    textView.getId();


            // These text views have their own colors
            // and should not be changed here.

            if (id != R.id.itemName &&
                    id != R.id.ownerName &&
                    id != R.id.bookingStatus &&
                    id != R.id.ratingTitle &&
                    id != R.id.ratingHint &&
                    id != R.id.writeReviewTitle &&
                    id != R.id.reviewText &&
                    id != R.id.submitReviewButton) {

                textView.setTextColor(
                        color
                );
            }
        }


        if (view instanceof ViewGroup) {

            ViewGroup group =
                    (ViewGroup) view;


            for (int i = 0;
                 i < group.getChildCount();
                 i++) {

                reviewTitleColor(
                        group.getChildAt(i),
                        color
                );
            }
        }
    }
}