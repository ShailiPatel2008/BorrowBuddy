package com.borrowbuddy.app.activities;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.borrowbuddy.app.R;
import com.google.android.material.appbar.MaterialToolbar;

public class TermsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        MaterialToolbar toolbarTerms = findViewById(R.id.toolbarTerms);

        toolbarTerms.setBackgroundColor(Color.parseColor("#6C4AB6"));

        // Make back arrow white
        toolbarTerms.setNavigationIconTint(Color.WHITE);

        toolbarTerms.setNavigationOnClickListener(v -> {
            finish();
        });
    }
}