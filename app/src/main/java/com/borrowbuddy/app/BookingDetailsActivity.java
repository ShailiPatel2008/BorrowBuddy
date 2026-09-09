package com.borrowbuddy.app;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BookingDetailsActivity extends AppCompatActivity {

    LinearLayout mainLayout;

    LinearLayout imageCard;
    LinearLayout bookingInfoCard;
    LinearLayout customerCard;
    LinearLayout ownerCard;
    LinearLayout trustCard;
    LinearLayout itemInfoCard;
    LinearLayout rentalCard;
    LinearLayout pickupCard;

    TextView btnBack;

    TextView titleBookingInfo;
    TextView titleCustomer;
    TextView titleOwner;
    TextView titleTrust;
    TextView titleItem;
    TextView titleRental;
    TextView titlePickup;

    TextView txtBookingId;
    TextView txtBookingDate;
    TextView txtStatus;

    TextView txtCustomerName;
    TextView txtCustomerPhone;

    TextView txtOwnerName;
    TextView txtOwnerPhone;

    TextView txtTrustScore;
    TextView txtReviews;

    TextView txtItemName;
    TextView txtCategory;
    TextView txtCondition;
    TextView txtQuantity;

    TextView txtStartDate;
    TextView txtEndDate;
    TextView txtPricePerDay;
    TextView txtDeposit;
    TextView txtTotalAmount;

    TextView txtPickup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_booking_details
        );


        // ================= FIND VIEWS =================

        mainLayout = findViewById(
                R.id.mainLayout
        );

        imageCard = findViewById(
                R.id.imageCard
        );

        bookingInfoCard = findViewById(
                R.id.bookingInfoCard
        );

        customerCard = findViewById(
                R.id.customerCard
        );

        ownerCard = findViewById(
                R.id.ownerCard
        );

        trustCard = findViewById(
                R.id.trustCard
        );

        itemInfoCard = findViewById(
                R.id.itemInfoCard
        );

        rentalCard = findViewById(
                R.id.rentalCard
        );

        pickupCard = findViewById(
                R.id.pickupCard
        );


        btnBack = findViewById(
                R.id.btnBack
        );


        titleBookingInfo = findViewById(
                R.id.titleBookingInfo
        );

        titleCustomer = findViewById(
                R.id.titleCustomer
        );

        titleOwner = findViewById(
                R.id.titleOwner
        );

        titleTrust = findViewById(
                R.id.titleTrust
        );

        titleItem = findViewById(
                R.id.titleItem
        );

        titleRental = findViewById(
                R.id.titleRental
        );

        titlePickup = findViewById(
                R.id.titlePickup
        );


        txtBookingId = findViewById(
                R.id.txtBookingId
        );

        txtBookingDate = findViewById(
                R.id.txtBookingDate
        );

        txtStatus = findViewById(
                R.id.txtStatus
        );


        txtCustomerName = findViewById(
                R.id.txtCustomerName
        );

        txtCustomerPhone = findViewById(
                R.id.txtCustomerPhone
        );


        txtOwnerName = findViewById(
                R.id.txtOwnerName
        );

        txtOwnerPhone = findViewById(
                R.id.txtOwnerPhone
        );


        txtTrustScore = findViewById(
                R.id.txtTrustScore
        );

        txtReviews = findViewById(
                R.id.txtReviews
        );


        txtItemName = findViewById(
                R.id.txtItemName
        );

        txtCategory = findViewById(
                R.id.txtCategory
        );

        txtCondition = findViewById(
                R.id.txtCondition
        );

        txtQuantity = findViewById(
                R.id.txtQuantity
        );


        txtStartDate = findViewById(
                R.id.txtStartDate
        );

        txtEndDate = findViewById(
                R.id.txtEndDate
        );

        txtPricePerDay = findViewById(
                R.id.txtPricePerDay
        );

        txtDeposit = findViewById(
                R.id.txtDeposit
        );

        txtTotalAmount = findViewById(
                R.id.txtTotalAmount
        );


        txtPickup = findViewById(
                R.id.txtPickup
        );


        // ================= BACK =================

        btnBack.setOnClickListener(
                v -> finish()
        );


        // ================= LOAD DATA =================

        loadBookingData();

        applyDarkMode();
    }


    // =====================================================
    // LOAD BOOKING DATA
    // =====================================================

    private void loadBookingData() {

        String bookingId =
                getIntent().getStringExtra(
                        "bookingId"
                );

        String bookingDate =
                getIntent().getStringExtra(
                        "bookingDate"
                );

        String customerName =
                getIntent().getStringExtra(
                        "customerName"
                );

        String customerPhone =
                getIntent().getStringExtra(
                        "customerPhone"
                );

        String ownerName =
                getIntent().getStringExtra(
                        "ownerName"
                );

        String ownerPhone =
                getIntent().getStringExtra(
                        "ownerPhone"
                );

        String itemName =
                getIntent().getStringExtra(
                        "itemName"
                );

        String category =
                getIntent().getStringExtra(
                        "category"
                );

        String condition =
                getIntent().getStringExtra(
                        "condition"
                );

        String quantity =
                getIntent().getStringExtra(
                        "quantity"
                );

        String startDate =
                getIntent().getStringExtra(
                        "startDate"
                );

        String endDate =
                getIntent().getStringExtra(
                        "endDate"
                );

        String pricePerDay =
                getIntent().getStringExtra(
                        "pricePerDay"
                );

        String deposit =
                getIntent().getStringExtra(
                        "deposit"
                );

        String totalAmount =
                getIntent().getStringExtra(
                        "totalAmount"
                );

        String pickup =
                getIntent().getStringExtra(
                        "pickup"
                );

        String trustScore =
                getIntent().getStringExtra(
                        "trustScore"
                );

        String reviews =
                getIntent().getStringExtra(
                        "reviews"
                );


        // ================= SET TEXT =================

        if (bookingId != null) {
            txtBookingId.setText(
                    "Booking ID: " + bookingId
            );
        }

        if (bookingDate != null) {
            txtBookingDate.setText(
                    "Booking Date: " + bookingDate
            );
        }

        // New Booking Details screen opened from Manage Booking
        txtStatus.setText(
                "Status: Pending"
        );


        if (customerName != null) {
            txtCustomerName.setText(
                    "Customer Name: " + customerName
            );
        }

        if (customerPhone != null) {
            txtCustomerPhone.setText(
                    "Contact Number: " + customerPhone
            );
        }


        if (ownerName != null) {
            txtOwnerName.setText(
                    "Owner Name: " + ownerName
            );
        }

        if (ownerPhone != null) {
            txtOwnerPhone.setText(
                    "Contact Number: " + ownerPhone
            );
        }


        if (trustScore != null) {
            txtTrustScore.setText(
                    "Trust Score: " + trustScore + " ⭐"
            );
        }

        if (reviews != null) {
            txtReviews.setText(
                    "Reviews: " + reviews
            );
        }


        if (itemName != null) {
            txtItemName.setText(
                    "Item Name: " + itemName
            );
        }

        if (category != null) {
            txtCategory.setText(
                    "Category: " + category
            );
        }

        if (condition != null) {
            txtCondition.setText(
                    "Condition: " + condition
            );
        }

        if (quantity != null) {
            txtQuantity.setText(
                    "Quantity: " + quantity
            );
        }


        if (startDate != null) {
            txtStartDate.setText(
                    "Start Date: " + startDate
            );
        }

        if (endDate != null) {
            txtEndDate.setText(
                    "End Date: " + endDate
            );
        }

        if (pricePerDay != null) {
            txtPricePerDay.setText(
                    "Price Per Day: " + pricePerDay
            );
        }

        if (deposit != null) {
            txtDeposit.setText(
                    "Security Deposit: " + deposit
            );
        }

        if (totalAmount != null) {
            txtTotalAmount.setText(
                    "Total Amount: " + totalAmount
            );
        }


        if (pickup != null) {
            txtPickup.setText(
                    "Pickup Location: " + pickup
            );
        }
    }


    // =====================================================
    // DARK MODE
    // =====================================================

    @Override
    protected void onResume() {

        super.onResume();

        applyDarkMode();
    }


    private void applyDarkMode() {

        boolean darkMode =
                getSharedPreferences(
                        "Settings",
                        MODE_PRIVATE
                ).getBoolean(
                        "dark_mode",
                        false
                );


        LinearLayout[] cards = {

                imageCard,
                bookingInfoCard,
                customerCard,
                ownerCard,
                trustCard,
                itemInfoCard,
                rentalCard,
                pickupCard
        };


        TextView[] texts = {

                titleBookingInfo,
                titleCustomer,
                titleOwner,
                titleTrust,
                titleItem,
                titleRental,
                titlePickup,

                txtBookingId,
                txtBookingDate,
                txtStatus,

                txtCustomerName,
                txtCustomerPhone,

                txtOwnerName,
                txtOwnerPhone,

                txtTrustScore,
                txtReviews,

                txtItemName,
                txtCategory,
                txtCondition,
                txtQuantity,

                txtStartDate,
                txtEndDate,
                txtPricePerDay,
                txtDeposit,
                txtTotalAmount,

                txtPickup
        };


        if (darkMode) {

            mainLayout.setBackgroundColor(
                    Color.rgb(16, 16, 16)
            );

            for (LinearLayout card : cards) {

                card.setBackgroundColor(
                        Color.rgb(35, 35, 35)
                );
            }

            for (TextView text : texts) {

                text.setTextColor(
                        Color.WHITE
                );
            }

        } else {

            mainLayout.setBackgroundColor(
                    Color.rgb(246, 243, 248)
            );

            for (LinearLayout card : cards) {

                card.setBackgroundColor(
                        Color.WHITE
                );
            }

            for (TextView text : texts) {

                text.setTextColor(
                        Color.BLACK
                );
            }
        }


        // Toolbar always purple / white

        btnBack.setTextColor(
                Color.WHITE
        );
    }
}