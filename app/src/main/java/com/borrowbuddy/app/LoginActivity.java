package com.borrowbuddy.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin, btnCreate;
    private TextView txtForgot;
    private ImageView imgLogo;
    private CardView loginCard;
    private TextView txtBackLogin;

    private String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        // Get selected role
        role = getIntent().getStringExtra("role");

        // Initialize Views
        imgLogo = findViewById(R.id.imgLogo);
        loginCard = findViewById(R.id.loginCard);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnCreate = findViewById(R.id.btnCreate);

        txtForgot = findViewById(R.id.txtForgot);
        txtBackLogin = findViewById(R.id.txtBackLogin);

        // Animation
        Animation logoAnim =
                AnimationUtils.loadAnimation(
                        this,
                        R.anim.logo_zoom
                );

        Animation cardAnim =
                AnimationUtils.loadAnimation(
                        this,
                        R.anim.card_slide_up
                );

        imgLogo.startAnimation(logoAnim);
        loginCard.startAnimation(cardAnim);

        // =========================
        // LOGIN
        // =========================

        btnLogin.setOnClickListener(v -> {

            String email =
                    etEmail.getText().toString().trim();

            String password =
                    etPassword.getText().toString().trim();

            if (email.isEmpty()) {
                etEmail.setError("Enter email");
                etEmail.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                etPassword.setError("Enter password");
                etPassword.requestFocus();
                return;
            }

            // TEST LOGIN
            if (email.equals("admin@gmail.com")
                    && password.equals("123456")) {

                Toast.makeText(
                        LoginActivity.this,
                        "Login Successful",
                        Toast.LENGTH_SHORT
                ).show();

                // Go to OTP
                Intent intent = new Intent(
                        LoginActivity.this,
                        OTPActivity.class
                );

                // Pass selected role
                intent.putExtra("role", role);

                startActivity(intent);
                finish();

            } else {

                Toast.makeText(
                        LoginActivity.this,
                        "Invalid Email or Password",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        // =========================
        // CREATE ACCOUNT
        // =========================

        btnCreate.setOnClickListener(v -> {

            Intent intent = new Intent(
                    LoginActivity.this,
                    RegisterActivity.class
            );

            // Keep role
            intent.putExtra("role", role);

            startActivity(intent);
        });

        // =========================
        // FORGOT PASSWORD
        // =========================

        txtForgot.setOnClickListener(v -> {

            Intent intent = new Intent(
                    LoginActivity.this,
                    ForgotPasswordActivity.class
            );

            startActivity(intent);
        });

        // =========================
        // BACK
        // =========================

        txtBackLogin.setOnClickListener(v -> {
            finish();
        });
    }
}