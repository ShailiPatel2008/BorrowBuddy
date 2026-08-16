package com.borrowbuddy.app.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.borrowbuddy.app.R;

import android.graphics.Color;
import com.google.android.material.appbar.MaterialToolbar;

public class HelpSupportActivity extends AppCompatActivity {

    Button btnCallAdmin, btnEmailSupport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_support);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(Color.WHITE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setTint(Color.WHITE);
        }

        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Help & Support");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        btnCallAdmin = findViewById(R.id.btnCallAdmin);
        btnEmailSupport = findViewById(R.id.btnEmailSupport);

        btnCallAdmin.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:9898733203"));
            startActivity(intent);

        });

        btnEmailSupport.setOnClickListener(v -> {

            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:admin@borrowbuddy.com"));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "BorrowBuddy Support");

            startActivity(emailIntent);

        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}