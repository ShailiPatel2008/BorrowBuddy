package com.borrowbuddy.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class PaymentActivity extends AppCompatActivity
        implements PaymentResultListener {

    // ==================================================
    // RAZORPAY TEST KEY
    // ==================================================

    // Yahan apni valid Razorpay TEST KEY ID rakho.
    // Example:
    // private static final String RAZORPAY_KEY_ID = "rzp_test_xxxxxxxxxx";

    private static final String RAZORPAY_KEY_ID =
            "rzp_test_TW1nbXrfkT1wt9,iGp24SKYGHBZtPj7VJ9ppXm7\n";


    // ==================================================
    // COLORS
    // ==================================================

    private static final int PURPLE =
            Color.rgb(106, 27, 154);

    private static final int DARK_CARD =
            Color.rgb(43, 41, 50);


    // ==================================================
    // HEADER
    // ==================================================

    MaterialToolbar toolbar;


    // ==================================================
    // BOOKING SUMMARY
    // ==================================================

    TextView txtItem;
    TextView txtDate;
    TextView txtDuration;


    // ==================================================
    // PAYMENT METHOD
    // ==================================================

    RadioGroup paymentMethodGroup;

    RadioButton radioCashOnDelivery;
    RadioButton radioUPI;
    RadioButton radioCard;


    // ==================================================
    // PRICE
    // ==================================================

    TextView txtRentalAmount;
    TextView txtServiceFee;
    TextView txtSecurityDeposit;
    TextView txtTotalAmount;


    // ==================================================
    // SECURITY DEPOSIT
    // ==================================================

    TextView securityDescription;


    // ==================================================
    // BUTTON
    // ==================================================

    Button btnPay;


    // ==================================================
    // LAYOUTS / CARDS
    // ==================================================

    ScrollView paymentScrollView;

    ViewGroup paymentMainLayout;

    MaterialCardView bookingSummaryCard;
    MaterialCardView paymentMethodCard;
    MaterialCardView priceDetailsCard;
    MaterialCardView securityDepositCard;


    // ==================================================
    // PAYMENT VALUES
    // ==================================================

    int totalAmount = 0;

    int rentalAmount = 0;

    int serviceFee = 0;

    int securityDeposit = 0;

    int quantity = 1;

    int duration = 1;

    String itemName = "Item";


    // ==================================================
    // ON CREATE
    // ==================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_payment);


        // ==================================================
        // TOOLBAR
        // ==================================================

        toolbar =
                findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(Color.WHITE);

        toolbar.setNavigationIconTint(Color.WHITE);

        if (getSupportActionBar() != null) {

            getSupportActionBar().setTitle(
                    "Payment"
            );

            getSupportActionBar()
                    .setDisplayHomeAsUpEnabled(true);

        }

        toolbar.setNavigationOnClickListener(v -> finish());


        // ==================================================
        // STATUS BAR
        // ==================================================

        getWindow().setStatusBarColor(PURPLE);

        getWindow()
                .getDecorView()
                .setSystemUiVisibility(0);


        // ==================================================
        // FIND VIEWS
        // ==================================================

        txtItem =
                findViewById(R.id.txtItem);

        txtDate =
                findViewById(R.id.txtDate);

        txtDuration =
                findViewById(R.id.txtDuration);


        paymentMethodGroup =
                findViewById(R.id.paymentMethodGroup);


        radioCashOnDelivery =
                findViewById(R.id.radioCashOnDelivery);

        radioUPI =
                findViewById(R.id.radioUPI);

        radioCard =
                findViewById(R.id.radioCard);


        txtRentalAmount =
                findViewById(R.id.txtRentalAmount);

        txtServiceFee =
                findViewById(R.id.txtServiceFee);

        txtSecurityDeposit =
                findViewById(R.id.txtSecurityDeposit);

        txtTotalAmount =
                findViewById(R.id.txtTotalAmount);


        securityDescription =
                findViewById(R.id.securityDescription);


        btnPay =
                findViewById(R.id.btnPay);


        // ==================================================
        // DARK MODE VIEWS
        // ==================================================

        paymentScrollView =
                findViewById(R.id.paymentScrollView);

        paymentMainLayout =
                findViewById(R.id.paymentMainLayout);


        bookingSummaryCard =
                findViewById(R.id.bookingSummaryCard);

        paymentMethodCard =
                findViewById(R.id.paymentMethodCard);

        priceDetailsCard =
                findViewById(R.id.priceDetailsCard);

        securityDepositCard =
                findViewById(R.id.securityDepositCard);


        // ==================================================
        // GET DATA FROM BOOKING ACTIVITY
        // ==================================================

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


        String receivedItemName =
                getIntent().getStringExtra(
                        "itemName"
                );


        if (receivedItemName != null &&
                !receivedItemName.isEmpty()) {

            itemName =
                    receivedItemName;
        }


        // ==================================================
        // GET OPTIONAL PRICE VALUES
        // ==================================================

        rentalAmount =
                getIntent().getIntExtra(
                        "rentalAmount",
                        0
                );


        serviceFee =
                getIntent().getIntExtra(
                        "serviceFee",
                        0
                );


        securityDeposit =
                getIntent().getIntExtra(
                        "securityDeposit",
                        0
                );


        // ==================================================
        // BOOKING SUMMARY
        // ==================================================

        txtItem.setText(
                "📷  Item: " + itemName
        );


        txtDate.setText(
                "📅  Date: Today"
        );


        txtDuration.setText(
                "⏱  Duration: " +
                        duration +
                        " Days"
        );


        // ==================================================
        // PRICE DETAILS
        // ==================================================

        txtRentalAmount.setText(
                "Rental Amount          ₹" +
                        rentalAmount
        );


        txtServiceFee.setText(
                "Service Fee              ₹" +
                        serviceFee
        );


        txtSecurityDeposit.setText(
                "Security Deposit       ₹" +
                        securityDeposit
        );


        // ==================================================
        // IMPORTANT
        // TOTAL AMOUNT FROM BOOKING ACTIVITY
        // ==================================================

        txtTotalAmount.setText(
                "Total Amount            ₹" +
                        totalAmount
        );


        btnPay.setText(
                "Pay ₹" +
                        totalAmount
        );


        // ==================================================
        // DEFAULT PAYMENT METHOD
        // ==================================================

        radioCashOnDelivery.setChecked(true);


        // ==================================================
        // PAY BUTTON
        // ==================================================

        btnPay.setOnClickListener(v -> {

            int selectedId =
                    paymentMethodGroup
                            .getCheckedRadioButtonId();


            if (selectedId == -1) {

                Toast.makeText(
                        PaymentActivity.this,
                        "Please select a payment method",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }


            RadioButton selectedRadio =
                    findViewById(selectedId);


            String paymentMethod =
                    selectedRadio
                            .getText()
                            .toString();


            // ==================================================
            // CASH ON DELIVERY
            // ==================================================

            if (selectedId ==
                    R.id.radioCashOnDelivery) {

                openBookingSuccess(
                        paymentMethod
                );

                return;
            }


            // ==================================================
            // UPI / CARD
            // ==================================================

            startRazorpayPayment(
                    paymentMethod
            );

        });


        // ==================================================
        // DARK MODE
        // ==================================================

        applyDarkMode();

    }


    // ==================================================
    // ON RESUME
    // ==================================================

    @Override
    protected void onResume() {

        super.onResume();

        if (paymentMainLayout != null) {

            applyDarkMode();

        }

    }


    // ==================================================
    // RAZORPAY PAYMENT
    // ==================================================

    private void startRazorpayPayment(
            String paymentMethod) {

        try {

            Checkout checkout =
                    new Checkout();


            checkout.setKeyID(
                    RAZORPAY_KEY_ID
            );


            JSONObject options =
                    new JSONObject();


            options.put(
                    "name",
                    "BorrowBuddy"
            );


            options.put(
                    "description",
                    "BorrowBuddy Rental Payment"
            );


            options.put(
                    "currency",
                    "INR"
            );


            // Razorpay amount is in paise

            options.put(
                    "amount",
                    totalAmount * 100
            );


            options.put(
                    "prefill.name",
                    "Shweta"
            );


            options.put(
                    "theme.color",
                    "#6A1B9A"
            );


            checkout.open(
                    PaymentActivity.this,
                    options
            );


        } catch (Exception e) {

            Toast.makeText(
                    PaymentActivity.this,
                    "Unable to start payment",
                    Toast.LENGTH_LONG
            ).show();

        }

    }


    // ==================================================
    // RAZORPAY SUCCESS
    // ==================================================

    @Override
    public void onPaymentSuccess(
            String razorpayPaymentID) {

        Toast.makeText(
                PaymentActivity.this,
                "Payment Successful",
                Toast.LENGTH_SHORT
        ).show();


        String paymentMethod =
                "Razorpay";


        int selectedId =
                paymentMethodGroup
                        .getCheckedRadioButtonId();


        if (selectedId != -1) {

            RadioButton selectedRadio =
                    findViewById(selectedId);


            if (selectedRadio != null) {

                paymentMethod =
                        selectedRadio
                                .getText()
                                .toString();

            }

        }


        openBookingSuccess(
                paymentMethod
        );

    }


    // ==================================================
    // RAZORPAY ERROR
    // ==================================================

    @Override
    public void onPaymentError(
            int code,
            String response) {

        Toast.makeText(
                PaymentActivity.this,
                "Payment Failed",
                Toast.LENGTH_LONG
        ).show();

    }


    // ==================================================
    // OPEN BOOKING SUCCESS
    // ==================================================

    private void openBookingSuccess(
            String paymentMethod) {

        Intent intent =
                new Intent(
                        PaymentActivity.this,
                        BookingSuccessActivity.class
                );


        intent.putExtra(
                "itemName",
                itemName
        );


        intent.putExtra(
                "totalAmount",
                totalAmount
        );


        intent.putExtra(
                "paymentMethod",
                paymentMethod
        );


        intent.putExtra(
                "quantity",
                quantity
        );


        intent.putExtra(
                "duration",
                duration
        );


        startActivity(intent);


        finish();

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


        // ==================================================
        // STATUS BAR
        // ==================================================

        getWindow().setStatusBarColor(
                PURPLE
        );


        getWindow()
                .getDecorView()
                .setSystemUiVisibility(0);


        // ==================================================
        // TOOLBAR
        // ==================================================

        toolbar.setBackgroundColor(
                PURPLE
        );

        toolbar.setTitleTextColor(
                Color.WHITE
        );

        toolbar.setNavigationIconTint(
                Color.WHITE
        );


        // ==================================================
        // DARK MODE ON
        // ==================================================

        if (darkMode) {

            paymentScrollView
                    .setBackgroundColor(
                            Color.BLACK
                    );


            paymentMainLayout
                    .setBackgroundColor(
                            Color.BLACK
                    );


            bookingSummaryCard
                    .setCardBackgroundColor(
                            DARK_CARD
                    );


            paymentMethodCard
                    .setCardBackgroundColor(
                            DARK_CARD
                    );


            priceDetailsCard
                    .setCardBackgroundColor(
                            DARK_CARD
                    );


            securityDepositCard
                    .setCardBackgroundColor(
                            DARK_CARD
                    );


            changeAllTextColor(
                    paymentMainLayout,
                    Color.WHITE
            );


            securityDescription.setTextColor(
                    Color.LTGRAY
            );


            radioCashOnDelivery.setTextColor(
                    Color.WHITE
            );


            radioUPI.setTextColor(
                    Color.WHITE
            );


            radioCard.setTextColor(
                    Color.WHITE
            );

        }


        // ==================================================
        // LIGHT MODE
        // ==================================================

        else {

            paymentScrollView
                    .setBackgroundColor(
                            Color.rgb(
                                    248,
                                    249,
                                    250
                            )
                    );


            paymentMainLayout
                    .setBackgroundColor(
                            Color.rgb(
                                    248,
                                    249,
                                    250
                            )
                    );


            bookingSummaryCard
                    .setCardBackgroundColor(
                            Color.WHITE
                    );


            paymentMethodCard
                    .setCardBackgroundColor(
                            Color.WHITE
                    );


            priceDetailsCard
                    .setCardBackgroundColor(
                            Color.WHITE
                    );


            securityDepositCard
                    .setCardBackgroundColor(
                            Color.WHITE
                    );


            changeAllTextColor(
                    paymentMainLayout,
                    Color.BLACK
            );


            securityDescription.setTextColor(
                    Color.rgb(
                            85,
                            85,
                            85
                    )
            );


            radioCashOnDelivery.setTextColor(
                    Color.BLACK
            );


            radioUPI.setTextColor(
                    Color.BLACK
            );


            radioCard.setTextColor(
                    Color.BLACK
            );

        }


        // ==================================================
        // PAY BUTTON
        // ==================================================

        btnPay.setBackgroundTintList(
                ColorStateList.valueOf(
                        PURPLE
                )
        );


        btnPay.setTextColor(
                Color.WHITE
        );

    }


    // ==================================================
    // CHANGE ALL TEXT COLOR
    // ==================================================

    private void changeAllTextColor(
            View view,
            int color) {

        // Do not change toolbar title color
        if (view == toolbar) {
            return;
        }


        if (view instanceof TextView) {

            TextView textView =
                    (TextView) view;


            textView.setTextColor(
                    color
            );

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

}