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

    private static final int TOOLBAR_PURPLE =
            Color.rgb(108, 74, 182);

    private static final int LIGHT_BACKGROUND =
            Color.rgb(248, 249, 250);

    private static final int DARK_BACKGROUND =
            Color.BLACK;

    private static final int LIGHT_CARD =
            Color.WHITE;

    private static final int DARK_CARD =
            Color.rgb(43, 41, 50);

    private static final int LIGHT_STROKE =
            Color.rgb(221, 221, 221);

    private static final int DARK_STROKE =
            Color.rgb(80, 80, 80);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_terms);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        toolbarTerms = findViewById(R.id.toolbarTerms);
        termsScrollView = findViewById(R.id.termsScrollView);

        toolbarTerms.setNavigationIconTint(Color.WHITE);

        toolbarTerms.setNavigationOnClickListener(
                v -> finish()
        );

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
            applyDarkMode();
        } else {
            applyLightMode();
        }
    }

    // ==================================================
    // DARK MODE
    // ==================================================

    private void applyDarkMode() {

        // Entire activity background
        View root =
                findViewById(android.R.id.content);

        root.setBackgroundColor(DARK_BACKGROUND);

        // ScrollView
        termsScrollView.setBackgroundColor(
                DARK_BACKGROUND
        );

        View content =
                termsScrollView.getChildAt(0);

        if (content != null) {

            content.setBackgroundColor(
                    DARK_BACKGROUND
            );

            changeTextColor(
                    content,
                    Color.WHITE
            );

            changeCardsColor(
                    content,
                    DARK_CARD
            );
        }

        // Toolbar
        toolbarTerms.setBackgroundColor(
                TOOLBAR_PURPLE
        );

        toolbarTerms.setNavigationIconTint(
                Color.WHITE
        );

        setToolbarTextWhite();

        // Status bar SAME as toolbar
        getWindow().setStatusBarColor(
                TOOLBAR_PURPLE
        );

        getWindow()
                .getDecorView()
                .setSystemUiVisibility(0);
    }

    // ==================================================
    // LIGHT MODE
    // ==================================================

    private void applyLightMode() {

        int lightBackground = Color.rgb(248, 249, 250);

        // Entire activity
        View root = findViewById(android.R.id.content);
        root.setBackgroundColor(lightBackground);

        // ScrollView
        termsScrollView.setBackgroundColor(lightBackground);

        // Content inside ScrollView
        View content = termsScrollView.getChildAt(0);

        if (content != null) {

            setLightBackgroundRecursively(
                    content,
                    lightBackground
            );

            changeTextColor(
                    content,
                    Color.BLACK
            );

            // Cards must remain white
            changeCardsColor(
                    content,
                    Color.WHITE
            );
        }

        // Toolbar
        toolbarTerms.setBackgroundColor(
                TOOLBAR_PURPLE
        );

        toolbarTerms.setNavigationIconTint(
                Color.WHITE
        );

        setToolbarTextWhite();

        // Status bar SAME as toolbar
        getWindow().setStatusBarColor(
                TOOLBAR_PURPLE
        );

        getWindow()
                .getDecorView()
                .setSystemUiVisibility(0);
    }
    // ==================================================
    // TOOLBAR TEXT
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
                        .setTextColor(Color.WHITE);
            }
        }
    }

    // ==================================================
    // CHANGE TEXT COLOR
    // ==================================================

    private void changeTextColor(
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

                changeTextColor(
                        group.getChildAt(i),
                        color
                );
            }
        }
    }

    // ==================================================
    // CHANGE CARD COLOR
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

            if (color == DARK_CARD) {

                card.setStrokeColor(
                        DARK_STROKE
                );

            } else {

                card.setStrokeColor(
                        LIGHT_STROKE
                );
            }

            card.setStrokeWidth(1);
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

    private void setLightBackgroundRecursively(
            View view,
            int color
    ) {

        // Don't overwrite card backgrounds
        if (view instanceof MaterialCardView) {
            return;
        }

        view.setBackgroundColor(color);

        if (view instanceof ViewGroup) {

            ViewGroup group =
                    (ViewGroup) view;

            for (int i = 0;
                 i < group.getChildCount();
                 i++) {

                setLightBackgroundRecursively(
                        group.getChildAt(i),
                        color
                );
            }
        }
    }
}