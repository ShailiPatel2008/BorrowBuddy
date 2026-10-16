package com.borrowbuddy.app;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AdminProfileActivity extends AppCompatActivity {

    // ================= VIEWS =================

    View mainLayout;

    ImageView imgAdminProfile;

    ImageButton btnBack;
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
    // ================= FIREBASE =================

    FirebaseAuth auth;
    FirebaseFirestore db;



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
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


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
                    AdminEditProfileActivity.class
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

        FirebaseUser currentUser = auth.getCurrentUser();

        if (currentUser == null) {

            finish();
            return;
        }

        String uid = currentUser.getUid();

        db.collection("users")
                .document(uid)
                .get()
                .addOnSuccessListener(documentSnapshot -> {

                    if (documentSnapshot.exists()) {

                        String name =
                                documentSnapshot.getString("full_name");

                        String email =
                                documentSnapshot.getString("email");

                        String phone =
                                documentSnapshot.getString("phone");

                        String location =
                                documentSnapshot.getString("location");
                        String imageUrl =
                                documentSnapshot.getString("profileImageUrl");


                        if(imageUrl != null && !imageUrl.isEmpty()){

                            com.squareup.picasso.Picasso.get()
                                    .load(imageUrl)
                                    .into(imgAdminProfile);

                        }
                        if (phone == null || phone.isEmpty()) {
                            phone = "Not Added";
                        }

                        if (location == null || location.isEmpty()) {
                            location = "Not Added";
                        }

                        txtName.setText(name);
                        txtEmail.setText(email);
                        txtPhone.setText(phone);
                        txtLocation.setText(location);
                    }

                });

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
                    Color.rgb(108, 74, 182)
            );
        }


        // ================= BUTTON =================

        btnEditProfile.setTextColor(Color.WHITE);

    }

}