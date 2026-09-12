package com.borrowbuddy.app;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class BookingActivity extends AppCompatActivity {

    private MaterialToolbar toolbar;

    TextView itemName;
    TextView itemPrice;
    TextView ownerName;
    TextView availableQuantity;

    ImageView itemImage;

    Button btnMinus;
    Button btnPlus;
    TextView txtQuantity;

    Button btnStartDate;
    Button btnEndDate;
    TextView txtDuration;

    TextView txtCalculation;
    TextView txtRentalAmount;
    TextView txtDepositAmount;
    TextView txtTotal;

    TextView txtPickupLocation;
    TextView txtOwnerContact;

    Button btnConfirmBooking;

    View bookingLayout;

    MaterialCardView selectedItemCard;
    MaterialCardView quantityCard;
    MaterialCardView rentalPeriodCard;
    MaterialCardView priceDetailsCard;
    MaterialCardView pickupLocationCard;

    private static final int PURPLE =
            Color.rgb(106, 27, 154);

    private static final int DARK_CARD =
            Color.rgb(43, 41, 50);

    private static final int LIGHT_BACKGROUND =
            Color.rgb(248, 249, 250);

    int quantity = 2;
    int available = 5;

    int pricePerDay = 300;
    int securityDepositPerUnit = 2000;

    long startDateMillis = 0;
    long endDateMillis = 0;

    int durationDays = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_booking);

        // =========================
        // TOOLBAR
        // =========================

        toolbar =
                findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIconTint(Color.WHITE);
        getWindow().setStatusBarColor(
                Color.rgb(108, 74, 182)
        );

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(
                    "Confirm Booking"
            );

            getSupportActionBar()
                    .setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(
                v -> finish()
        );


        // =========================
        // FIND VIEWS
        // =========================

        bookingLayout =
                findViewById(R.id.bookingLayout);

        selectedItemCard =
                findViewById(R.id.selectedItemCard);

        quantityCard =
                findViewById(R.id.quantityCard);

        rentalPeriodCard =
                findViewById(R.id.rentalPeriodCard);

        priceDetailsCard =
                findViewById(R.id.priceDetailsCard);

        pickupLocationCard =
                findViewById(R.id.pickupLocationCard);

        itemName =
                findViewById(R.id.itemName);

        itemPrice =
                findViewById(R.id.itemPrice);

        ownerName =
                findViewById(R.id.ownerName);

        availableQuantity =
                findViewById(R.id.availableQuantity);

        itemImage =
                findViewById(R.id.itemImage);

        btnMinus =
                findViewById(R.id.btnMinus);

        btnPlus =
                findViewById(R.id.btnPlus);

        txtQuantity =
                findViewById(R.id.txtQuantity);

        btnStartDate =
                findViewById(R.id.btnStartDate);

        btnEndDate =
                findViewById(R.id.btnEndDate);

        txtDuration =
                findViewById(R.id.txtDuration);

        txtCalculation =
                findViewById(R.id.txtCalculation);

        txtRentalAmount =
                findViewById(R.id.txtRentalAmount);

        txtDepositAmount =
                findViewById(R.id.txtDepositAmount);

        txtTotal =
                findViewById(R.id.txtTotal);

        txtPickupLocation =
                findViewById(R.id.txtPickupLocation);

        txtOwnerContact =
                findViewById(R.id.txtOwnerContact);

        btnConfirmBooking =
                findViewById(R.id.btnConfirmBooking);

        // =========================
        // RECEIVE ITEM DATA
        // =========================

        Intent receivedIntent =
                getIntent();

        String receivedName =
                receivedIntent.getStringExtra("name");

        String receivedPrice =
                receivedIntent.getStringExtra("price");

        String receivedLocation =
                receivedIntent.getStringExtra("location");

        String receivedRating =
                receivedIntent.getStringExtra("rating");

        String receivedCategory =
                receivedIntent.getStringExtra("category");

        // =========================
        // ITEM NAME
        // =========================

        if (receivedName != null &&
                !receivedName.trim().isEmpty()) {

            itemName.setText(receivedName);

        } else {

            itemName.setText("Selected Item");
        }

        // =========================
        // PRICE
        // =========================

        if (receivedPrice != null &&
                !receivedPrice.trim().isEmpty()) {

            itemPrice.setText(receivedPrice);

            String numberOnly =
                    receivedPrice.replaceAll(
                            "[^0-9]",
                            ""
                    );

            if (!numberOnly.isEmpty()) {

                try {

                    pricePerDay =
                            Integer.parseInt(
                                    numberOnly
                            );

                } catch (Exception ignored) {
                }
            }

        } else {

            itemPrice.setText(
                    "₹300 / day / unit"
            );
        }

        // =========================
        // LOCATION
        // =========================

        if (receivedLocation != null &&
                !receivedLocation.trim().isEmpty()) {

            txtPickupLocation.setText(
                    "📍 " +
                            receivedLocation
            );

        } else {

            txtPickupLocation.setText(
                    "📍 Ahmedabad"
            );
        }

        // =========================
        // CATEGORY
        // =========================

        if (receivedCategory != null &&
                !receivedCategory.trim().isEmpty()) {

            // Category available
            // for future Firebase use
        }

        // =========================
        // DEFAULT OWNER
        // =========================

        ownerName.setText(
                "Owner: Not Available"
        );

        txtOwnerContact.setText(
                "📞 Contact available after booking"
        );

        availableQuantity.setText(
                "Available Quantity: " +
                        available
        );

        txtQuantity.setText(
                String.valueOf(quantity)
        );

        // =========================
        // MINUS
        // =========================

        btnMinus.setOnClickListener(v -> {

            if (quantity > 1) {

                quantity--;

                txtQuantity.setText(
                        String.valueOf(quantity)
                );

                calculateAmount();

                updateQuantityButtons();
            }
        });

        // =========================
        // PLUS
        // =========================

        btnPlus.setOnClickListener(v -> {

            if (quantity < available) {

                quantity++;

                txtQuantity.setText(
                        String.valueOf(quantity)
                );

                calculateAmount();

                updateQuantityButtons();
            }
        });

        updateQuantityButtons();

        // =========================
        // START DATE
        // =========================

        btnStartDate.setOnClickListener(
                v -> openStartDatePicker()
        );

        // =========================
        // END DATE
        // =========================

        btnEndDate.setOnClickListener(
                v -> openEndDatePicker()
        );

        // =========================
        // INITIAL CALCULATION
        // =========================

        calculateAmount();

        // =========================
        // DARK MODE
        // =========================

        applyDarkMode();

        // =========================
        // CONFIRM BOOKING
        // =========================

        btnConfirmBooking.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            BookingActivity.this,
                            PaymentActivity.class
                    );

            // =========================
            // SELECTED ITEM
            // =========================

            intent.putExtra(
                    "itemName",
                    itemName.getText().toString()
            );

            intent.putExtra(
                    "name",
                    itemName.getText().toString()
            );

            intent.putExtra(
                    "itemPrice",
                    itemPrice.getText().toString()
            );

            intent.putExtra(
                    "location",
                    receivedLocation != null
                            ? receivedLocation
                            : "Ahmedabad"
            );

            intent.putExtra(
                    "category",
                    receivedCategory != null
                            ? receivedCategory
                            : ""
            );

            // =========================
            // BOOKING INFORMATION
            // =========================

            intent.putExtra(
                    "quantity",
                    quantity
            );

            intent.putExtra(
                    "duration",
                    durationDays
            );

            // =========================
            // PAYMENT CALCULATION
            // =========================

            int rentalAmount =
                    pricePerDay *
                            quantity *
                            durationDays;

            int serviceFee = 0;

            int securityDeposit =
                    securityDepositPerUnit *
                            quantity;

            int totalAmount =
                    rentalAmount +
                            serviceFee +
                            securityDeposit;

            // =========================
            // SEND PAYMENT DETAILS
            // =========================

            intent.putExtra(
                    "rentalAmount",
                    rentalAmount
            );

            intent.putExtra(
                    "serviceFee",
                    serviceFee
            );

            intent.putExtra(
                    "securityDeposit",
                    securityDeposit
            );

            intent.putExtra(
                    "totalAmount",
                    totalAmount
            );

            startActivity(intent);
        });
    }

    // ==================================================
    // QUANTITY BUTTONS
    // ==================================================

    private void updateQuantityButtons() {

        btnMinus.setEnabled(
                quantity > 1
        );

        btnPlus.setEnabled(
                quantity < available
        );

        btnMinus.setAlpha(
                quantity > 1
                        ? 1f
                        : 0.5f
        );

        btnPlus.setAlpha(
                quantity < available
                        ? 1f
                        : 0.5f
        );

        // WHITE + / -
        btnMinus.setTextColor(
                Color.WHITE
        );

        btnPlus.setTextColor(
                Color.WHITE
        );

        btnMinus.setBackgroundTintList(
                ColorStateList.valueOf(
                        PURPLE
                )
        );

        btnPlus.setBackgroundTintList(
                ColorStateList.valueOf(
                        PURPLE
                )
        );
    }

    // ==================================================
    // START DATE
    // ==================================================

    private void openStartDatePicker() {

        Calendar calendar =
                Calendar.getInstance();

        DatePickerDialog dialog =
                new DatePickerDialog(
                        this,
                        (view, year, month, day) -> {

                            Calendar selected =
                                    Calendar.getInstance();

                            selected.set(
                                    year,
                                    month,
                                    day,
                                    0,
                                    0,
                                    0
                            );

                            startDateMillis =
                                    selected
                                            .getTimeInMillis();

                            btnStartDate.setText(
                                    day + " " +
                                            getMonthName(
                                                    month
                                            ) +
                                            " " +
                                            year
                            );

                            calculateDuration();
                        },
                        calendar.get(
                                Calendar.YEAR
                        ),
                        calendar.get(
                                Calendar.MONTH
                        ),
                        calendar.get(
                                Calendar.DAY_OF_MONTH
                        )
                );

        dialog.show();
    }

    // ==================================================
    // END DATE
    // ==================================================

    private void openEndDatePicker() {

        Calendar calendar =
                Calendar.getInstance();

        DatePickerDialog dialog =
                new DatePickerDialog(
                        this,
                        (view, year, month, day) -> {

                            Calendar selected =
                                    Calendar.getInstance();

                            selected.set(
                                    year,
                                    month,
                                    day,
                                    0,
                                    0,
                                    0
                            );

                            endDateMillis =
                                    selected
                                            .getTimeInMillis();

                            btnEndDate.setText(
                                    day + " " +
                                            getMonthName(
                                                    month
                                            ) +
                                            " " +
                                            year
                            );

                            calculateDuration();
                        },
                        calendar.get(
                                Calendar.YEAR
                        ),
                        calendar.get(
                                Calendar.MONTH
                        ),
                        calendar.get(
                                Calendar.DAY_OF_MONTH
                        )
                );

        dialog.show();
    }

    // ==================================================
    // DURATION
    // ==================================================

    private void calculateDuration() {

        if (startDateMillis == 0 ||
                endDateMillis == 0) {

            return;
        }

        if (endDateMillis < startDateMillis) {

            txtDuration.setText(
                    "Duration: Invalid dates"
            );

            return;
        }

        long difference =
                endDateMillis -
                        startDateMillis;

        durationDays =
                (int) TimeUnit.MILLISECONDS
                        .toDays(
                                difference
                        ) + 1;

        txtDuration.setText(
                "Duration: " +
                        durationDays +
                        " Days"
        );

        calculateAmount();
    }

    // ==================================================
    // CALCULATE AMOUNT
    // ==================================================

    private void calculateAmount() {

        int rentalAmount =
                pricePerDay *
                        quantity *
                        durationDays;

        int depositAmount =
                securityDepositPerUnit *
                        quantity;

        int totalAmount =
                rentalAmount +
                        depositAmount;

        txtCalculation.setText(
                "₹" +
                        pricePerDay +
                        " × " +
                        quantity +
                        " units × " +
                        durationDays +
                        " days"
        );

        txtRentalAmount.setText(
                "Rental Amount          ₹" +
                        rentalAmount
        );

        txtDepositAmount.setText(
                "₹" +
                        securityDepositPerUnit +
                        " × " +
                        quantity +
                        " units       ₹" +
                        depositAmount
        );

        txtTotal.setText(
                "Total Amount          ₹" +
                        totalAmount
        );
    }

    // ==================================================
    // TOTAL
    // ==================================================

    private int calculateTotal() {

        int rentalAmount =
                pricePerDay *
                        quantity *
                        durationDays;

        int depositAmount =
                securityDepositPerUnit *
                        quantity;

        return rentalAmount +
                depositAmount;
    }

    // ==================================================
    // MONTH NAME
    // ==================================================

    private String getMonthName(
            int month) {

        String[] months = {
                "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December"
        };

        return months[month];
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

        Color.rgb(108, 74, 182);

        // =========================
        // TOOLBAR
        // =========================

        toolbar.setBackgroundColor(
                Color.rgb(108, 74, 182)
        );
        toolbar.setTitleTextColor(
                Color.WHITE
        );

        toolbar.setNavigationIconTint(
                Color.WHITE
        );

        // =========================
        // BUTTON COLORS
        // =========================

        btnMinus.setBackgroundTintList(
                ColorStateList.valueOf(
                        PURPLE
                )
        );

        btnPlus.setBackgroundTintList(
                ColorStateList.valueOf(
                        PURPLE
                )
        );

        btnStartDate.setBackgroundTintList(
                ColorStateList.valueOf(
                        PURPLE
                )
        );

        btnEndDate.setBackgroundTintList(
                ColorStateList.valueOf(
                        PURPLE
                )
        );

        btnConfirmBooking
                .setBackgroundTintList(
                        ColorStateList.valueOf(
                                PURPLE
                        )
                );

        // WHITE BUTTON TEXT
        btnMinus.setTextColor(
                Color.WHITE
        );

        btnPlus.setTextColor(
                Color.WHITE
        );

        btnStartDate.setTextColor(
                Color.WHITE
        );

        btnEndDate.setTextColor(
                Color.WHITE
        );

        btnConfirmBooking.setTextColor(
                Color.WHITE
        );

        // =========================
        // DARK MODE ON
        // =========================

            if (darkMode) {

                getWindow().setNavigationBarColor(Color.BLACK);

                bookingLayout.setBackgroundColor(
                        Color.BLACK
                );

            selectedItemCard
                    .setCardBackgroundColor(
                            DARK_CARD
                    );

            quantityCard
                    .setCardBackgroundColor(
                            DARK_CARD
                    );

            rentalPeriodCard
                    .setCardBackgroundColor(
                            DARK_CARD
                    );

            priceDetailsCard
                    .setCardBackgroundColor(
                            DARK_CARD
                    );

            pickupLocationCard
                    .setCardBackgroundColor(
                            DARK_CARD
                    );

            setTextColorInside(
                    selectedItemCard,
                    Color.WHITE
            );

            setTextColorInside(
                    quantityCard,
                    Color.WHITE
            );

            setTextColorInside(
                    rentalPeriodCard,
                    Color.WHITE
            );

            setTextColorInside(
                    priceDetailsCard,
                    Color.WHITE
            );

            setTextColorInside(
                    pickupLocationCard,
                    Color.WHITE
            );

            // Keep date buttons WHITE
            btnStartDate.setTextColor(
                    Color.WHITE
            );

            btnEndDate.setTextColor(
                    Color.WHITE
            );

            // Keep + and - WHITE
            btnMinus.setTextColor(
                    Color.WHITE
            );

            btnPlus.setTextColor(
                    Color.WHITE
            );

        } else {

            bookingLayout.setBackgroundColor(
                    LIGHT_BACKGROUND
            );

            selectedItemCard
                    .setCardBackgroundColor(
                            Color.WHITE
                    );

            quantityCard
                    .setCardBackgroundColor(
                            Color.WHITE
                    );

            rentalPeriodCard
                    .setCardBackgroundColor(
                            Color.WHITE
                    );

            priceDetailsCard
                    .setCardBackgroundColor(
                            Color.WHITE
                    );

            pickupLocationCard
                    .setCardBackgroundColor(
                            Color.WHITE
                    );

            setTextColorInside(
                    selectedItemCard,
                    Color.BLACK
            );

            setTextColorInside(
                    quantityCard,
                    Color.BLACK
            );

            setTextColorInside(
                    rentalPeriodCard,
                    Color.BLACK
            );

            setTextColorInside(
                    priceDetailsCard,
                    Color.BLACK
            );

            setTextColorInside(
                    pickupLocationCard,
                    Color.BLACK
            );

            // Keep + and - WHITE in light mode
            btnMinus.setTextColor(
                    Color.WHITE
            );

            btnPlus.setTextColor(
                    Color.WHITE
            );

            // Keep Start Date and End Date WHITE in light mode
            btnStartDate.setTextColor(
                    Color.WHITE
            );

            btnEndDate.setTextColor(
                    Color.WHITE
            );

        }

            // =========================
            // STATUS BAR
            // =========================

            getWindow().setStatusBarColor(
                Color.rgb(108, 74, 182)
            );
        }


    // ==================================================
    // SET TEXT COLOR INSIDE CARD
    // ==================================================

    private void setTextColorInside(
            ViewGroup parent,
            int color) {

        for (int i = 0;
             i < parent.getChildCount();
             i++) {

            View child =
                    parent.getChildAt(i);

            if (child instanceof TextView) {

                TextView textView =
                        (TextView) child;

                textView.setTextColor(
                        color
                );

            } else if (child instanceof ViewGroup) {

                setTextColorInside(
                        (ViewGroup) child,
                        color
                );
            }
        }
    }
}

