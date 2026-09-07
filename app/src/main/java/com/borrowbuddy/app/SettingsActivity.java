package com.borrowbuddy.app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class SettingsActivity extends AppCompatActivity {

    // =========================
    // CARDS
    // =========================

    MaterialCardView editProfileCard;
    MaterialCardView notificationCard;
    MaterialCardView darkModeCard;
    MaterialCardView changePasswordCard;
    MaterialCardView termsCard;
    MaterialCardView helpCard;
    MaterialCardView logoutCard;

    // =========================
    // SWITCHES
    // =========================

    Switch notificationSwitch;
    Switch darkModeSwitch;

    // =========================
    // LAYOUT
    // =========================

    ScrollView settingsScrollView;
    LinearLayout mainLayout;

    // =========================
    // PREFERENCES
    // =========================

    private static final String PREF_NAME = "BorrowBuddy";
    private static final String DARK_MODE = "darkMode";

    // =========================
    // PURPLE COLOR
    // Same as Home Screen
    // =========================

    private static final int PURPLE =
            Color.rgb(106, 27, 154);   // #6A1B9A


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        // =========================
        // HIDE ACTION BAR
        // =========================

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // =========================
        // STATUS BAR PURPLE
        // =========================

        getWindow().setStatusBarColor(PURPLE);

        // White icons/text in status bar
        getWindow().getDecorView().setSystemUiVisibility(0);

        // =========================
        // FIND CARDS
        // =========================

        editProfileCard =
                findViewById(R.id.editProfileCard);

        notificationCard =
                findViewById(R.id.notificationCard);

        darkModeCard =
                findViewById(R.id.darkModeCard);

        changePasswordCard =
                findViewById(R.id.changePasswordCard);

        termsCard =
                findViewById(R.id.termsCard);

        helpCard =
                findViewById(R.id.helpCard);

        logoutCard =
                findViewById(R.id.logoutCard);

        // =========================
        // FIND SWITCHES
        // =========================

        notificationSwitch =
                findViewById(R.id.notificationSwitch);

        darkModeSwitch =
                findViewById(R.id.darkModeSwitch);

        // =========================
        // MAIN LAYOUT
        // =========================

        settingsScrollView =
                findViewById(R.id.settingsScrollView);

        mainLayout =
                findViewById(R.id.mainLayout);

        // =========================
        // BACK BUTTON
        // =========================

        findViewById(R.id.backButton)
                .setOnClickListener(v -> finish());

        // =========================
        // LOAD DARK MODE
        // =========================

        boolean isDarkMode =
                getSharedPreferences(
                        PREF_NAME,
                        MODE_PRIVATE
                ).getBoolean(
                        DARK_MODE,
                        false
                );

        darkModeSwitch.setChecked(isDarkMode);

        if (isDarkMode) {

            darkMode();

        } else {

            lightMode();
        }

        // =========================
        // EDIT PROFILE
        // =========================

        editProfileCard.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            SettingsActivity.this,
                            ProfileActivity.class
                    );

            startActivity(intent);
        });

        // =========================
        // NOTIFICATION
        // =========================

        notificationCard.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            SettingsActivity.this,
                            NotificationActivity.class
                    );

            startActivity(intent);
        });

        // =========================
        // NOTIFICATION SWITCH
        // =========================

        notificationSwitch.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {

                    if (isChecked) {

                        Toast.makeText(
                                SettingsActivity.this,
                                "Notifications ON",
                                Toast.LENGTH_SHORT
                        ).show();

                    } else {

                        Toast.makeText(
                                SettingsActivity.this,
                                "Notifications OFF",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
        );

        // =========================
        // DARK MODE SWITCH
        // =========================

        darkModeSwitch.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {

                    getSharedPreferences(
                            PREF_NAME,
                            MODE_PRIVATE
                    )
                            .edit()
                            .putBoolean(
                                    DARK_MODE,
                                    isChecked
                            )
                            .apply();

                    if (isChecked) {

                        darkMode();

                        Toast.makeText(
                                SettingsActivity.this,
                                "Dark Mode ON",
                                Toast.LENGTH_SHORT
                        ).show();

                    } else {

                        lightMode();

                        Toast.makeText(
                                SettingsActivity.this,
                                "Dark Mode OFF",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
        );

        // =========================
        // CHANGE PASSWORD
        // =========================

        changePasswordCard.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            SettingsActivity.this,
                            ChangePasswordActivity.class
                    );

            startActivity(intent);
        });

        // =========================
        // TERMS
        // =========================

        termsCard.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            SettingsActivity.this,
                            TermsActivity.class
                    );

            startActivity(intent);
        });

        // =========================
        // HELP
        // =========================

        helpCard.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            SettingsActivity.this,
                            HelpSupportActivity.class
                    );

            startActivity(intent);
        });

        // =========================
        // LOGOUT
        // =========================

        logoutCard.setOnClickListener(v -> {

            new AlertDialog.Builder(
                    SettingsActivity.this
            )
                    .setTitle("Logout")
                    .setMessage(
                            "Are you sure you want to logout?"
                    )
                    .setNegativeButton(
                            "CANCEL",
                            null
                    )
                    .setPositiveButton(
                            "LOGOUT",
                            (dialog, which) -> {

                                Toast.makeText(
                                        SettingsActivity.this,
                                        "Logged out successfully",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                    )
                    .show();
        });
    }


    // ==================================================
    // DARK MODE
    // ==================================================

    private void darkMode() {

        // Background
        settingsScrollView.setBackgroundColor(
                Color.BLACK
        );

        mainLayout.setBackgroundColor(
                Color.BLACK
        );

        // Cards
        setCardsColor(Color.BLACK);

        // Text
        changeAllTextColor(
                mainLayout,
                Color.WHITE
        );

        // Header
        setHeaderBackground();

        setHeaderTextWhite();

        // Status Bar
        getWindow().setStatusBarColor(PURPLE);

        // White status bar icons
        getWindow()
                .getDecorView()
                .setSystemUiVisibility(0);
    }


    // ==================================================
    // LIGHT MODE
    // ==================================================

    private void lightMode() {

        // Background
        settingsScrollView.setBackgroundColor(
                Color.rgb(248, 249, 250)
        );

        mainLayout.setBackgroundColor(
                Color.rgb(248, 249, 250)
        );

        // Cards
        setCardsColor(Color.WHITE);

        // Text
        changeAllTextColor(
                mainLayout,
                Color.BLACK
        );

        // Header
        setHeaderBackground();

        setHeaderTextWhite();

        // Status Bar
        getWindow().setStatusBarColor(PURPLE);

        // White status bar icons
        getWindow()
                .getDecorView()
                .setSystemUiVisibility(0);
    }


    // ==================================================
    // CARD COLOR
    // ==================================================

    private void setCardsColor(int color) {

        editProfileCard.setCardBackgroundColor(color);

        notificationCard.setCardBackgroundColor(color);

        darkModeCard.setCardBackgroundColor(color);

        changePasswordCard.setCardBackgroundColor(color);

        termsCard.setCardBackgroundColor(color);

        helpCard.setCardBackgroundColor(color);

        logoutCard.setCardBackgroundColor(color);
    }


    // ==================================================
    // HEADER BACKGROUND
    // ==================================================

    private void setHeaderBackground() {

        if (mainLayout.getChildCount() > 0) {

            View header =
                    mainLayout.getChildAt(0);

            header.setBackgroundColor(PURPLE);
        }
    }


    // ==================================================
    // HEADER TEXT WHITE
    // ==================================================

    private void setHeaderTextWhite() {

        if (mainLayout == null) {
            return;
        }

        if (mainLayout.getChildCount() > 0) {

            View firstChild =
                    mainLayout.getChildAt(0);

            if (firstChild instanceof ViewGroup) {

                setHeaderChildrenWhite(
                        (ViewGroup) firstChild
                );
            }
        }
    }


    private void setHeaderChildrenWhite(
            ViewGroup header
    ) {

        for (int i = 0;
             i < header.getChildCount();
             i++) {

            View child =
                    header.getChildAt(i);

            if (child instanceof TextView) {

                ((TextView) child)
                        .setTextColor(
                                Color.WHITE
                        );
            }

            if (child instanceof ViewGroup) {

                setHeaderChildrenWhite(
                        (ViewGroup) child
                );
            }
        }
    }


    // ==================================================
    // CHANGE ALL TEXT COLORS
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
}