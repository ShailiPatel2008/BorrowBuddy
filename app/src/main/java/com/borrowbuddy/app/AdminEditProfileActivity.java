package com.borrowbuddy.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class AdminEditProfileActivity extends AppCompatActivity {

    View mainLayout;

    ImageButton btnBack;
    TextView btnChangePhoto;
    TextView btnSaveChanges;

    ImageView imgProfile;

    TextView lblFullName;
    TextView lblEmail;
    TextView lblPhone;
    TextView lblLocation;

    EditText edtName;
    EditText edtEmail;
    EditText edtPhone;
    EditText edtLocation;

    SharedPreferences profilePreferences;

    private static final int PICK_IMAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_edit_profile);

        // ================= FIND VIEWS =================

        mainLayout = findViewById(R.id.mainLayout);

        btnBack = findViewById(R.id.btnBack);
        btnChangePhoto = findViewById(R.id.btnChangePhoto);
        btnSaveChanges = findViewById(R.id.btnSaveChanges);

        imgProfile = findViewById(R.id.imgProfile);

        lblFullName = findViewById(R.id.lblFullName);
        lblEmail = findViewById(R.id.lblEmail);
        lblPhone = findViewById(R.id.lblPhone);
        lblLocation = findViewById(R.id.lblLocation);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        edtLocation = findViewById(R.id.edtLocation);

        // ================= SHARED PREFERENCES =================

        profilePreferences = getSharedPreferences(
                "AdminProfile",
                MODE_PRIVATE
        );

        // ================= LOAD PROFILE =================

        loadProfile();

        // ================= DARK MODE =================

        applyDarkMode();

        // ================= BACK BUTTON =================

        btnBack.setOnClickListener(v -> finish());

        // ================= CHANGE PHOTO =================

        btnChangePhoto.setOnClickListener(v -> {

            Intent intent = new Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            );

            startActivityForResult(intent, PICK_IMAGE);
        });

        // ================= SAVE CHANGES =================

        btnSaveChanges.setOnClickListener(v -> saveProfile());
    }

    // =========================================================
    // ON RESUME
    // =========================================================

    @Override
    protected void onResume() {
        super.onResume();

        if (profilePreferences != null) {
            loadProfile();
        }

        applyDarkMode();
    }

    // =========================================================
    // LOAD PROFILE
    // =========================================================

    private void loadProfile() {

        String name = profilePreferences.getString(
                "name",
                "Admin"
        );

        String email = profilePreferences.getString(
                "email",
                "admin@gmail.com"
        );

        String phone = profilePreferences.getString(
                "phone",
                ""
        );

        String location = profilePreferences.getString(
                "location",
                ""
        );

        edtName.setText(name);
        edtEmail.setText(email);
        edtPhone.setText(phone);
        edtLocation.setText(location);

        // Load saved photo
        String photoUri = profilePreferences.getString(
                "photoUri",
                ""
        );

        if (!photoUri.isEmpty()) {

            try {

                imgProfile.setImageURI(
                        Uri.parse(photoUri)
                );

            } catch (Exception e) {

                imgProfile.setImageResource(
                        android.R.drawable.ic_menu_myplaces
                );
            }
        }
    }

    // =========================================================
    // SAVE PROFILE
    // =========================================================

    private void saveProfile() {

        String name = edtName.getText()
                .toString()
                .trim();

        String email = edtEmail.getText()
                .toString()
                .trim();

        String phone = edtPhone.getText()
                .toString()
                .trim();

        String location = edtLocation.getText()
                .toString()
                .trim();

        // ================= VALIDATION =================

        if (TextUtils.isEmpty(name)) {

            edtName.setError("Enter full name");
            edtName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {

            edtEmail.setError("Enter email address");
            edtEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(phone)) {

            edtPhone.setError("Enter mobile number");
            edtPhone.requestFocus();
            return;
        }

        if (phone.length() != 10) {

            edtPhone.setError(
                    "Enter valid 10 digit mobile number"
            );

            edtPhone.requestFocus();
            return;
        }

        // ================= SAVE DATA =================

        profilePreferences.edit()
                .putString("name", name)
                .putString("email", email)
                .putString("phone", phone)
                .putString("location", location)
                .apply();

        // ================= SUCCESS MESSAGE =================

        Toast.makeText(
                AdminEditProfileActivity.this,
                "Profile updated successfully",
                Toast.LENGTH_SHORT
        ).show();

        // ================= GO BACK TO ADMIN PROFILE =================

        finish();
    }

    // =========================================================
    // IMAGE PICK RESULT
    // =========================================================

    @Override
    protected void onActivityResult(
            int requestCode,
            int resultCode,
            Intent data) {

        super.onActivityResult(
                requestCode,
                resultCode,
                data
        );

        if (requestCode == PICK_IMAGE
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            Uri imageUri = data.getData();

            // Show selected image
            imgProfile.setImageURI(imageUri);

            // Save photo URI
            profilePreferences.edit()
                    .putString(
                            "photoUri",
                            imageUri.toString()
                    )
                    .apply();
        }
    }

    // =========================================================
    // DARK MODE
    // =========================================================

    private void applyDarkMode() {

        if (mainLayout == null) {
            return;
        }

        boolean darkMode =
                getSharedPreferences(
                        "Settings",
                        MODE_PRIVATE
                ).getBoolean(
                        "dark_mode",
                        false
                );

        if (darkMode) {

            // Background
            mainLayout.setBackgroundColor(
                    Color.rgb(16, 16, 16)
            );

            // Labels
            lblFullName.setTextColor(Color.WHITE);
            lblEmail.setTextColor(Color.WHITE);
            lblPhone.setTextColor(Color.WHITE);
            lblLocation.setTextColor(Color.WHITE);

            // EditText text
            edtName.setTextColor(Color.WHITE);
            edtEmail.setTextColor(Color.WHITE);
            edtPhone.setTextColor(Color.WHITE);
            edtLocation.setTextColor(Color.WHITE);

            // Hint
            edtName.setHintTextColor(Color.LTGRAY);
            edtEmail.setHintTextColor(Color.LTGRAY);
            edtPhone.setHintTextColor(Color.LTGRAY);
            edtLocation.setHintTextColor(Color.LTGRAY);

            // EditText background
            edtName.setBackgroundColor(
                    Color.rgb(45, 45, 45)
            );

            edtEmail.setBackgroundColor(
                    Color.rgb(45, 45, 45)
            );

            edtPhone.setBackgroundColor(
                    Color.rgb(45, 45, 45)
            );

            edtLocation.setBackgroundColor(
                    Color.rgb(45, 45, 45)
            );

        } else {

            // Background
            mainLayout.setBackgroundColor(
                    Color.rgb(246, 243, 248)
            );

            // Labels
            lblFullName.setTextColor(Color.BLACK);
            lblEmail.setTextColor(Color.BLACK);
            lblPhone.setTextColor(Color.BLACK);
            lblLocation.setTextColor(Color.BLACK);

            // EditText text
            edtName.setTextColor(Color.BLACK);
            edtEmail.setTextColor(Color.BLACK);
            edtPhone.setTextColor(Color.BLACK);
            edtLocation.setTextColor(Color.BLACK);

            // Hint
            edtName.setHintTextColor(
                    Color.rgb(119, 119, 119)
            );

            edtEmail.setHintTextColor(
                    Color.rgb(119, 119, 119)
            );

            edtPhone.setHintTextColor(
                    Color.rgb(119, 119, 119)
            );

            edtLocation.setHintTextColor(
                    Color.rgb(119, 119, 119)
            );

            // EditText background
            edtName.setBackgroundColor(Color.WHITE);
            edtEmail.setBackgroundColor(Color.WHITE);
            edtPhone.setBackgroundColor(Color.WHITE);
            edtLocation.setBackgroundColor(Color.WHITE);
        }

        // Buttons
        btnChangePhoto.setTextColor(Color.WHITE);
        btnSaveChanges.setTextColor(Color.WHITE);

        // Toolbar purple
        View toolbar = (View) btnBack.getParent();

        if (toolbar != null) {

            toolbar.setBackgroundColor(
                    Color.rgb(108, 74, 182)
            );
        }
    }
}