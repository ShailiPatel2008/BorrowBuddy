package com.borrowbuddy.app.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.borrowbuddy.app.ChooseRoleActivity;
import com.borrowbuddy.app.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingsActivity extends AppCompatActivity {

    ImageButton btnBack;
    MaterialCardView cardEditProfile;

    SwitchMaterial switchNotifications;

    MaterialCardView cardLogout;
    MaterialCardView cardTerms;

    private MaterialCardView cardHelpSupport;
    private MaterialCardView cardChangePassword;
    private SwitchMaterial switchDarkMode;

    private boolean isInitializingDarkMode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences preferences = getSharedPreferences(
                "BorrowBuddySettings",
                MODE_PRIVATE
        );

        // =========================
        // DARK MODE
        // =========================

        switchDarkMode = findViewById(R.id.switchDarkMode);

        // Get saved preference
        boolean isDarkMode = preferences.getBoolean("dark_mode", false);

        // Set switch according to saved preference
        switchDarkMode.setChecked(isDarkMode);

        // Initialization finished
        isInitializingDarkMode = false;

        // Listen for user changes
        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {

            // Ignore changes during initialization
            if (isInitializingDarkMode) {
                return;
            }

            // Save preference
            preferences.edit()
                    .putBoolean("dark_mode", isChecked)
                    .apply();

            // Change application theme
            if (isChecked) {

                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES
                );

            } else {

                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO
                );
            }
        });

        // =========================
        // CHANGE PASSWORD
        // =========================

        cardChangePassword = findViewById(R.id.cardChangePassword);

        cardTerms = findViewById(R.id.cardTerms);
        cardHelpSupport = findViewById(R.id.cardHelpSupport);

        // =========================
        // BACK BUTTON
        // =========================

        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> finish());

        // =========================
        // EDIT PROFILE
        // =========================

        cardEditProfile = findViewById(R.id.cardEditProfile);

        cardEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(
                    SettingsActivity.this,
                    OwnerProfileActivity.class
            );
            startActivity(intent);
        });

        // =========================
        // NOTIFICATIONS
        // =========================

        switchNotifications = findViewById(R.id.switchNotifications);

        switchNotifications.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {
                    // Notifications are ON/OFF
                }
        );

        // =========================
        // LOGOUT
        // =========================

        cardLogout = findViewById(R.id.cardLogout);

        cardLogout.setOnClickListener(v -> {

            new AlertDialog.Builder(SettingsActivity.this)
                    .setTitle("Logout")
                    .setMessage("Are you sure you want to logout?")
                    .setNegativeButton("CANCEL", null)
                    .setPositiveButton("LOGOUT", (dialog, which) -> {

                        Intent intent = new Intent(
                                SettingsActivity.this,
                                ChooseRoleActivity.class
                        );

                        // Clear previous screens
                        intent.setFlags(
                                Intent.FLAG_ACTIVITY_NEW_TASK |
                                        Intent.FLAG_ACTIVITY_CLEAR_TASK
                        );

                        startActivity(intent);
                    })
                    .show();
        });

        // =========================
        // TERMS
        // =========================

        cardTerms.setOnClickListener(v -> {

            Intent intent = new Intent(
                    SettingsActivity.this,
                    TermsActivity.class
            );

            startActivity(intent);
        });

        // =========================
        // HELP & SUPPORT
        // =========================

        cardHelpSupport.setOnClickListener(v -> {

            Intent intent = new Intent(
                    SettingsActivity.this,
                    HelpSupportActivity.class
            );

            startActivity(intent);
        });

        // =========================
        // CHANGE PASSWORD
        // =========================

        cardChangePassword.setOnClickListener(v -> {

            Intent intent = new Intent(
                    SettingsActivity.this,
                    ChangePasswordActivity.class
            );

            startActivity(intent);
        });
    }
}