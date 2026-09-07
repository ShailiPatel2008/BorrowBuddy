package com.borrowbuddy.app;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class TermsActivity extends AppCompatActivity {

    TextView backButton;

    ScrollView termsScrollView;
    LinearLayout termsMainLayout;
    MaterialCardView termsCard;

    private static final int PURPLE =
            Color.rgb(106, 27, 154);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_terms);

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

        // White status bar icons
        getWindow().getDecorView().setSystemUiVisibility(0);

        // =========================
        // FIND VIEWS
        // =========================

        backButton =
                findViewById(R.id.backButton);

        termsScrollView =
                findViewById(R.id.termsScrollView);

        termsMainLayout =
                findViewById(R.id.termsMainLayout);

        termsCard =
                findViewById(R.id.termsCard);

        // =========================
        // BACK BUTTON
        // =========================

        backButton.setOnClickListener(v -> {
            finish();
        });

        // =========================
        // LOAD DARK MODE
        // =========================

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

    // ==================================================
    // DARK MODE
    // ==================================================

    private void darkMode() {

        // =========================
        // BACKGROUND
        // =========================

        termsScrollView.setBackgroundColor(
                Color.BLACK
        );

        termsMainLayout.setBackgroundColor(
                Color.BLACK
        );

        // =========================
        // CARD
        // =========================

        termsCard.setCardBackgroundColor(
                Color.BLACK
        );

        termsCard.setStrokeColor(
                Color.WHITE
        );

        termsCard.setStrokeWidth(2);

        // =========================
        // ALL TEXT WHITE
        // =========================

        changeAllTextColor(
                termsMainLayout,
                Color.WHITE
        );

        // =========================
        // HEADER
        // =========================

        View header =
                findViewById(R.id.termsHeader);

        if (header != null) {

            header.setBackgroundColor(
                    PURPLE
            );

            setHeaderTextWhite(header);
        }

        // =========================
        // STATUS BAR
        // =========================

        getWindow().setStatusBarColor(
                PURPLE
        );

        getWindow()
                .getDecorView()
                .setSystemUiVisibility(0);
    }

    // ==================================================
    // LIGHT MODE
    // ==================================================

    private void lightMode() {

        // =========================
        // BACKGROUND
        // =========================

        termsScrollView.setBackgroundColor(
                Color.rgb(248, 249, 250)
        );

        termsMainLayout.setBackgroundColor(
                Color.rgb(248, 249, 250)
        );

        // =========================
        // CARD
        // =========================

        termsCard.setCardBackgroundColor(
                Color.WHITE
        );

        termsCard.setStrokeColor(
                Color.rgb(221, 221, 221)
        );

        termsCard.setStrokeWidth(1);

        // =========================
        // ALL TEXT BLACK
        // =========================

        changeAllTextColor(
                termsMainLayout,
                Color.BLACK
        );

        // =========================
        // HEADER
        // =========================

        View header =
                findViewById(R.id.termsHeader);

        if (header != null) {

            header.setBackgroundColor(
                    PURPLE
            );

            setHeaderTextWhite(header);
        }

        // =========================
        // STATUS BAR
        // =========================

        getWindow().setStatusBarColor(
                PURPLE
        );

        // White status bar icons
        getWindow()
                .getDecorView()
                .setSystemUiVisibility(0);
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

            // Header ko baad mein white rakhenge
            if (textView.getId()
                    != R.id.backButton &&
                    textView.getId()
                            != R.id.headerTitle) {

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