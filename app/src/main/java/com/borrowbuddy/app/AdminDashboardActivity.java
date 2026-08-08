package com.borrowbuddy.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AdminDashboardActivity extends AppCompatActivity {

    private TextView txtWelcome;
    private Button btnManageUsers, btnManageOwners, btnManageItems, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        txtWelcome = findViewById(R.id.txtWelcome);
        btnManageUsers = findViewById(R.id.btnManageUsers);
        btnManageOwners = findViewById(R.id.btnManageOwners);
        btnManageItems = findViewById(R.id.btnManageItems);
        btnLogout = findViewById(R.id.btnLogout);

        txtWelcome.setText("Welcome Admin");

        btnManageUsers.setOnClickListener(v -> {
            // Intent intent = new Intent(this, ManageUsersActivity.class);
            // startActivity(intent);
        });

        btnManageOwners.setOnClickListener(v -> {
            // Intent intent = new Intent(this, ManageOwnersActivity.class);
            // startActivity(intent);
        });

        btnManageItems.setOnClickListener(v -> {
            // Intent intent = new Intent(this, ManageItemsActivity.class);
            // startActivity(intent);
        });

        btnLogout.setOnClickListener(v -> {

            Intent intent = new Intent(AdminDashboardActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();

        });
    }
}