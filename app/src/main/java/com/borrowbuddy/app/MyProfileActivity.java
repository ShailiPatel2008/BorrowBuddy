package com.borrowbuddy.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

public class MyProfileActivity extends AppCompatActivity {

    private ScrollView profileScrollView;
    private LinearLayout profileLayout;
    private MaterialToolbar toolbar;

    private ImageView profileImage;

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

    private final int PURPLE = Color.rgb(106, 27, 154);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_profile);

        preferences =
                getSharedPreferences(
                        "BorrowBuddy",
                        MODE_PRIVATE
                );

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("My Profile");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIconTint(Color.WHITE);

        toolbar.setNavigationOnClickListener(
                v -> finish()
        );

        initializeViews();
        loadProfileData();
        setupClickListeners();
        applyTheme();
    }

    private void initializeViews() {

        profileScrollView =
                findViewById(R.id.profileScrollView);

        profileLayout =
                findViewById(R.id.profileLayout);

        profileImage =
                findViewById(R.id.profileImage);

        personalInfoTitle =
                findViewById(R.id.personalInfoTitle);

        nameValue =
                findViewById(R.id.nameValue);

        profileEmail =
                findViewById(R.id.profileEmail);

        phoneValue =
                findViewById(R.id.phoneValue);

        locationValue =
                findViewById(R.id.locationValue);

        nameLabel =
                findViewById(R.id.nameLabel);

        emailLabel =
                findViewById(R.id.emailLabel);

        phoneLabel =
                findViewById(R.id.phoneLabel);

        locationLabel =
                findViewById(R.id.locationLabel);

        nameCard =
                findViewById(R.id.nameCard);

        emailCard =
                findViewById(R.id.emailCard);

        phoneCard =
                findViewById(R.id.phoneCard);

        locationCard =
                findViewById(R.id.locationCard);

        saveChangesButton =
                findViewById(R.id.saveChangesButton);
    }

    private String getProfileValue(
            String profileKey,
            String oldUserKey,
            String defaultValue
    ) {

        String value =
                preferences.getString(
                        profileKey,
                        ""
                );

        if (value == null || value.isEmpty()) {

            value =
                    preferences.getString(
                            oldUserKey,
                            defaultValue
                    );
        }

        return value;
    }

    private void loadProfileData() {

        String name =
                getProfileValue(
                        "profileName",
                        "userName",
                        "Shweta"
                );

        String email =
                getProfileValue(
                        "profileEmail",
                        "userEmail",
                        "shweta@gmail.com"
                );

        String phone =
                getProfileValue(
                        "profilePhone",
                        "userPhone",
                        "+91 1234567890"
                );

        String location =
                preferences.getString(
                        "profileLocation",
                        "Ahmedabad, Gujarat"
                );

        nameValue.setText(name);
        profileEmail.setText(email);
        phoneValue.setText(phone);
        locationValue.setText(location);

        loadProfilePhoto();
    }

    private void loadProfilePhoto() {

        String photoUri =
                preferences.getString(
                        "profilePhotoUri",
                        ""
                );

        if (photoUri != null &&
                !photoUri.isEmpty()) {

            try {

                profileImage.setImageURI(
                        Uri.parse(photoUri)
                );

            } catch (Exception e) {

                profileImage.setImageDrawable(null);
            }
        }
    }

    private void setupClickListeners() {

        saveChangesButton.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MyProfileActivity.this,
                            EditProfileActivity.class
                    );

            startActivity(intent);
        });
    }

    private void applyTheme() {

        boolean darkMode =
                preferences.getBoolean(
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

        toolbar.setBackgroundColor(PURPLE);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIconTint(Color.WHITE);

        personalInfoTitle.setTextColor(
                Color.BLACK
        );

        nameCard.setCardBackgroundColor(
                Color.WHITE
        );

        emailCard.setCardBackgroundColor(
                Color.WHITE
        );

        phoneCard.setCardBackgroundColor(
                Color.WHITE
        );

        locationCard.setCardBackgroundColor(
                Color.WHITE
        );

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

        saveChangesButton.setBackgroundTintList(
                android.content.res.ColorStateList
                        .valueOf(PURPLE)
        );

        saveChangesButton.setTextColor(
                Color.WHITE
        );
    }

    private void applyDarkMode() {

        profileLayout.setBackgroundColor(
                Color.BLACK
        );

        toolbar.setBackgroundColor(PURPLE);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIconTint(Color.WHITE);

        personalInfoTitle.setTextColor(
                Color.WHITE
        );

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

        saveChangesButton.setBackgroundTintList(
                android.content.res.ColorStateList
                        .valueOf(PURPLE)
        );

        saveChangesButton.setTextColor(
                Color.WHITE
        );
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (preferences == null) {

            preferences =
                    getSharedPreferences(
                            "BorrowBuddy",
                            MODE_PRIVATE
                    );
        }

        loadProfileData();
        applyTheme();
    }
}

