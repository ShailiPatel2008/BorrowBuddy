package com.borrowbuddy.app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ChooseRoleActivity extends AppCompatActivity {

    CardView cardUser, cardOwner, cardAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_choose_role);

        cardUser = findViewById(R.id.cardUser);
        cardOwner = findViewById(R.id.cardOwner);
        cardAdmin = findViewById(R.id.cardAdmin);

        // =========================
        // USER
        // =========================

        cardUser.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ChooseRoleActivity.this,
                    LoginActivity.class
            );

            intent.putExtra("role", "user");

            startActivity(intent);
        });


        // =========================
        // OWNER
        // =========================

        cardOwner.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ChooseRoleActivity.this,
                    OwnerLoginActivity.class
            );

            intent.putExtra("role", "owner");

            startActivity(intent);
        });


        // =========================
        // ADMIN
        // =========================

        cardAdmin.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ChooseRoleActivity.this,
                    AdminLoginActivity.class
            );

            intent.putExtra("role", "admin");

            startActivity(intent);
        });
    }
}