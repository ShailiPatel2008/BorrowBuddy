package com.borrowbuddy.app;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.android.material.appbar.MaterialToolbar;

import androidx.appcompat.app.AppCompatActivity;

public class MyActivityActivity extends AppCompatActivity {

    com.google.android.material.appbar.MaterialToolbar toolbar;
    ScrollView activityScrollView;
    LinearLayout activityMainLayout;

    LinearLayout totalRentalsCard;
    LinearLayout myBookingsCard;
    LinearLayout itemsListedCard;
    LinearLayout completedCard;
    LinearLayout cancelledCard;
    LinearLayout trustScoreCard;

    private static final String PREF_NAME = "BorrowBuddy";
    private static final String DARK_MODE = "darkMode";

    private static final int PURPLE =
            Color.rgb(106, 27, 154);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_activity);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("My Activity");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(v -> finish());


        // =========================
        // FIND VIEWS
        // =========================



        activityScrollView =
                findViewById(R.id.activityScrollView);

        activityMainLayout =
                findViewById(R.id.activityMainLayout);

        totalRentalsCard =
                findViewById(R.id.totalRentalsCard);

        myBookingsCard =
                findViewById(R.id.myBookingsCard);

        itemsListedCard =
                findViewById(R.id.itemsListedCard);

        completedCard =
                findViewById(R.id.completedCard);

        cancelledCard =
                findViewById(R.id.cancelledCard);

        trustScoreCard =
                findViewById(R.id.trustScoreCard);


        // =========================
        // APPLY MODE
        // =========================

        applyDarkMode();
    }


    // ==================================================
    // APPLY DARK / LIGHT MODE
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

        // =========================
        // STATUS BAR
        // =========================

        Window window = getWindow();

        window.setStatusBarColor(PURPLE);

        // White status bar icons
        window.getDecorView()
                .setSystemUiVisibility(0);


        // =========================
        // BACKGROUND
        // =========================

        activityScrollView.setBackgroundColor(
                Color.BLACK
        );

        activityMainLayout.setBackgroundColor(
                Color.BLACK
        );


        // =========================
        // HEADER
        // =========================

        toolbar.setBackgroundColor(PURPLE);
        toolbar.setTitleTextColor(Color.WHITE);


        // =========================
        // CARDS
        // =========================

        setDarkCard(totalRentalsCard);
        setDarkCard(myBookingsCard);
        setDarkCard(itemsListedCard);
        setDarkCard(completedCard);
        setDarkCard(cancelledCard);
        setDarkCard(trustScoreCard);


        // =========================
        // ALL TEXT WHITE
        // =========================

        changeAllTextColor(
                activityMainLayout,
                Color.WHITE
        );

    }


    // ==================================================
    // LIGHT MODE
    // ==================================================

    private void lightMode() {

        // =========================
        // STATUS BAR
        // =========================

        Window window = getWindow();

        window.setStatusBarColor(PURPLE);

        // White status bar icons
        window.getDecorView()
                .setSystemUiVisibility(0);


        // =========================
        // BACKGROUND
        // =========================

        activityScrollView.setBackgroundColor(
                Color.WHITE
        );

        activityMainLayout.setBackgroundColor(
                Color.WHITE
        );


        // =========================
        // HEADER
        // =========================

        toolbar.setBackgroundColor(PURPLE);

        toolbar.setTitleTextColor(Color.WHITE);


        // =========================
        // CARDS
        // =========================

        setLightCard(totalRentalsCard);
        setLightCard(myBookingsCard);
        setLightCard(itemsListedCard);
        setLightCard(completedCard);
        setLightCard(cancelledCard);
        setLightCard(trustScoreCard);


        // =========================
        // TEXT
        // =========================

        changeAllTextColor(
                activityMainLayout,
                Color.rgb(34, 34, 34)
        );


        // Purple numbers
        setPurpleNumber(
                totalRentalsCard
        );

        setPurpleNumber(
                myBookingsCard
        );

        setPurpleNumber(
                itemsListedCard
        );

        setPurpleNumber(
                completedCard
        );

        setPurpleNumber(
                cancelledCard
        );

        setPurpleNumber(
                trustScoreCard
        );
    }


    // ==================================================
    // DARK CARD
    // ==================================================

    private void setDarkCard(
            LinearLayout card
    ) {

        GradientDrawable drawable =
                new GradientDrawable();

        drawable.setColor(
                Color.rgb(30, 30, 30)
        );

        drawable.setCornerRadius(
                16
        );

        drawable.setStroke(
                2,
                Color.WHITE
        );

        card.setBackground(drawable);
    }


    // ==================================================
    // LIGHT CARD
    // ==================================================

    private void setLightCard(
            LinearLayout card
    ) {

        GradientDrawable drawable =
                new GradientDrawable();

        drawable.setColor(
                Color.rgb(245, 240, 255)
        );

        drawable.setCornerRadius(
                16
        );

        drawable.setStroke(
                1,
                Color.rgb(225, 215, 235)
        );

        card.setBackground(drawable);
    }


    // ==================================================
    // PURPLE NUMBER
    // ==================================================

    private void setPurpleNumber(
            LinearLayout card
    ) {

        if (card.getChildCount() > 1) {

            View view =
                    card.getChildAt(1);

            if (view instanceof TextView) {

                ((TextView) view)
                        .setTextColor(PURPLE);
            }
        }
    }


    // ==================================================
    // CHANGE ALL TEXT
    // ==================================================

    private void changeAllTextColor(
            View view,
            int color
    ) {

        if (view instanceof TextView) {

            ((TextView) view)
                    .setTextColor(color);
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

        if (activityMainLayout != null) {

            applyDarkMode();
        }
    }
}