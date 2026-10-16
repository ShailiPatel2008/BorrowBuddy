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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.appbar.MaterialToolbar;

public class MyBookingsActivity extends AppCompatActivity {

    // =========================
    // VIEWS
    // =========================

    MaterialToolbar toolbar;

    ImageView bookingItemImage;

    TextView bookingItemName;
    TextView bookingItemPrice;
    TextView bookingDate;
    TextView bookingDuration;
    TextView bookingTotal;
    TextView bookingStatus;

    Button viewDetailsButton;
    Button giveReviewButton;

    ScrollView myBookingsScrollView;
    LinearLayout myBookingsMainLayout;

    MaterialCardView bookingCard;

    // =========================
    // PREFERENCES
    // =========================

    private static final String PREF_NAME = "BorrowBuddy";
    private static final String DARK_MODE = "darkMode";

    // =========================
    // COLORS
    // =========================

    private static final int HOME_PURPLE =
            Color.rgb(106, 27, 154);

    private static final int BUTTON_PURPLE =
            Color.rgb(103, 58, 183);

    // =========================
    // BOOKING DATA
    // =========================

    private String itemName = "";
    private int totalAmount = 0;
    private int duration = 0;
    private int quantity = 0;
    private String itemPrice = "";
    // =========================
    // OWNER DATA
    // =========================
    private String bookingId = "";
    private String ownerId="";
    private String itemId="";
    private String startDate = "";
    private String endDate = "";
    private String ownerName = "";

