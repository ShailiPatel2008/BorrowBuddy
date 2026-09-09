package com.borrowbuddy.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

public class HelpsSupportActivity extends AppCompatActivity {

    // =========================
    // VIEWS
    // =========================

    MaterialToolbar toolbar;

    ScrollView helpScrollView;
    LinearLayout helpMainLayout;

    MaterialCardView faqCard;
    MaterialCardView contactCard;

    Button contactSupportButton;

    // =========================
    // COLORS
    // =========================

    private static final int PURPLE =
            Color.rgb(106, 27, 154);

    private static final int LIGHT_BACKGROUND =
            Color.rgb(248, 249, 250);

    private static final int LIGHT_BORDER =
            Color.rgb(221, 221, 221);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_helps_support);


        // =========================
        // TOOLBAR
        // =========================

        toolbar =
                findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIconTint(Color.WHITE);

        if (getSupportActionBar() != null) {

            getSupportActionBar().setTitle(
                    "Help & Support"
            );

            getSupportActionBar().setDisplayHomeAsUpEnabled(
                    true
            );
        }

        toolbar.setNavigationOnClickListener(
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
        // FIND VIEWS
        // =========================

        helpScrollView =
                findViewById(R.id.helpScrollView);

        helpMainLayout =
                findViewById(R.id.helpMainLayout);

        faqCard =
                findViewById(R.id.faqCard);

        contactCard =
                findViewById(R.id.contactCard);

        contactSupportButton =
                findViewById(R.id.contactSupportButton);


        // =========================
        // CONTACT SUPPORT
        // =========================

        contactSupportButton.setOnClickListener(v -> {

            Intent emailIntent =
                    new Intent(Intent.ACTION_SENDTO);

            emailIntent.setData(
                    Uri.parse(
                            "mailto:support@borrowbuddy.com"
                    )
            );

            emailIntent.putExtra(
                    Intent.EXTRA_SUBJECT,
                    "BorrowBuddy Support"
            );

            try {

                startActivity(emailIntent);

            } catch (Exception e) {

                Toast.makeText(
                        HelpsSupportActivity.this,
                        "No email application found",
                        Toast.LENGTH_SHORT
                ).show();
            }
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

        helpScrollView.setBackgroundColor(
                Color.BLACK
        );

        helpMainLayout.setBackgroundColor(
                Color.BLACK
        );


        // =========================
        // FAQ CARD
        // =========================

        faqCard.setCardBackgroundColor(
                Color.BLACK
        );

        faqCard.setStrokeColor(
                Color.WHITE
        );

        faqCard.setStrokeWidth(1);


        // =========================
        // CONTACT CARD
        // =========================

        contactCard.setCardBackgroundColor(
                Color.BLACK
        );

        contactCard.setStrokeColor(
                Color.WHITE
        );

        contactCard.setStrokeWidth(1);


        // =========================
        // ALL TEXT WHITE
        // =========================

        changeAllTextColor(
                helpMainLayout,
                Color.WHITE
        );


        // =========================
        // HEADER
        // =========================

        toolbar.setBackgroundColor(
                PURPLE
        );

        toolbar.setTitleTextColor(
                Color.WHITE
        );

        toolbar.setNavigationIconTint(
                Color.WHITE
        );


        // =========================
        // BUTTON
        // =========================

        contactSupportButton.setBackgroundTintList(
                ColorStateList.valueOf(PURPLE)
        );

        contactSupportButton.setTextColor(
                Color.WHITE
        );


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

        helpScrollView.setBackgroundColor(
                LIGHT_BACKGROUND
        );

        helpMainLayout.setBackgroundColor(
                LIGHT_BACKGROUND
        );


        // =========================
        // FAQ CARD
        // =========================

        faqCard.setCardBackgroundColor(
                Color.WHITE
        );

        faqCard.setStrokeColor(
                LIGHT_BORDER
        );

        faqCard.setStrokeWidth(1);


        // =========================
        // CONTACT CARD
        // =========================

        contactCard.setCardBackgroundColor(
                Color.WHITE
        );

        contactCard.setStrokeColor(
                LIGHT_BORDER
        );

        contactCard.setStrokeWidth(1);


        // =========================
        // ALL TEXT BLACK
        // =========================

        changeAllTextColor(
                helpMainLayout,
                Color.BLACK
        );


        // =========================
        // HEADER
        // =========================

        toolbar.setBackgroundColor(
                PURPLE
        );

        toolbar.setTitleTextColor(
                Color.WHITE
        );

        toolbar.setNavigationIconTint(
                Color.WHITE
        );


        // =========================
        // BUTTON
        // =========================

        contactSupportButton.setBackgroundTintList(
                ColorStateList.valueOf(PURPLE)
        );

        contactSupportButton.setTextColor(
                Color.WHITE
        );


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
    // CHANGE ALL TEXT COLOR
    // ==================================================

    private void changeAllTextColor(
            View view,
            int color
    ) {

        // Do not change toolbar title
        if (view == toolbar) {
            return;
        }


        if (view instanceof TextView) {

            TextView textView =
                    (TextView) view;

            if (textView.getId()
                    != R.id.contactSupportButton) {

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

