package com.borrowbuddy.app.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.borrowbuddy.app.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import com.google.android.material.appbar.MaterialToolbar;
import android.content.Intent;
import android.net.Uri;

import com.google.android.material.imageview.ShapeableImageView;

public class EditOwnerProfileActivity extends AppCompatActivity {

    private TextInputEditText etName, etEmail, etPhone, etLocation;
    private MaterialButton btnSave, btnChangePhoto;

    private ShapeableImageView imgProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_owner_profile);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        // Initialize Views
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etLocation = findViewById(R.id.etLocation);

        btnSave = findViewById(R.id.btnSave);
        btnChangePhoto = findViewById(R.id.btnChangePhoto);
        btnChangePhoto = findViewById(R.id.btnChangePhoto);
        imgProfile = findViewById(R.id.imgProfile);
        // Temporary sample data
        etName.setText("Shaili Patel");
        etEmail.setText("owner@gmail.com");
        etPhone.setText("9876543210");
        etLocation.setText("Surat, Gujarat");

        // Change Photo
        btnChangePhoto.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");

            startActivityForResult(intent, 100);
        });

        // Save Changes
        btnSave.setOnClickListener(v -> {

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

            Toast.makeText(this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();

            finish(); // Return to Owner Profile
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {

            Uri imageUri = data.getData();

            if (imageUri != null) {
                imgProfile.setImageURI(imageUri);
            }
        }
    }
}