    private String ownerContact = "";
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private String bookingStatusValue = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_my_bookings);

        // =========================
        // HIDE ACTION BAR
        // =========================

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("My Bookings");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(v -> finish());
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



        bookingItemImage =
                findViewById(R.id.bookingItemImage);

        bookingItemName =
                findViewById(R.id.bookingItemName);

        bookingItemPrice =
                findViewById(R.id.bookingItemPrice);

        bookingDate =
                findViewById(R.id.bookingDate);

        bookingDuration =
                findViewById(R.id.bookingDuration);

        bookingTotal =
                findViewById(R.id.bookingTotal);

        bookingStatus =
                findViewById(R.id.bookingStatus);

        viewDetailsButton =
                findViewById(R.id.viewDetailsButton);

        giveReviewButton =
                findViewById(R.id.giveReviewButton);

        myBookingsScrollView =
                findViewById(R.id.myBookingsScrollView);

        myBookingsMainLayout =
                findViewById(R.id.myBookingsMainLayout);

        bookingCard =
                findViewById(R.id.bookingCard);

        // ==================================================
        // GET BOOKING DATA
        // ==================================================



        // ==================================================
        // GET OWNER ACCORDING TO ITEM
        // ==================================================

        // ==================================================
    // OWNER ACCORDING TO ITEM
    // ==================================================


        // ==================================================
        // SHOW BOOKING DATA
        // ==================================================



        // ==================================================
        // ITEM IMAGE
        // ==================================================

        bookingItemImage.setImageResource(
                R.drawable.ic_launcher_foreground
        );


        // ==================================================
        // VIEW DETAILS
        // ==================================================


        giveReviewButton.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MyBookingsActivity.this,
                            RentalReviewActivity.class
                    );

            // SAME ITEM
            intent.putExtra(
                    "itemName",
                    itemName
            );

            // SAME OWNER
            intent.putExtra(
                    "ownerName",
                    ownerName
            );

            // STATUS
            intent.putExtra(
                    "status",
                    bookingStatusValue
            );


            startActivity(intent);
        });

        // ==================================================
        // DARK MODE
        // ==================================================
        loadBookingData();
        applyDarkMode();
    }
    private void loadBookingData() {

        if(auth.getCurrentUser() == null){
            return;
        }

        String uid = auth.getCurrentUser().getUid();

        db.collection("Bookings")
                .whereEqualTo("userId", uid)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {

                    if(queryDocumentSnapshots.isEmpty()){

                        bookingCard.setVisibility(View.GONE);

                        return;
                    }

                    for(QueryDocumentSnapshot document : queryDocumentSnapshots){
                        System.out.println("========== BOOKING ==========");
                        System.out.println(document.getData());
                        System.out.println("=============================");

                        itemName = document.getString("itemName");


                        Long amount = document.getLong("totalAmount");
                        if(amount != null){
                            totalAmount = amount.intValue();
                        }


                        Long durationValue = document.getLong("duration");
                        if(durationValue != null){
                            duration = durationValue.intValue();
                        }


                        Long quantityValue = document.getLong("quantity");
                        if(quantityValue != null){
                            quantity = quantityValue.intValue();
                        }
                        bookingStatusValue = document.getString("status");

                        if (bookingStatusValue == null) {
                            bookingStatusValue = "Completed";
                        }

                        itemPrice = document.getString("rentalPrice");
                        bookingId = document.getId();

                        startDate = document.getString("startDate");

                        endDate = document.getString("endDate");

                        ownerName = document.getString("ownerName");
                        ownerContact = document.getString("ownerContact");
                        if(ownerName == null || ownerName.isEmpty()){
                            setOwnerAccordingToItem();
                        }

                        // UPDATE UI HERE
                        bookingItemName.setText(itemName);
                        bookingItemPrice.setText("₹ " + itemPrice);
                        bookingDuration.setText(duration + " Days");
                        bookingTotal.setText("Total: ₹ " + totalAmount);
                        bookingStatus.setText(bookingStatusValue);

                        viewDetailsButton.setOnClickListener(v -> {

                            Intent intent = new Intent(
                                    MyBookingsActivity.this,
                                    BookingDetailActivity.class
                            );

                            intent.putExtra("itemName", itemName);
                            intent.putExtra("price", itemPrice);
                            intent.putExtra("duration", duration + " Days");

                            intent.putExtra("status", bookingStatusValue);

                            intent.putExtra("bookingId", bookingId);

                            intent.putExtra("ownerName", ownerName);
                            intent.putExtra("ownerContact", ownerContact);

                            intent.putExtra("startDate", startDate);
                            intent.putExtra("endDate", endDate);

                            startActivity(intent);

                        });
                        break;
                    }

                })
                .addOnFailureListener(e -> {

                    bookingItemName.setText(
                            "Error: " + e.getMessage()
                    );

                    System.out.println(
                            "BOOKING ERROR = " + e.getMessage()
                    );

                });
    }

    // ==================================================
    // OWNER ACCORDING TO ITEM
    // ==================================================

    private void setOwnerAccordingToItem() {

        if (itemName.equalsIgnoreCase("Canon Camera")) {

            ownerName = "Rahul Patel";
            ownerContact = "9876543210";

        } else if (itemName.equalsIgnoreCase("Laptop")) {

            ownerName = "Neha Shah";
            ownerContact = "9876501234";

        } else if (itemName.equalsIgnoreCase("Bicycle")) {

            ownerName = "Amit Patel";
            ownerContact = "9898989898";

        } else {

            ownerName = "Unknown Owner";
            ownerContact = "Not Available";
        }
    }


    // ==================================================
    // ON RESUME
    // ==================================================

    @Override
    protected void onResume() {

        super.onResume();

        if (myBookingsMainLayout != null) {
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

        myBookingsScrollView.setBackgroundColor(
                Color.BLACK
        );

        myBookingsMainLayout.setBackgroundColor(
                Color.BLACK
        );

        bookingCard.setCardBackgroundColor(
                Color.rgb(43, 41, 50)
        );

        bookingCard.setStrokeColor(
                Color.WHITE
        );

        bookingCard.setStrokeWidth(1);

        changeAllTextColor(
                myBookingsMainLayout,
                Color.WHITE
        );

        toolbar.setBackgroundColor(
                Color.rgb(108, 74, 182)
        );
        toolbar.setTitleTextColor(Color.WHITE);

        getWindow().setStatusBarColor(Color.rgb(108, 74, 182));
        bookingItemPrice.setTextColor(
                Color.rgb(186, 104, 200)
        );

        bookingStatus.setTextColor(
                Color.rgb(129, 199, 132)
        );

        bookingStatus.setBackgroundTintList(
                ColorStateList.valueOf(
                        Color.rgb(27, 60, 30)
                )
        );

        viewDetailsButton.setBackgroundTintList(
                ColorStateList.valueOf(
                        BUTTON_PURPLE
                )
        );

        viewDetailsButton.setTextColor(
                Color.WHITE
        );

        giveReviewButton.setBackgroundTintList(
                ColorStateList.valueOf(
                        BUTTON_PURPLE
                )
        );

        giveReviewButton.setTextColor(
                Color.WHITE
        );
    }


    // ==================================================
    // LIGHT MODE
    // ==================================================

    private void lightMode() {

        myBookingsScrollView.setBackgroundColor(
                Color.rgb(248, 249, 250)
        );

        myBookingsMainLayout.setBackgroundColor(
                Color.rgb(248, 249, 250)
        );

        bookingCard.setCardBackgroundColor(
                Color.WHITE
        );

        bookingCard.setStrokeColor(
                Color.rgb(221, 221, 221)
        );

        bookingCard.setStrokeWidth(1);

        changeAllTextColor(
                myBookingsMainLayout,
                Color.BLACK
        );

        toolbar.setBackgroundColor(
                Color.rgb(108, 74, 182)
        );
        toolbar.setTitleTextColor(Color.WHITE);

        getWindow().setStatusBarColor(Color.rgb(108, 74, 182));

        bookingItemPrice.setTextColor(
                HOME_PURPLE
        );

        bookingStatus.setTextColor(
                Color.rgb(46, 125, 50)
        );

        bookingStatus.setBackgroundTintList(
                ColorStateList.valueOf(
                        Color.rgb(232, 245, 233)
                )
        );

        viewDetailsButton.setBackgroundTintList(
                ColorStateList.valueOf(
                        BUTTON_PURPLE
                )
        );

        viewDetailsButton.setTextColor(
                Color.WHITE
        );

        giveReviewButton.setBackgroundTintList(
                ColorStateList.valueOf(
                        BUTTON_PURPLE
                )
        );

        giveReviewButton.setTextColor(
                Color.WHITE
        );
    }


    // ==================================================
    // HEADER TEXT WHITE
    // ==================================================

    private void setHeaderTextWhite(View view) {

        if (view instanceof TextView) {

            ((TextView) view).setTextColor(
                    Color.WHITE
            );
        }

        if (view instanceof ViewGroup) {

            ViewGroup group =
                    (ViewGroup) view;

            for (int i = 0;
                 i < group.getChildCount();
                 i++) {

                setHeaderTextWhite(
                        group.getChildAt(i)
                );
            }
        }
    }


    // ==================================================
    // CHANGE ALL TEXT COLOR
    // ==================================================

    private void changeAllTextColor(
            View view,
            int color
    ) {

        if (view instanceof TextView) {

            TextView textView =
                    (TextView) view;

            if (textView.getId() != R.id.viewDetailsButton &&
                    textView.getId() != R.id.giveReviewButton) {

                textView.setTextColor(color);
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
}