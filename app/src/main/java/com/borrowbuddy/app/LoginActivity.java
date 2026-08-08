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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Views
        imgLogo = findViewById(R.id.imgLogo);
        loginCard = findViewById(R.id.loginCard);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnCreate = findViewById(R.id.btnCreate);

        txtForgot = findViewById(R.id.txtForgot);

        // Animation
        Animation logoAnim = AnimationUtils.loadAnimation(this, R.anim.logo_zoom);
        Animation cardAnim = AnimationUtils.loadAnimation(this, R.anim.card_slide_up);

        imgLogo.startAnimation(logoAnim);
        loginCard.startAnimation(cardAnim);

        // Login
        btnLogin.setOnClickListener(v -> {

            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.equals("admin@gmail.com") && password.equals("123456")) {

                Toast.makeText(
                        LoginActivity.this,
                        "Login Successful",
                        Toast.LENGTH_SHORT
                ).show();

                Intent intent = new Intent(LoginActivity.this, OTPActivity.class);

                String role = getIntent().getStringExtra("role");
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

        // Create Account
        btnCreate.setOnClickListener(v -> {

            Intent intent = new Intent(
                    LoginActivity.this,
                    RegisterActivity.class
            );

            startActivity(intent);

        });

        // Forgot Password
        txtForgot.setOnClickListener(v -> {

            Intent intent = new Intent(
                    LoginActivity.this,
                    ForgotPasswordActivity.class
            );

            startActivity(intent);

        });

    }
}