package com.borrowbuddy.app.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.borrowbuddy.app.R;
import com.google.android.material.appbar.MaterialToolbar;

public class EarningsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_earnings);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Earnings");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });
    }
}