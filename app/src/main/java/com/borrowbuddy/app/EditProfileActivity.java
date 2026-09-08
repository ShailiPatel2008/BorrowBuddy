package com.borrowbuddy.app;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class EditProfileActivity extends AppCompatActivity {

    private TextView backButton;
    private TextView headerTitle;

    private EditText editName;
    private EditText editEmail;
    private EditText editPhone;

    private Button saveButton;

    private MaterialCardView nameCard;
    private MaterialCardView emailCard;
    private MaterialCardView phoneCard;

    private View mainLayout;

    private static final String PREF_NAME = "BorrowBuddy";
    private static final String DARK_MODE = "darkMode";

    private static final int PURPLE = Color.rgb(106, 27, 154);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_profile);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        getWindow().setStatusBarColor(PURPLE);
        getWindow().getDecorView().setSystemUiVisibility(0);

        // Find Views
        backButton = findViewById(R.id.backButton);
        headerTitle = findViewById(R.id.headerTitle);

        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editPhone = findViewById(R.id.editPhone);

        saveButton = findViewById(R.id.saveButton);

        nameCard = findViewById(R.id.nameCard);
        emailCard = findViewById(R.id.emailCard);
        phoneCard = findViewById(R.id.phoneCard);

        mainLayout = findViewById(R.id.editProfileMainLayout);

        // Load saved profile data
        SharedPreferences preferences =
                getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        String name = preferences.getString("userName", "");
        String email = preferences.getString("userEmail", "");
        String phone = preferences.getString("userPhone", "");

        editName.setText(name);
        editEmail.setText(email);
        editPhone.setText(phone);

        // Back button
        backButton.setOnClickListener(v -> finish());

        // Save button
        saveButton.setOnClickListener(v -> saveProfile());

        // Apply Dark / Light Mode
        boolean isDarkMode =
                preferences.getBoolean(DARK_MODE, false);

        if (isDarkMode) {
            applyDarkMode();
        } else {
            applyLightMode();
        }
    }

    private void saveProfile() {

        String name = editName.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String phone = editPhone.getText().toString().trim();

        // Validation
        if (name.isEmpty()) {
            editName.setError("Enter your name");
            editName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editEmail.setError("Enter your email");
            editEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS
                .matcher(email)
                .matches()) {

            editEmail.setError("Enter a valid email");
            editEmail.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            editPhone.setError("Enter your phone number");
            editPhone.requestFocus();
            return;
        }

        if (phone.length() < 10) {
            editPhone.setError("Enter a valid phone number");
            editPhone.requestFocus();
            return;
        }

        // Save data
        SharedPreferences preferences =
                getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        preferences.edit()
                .putString("userName", name)
                .putString("userEmail", email)
                .putString("userPhone", phone)
                .apply();

        Toast.makeText(
                EditProfileActivity.this,
                "Profile updated successfully",
                Toast.LENGTH_SHORT
        ).show();

        finish();
    }

    private void applyDarkMode() {

        mainLayout.setBackgroundColor(Color.BLACK);

        nameCard.setCardBackgroundColor(Color.rgb(25, 25, 25));
        emailCard.setCardBackgroundColor(Color.rgb(25, 25, 25));
        phoneCard.setCardBackgroundColor(Color.rgb(25, 25, 25));

        nameCard.setStrokeColor(Color.WHITE);
        emailCard.setStrokeColor(Color.WHITE);
        phoneCard.setStrokeColor(Color.WHITE);

        nameCard.setStrokeWidth(1);
        emailCard.setStrokeWidth(1);
        phoneCard.setStrokeWidth(1);

        changeAllTextColor(mainLayout, Color.WHITE);

        headerTitle.setTextColor(Color.WHITE);
        backButton.setTextColor(Color.WHITE);

        editName.setTextColor(Color.WHITE);
        editEmail.setTextColor(Color.WHITE);
        editPhone.setTextColor(Color.WHITE);

        editName.setHintTextColor(Color.LTGRAY);
        editEmail.setHintTextColor(Color.LTGRAY);
        editPhone.setHintTextColor(Color.LTGRAY);

        saveButton.setTextColor(Color.WHITE);
        saveButton.setBackgroundColor(PURPLE);

        View header = findViewById(R.id.editProfileHeader);

        if (header != null) {
            header.setBackgroundColor(PURPLE);
        }

        getWindow().setStatusBarColor(PURPLE);
    }

    private void applyLightMode() {

        mainLayout.setBackgroundColor(
                Color.rgb(248, 249, 250)
        );

        nameCard.setCardBackgroundColor(Color.WHITE);
        emailCard.setCardBackgroundColor(Color.WHITE);
        phoneCard.setCardBackgroundColor(Color.WHITE);

        nameCard.setStrokeColor(
                Color.rgb(221, 221, 221)
        );

        emailCard.setStrokeColor(
                Color.rgb(221, 221, 221)
        );

        phoneCard.setStrokeColor(
                Color.rgb(221, 221, 221)
        );

        nameCard.setStrokeWidth(1);
        emailCard.setStrokeWidth(1);
        phoneCard.setStrokeWidth(1);

        changeAllTextColor(mainLayout, Color.BLACK);

        headerTitle.setTextColor(Color.WHITE);
        backButton.setTextColor(Color.WHITE);

        editName.setTextColor(Color.BLACK);
        editEmail.setTextColor(Color.BLACK);
        editPhone.setTextColor(Color.BLACK);

        editName.setHintTextColor(Color.GRAY);
        editEmail.setHintTextColor(Color.GRAY);
        editPhone.setHintTextColor(Color.GRAY);

        saveButton.setTextColor(Color.WHITE);
        saveButton.setBackgroundColor(PURPLE);

        View header = findViewById(R.id.editProfileHeader);

        if (header != null) {
            header.setBackgroundColor(PURPLE);
        }

        getWindow().setStatusBarColor(PURPLE);
    }

    private void changeAllTextColor(View view, int color) {

        if (view instanceof TextView) {

            TextView textView =
                    (TextView) view;

            // Don't change header text here
            if (textView.getId() != R.id.backButton &&
                    textView.getId() != R.id.headerTitle) {

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