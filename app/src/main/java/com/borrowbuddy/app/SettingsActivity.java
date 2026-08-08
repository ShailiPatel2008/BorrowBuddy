package com.borrowbuddy.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingsActivity extends AppCompatActivity {

    private CardView cardProfile, cardPassword,
            cardAbout, cardContact, cardLogout;

    private SwitchMaterial switchDark, switchNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Initialize Views

        cardProfile = findViewById(R.id.cardProfile);
        cardPassword = findViewById(R.id.cardPassword);
        cardAbout = findViewById(R.id.cardAbout);
        cardContact = findViewById(R.id.cardContact);
        cardLogout = findViewById(R.id.cardLogout);

        switchDark = findViewById(R.id.switchDark);
        switchNotification = findViewById(R.id.switchNotification);

        // =============================
        // My Profile
        // =============================

        cardProfile.setOnClickListener(v -> {

            Intent intent = new Intent(
                    SettingsActivity.this,
                    MyProfileActivity.class);

            startActivity(intent);

        });

        // =============================
        // Change Password
        // =============================

        cardPassword.setOnClickListener(v -> {

            Intent intent = new Intent(
                    SettingsActivity.this,
                    CreateNewPasswordActivity.class);

            startActivity(intent);

        });

        // =============================
        // Dark Mode
        // =============================

        switchDark.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {

                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO);

            }

        });

        // =============================
        // Notifications
        // =============================

        switchNotification.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {

                Toast.makeText(
                        this,
                        "Notifications Enabled",
                        Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(
                        this,
                        "Notifications Disabled",
                        Toast.LENGTH_SHORT).show();

            }

        });

        // =============================
        // About App
        // =============================

        cardAbout.setOnClickListener(v -> {

            new AlertDialog.Builder(SettingsActivity.this)
                    .setTitle("About BorrowBuddy")
                    .setMessage(
                            "BorrowBuddy\n\n" +
                                    "Version : 1.0\n\n" +
                                    "Developed By\n" +
                                    "Fency Patel")
                    .setPositiveButton("OK", null)
                    .show();

        });

        // =============================
        // Contact Us
        // =============================

        cardContact.setOnClickListener(v -> {

            new AlertDialog.Builder(SettingsActivity.this)
                    .setTitle("Contact Us")
                    .setMessage(
                            "Email : borrowbuddy@gmail.com\n\n" +
                                    "Phone : +91 9537492163" +
                                    "" +
                                    "2" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\")
                    .setPositiveButton("OK", null)
                    .show();

        });

        // =============================
        // Logout
        // =============================

        cardLogout.setOnClickListener(v -> {

            new AlertDialog.Builder(SettingsActivity.this)
                    .setTitle("Logout")
                    .setMessage("Are you sure you want to Logout?")
                    .setPositiveButton("Logout",
                            (dialog, which) -> {

                                Intent intent = new Intent(
                                        SettingsActivity.this,
                                        LoginActivity.class);

                                intent.setFlags(
                                        Intent.FLAG_ACTIVITY_NEW_TASK |
                                                Intent.FLAG_ACTIVITY_CLEAR_TASK);

                                startActivity(intent);
                                finish();

                            })
                    .setNegativeButton("Cancel", null)
                    .show();

        });

    }
}