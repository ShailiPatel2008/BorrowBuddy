package com.borrowbuddy.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        cardChangePassword = findViewById(R.id.cardChangePassword);

        cardTerms = findViewById(R.id.cardTerms);
        cardHelpSupport = findViewById(R.id.cardHelpSupport);

        // Back button
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> {
            finish();
        });

        // Edit Profile
        cardEditProfile = findViewById(R.id.cardEditProfile);

        cardEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(
                    SettingsActivity.this,
                    OwnerProfileActivity.class
            );
            startActivity(intent);
        });

        switchNotifications = findViewById(R.id.switchNotifications);

        switchNotifications.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                // Notifications are ON
            } else {
                // Notifications are OFF
            }

        });

        cardLogout = findViewById(R.id.cardLogout);

        cardLogout.setOnClickListener(v -> {

            new androidx.appcompat.app.AlertDialog.Builder(SettingsActivity.this)
                    .setTitle("Logout")
                    .setMessage("Are you sure you want to logout?")
                    .setNegativeButton("CANCEL", null)
                    .setPositiveButton("LOGOUT", (dialog, which) -> {

                        // Logout action will be added here

                    })
                    .show();
        });

        cardTerms.setOnClickListener(v -> {

            Intent intent = new Intent(
                    SettingsActivity.this,
                    TermsActivity.class
            );

            startActivity(intent);

        });

        cardHelpSupport.setOnClickListener(v -> {

            Intent intent = new Intent(
                    SettingsActivity.this,
                    HelpSupportActivity.class
            );

            startActivity(intent);

        });

        cardChangePassword.setOnClickListener(v -> {

            Intent intent = new Intent(
                    SettingsActivity.this,
                    ChangePasswordActivity.class
            );

            startActivity(intent);

        });

    }
}