package com.borrowbuddy.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AdminProfileActivity extends AppCompatActivity {

    // ================= VIEWS =================

    View mainLayout;

    ImageView imgAdminProfile;

    TextView btnBack;
    TextView btnEditProfile;

    TextView txtName;
    TextView txtEmail;
    TextView txtPhone;
    TextView txtLocation;

    TextView lblName;
    TextView lblEmail;
    TextView lblPhone;
    TextView lblLocation;

    // ================= SHARED PREFERENCES =================

    SharedPreferences profilePreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_profile);

        // ================= FIND VIEWS =================

        mainLayout = findViewById(R.id.mainLayout);

        imgAdminProfile =
                findViewById(R.id.imgAdminProfile);

        btnBack =
                findViewById(R.id.btnBack);

        btnEditProfile =
                findViewById(R.id.btnEditProfile);

        txtName =
                findViewById(R.id.txtName);

        txtEmail =
                findViewById(R.id.txtEmail);

        txtPhone =
                findViewById(R.id.txtPhone);

        txtLocation =
                findViewById(R.id.txtLocation);

        lblName =
                findViewById(R.id.lblName);

        lblEmail =
                findViewById(R.id.lblEmail);

        lblPhone =
                findViewById(R.id.lblPhone);

        lblLocation =
                findViewById(R.id.lblLocation);


        // ================= SHARED PREFERENCES =================

        profilePreferences =
                getSharedPreferences(
                        "AdminProfile",
                        MODE_PRIVATE
                );


        // ================= LOAD PROFILE =================

        loadProfile();


        // ================= DARK MODE =================

        applyDarkMode();


        // ================= BACK BUTTON =================

        btnBack.setOnClickListener(v -> finish());


        // ================= EDIT PROFILE =================

        btnEditProfile.setOnClickListener(v -> {

            Intent intent = new Intent(
                    AdminProfileActivity.this,
                    EditProfileActivity.class
            );

            startActivity(intent);

        });

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

        // ================= GET SAVED DATA =================

        String name =
                profilePreferences.getString(
                        "name",
                        "Admin"
                );

        String email =
                profilePreferences.getString(
                        "email",
                        "admin@gmail.com"
                );

        String phone =
                profilePreferences.getString(
                        "phone",
                        ""
                );

        String location =
                profilePreferences.getString(
                        "location",
                        ""
                );


        // ================= SHOW DATA =================

        txtName.setText(name);

        txtEmail.setText(email);

        txtPhone.setText(phone);

        txtLocation.setText(location);


        // ================= LOAD PROFILE PHOTO =================

        String photoUri =
                profilePreferences.getString(
                        "photoUri",
                        ""
                );

        if (!photoUri.isEmpty()) {

            try {

                imgAdminProfile.setImageURI(
                        Uri.parse(photoUri)
                );

            } catch (Exception e) {

                imgAdminProfile.setImageResource(
                        android.R.drawable.ic_menu_myplaces
                );
            }
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

            // ================= DARK BACKGROUND =================

            mainLayout.setBackgroundColor(
                    Color.rgb(16, 16, 16)
            );


            // ================= LABELS =================

            lblName.setTextColor(Color.WHITE);

            lblEmail.setTextColor(Color.WHITE);

            lblPhone.setTextColor(Color.WHITE);

            lblLocation.setTextColor(Color.WHITE);


            // ================= DATA =================

            txtName.setTextColor(Color.WHITE);

            txtEmail.setTextColor(Color.WHITE);

            txtPhone.setTextColor(Color.WHITE);

            txtLocation.setTextColor(Color.WHITE);

        } else {

            // ================= LIGHT BACKGROUND =================

            mainLayout.setBackgroundColor(
                    Color.rgb(246, 243, 248)
            );


            // ================= LABELS =================

            lblName.setTextColor(Color.BLACK);

            lblEmail.setTextColor(Color.BLACK);

            lblPhone.setTextColor(Color.BLACK);

            lblLocation.setTextColor(Color.BLACK);


            // ================= DATA =================

            txtName.setTextColor(Color.BLACK);

            txtEmail.setTextColor(Color.BLACK);

            txtPhone.setTextColor(Color.BLACK);

            txtLocation.setTextColor(Color.BLACK);

        }


        // ================= TOOLBAR =================

        View toolbar =
                (View) btnBack.getParent();

        if (toolbar != null) {

            toolbar.setBackgroundColor(
                    Color.rgb(171, 71, 188)
            );
        }


        // ================= BUTTON =================

        btnBack.setTextColor(Color.WHITE);

        btnEditProfile.setTextColor(Color.WHITE);

    }

}