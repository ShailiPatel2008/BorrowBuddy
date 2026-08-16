package com.borrowbuddy.app.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.borrowbuddy.app.R;
import com.google.android.material.card.MaterialCardView;

import android.widget.ImageButton;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class OwnerDashboardActivity extends AppCompatActivity {

    private MaterialCardView cardAddItem;

    private MaterialCardView cardMyListings;
    private MaterialCardView cardRentalRequests;
    private MaterialCardView cardEarnings;
    private MaterialCardView cardReviews;

    private MaterialCardView cardMyItemRequests;

    private MaterialCardView cardProfile;

    private MaterialCardView myReviewsCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_dashboard);

        ImageButton btnSettings = findViewById(R.id.btnSettings);

        btnSettings.setOnClickListener(v -> {
            Intent intent = new Intent(
                    OwnerDashboardActivity.this,
                    SettingsActivity.class
            );

            startActivity(intent);
        });

        ImageButton btnProfile = findViewById(R.id.btnProfile);

        btnProfile.setOnClickListener(v -> {
            Intent intent = new Intent(
                    OwnerDashboardActivity.this,
                    OwnerProfileActivity.class
            );
            startActivity(intent);
        });



        cardAddItem = findViewById(R.id.cardAddItem);
        cardMyListings = findViewById(R.id.cardMyListings);
        cardRentalRequests = findViewById(R.id.cardRentalRequests);
        cardEarnings = findViewById(R.id.cardEarnings);
        cardReviews = findViewById(R.id.cardReviews);
        cardMyItemRequests = findViewById(R.id.cardMyItemRequests);
        cardProfile = findViewById(R.id.cardProfile);
        myReviewsCard = findViewById(R.id.myReviewsCard);

        View dashboardContainer = findViewById(R.id.dashboardContainer);

        Animation slideUp =
                AnimationUtils.loadAnimation(this, R.anim.slide_up);

        dashboardContainer.startAnimation(slideUp);



        cardAddItem.setOnClickListener(v -> {
            Intent intent = new Intent(OwnerDashboardActivity.this,
                    AddItemActivity.class);
            startActivity(intent);

        });

        cardMyItemRequests.setOnClickListener(v -> {
            Intent intent = new Intent(OwnerDashboardActivity.this,
                    MyItemRequestsActivity.class);
            startActivity(intent);

        });

        cardMyListings.setOnClickListener(v -> {
            Intent intent = new Intent(OwnerDashboardActivity.this,
                    MyListingsActivity.class);
            startActivity(intent);

        });

        cardRentalRequests.setOnClickListener(v -> {
            Intent intent = new Intent(
                    OwnerDashboardActivity.this,
                    RentalHistoryActivity.class
            );

            startActivity(intent);


        });

        cardEarnings.setOnClickListener(v -> {
            Intent intent = new Intent(
                    OwnerDashboardActivity.this,
                    EarningsActivity.class
            );

            startActivity(intent);

        });

        cardReviews.setOnClickListener(v -> {
            Intent intent = new Intent(
                    OwnerDashboardActivity.this,
                    BorrowerRatingActivity.class
            );

            startActivity(intent);

        });


        cardProfile.setOnClickListener(v -> {
            Intent intent = new Intent(
                    OwnerDashboardActivity.this,
                    OwnerProfileActivity.class
            );

            startActivity(intent);

        });

        myReviewsCard.setOnClickListener(v -> {
            Intent intent = new Intent(
                    OwnerDashboardActivity.this,
                    RatingsReviewsActivity.class
            );

            startActivity(intent);
        });
    }
}