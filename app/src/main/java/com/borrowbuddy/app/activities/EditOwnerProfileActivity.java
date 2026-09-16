package com.borrowbuddy.app.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.borrowbuddy.app.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditOwnerProfileActivity extends AppCompatActivity {

    private TextInputEditText etName;
    private TextInputEditText etEmail;
    private TextInputEditText etPhone;
    private TextInputEditText etLocation;

    private MaterialButton btnSave;
    private MaterialButton btnChangePhoto;

    private ShapeableImageView imgProfile;

    private FirebaseAuth auth;
    private FirebaseFirestore db;

    private static final int PICK_IMAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_owner_profile);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        MaterialToolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationOnClickListener(v -> finish());

        // Initialize Views
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etLocation = findViewById(R.id.etLocation);

        btnSave = findViewById(R.id.btnSave);
        btnChangePhoto = findViewById(R.id.btnChangePhoto);

        imgProfile = findViewById(R.id.imgProfile);

        // Load owner data
        loadOwnerData();

        // Change Photo
        btnChangePhoto.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, PICK_IMAGE);

        });

        // Save Button
        btnSave.setOnClickListener(v -> saveProfile());
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

                        String name = document.getString("full_name");
                        String email = document.getString("email");
                        String phone = document.getString("phone");
                        String location = document.getString("location");

                        if (name != null)
                            etName.setText(name);

                        if (email != null)
                            etEmail.setText(email);

                        if (phone != null)
                            etPhone.setText(phone);

                        if (location != null)
                            etLocation.setText(location);

                    }

                });

    }

    private void saveProfile() {

        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String location = etLocation.getText().toString().trim();

        if (name.isEmpty()) {
            etName.setError("Enter Name");
            return;
        }

        if (email.isEmpty()) {
            etEmail.setError("Enter Email");
            return;
        }

        if (phone.isEmpty()) {
            etPhone.setError("Enter Phone Number");
            return;
        }

        if (location.isEmpty()) {
            etLocation.setError("Enter Location");
            return;
        }

        FirebaseUser currentUser = auth.getCurrentUser();

        if (currentUser == null) {
            return;
        }

        Map<String, Object> updates = new HashMap<>();

        updates.put("full_name", name);
        updates.put("email", email);
        updates.put("phone", phone);
        updates.put("location", location);

        db.collection("users")
                .document(currentUser.getUid())
                .update(updates)
                .addOnSuccessListener(unused -> {

                    Toast.makeText(
                            EditOwnerProfileActivity.this,
                            "Profile Updated Successfully",
                            Toast.LENGTH_SHORT
                    ).show();

                    finish();

                })
                .addOnFailureListener(e ->

                        Toast.makeText(
                                EditOwnerProfileActivity.this,
                                e.getMessage(),
                                Toast.LENGTH_LONG
                        ).show()

                );

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE &&
                resultCode == RESULT_OK &&
                data != null) {

            Uri imageUri = data.getData();

            if (imageUri != null) {
                imgProfile.setImageURI(imageUri);
            }
        }
    }
}