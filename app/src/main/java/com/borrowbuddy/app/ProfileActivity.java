package com.borrowbuddy.app;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class ProfileActivity extends AppCompatActivity {

    // =========================
    // COLORS
    // =========================

    private static final int PURPLE =
            Color.rgb(106, 27, 154);

    private static final int LIGHT_BG =
            Color.rgb(245, 245, 245);

    private static final int DARK_CARD =
            Color.BLACK;

    private static final int WHITE =
            Color.WHITE;

    private static final int BLACK =
            Color.BLACK;

    // =========================
    // TEXT VIEWS
    // =========================

    TextView backButton;
    TextView headerTitle;
    TextView personalInfoTitle;
    TextView changePhotoButton;

    // =========================
    // IMAGE
    // =========================

    ImageView profileImage;

    // =========================
    // EDIT TEXT
    // =========================

    EditText nameValue;
    EditText profileEmail;
    EditText phoneValue;
    EditText locationValue;

    // =========================
    // BUTTON
    // =========================

    Button saveChangesButton;

    // =========================
    // LAYOUT
    // =========================

    ScrollView profileScrollView;
    ViewGroup profileLayout;

    // =========================
    // CARDS
    // =========================

    MaterialCardView nameCard;
    MaterialCardView emailCard;
    MaterialCardView phoneCard;
    MaterialCardView locationCard;

    // =========================
    // SHARED PREFERENCES
    // =========================

    private static final String PREF_NAME = "BorrowBuddy";
    private static final String DARK_MODE = "darkMode";

    // =========================
    // GALLERY
    // =========================

    private final ActivityResultLauncher<String> galleryLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.GetContent(),
                    uri -> {

                        if (uri != null) {

                            // Selected photo show
                            profileImage.setImageURI(uri);

                            // Photo save
                            getSharedPreferences(
                                    PREF_NAME,
                                    MODE_PRIVATE
                            )
                                    .edit()
                                    .putString(
                                            "profilePhoto",
                                            uri.toString()
                                    )
                                    .apply();
                        }
                    }
            );


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);


        // =========================
        // HIDE ACTION BAR
        // =========================

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        // =========================
        // STATUS BAR + NAVIGATION BAR
        // =========================

        Window window = getWindow();

        window.setStatusBarColor(PURPLE);
        window.setNavigationBarColor(PURPLE);

        window.getDecorView().setSystemUiVisibility(0);


        // =========================
        // FIND VIEWS
        // =========================

        backButton =
                findViewById(R.id.backButton);

        headerTitle =
                findViewById(R.id.headerTitle);

        personalInfoTitle =
                findViewById(R.id.personalInfoTitle);

        profileImage =
                findViewById(R.id.profileImage);

        changePhotoButton =
                findViewById(R.id.changePhotoButton);

        nameValue =
                findViewById(R.id.nameValue);

        profileEmail =
                findViewById(R.id.profileEmail);

        phoneValue =
                findViewById(R.id.phoneValue);

        locationValue =
                findViewById(R.id.locationValue);

        saveChangesButton =
                findViewById(R.id.saveChangesButton);


        // =========================
        // LAYOUT
        // =========================

        profileScrollView =
                findViewById(R.id.profileScrollView);

        profileLayout =
                findViewById(R.id.profileLayout);


        // =========================
        // CARDS
        // =========================

        nameCard =
                findViewById(R.id.nameCard);

        emailCard =
                findViewById(R.id.emailCard);

        phoneCard =
                findViewById(R.id.phoneCard);

        locationCard =
                findViewById(R.id.locationCard);


        // =========================
        // SHARED PREFERENCES
        // =========================

        SharedPreferences preferences =
                getSharedPreferences(
                        PREF_NAME,
                        MODE_PRIVATE
                );


        // =========================
        // BACK BUTTON
        // =========================

        backButton.setOnClickListener(v -> finish());


        // =========================
        // DEFAULT PROFILE DATA
        // =========================

        String savedName =
                preferences.getString(
                        "profileName",
                        "Shweta"
                );

        String savedEmail =
                preferences.getString(
                        "profileEmail",
                        "shweta@gmail.com"
                );

        String savedPhone =
                preferences.getString(
                        "profilePhone",
                        "9876543210"
                );

        String savedLocation =
                preferences.getString(
                        "profileLocation",
                        "Ahmedabad, Gujarat"
                );


        nameValue.setText(savedName);
        profileEmail.setText(savedEmail);
        phoneValue.setText(savedPhone);
        locationValue.setText(savedLocation);


        // =========================
        // LOAD PROFILE PHOTO
        // =========================

        String savedPhoto =
                preferences.getString(
                        "profilePhoto",
                        ""
                );

        if (!savedPhoto.isEmpty()) {

            try {

                profileImage.setImageURI(
                        Uri.parse(savedPhoto)
                );

            } catch (Exception e) {

                // Keep default image
            }
        }


        // =========================
        // CHANGE PHOTO
        // =========================

        changePhotoButton.setOnClickListener(v -> {

            galleryLauncher.launch("image/*");

        });


        // =========================
        // SAVE CHANGES
        // =========================

        saveChangesButton.setOnClickListener(v -> {

            String name =
                    nameValue.getText()
                            .toString()
                            .trim();

            String email =
                    profileEmail.getText()
                            .toString()
                            .trim();

            String phone =
                    phoneValue.getText()
                            .toString()
                            .trim();

            String location =
                    locationValue.getText()
                            .toString()
                            .trim();


            // NAME
            if (name.isEmpty()) {

                nameValue.setError(
                        "Enter your name"
                );

                nameValue.requestFocus();

                return;
            }


            // EMAIL
            if (email.isEmpty()) {

                profileEmail.setError(
                        "Enter your email"
                );

                profileEmail.requestFocus();

                return;
            }


            // PHONE
            if (phone.isEmpty()) {

                phoneValue.setError(
                        "Enter phone number"
                );

                phoneValue.requestFocus();

                return;
            }


            // LOCATION
            if (location.isEmpty()) {

                locationValue.setError(
                        "Enter your location"
                );

                locationValue.requestFocus();

                return;
            }


            // =========================
            // SAVE PROFILE
            // =========================

            getSharedPreferences(
                    PREF_NAME,
                    MODE_PRIVATE
            )
                    .edit()
                    .putString(
                            "profileName",
                            name
                    )
                    .putString(
                            "profileEmail",
                            email
                    )
                    .putString(
                            "profilePhone",
                            phone
                    )
                    .putString(
                            "profileLocation",
                            location
                    )
                    .apply();


            Toast.makeText(
                    ProfileActivity.this,
                    "Profile updated successfully",
                    Toast.LENGTH_SHORT
            ).show();

        });


        // =========================
        // LOAD DARK MODE
        // =========================

        boolean isDarkMode =
                preferences.getBoolean(
                        DARK_MODE,
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

        // Background
        profileScrollView.setBackgroundColor(
                Color.BLACK
        );

        profileLayout.setBackgroundColor(
                Color.BLACK
        );


        // Cards
        setDarkCard(nameCard);
        setDarkCard(emailCard);
        setDarkCard(phoneCard);
        setDarkCard(locationCard);


        // Text
        changeAllTextColor(
                profileLayout,
                WHITE
        );


        // EditTexts
        setEditTextDark(nameValue);
        setEditTextDark(profileEmail);
        setEditTextDark(phoneValue);
        setEditTextDark(locationValue);


        // Header
        View header =
                findViewById(R.id.profileHeader);

        if (header != null) {

            header.setBackgroundColor(
                    PURPLE
            );
        }


        headerTitle.setTextColor(
                WHITE
        );

        backButton.setTextColor(
                WHITE
        );


        // Change Photo purple
        changePhotoButton.setTextColor(
                Color.rgb(186, 104, 200)
        );


        // Button
        saveChangesButton.setBackgroundTintList(
                ColorStateList.valueOf(
                        PURPLE
                )
        );

        saveChangesButton.setTextColor(
                WHITE
        );


        // Status Bar
        getWindow().setStatusBarColor(
                PURPLE
        );

        getWindow().setNavigationBarColor(
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

        // Background
        profileScrollView.setBackgroundColor(
                LIGHT_BG
        );

        profileLayout.setBackgroundColor(
                LIGHT_BG
        );


        // Cards
        setLightCard(nameCard);
        setLightCard(emailCard);
        setLightCard(phoneCard);
        setLightCard(locationCard);


        // Text
        changeAllTextColor(
                profileLayout,
                BLACK
        );


        // EditTexts
        setEditTextLight(nameValue);
        setEditTextLight(profileEmail);
        setEditTextLight(phoneValue);
        setEditTextLight(locationValue);


        // Header
        View header =
                findViewById(R.id.profileHeader);

        if (header != null) {

            header.setBackgroundColor(
                    PURPLE
            );
        }


        headerTitle.setTextColor(
                WHITE
        );

        backButton.setTextColor(
                WHITE
        );


        // Change Photo
        changePhotoButton.setTextColor(
                PURPLE
        );


        // Button
        saveChangesButton.setBackgroundTintList(
                ColorStateList.valueOf(
                        PURPLE
                )
        );

        saveChangesButton.setTextColor(
                WHITE
        );


        // Status Bar
        getWindow().setStatusBarColor(
                PURPLE
        );

        getWindow().setNavigationBarColor(
                PURPLE
        );

        getWindow()
                .getDecorView()
                .setSystemUiVisibility(0);
    }


    // ==================================================
    // DARK CARD
    // ==================================================

    private void setDarkCard(
            MaterialCardView card
    ) {

        card.setCardBackgroundColor(
                DARK_CARD
        );

        card.setStrokeColor(
                Color.rgb(70, 70, 70)
        );

        card.setStrokeWidth(1);
    }


    // ==================================================
    // LIGHT CARD
    // ==================================================

    private void setLightCard(
            MaterialCardView card
    ) {

        card.setCardBackgroundColor(
                WHITE
        );

        card.setStrokeColor(
                Color.rgb(221, 221, 221)
        );

        card.setStrokeWidth(1);
    }


    // ==================================================
    // DARK EDIT TEXT
    // ==================================================

    private void setEditTextDark(
            EditText editText
    ) {

        editText.setTextColor(
                WHITE
        );

        editText.setHintTextColor(
                Color.LTGRAY
        );
    }


    // ==================================================
    // LIGHT EDIT TEXT
    // ==================================================

    private void setEditTextLight(
            EditText editText
    ) {

        editText.setTextColor(
                BLACK
        );

        editText.setHintTextColor(
                Color.rgb(117, 117, 117)
        );
    }


    // ==================================================
    // CHANGE ALL TEXT COLORS
    // ==================================================

    private void changeAllTextColor(
            View view,
            int color
    ) {

        if (view instanceof TextView) {

            TextView textView =
                    (TextView) view;


            // These are handled separately
            if (textView.getId()
                    != R.id.backButton
                    &&
                    textView.getId()
                            != R.id.headerTitle
                    &&
                    textView.getId()
                            != R.id.changePhotoButton
                    &&
                    textView.getId()
                            != R.id.saveChangesButton
                    &&
                    textView.getId()
                            != R.id.nameValue
                    &&
                    textView.getId()
                            != R.id.profileEmail
                    &&
                    textView.getId()
                            != R.id.phoneValue
                    &&
                    textView.getId()
                            != R.id.locationValue) {

                textView.setTextColor(
                        color
                );
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


    // ==================================================
    // ON RESUME
    // ==================================================

    @Override
    protected void onResume() {

        super.onResume();


        if (profileLayout == null) {
            return;
        }


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

            darkMode();

        } else {

            lightMode();

        }
    }
}