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

import com.google.android.material.card.MaterialCardView;

public class HelpSupportActivity extends AppCompatActivity {

    // =========================
    // VIEWS
    // =========================

    TextView backButton;
    TextView headerTitle;

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
        getWindow()
                .getDecorView()
                .setSystemUiVisibility(0);

        // =========================
        // FIND VIEWS
        // =========================

        backButton =
                findViewById(R.id.backButton);

        headerTitle =
                findViewById(R.id.headerTitle);

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
        // BACK BUTTON
        // =========================

        backButton.setOnClickListener(v -> {
            finish();
        });


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
                        HelpSupportActivity.this,
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

        View header =
                findViewById(R.id.helpHeader);

        if (header != null) {

            header.setBackgroundColor(
                    PURPLE
            );

            setHeaderTextWhite(header);
        }


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

        View header =
                findViewById(R.id.helpHeader);

        if (header != null) {

            header.setBackgroundColor(
                    PURPLE
            );

            setHeaderTextWhite(header);
        }


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
    // SET HEADER TEXT WHITE
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

            // Header ko baad mein white karenge
            if (textView.getId()
                    != R.id.backButton &&
                    textView.getId()
                            != R.id.headerTitle &&
                    textView.getId()
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