package com.borrowbuddy.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import com.google.android.material.card.MaterialCardView;

public class BookingDetailActivity extends AppCompatActivity {

    // ================= HEADER =================

    LinearLayout headerLayout;
    TextView backButton;
    TextView headerTitle;

    // ================= MAIN =================

    ScrollView bookingDetailScrollView;
    LinearLayout bookingDetailMainLayout;

    // ================= IMAGE =================

    ImageView itemImage;

    // ================= TEXT =================

    TextView itemName;
    TextView priceText;
    TextView durationText;
    TextView startDateText;
    TextView endDateText;
    TextView statusText;
    TextView bookingIdText;
    TextView ownerNameText;
    TextView ownerContactText;

    // ================= CARDS =================

    MaterialCardView bookingInfoCard;
    MaterialCardView ownerDetailsCard;

    // ================= BUTTON =================

    Button cancelBookingButton;

    // ================= COLORS =================

    private static final int PURPLE =
            Color.rgb(106, 27, 154);

    private static final int DARK_CARD =
            Color.rgb(43, 41, 50);

    private static final int LIGHT_BACKGROUND =
            Color.rgb(245, 247, 250);

    // ================= PREFERENCES =================

    private static final String PREF_NAME =
            "BorrowBuddy";

    private static final String DARK_MODE =
            "darkMode";

    // ================= CURRENT BOOKING DATA =================

