package com.borrowbuddy.app.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.borrowbuddy.app.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class OwnerProfileActivity extends AppCompatActivity {

    private MaterialButton btnEditProfile;

    private TextView txtOwnerName;
    private TextView txtEmail;
    private TextView txtPhone;
    private TextView txtLocation;
    private TextView txtListings;
    private TextView txtEarnings;

    private ShapeableImageView imgProfile;

    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_profile);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Owner Profile");
        }

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIconTint(Color.WHITE);

        // Initialize Views
        btnEditProfile = findViewById(R.id.btnEditProfile);

        imgProfile = findViewById(R.id.imgProfile);

        txtOwnerName = findViewById(R.id.txtOwnerName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);
        txtLocation = findViewById(R.id.txtLocation);
        txtListings = findViewById(R.id.txtListings);
        txtEarnings = findViewById(R.id.txtEarnings);

        loadOwnerData();

        // Edit Profile Button
        btnEditProfile.setOnClickListener(v -> {

            Intent intent = new Intent(
                    OwnerProfileActivity.this,
                    EditOwnerProfileActivity.class
            );

            startActivity(intent);

        });
    }

    private void loadOwnerData() {

        FirebaseUser currentUser = auth.getCurrentUser();

        if (currentUser == null) {
            return;
        }

        db.collection("users")
                .document(currentUser.getUid())
                .get()
                .addOnSuccessListener(document -> {

                    if (document.exists()) {

                        // Name
                        String name = document.getString("full_name");
                        if (name == null || name.isEmpty()) {
                            name = "Owner";
                        }
                        txtOwnerName.setText(name);

                        // Email
                        String email = document.getString("email");
                        if (email == null || email.isEmpty()) {
                            email = "No Email";
                        }
                        txtEmail.setText(email);

                        // Phone
                        String phone = document.getString("phone");
                        if (phone == null || phone.isEmpty()) {
                            phone = "Not Added";
                        }
                        txtPhone.setText("📞 Phone : " + phone);

                        // Location
                        String location = document.getString("location");
                        if (location == null || location.isEmpty()) {
                            location = "Not Added";
                        }
                        txtLocation.setText("📍 Location : " + location);

                        // Default values
                        txtListings.setText("📦 Total Listings : 0");
                        txtEarnings.setText("💰 Total Earnings : ₹0");

                    } else {

                        txtOwnerName.setText("Owner");
                        txtEmail.setText("No Email");
                        txtPhone.setText("📞 Phone : Not Added");
                        txtLocation.setText("📍 Location : Not Added");
                        txtListings.setText("📦 Total Listings : 0");
                        txtEarnings.setText("💰 Total Earnings : ₹0");

                    }

                })
                .addOnFailureListener(e -> {

                    txtOwnerName.setText("Owner");
                    txtEmail.setText("No Email");
                    txtPhone.setText("📞 Phone : Not Added");
                    txtLocation.setText("📍 Location : Not Added");
                    txtListings.setText("📦 Total Listings : 0");
                    txtEarnings.setText("💰 Total Earnings : ₹0");

                });

    }

    @Override
    protected void onResume() {
        super.onResume();

        loadOwnerData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}