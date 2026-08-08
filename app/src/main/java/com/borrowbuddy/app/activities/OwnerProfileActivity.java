package com.borrowbuddy.app.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.borrowbuddy.app.R;
import com.google.android.material.button.MaterialButton;

import com.google.android.material.appbar.MaterialToolbar;
import android.graphics.Color;

public class OwnerProfileActivity extends AppCompatActivity {

    MaterialButton btnEditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_profile);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIconTint(Color.WHITE);

        // Initialize Buttons
        btnEditProfile = findViewById(R.id.btnEditProfile);


        // Open Edit Profile Screen
        btnEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(OwnerProfileActivity.this,
                    EditOwnerProfileActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}