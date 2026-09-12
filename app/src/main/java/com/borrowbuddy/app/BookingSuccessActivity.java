package com.borrowbuddy.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

public class BookingSuccessActivity extends AppCompatActivity {

    private ScrollView successScrollView;
    private ViewGroup successMainLayout;

    private MaterialToolbar toolbar;

    private TextView successIcon;
    private TextView successText;
    private TextView bookingMessage;
    private TextView bookingConfirmedText;

    private Button viewBookingsButton;
    private Button homeButton;

    // =========================
    // BOOKING DATA
    // =========================

    private String itemName;
    private int totalAmount;
    private int quantity;
    private int duration;
    private String paymentMethod;

    private static final int PURPLE =
            Color.rgb(106, 27, 154);

    private static final int BUTTON_PURPLE =
            Color.rgb(103, 58, 183);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_booking_success
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
                    "Booking Successful"
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

        getWindow().setStatusBarColor(Color.rgb(108, 74, 182));

        getWindow()
                .getDecorView()
                .setSystemUiVisibility(0);

        // =========================
        // FIND VIEWS
        // =========================

        successScrollView =
                findViewById(R.id.successScrollView);

        successMainLayout =
                findViewById(R.id.successMainLayout);

        successIcon =
                findViewById(R.id.successIcon);

        successText =
                findViewById(R.id.successText);

        bookingMessage =
                findViewById(R.id.bookingMessage);

        bookingConfirmedText =
                findViewById(R.id.bookingConfirmedText);

        viewBookingsButton =
                findViewById(R.id.viewBookingsButton);

        homeButton =
                findViewById(R.id.homeButton);

        // =========================
        // GET BOOKING DATA
        // =========================

        itemName =
                getIntent().getStringExtra("itemName");

        totalAmount =
                getIntent().getIntExtra(
                        "totalAmount",
                        0
                );

        quantity =
                getIntent().getIntExtra(
                        "quantity",
                        1
                );

        duration =
                getIntent().getIntExtra(
                        "duration",
                        1
                );

        paymentMethod =
                getIntent().getStringExtra(
                        "paymentMethod"
                );

        // =========================
        // SAFETY DEFAULT
        // =========================

        if (itemName == null ||
                itemName.trim().isEmpty()) {

            itemName = "Item";
        }

        // =========================
        // HEADER
        // =========================

        toolbar.setBackgroundColor(
                Color.rgb(108, 74, 182)
        );

        toolbar.setTitle(
                "Booking Successful"
        );

        toolbar.setTitleTextColor(
                Color.WHITE
        );

        toolbar.setNavigationIconTint(
                Color.WHITE
        );

        // =========================
        // SUCCESS TEXT
        // =========================

        successText.setText(
                "Booking Successful!"
        );

        bookingMessage.setText(
                "Your booking for " +
                        itemName +
                        " has been confirmed successfully."
        );

        bookingConfirmedText.setText(
                "✓ Booking Confirmed"
        );

        // =========================
        // VIEW MY BOOKINGS
        // =========================

        viewBookingsButton.setText(
                "View My Bookings"
        );

        viewBookingsButton.setTextColor(
                Color.WHITE
        );

        viewBookingsButton.setBackgroundTintList(
                ColorStateList.valueOf(
                        BUTTON_PURPLE
                )
        );

        viewBookingsButton.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            BookingSuccessActivity.this,
                            MyBookingsActivity.class
                    );

            // IMPORTANT:
            // Current item ka data MyBookings ko bhejna

            intent.putExtra(
                    "itemName",
                    itemName
            );

            intent.putExtra(
                    "totalAmount",
                    totalAmount
            );

            intent.putExtra(
                    "quantity",
                    quantity
            );

            intent.putExtra(
                    "duration",
                    duration
            );

            intent.putExtra(
                    "paymentMethod",
                    paymentMethod
            );

            startActivity(intent);
        });

        // =========================
        // BACK TO HOME
        // =========================

        homeButton.setText(
                "Back to Home"
        );

        homeButton.setTextColor(
                Color.WHITE
        );

        homeButton.setBackgroundTintList(
                ColorStateList.valueOf(
                        BUTTON_PURPLE
                )
        );

        homeButton.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            BookingSuccessActivity.this,
                            HomeActivity.class
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
    // ON RESUME
    // ==================================================

    @Override
    protected void onResume() {

        super.onResume();

        if (successMainLayout != null) {
            applyDarkMode();
        }
    }

    // ==================================================
    // DARK MODE
    // ==================================================

    private void darkMode() {

        successScrollView.setBackgroundColor(
                Color.BLACK
        );

        successMainLayout.setBackgroundColor(
                Color.BLACK
        );

        toolbar.setBackgroundColor(
                Color.rgb(108, 74, 182)
        );

        toolbar.setTitleTextColor(
                Color.WHITE
        );

        toolbar.setNavigationIconTint(
                Color.WHITE
        );

        getWindow().setStatusBarColor(Color.rgb(108, 74, 182));

        successText.setTextColor(
                Color.WHITE
        );

        bookingMessage.setTextColor(
                Color.LTGRAY
        );

        bookingConfirmedText.setTextColor(
                Color.rgb(76, 175, 80)
        );

        viewBookingsButton.setBackgroundTintList(
                ColorStateList.valueOf(
                        BUTTON_PURPLE
                )
        );

        viewBookingsButton.setTextColor(
                Color.WHITE
        );

        homeButton.setBackgroundTintList(
                ColorStateList.valueOf(
                        BUTTON_PURPLE
                )
        );

        homeButton.setTextColor(
                Color.WHITE
        );
    }

    // ==================================================
    // LIGHT MODE
    // ==================================================

    private void lightMode() {

        successScrollView.setBackgroundColor(
                Color.rgb(248, 249, 250)
        );

        successMainLayout.setBackgroundColor(
                Color.rgb(248, 249, 250)
        );

        toolbar.setBackgroundColor(
                Color.rgb(108, 74, 182)
        );

        toolbar.setTitleTextColor(
                Color.WHITE
        );

        toolbar.setNavigationIconTint(
                Color.WHITE
        );

        getWindow().setStatusBarColor(Color.rgb(108, 74, 182));

        successText.setTextColor(
                Color.BLACK
        );

        bookingMessage.setTextColor(
                Color.rgb(85, 85, 85)
        );

        bookingConfirmedText.setTextColor(
                Color.rgb(76, 175, 80)
        );

        viewBookingsButton.setBackgroundTintList(
                ColorStateList.valueOf(
                        BUTTON_PURPLE
                )
        );

        viewBookingsButton.setTextColor(
                Color.WHITE
        );

        homeButton.setBackgroundTintList(
                ColorStateList.valueOf(
                        BUTTON_PURPLE
                )
        );

        homeButton.setTextColor(
                Color.WHITE
        );
    }

    // ==================================================
    // APPLY DARK MODE
    // ==================================================

    private void applyDarkMode() {

        SharedPreferences preferences =
                getSharedPreferences(
                        "BorrowBuddy",
                        MODE_PRIVATE
                );

        boolean isDarkMode =
                preferences.getBoolean(
                        "darkMode",
                        false
                );

        if (isDarkMode) {

            darkMode();

        } else {

            lightMode();
        }
    }
}