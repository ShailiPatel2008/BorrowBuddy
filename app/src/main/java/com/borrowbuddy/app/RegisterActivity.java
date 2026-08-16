package com.borrowbuddy.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    private ImageView imgLogo;
    private CardView registerCard;

    private TextInputEditText etName;
    private TextInputEditText etEmail;
    private TextInputEditText etPassword;
    private TextInputEditText etConfirmPassword;

    private MaterialButton btnRegister;
    private TextView txtLogin;

    // Role
    private String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        // Get role
        role = getIntent().getStringExtra("role");

        // Logo & Card
        imgLogo = findViewById(R.id.imgLogo);
        registerCard = findViewById(R.id.registerCard);

        // EditTexts
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);

        // Buttons
        btnRegister = findViewById(R.id.btnRegister);
        txtLogin = findViewById(R.id.txtLogin);

        // Logo Animation
        Animation logoAnim =
                AnimationUtils.loadAnimation(
                        this,
                        R.anim.logo_zoom
                );

        imgLogo.startAnimation(logoAnim);

        // Card Animation
        Animation cardAnim =
                AnimationUtils.loadAnimation(
                        this,
                        R.anim.slide_up
                );

        registerCard.startAnimation(cardAnim);

        // =========================
        // REGISTER
        // =========================

        btnRegister.setOnClickListener(v -> {

            Animation clickAnim =
                    AnimationUtils.loadAnimation(
                            this,
                            R.anim.button_scale
                    );

            btnRegister.startAnimation(clickAnim);

            String name =
                    etName.getText().toString().trim();

            String email =
                    etEmail.getText().toString().trim();

            String password =
                    etPassword.getText().toString().trim();

            String confirmPassword =
                    etConfirmPassword.getText().toString().trim();


            // Name
            if (TextUtils.isEmpty(name)) {

                etName.setError("Enter Full Name");
                etName.requestFocus();

                return;
            }


            // Email
            if (TextUtils.isEmpty(email)) {

                etEmail.setError("Enter Email");
                etEmail.requestFocus();

                return;
            }


            // Password
            if (TextUtils.isEmpty(password)) {

                etPassword.setError("Enter Password");
                etPassword.requestFocus();

                return;
            }


            // Confirm Password
            if (TextUtils.isEmpty(confirmPassword)) {

                etConfirmPassword.setError(
                        "Confirm Password"
                );

                etConfirmPassword.requestFocus();

                return;
            }


            // Password Match
            if (!password.equals(confirmPassword)) {

                Toast.makeText(
                        RegisterActivity.this,
                        "Passwords do not match",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }


            Toast.makeText(
                    RegisterActivity.this,
                    "Registration Successful",
                    Toast.LENGTH_SHORT
            ).show();


            // =========================
            // GO TO CORRECT LOGIN
            // =========================

            openCorrectLogin();
        });


        // =========================
        // ALREADY HAVE ACCOUNT
        // =========================

        txtLogin.setOnClickListener(v -> {

            openCorrectLogin();
        });
    }


    // =========================
    // CORRECT LOGIN PAGE
    // =========================

    private void openCorrectLogin() {

        Intent intent;


        // OWNER
        if ("owner".equals(role)) {

            intent = new Intent(
                    RegisterActivity.this,
                    OwnerLoginActivity.class
            );

            intent.putExtra("role", "owner");

        }

        // ADMIN
        else if ("admin".equals(role)) {

            intent = new Intent(
                    RegisterActivity.this,
                    AdminLoginActivity.class
            );

            intent.putExtra("role", "admin");

        }

        // USER
        else {

            intent = new Intent(
                    RegisterActivity.this,
                    LoginActivity.class
            );

            intent.putExtra("role", "user");
        }


        startActivity(intent);
        finish();
    }
}