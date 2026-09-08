package com.borrowbuddy.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class MyProfileActivity extends AppCompatActivity {

    private ScrollView profileScrollView;
    private LinearLayout profileLayout;
    private LinearLayout profileHeader;

    private TextView backButton;
    private TextView headerTitle;
    private TextView changePhotoButton;
    private TextView personalInfoTitle;

    private TextView nameValue;
    private TextView profileEmail;
    private TextView phoneValue;
    private TextView locationValue;

    private TextView nameLabel;
    private TextView emailLabel;
    private TextView phoneLabel;
    private TextView locationLabel;

    private MaterialCardView nameCard;
    private MaterialCardView emailCard;
    private MaterialCardView phoneCard;
    private MaterialCardView locationCard;

    private Button saveChangesButton;

    private SharedPreferences preferences;

    private static final int PICK_IMAGE = 100;

    private final int PURPLE = Color.rgb(106, 27, 154);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_profile);

        preferences = getSharedPreferences("BorrowBuddy", MODE_PRIVATE);

        initializeViews();
        loadProfileData();
        setupClickListeners();
        applyTheme();
    }

    private void initializeViews() {

        profileScrollView = findViewById(R.id.profileScrollView);
        profileLayout = findViewById(R.id.profileLayout);
        profileHeader = findViewById(R.id.profileHeader);

        backButton = findViewById(R.id.backButton);
        headerTitle = findViewById(R.id.headerTitle);
        changePhotoButton = findViewById(R.id.changePhotoButton);
        personalInfoTitle = findViewById(R.id.personalInfoTitle);

        nameValue = findViewById(R.id.nameValue);
        profileEmail = findViewById(R.id.profileEmail);
        phoneValue = findViewById(R.id.phoneValue);
        locationValue = findViewById(R.id.locationValue);

        nameLabel = findViewById(R.id.nameLabel);
        emailLabel = findViewById(R.id.emailLabel);
        phoneLabel = findViewById(R.id.phoneLabel);
        locationLabel = findViewById(R.id.locationLabel);

        nameCard = findViewById(R.id.nameCard);
        emailCard = findViewById(R.id.emailCard);
        phoneCard = findViewById(R.id.phoneCard);
        locationCard = findViewById(R.id.locationCard);

        saveChangesButton = findViewById(R.id.saveChangesButton);
    }

    private void loadProfileData() {

        String name = preferences.getString("profileName", "Shweta");
        String email = preferences.getString(
                "profileEmail",
                "shweta@gmail.com"
        );

        String phone = preferences.getString(
                "profilePhone",
                "+91 1234567890"
        );

        String location = preferences.getString(
                "profileLocation",
                "Ahmedabad, Gujarat"
        );

        nameValue.setText(name);
        profileEmail.setText(email);
        phoneValue.setText(phone);
        locationValue.setText(location);
    }

    private void setupClickListeners() {

        // Back button
        backButton.setOnClickListener(v -> finish());

        // Change Photo
        changePhotoButton.setOnClickListener(v -> openGallery());

        // Save Changes
        saveChangesButton.setOnClickListener(v -> saveProfileData());
    }

    private void openGallery() {

        Intent intent = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        );

        intent.setType("image/*");

        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(
            int requestCode,
            int resultCode,
            Intent data
    ) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE &&
                resultCode == RESULT_OK &&
                data != null) {

            Uri selectedImage = data.getData();

            if (selectedImage != null) {

                // Photo URI save kar rahe hain
                preferences.edit()
                        .putString(
                                "profilePhotoUri",
                                selectedImage.toString()
                        )
                        .apply();

                Toast.makeText(
                        this,
                        "Photo selected successfully",
                        Toast.LENGTH_SHORT
                ).show();
            }
        }
    }

    private void saveProfileData() {

        String name = nameValue.getText().toString().trim();
        String email = profileEmail.getText().toString().trim();
        String phone = phoneValue.getText().toString().trim();
        String location = locationValue.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(
                    this,
                    "Please enter name",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        if (email.isEmpty()) {
            Toast.makeText(
                    this,
                    "Please enter email",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        if (phone.isEmpty()) {
            Toast.makeText(
                    this,
                    "Please enter phone number",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        if (location.isEmpty()) {
            Toast.makeText(
                    this,
                    "Please enter location",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        preferences.edit()
                .putString("profileName", name)
                .putString("profileEmail", email)
                .putString("profilePhone", phone)
                .putString("profileLocation", location)
                .apply();

        Toast.makeText(
                this,
                "Profile saved successfully",
                Toast.LENGTH_SHORT
        ).show();
    }

    private void applyTheme() {

        boolean darkMode = preferences.getBoolean(
                "darkMode",
                false
        );

        if (darkMode) {
            applyDarkMode();
        } else {
            applyLightMode();
        }
    }

    private void applyLightMode() {

        profileLayout.setBackgroundColor(
                Color.rgb(245, 245, 245)
        );

        profileHeader.setBackgroundColor(PURPLE);

        personalInfoTitle.setTextColor(Color.BLACK);

        nameCard.setCardBackgroundColor(Color.WHITE);
        emailCard.setCardBackgroundColor(Color.WHITE);
        phoneCard.setCardBackgroundColor(Color.WHITE);
        locationCard.setCardBackgroundColor(Color.WHITE);

        nameValue.setTextColor(Color.BLACK);
        profileEmail.setTextColor(Color.BLACK);
        phoneValue.setTextColor(Color.BLACK);
        locationValue.setTextColor(Color.BLACK);

        nameLabel.setTextColor(
                Color.rgb(117, 117, 117)
        );

        emailLabel.setTextColor(
                Color.rgb(117, 117, 117)
        );

        phoneLabel.setTextColor(
                Color.rgb(117, 117, 117)
        );

        locationLabel.setTextColor(
                Color.rgb(117, 117, 117)
        );

        changePhotoButton.setTextColor(PURPLE);

        saveChangesButton.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(PURPLE)
        );

        saveChangesButton.setTextColor(Color.WHITE);
    }

    private void applyDarkMode() {

        profileLayout.setBackgroundColor(Color.BLACK);

        profileHeader.setBackgroundColor(PURPLE);

        personalInfoTitle.setTextColor(Color.WHITE);

        nameCard.setCardBackgroundColor(
                Color.rgb(45, 45, 45)
        );

        emailCard.setCardBackgroundColor(
                Color.rgb(45, 45, 45)
        );

        phoneCard.setCardBackgroundColor(
                Color.rgb(45, 45, 45)
        );

        locationCard.setCardBackgroundColor(
                Color.rgb(45, 45, 45)
        );

        nameValue.setTextColor(Color.WHITE);
        profileEmail.setTextColor(Color.WHITE);
        phoneValue.setTextColor(Color.WHITE);
        locationValue.setTextColor(Color.WHITE);

        nameLabel.setTextColor(Color.LTGRAY);
        emailLabel.setTextColor(Color.LTGRAY);
        phoneLabel.setTextColor(Color.LTGRAY);
        locationLabel.setTextColor(Color.LTGRAY);

        changePhotoButton.setTextColor(
                Color.rgb(206, 147, 216)
        );

        saveChangesButton.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(PURPLE)
        );

        saveChangesButton.setTextColor(Color.WHITE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (preferences == null) {
            preferences = getSharedPreferences(
                    "BorrowBuddy",
                    MODE_PRIVATE
            );
        }

        loadProfileData();
        applyTheme();
    }
}