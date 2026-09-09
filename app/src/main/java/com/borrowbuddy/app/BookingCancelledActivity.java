package com.borrowbuddy.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import com.google.android.material.appbar.MaterialToolbar;

public class BookingCancelledActivity extends AppCompatActivity {

    // =========================
    // VIEWS
    // =========================

    MaterialToolbar toolbar;

    TextView cancelledText;
    TextView cancelledMessage;
    TextView itemNameText;
    TextView bookingIdText;

    TextView cancelIcon;

    Button backToMyBookingsButton;

    LinearLayout cancelledMainContent;

    // =========================
    // PREFERENCES
    // =========================

    private static final String PREF_NAME = "BorrowBuddy";
    private static final String DARK_MODE = "darkMode";

    // =========================
    // COLOR
    // =========================

    private static final int PURPLE =
            Color.rgb(106, 27, 154);

    private static final int DARK_BACKGROUND =
            Color.BLACK;

    private static final int LIGHT_BACKGROUND =
            Color.rgb(248, 249, 250);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_booking_cancelled
        );

        // =========================
        // TOOLBAR
        // =========================

        toolbar =
                findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(
                Color.WHITE
        );

        toolbar.setNavigationIconTint(
                Color.WHITE
        );

        if (getSupportActionBar() != null) {

            getSupportActionBar().setTitle(
                    "Booking Cancelled"
            );

            getSupportActionBar()
                    .setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(
                v -> finish()
        );

        // =========================
        // STATUS BAR
        // =========================

        getWindow().setStatusBarColor(
                PURPLE
        );

        getWindow().setNavigationBarColor(
                PURPLE
        );

        WindowCompat.getInsetsController(
                getWindow(),
                getWindow().getDecorView()
        ).setAppearanceLightStatusBars(false);

        // =========================
        // FIND VIEWS
        // =========================

        cancelledText =
                findViewById(R.id.cancelledText);

        cancelledMessage =
                findViewById(R.id.cancelledMessage);

        itemNameText =
                findViewById(R.id.itemNameText);

        bookingIdText =
                findViewById(R.id.bookingIdText);

        cancelIcon =
                findViewById(R.id.cancelIcon);

        backToMyBookingsButton =
                findViewById(
                        R.id.backToMyBookingsButton
                );

        cancelledMainContent =
                findViewById(
                        R.id.cancelledMainContent
                );

        // ==================================================
        // GET THE CANCELLED ITEM NAME
        // ==================================================

        String itemName =
                getIntent().getStringExtra("itemName");

        if (itemName == null ||
                itemName.trim().isEmpty()) {

            itemName = "Unknown Item";
        }

        itemNameText.setText(
                "Item: " + itemName
        );

        // ==================================================
        // GET BOOKING ID
        // ==================================================

        String bookingId =
                getIntent().getStringExtra("bookingId");

        if (bookingId == null ||
                bookingId.trim().isEmpty()) {

            bookingId = "BB1025";
        }

        bookingIdText.setText(
                "Booking ID: " + bookingId
        );

        // =========================
        // TEXT
        // =========================

        cancelledText.setText(
                "Booking Cancelled"
        );

        cancelledMessage.setText(
                "Your booking has been cancelled successfully."
        );

        // =========================
        // BACK TO MY BOOKINGS
        // =========================

        backToMyBookingsButton.setOnClickListener(v -> {

            Intent intent = new Intent(
                    BookingCancelledActivity.this,
                    MyBookingsActivity.class
            );

            intent.setFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_SINGLE_TOP
            );

            startActivity(intent);

            finish();

        });

        // =========================
        // DARK MODE
        // =========================

        applyDarkMode();
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

        cancelledMainContent.setBackgroundColor(
                DARK_BACKGROUND
        );

        toolbar.setBackgroundColor(
                PURPLE
        );

        toolbar.setTitleTextColor(
                Color.WHITE
        );

        toolbar.setNavigationIconTint(
                Color.WHITE
        );

        cancelledText.setTextColor(
                Color.WHITE
        );

        cancelledMessage.setTextColor(
                Color.LTGRAY
        );

        itemNameText.setTextColor(
                Color.WHITE
        );

        bookingIdText.setTextColor(
                Color.WHITE
        );

        // Success/Cancel icon

        cancelIcon.setBackgroundTintList(
                ColorStateList.valueOf(
                        Color.rgb(45, 70, 48)
                )
        );

        cancelIcon.setTextColor(
                Color.rgb(129, 199, 132)
        );

        // Button

        backToMyBookingsButton
                .setBackgroundTintList(
                        ColorStateList.valueOf(
                                PURPLE
                        )
                );

        backToMyBookingsButton.setTextColor(
                Color.WHITE
        );

        // Status + Navigation bar

        getWindow().setStatusBarColor(
                PURPLE
        );

        getWindow().setNavigationBarColor(
                PURPLE
        );
    }

    // ==================================================
    // LIGHT MODE
    // ==================================================

    private void lightMode() {

        cancelledMainContent.setBackgroundColor(
                LIGHT_BACKGROUND
        );

        toolbar.setBackgroundColor(
                PURPLE
        );

        toolbar.setTitleTextColor(
                Color.WHITE
        );

        toolbar.setNavigationIconTint(
                Color.WHITE
        );

        cancelledText.setTextColor(
                Color.BLACK
        );

        cancelledMessage.setTextColor(
                Color.rgb(102, 102, 102)
        );

        itemNameText.setTextColor(
                Color.BLACK
        );

        bookingIdText.setTextColor(
                Color.rgb(51, 51, 51)
        );

        // Icon

        cancelIcon.setBackgroundTintList(
                ColorStateList.valueOf(
                        Color.rgb(232, 245, 233)
                )
        );

        cancelIcon.setTextColor(
                Color.rgb(46, 125, 50)
        );

        // Button

        backToMyBookingsButton
                .setBackgroundTintList(
                        ColorStateList.valueOf(
                                PURPLE
                        )
                );

        backToMyBookingsButton.setTextColor(
                Color.WHITE
        );

        // Status + Navigation bar

        getWindow().setStatusBarColor(
                PURPLE
        );

        getWindow().setNavigationBarColor(
                PURPLE
        );
    }

    // ==================================================
    // ON RESUME
    // ==================================================

    @Override
    protected void onResume() {

        super.onResume();

        if (cancelledMainContent != null) {

            applyDarkMode();
        }
    }
}