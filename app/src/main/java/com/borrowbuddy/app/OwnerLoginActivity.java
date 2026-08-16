package com.borrowbuddy.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class OwnerLoginActivity extends AppCompatActivity {

    private TextInputEditText etOwnerEmail;
    private TextInputEditText etOwnerPassword;

    private MaterialButton btnOwnerLogin;
    private Button btnOwnerCreate;

    private TextView txtOwnerForgot;
    private TextView txtOwnerBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_owner_login);

        // Initialize Views
        etOwnerEmail = findViewById(R.id.etOwnerEmail);
        etOwnerPassword = findViewById(R.id.etOwnerPassword);

        btnOwnerLogin = findViewById(R.id.btnOwnerLogin);
        btnOwnerCreate = findViewById(R.id.btnOwnerCreate);

        txtOwnerForgot = findViewById(R.id.txtOwnerForgot);
        txtOwnerBack = findViewById(R.id.txtOwnerBack);


        // =========================
        // OWNER LOGIN
        // =========================

        btnOwnerLogin.setOnClickListener(v -> {

            String email =
                    etOwnerEmail.getText().toString().trim();

            String password =
                    etOwnerPassword.getText().toString().trim();


            if (email.isEmpty()) {

                etOwnerEmail.setError("Enter Owner Email");
                etOwnerEmail.requestFocus();

                return;
            }


            if (password.isEmpty()) {

                etOwnerPassword.setError("Enter Password");
                etOwnerPassword.requestFocus();

                return;
            }


            // Demo Owner Login
            if (email.equals("owner@gmail.com")
                    && password.equals("123456")) {

                Toast.makeText(
                        OwnerLoginActivity.this,
                        "Owner Login Successful",
                        Toast.LENGTH_SHORT
                ).show();


                Intent intent = new Intent(
                        OwnerLoginActivity.this,
                        OTPActivity.class
                );

                // VERY IMPORTANT
                intent.putExtra("role", "owner");

                startActivity(intent);

                finish();

            } else {

                Toast.makeText(
                        OwnerLoginActivity.this,
                        "Invalid Owner Email or Password",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });


        // =========================
        // CREATE NEW OWNER ACCOUNT
        // =========================

        btnOwnerCreate.setOnClickListener(v -> {

            Intent intent = new Intent(
                    OwnerLoginActivity.this,
                    RegisterActivity.class
            );

            // VERY IMPORTANT
            // Tell RegisterActivity this is OWNER registration
            intent.putExtra("role", "owner");

            startActivity(intent);
        });


        // =========================
        // FORGOT PASSWORD
        // =========================

        txtOwnerForgot.setOnClickListener(v -> {

            Intent intent = new Intent(
                    OwnerLoginActivity.this,
                    ForgotPasswordActivity.class
            );

            startActivity(intent);
        });


        // =========================
        // BACK
        // =========================

        txtOwnerBack.setOnClickListener(v -> {

            finish();
        });
    }
}