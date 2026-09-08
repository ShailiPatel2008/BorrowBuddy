package com.borrowbuddy.app;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

public class TermsActivity extends AppCompatActivity {

    private MaterialToolbar toolbarTerms;
    private ScrollView termsScrollView;

    private static final String PREF_NAME = "BorrowBuddy";
    private static final String DARK_MODE = "darkMode";

    private static final int PURPLE =
            Color.rgb(106, 27, 154);

    private static final int LIGHT_BACKGROUND =
            Color.rgb(248, 249, 250);

    private static final int LIGHT_STROKE =
            Color.rgb(221, 221, 221);

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
        // FIND VIEWS
        // =========================

        toolbarTerms =
                findViewById(R.id.toolbarTerms);

        termsScrollView =
                findViewById(R.id.termsScrollView);

        // =========================
        // TOOLBAR BACK BUTTON
        // =========================

        toolbarTerms.setNavigationOnClickListener(
                v -> finish()
        );

        // =========================
        // STATUS BAR
        // =========================

        getWindow().setStatusBarColor(PURPLE);

        // White status bar icons
        getWindow()
                .getDecorView()
                .setSystemUiVisibility(0);

        // =========================
        // LOAD DARK MODE
        // =========================

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

        // Background
        termsScrollView.setBackgroundColor(
                Color.BLACK
        );

        View content =
                termsScrollView.getChildAt(0);

        if (content != null) {

            content.setBackgroundColor(
                    Color.BLACK
            );

            changeAllTextColor(
                    content,
                    Color.WHITE
            );

            changeCardsColor(
                    content,
                    Color.BLACK
            );
        }

        // Toolbar
        toolbarTerms.setBackgroundColor(
                PURPLE
        );

        toolbarTerms.setNavigationIcon(
                R.drawable.ic_arrow_back
        );

        // Toolbar title white
        setToolbarTextWhite();

        // Status bar
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

        // Background
        termsScrollView.setBackgroundColor(
                LIGHT_BACKGROUND
        );

        View content =
                termsScrollView.getChildAt(0);

        if (content != null) {

            content.setBackgroundColor(
                    LIGHT_BACKGROUND
            );

            changeAllTextColor(
                    content,
                    Color.BLACK
            );

            changeCardsColor(
                    content,
                    Color.WHITE
            );
        }

        // Toolbar
        toolbarTerms.setBackgroundColor(
                PURPLE
        );

        toolbarTerms.setNavigationIcon(
                R.drawable.ic_arrow_back
        );

        // Toolbar title white
        setToolbarTextWhite();

        // Status bar
        getWindow().setStatusBarColor(
                PURPLE
        );

        getWindow()
                .getDecorView()
                .setSystemUiVisibility(0);
    }

    // ==================================================
    // TOOLBAR TEXT WHITE
    // ==================================================

    private void setToolbarTextWhite() {

        if (toolbarTerms == null) {
            return;
        }

        for (int i = 0;
             i < toolbarTerms.getChildCount();
             i++) {

            View child =
                    toolbarTerms.getChildAt(i);

            if (child instanceof TextView) {

                ((TextView) child)
                        .setTextColor(
                                Color.WHITE
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
    // CHANGE ALL CARDS
    // ==================================================

    private void changeCardsColor(
            View view,
            int color
    ) {

        if (view instanceof MaterialCardView) {

            MaterialCardView card =
                    (MaterialCardView) view;

            card.setCardBackgroundColor(
                    color
            );

            if (color == Color.BLACK) {

                card.setStrokeColor(
                        Color.WHITE
                );

                card.setStrokeWidth(1);

            } else {

                card.setStrokeColor(
                        LIGHT_STROKE
                );

                card.setStrokeWidth(1);
            }
        }

        if (view instanceof ViewGroup) {

            ViewGroup group =
                    (ViewGroup) view;

            for (int i = 0;
                 i < group.getChildCount();
                 i++) {

                changeCardsColor(
                        group.getChildAt(i),
                        color
                );
            }
        }
    }
}