    private String currentItemName = "Item";
    private String currentPrice = "₹0";
    private String currentDuration = "0 Days";
    private String currentStatus = "Confirmed";
    private String currentBookingId = "BB1025";
    private String currentOwnerName = "Unknown";
    private String currentOwnerContact = "Not Available";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_booking_detail
        );

        // =========================
        // HIDE ACTION BAR
        // =========================

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // =========================
        // STATUS BAR
        // =========================

        getWindow().setStatusBarColor(PURPLE);
        getWindow().setNavigationBarColor(PURPLE);

        WindowCompat.getInsetsController(
                getWindow(),
                getWindow().getDecorView()
        ).setAppearanceLightStatusBars(false);

        // =========================
        // FIND HEADER
        // =========================

        headerLayout =
                findViewById(R.id.headerLayout);

        backButton =
                findViewById(R.id.backButton);

        headerTitle =
                findViewById(R.id.headerTitle);

        // =========================
        // FIND MAIN
        // =========================

        bookingDetailScrollView =
                findViewById(
                        R.id.bookingDetailScrollView
                );

        bookingDetailMainLayout =
                findViewById(
                        R.id.bookingDetailMainLayout
                );

        // =========================
        // FIND IMAGE
        // =========================

        itemImage =
                findViewById(R.id.itemImage);

        // =========================
        // FIND TEXT
        // =========================

        itemName =
                findViewById(R.id.itemName);

        priceText =
                findViewById(R.id.priceText);

        durationText =
                findViewById(R.id.durationText);

        startDateText =
                findViewById(R.id.startDateText);

        endDateText =
                findViewById(R.id.endDateText);

        statusText =
                findViewById(R.id.statusText);

        bookingIdText =
                findViewById(R.id.bookingIdText);

        ownerNameText =
                findViewById(R.id.ownerNameText);

        ownerContactText =
                findViewById(R.id.ownerContactText);

        // =========================
        // FIND CARDS
        // =========================

        bookingInfoCard =
                findViewById(
                        R.id.bookingInfoCard
                );

        ownerDetailsCard =
                findViewById(
                        R.id.ownerDetailsCard
                );

        // =========================
        // FIND BUTTON
        // =========================

        cancelBookingButton =
                findViewById(
                        R.id.cancelBookingButton
                );

        // ==================================================
        // RECEIVE BOOKING DATA FROM MY BOOKINGS
        // ==================================================

        String receivedItemName =
                getIntent().getStringExtra(
                        "itemName"
                );

        if (receivedItemName != null &&
                !receivedItemName.trim().isEmpty()) {

            currentItemName =
                    receivedItemName;
        }

        String receivedPrice =
                getIntent().getStringExtra(
                        "price"
                );

        if (receivedPrice != null &&
                !receivedPrice.trim().isEmpty()) {

            currentPrice =
                    receivedPrice;
        }

        String receivedDuration =
                getIntent().getStringExtra(
                        "duration"
                );

        if (receivedDuration != null &&
                !receivedDuration.trim().isEmpty()) {

            currentDuration =
                    receivedDuration;
        }

        String receivedStatus =
                getIntent().getStringExtra(
                        "status"
                );

        if (receivedStatus != null &&
                !receivedStatus.trim().isEmpty()) {

            currentStatus =
                    receivedStatus;
        }

        String receivedBookingId =
                getIntent().getStringExtra(
                        "bookingId"
                );

        if (receivedBookingId != null &&
                !receivedBookingId.trim().isEmpty()) {

            currentBookingId =
                    receivedBookingId;
        }

        String receivedOwnerName =
                getIntent().getStringExtra(
                        "ownerName"
                );

        if (receivedOwnerName != null &&
                !receivedOwnerName.trim().isEmpty()) {

            currentOwnerName =
                    receivedOwnerName;
        }

        String receivedOwnerContact =
                getIntent().getStringExtra(
                        "ownerContact"
                );

        if (receivedOwnerContact != null &&
                !receivedOwnerContact.trim().isEmpty()) {

            currentOwnerContact =
                    receivedOwnerContact;
        }

        // ==================================================
        // SHOW BOOKING DATA
        // ==================================================

        itemName.setText(
                currentItemName
        );

        priceText.setText(
                "Price: " + currentPrice
        );

        durationText.setText(
                "Duration: " + currentDuration
        );

        startDateText.setText(
                "Start Date: Today"
        );

        endDateText.setText(
                "End Date: After " +
                        currentDuration
        );

        statusText.setText(
                "Status: " + currentStatus
        );

        bookingIdText.setText(
                "Booking ID: " +
                        currentBookingId
        );

        // ==================================================
        // OWNER
        // ==================================================

        ownerNameText.setText(
                "Owner: " +
                        currentOwnerName
        );

        ownerContactText.setText(
                "Contact: " +
                        currentOwnerContact
        );

        // ==================================================
        // IMAGE
        // ==================================================

        int imageResId =
                getIntent().getIntExtra(
                        "imageResId",
                        0
                );

        if (imageResId != 0) {

            itemImage.setImageResource(
                    imageResId
            );
        }

        // ==================================================
        // BACK BUTTON
        // ==================================================

        backButton.setOnClickListener(v -> {
            finish();
        });

        // ==================================================
        // CANCEL BOOKING
        // ==================================================

        cancelBookingButton.setOnClickListener(v -> {

            showCancelDialog();

        });

        // ==================================================
        // APPLY DARK MODE
        // ==================================================

        applyDarkMode();
    }

    // ==================================================
    // CANCEL DIALOG
    // ==================================================

    private void showCancelDialog() {

        new AlertDialog.Builder(
                BookingDetailActivity.this
        )

                .setTitle(
                        "Cancel Booking"
                )

                .setMessage(
                        "Are you sure you want to cancel " +
                                currentItemName +
                                " booking?"
                )

                .setNegativeButton(
                        "No",
                        null
                )

                .setPositiveButton(
                        "Yes, Cancel",
                        (dialog, which) -> {

                            Intent intent =
                                    new Intent(
                                            BookingDetailActivity.this,
                                            BookingCancelledActivity.class
                                    );

                            // SAME ITEM
                            intent.putExtra(
                                    "itemName",
                                    currentItemName
                            );

                            // SAME BOOKING ID
                            intent.putExtra(
                                    "bookingId",
                                    currentBookingId
                            );

                            // SAME OWNER
                            intent.putExtra(
                                    "ownerName",
                                    currentOwnerName
                            );

                            startActivity(intent);

                            finish();
                        }
                )

                .show();
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

        bookingDetailScrollView.setBackgroundColor(
                Color.BLACK
        );

        bookingDetailMainLayout.setBackgroundColor(
                Color.BLACK
        );

        bookingInfoCard.setCardBackgroundColor(
                DARK_CARD
        );

        ownerDetailsCard.setCardBackgroundColor(
                DARK_CARD
        );

        changeAllTextColor(
                bookingDetailMainLayout,
                Color.WHITE
        );

        setPurpleHeader();

        cancelBookingButton.setBackgroundTintList(
                ColorStateList.valueOf(
                        Color.rgb(211, 47, 47)
                )
        );

        cancelBookingButton.setTextColor(
                Color.WHITE
        );

        statusText.setTextColor(
                Color.rgb(129, 199, 132)
        );
    }

    // ==================================================
    // LIGHT MODE
    // ==================================================

    private void lightMode() {

        bookingDetailScrollView.setBackgroundColor(
                LIGHT_BACKGROUND
        );

        bookingDetailMainLayout.setBackgroundColor(
                LIGHT_BACKGROUND
        );

        bookingInfoCard.setCardBackgroundColor(
                Color.WHITE
        );

        ownerDetailsCard.setCardBackgroundColor(
                Color.WHITE
        );

        changeAllTextColor(
                bookingDetailMainLayout,
                Color.BLACK
        );

        setPurpleHeader();

        cancelBookingButton.setBackgroundTintList(
                ColorStateList.valueOf(
                        Color.rgb(211, 47, 47)
                )
        );

        cancelBookingButton.setTextColor(
                Color.WHITE
        );

        statusText.setTextColor(
                Color.rgb(46, 125, 50)
        );
    }

    // ==================================================
    // PURPLE HEADER
    // ==================================================

    private void setPurpleHeader() {

        headerLayout.setBackgroundColor(
                PURPLE
        );

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

        headerTitle.setTextColor(
                Color.WHITE
        );

        backButton.setTextColor(
                Color.WHITE
        );

        for (int i = 0;
             i < headerLayout.getChildCount();
             i++) {

            View child =
                    headerLayout.getChildAt(i);

            if (!(child instanceof TextView)) {

                child.setBackgroundColor(
                        PURPLE
                );
            }
        }
    }

    // ==================================================
    // CHANGE TEXT COLOR
    // ==================================================

    private void changeAllTextColor(
            View view,
            int color
    ) {

        if (view instanceof TextView) {

            TextView textView =
                    (TextView) view;

            if (textView.getId()
                    != R.id.backButton &&
                    textView.getId()
                            != R.id.headerTitle) {

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

                changeAllTextColor(
                        group.getChildAt(i),
                        color
                );
            }
        }
    }

    // ==================================================
    // ON RESUME
    // ==================================================

    @Override
    protected void onResume() {

        super.onResume();

        if (bookingDetailMainLayout != null) {

            applyDarkMode();
        }
    }
}