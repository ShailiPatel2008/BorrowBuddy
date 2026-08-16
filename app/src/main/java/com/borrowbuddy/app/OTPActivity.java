package com.borrowbuddy.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class OTPActivity extends AppCompatActivity {

    private EditText etOtp;
    private Button btnVerify;
    private TextView txtBackLogin;

    private ImageView imgLogo;
    private CardView otpCard;

    // Role
    private String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_otp);

        // Get role from Login page
        role = getIntent().getStringExtra("role");

        // Initialize Views
        etOtp = findViewById(R.id.etOtp);
        btnVerify = findViewById(R.id.btnVerify);
        txtBackLogin = findViewById(R.id.txtBackLogin);

        imgLogo = findViewById(R.id.imgLogo);
        otpCard = findViewById(R.id.otpCard);

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
                        R.anim.card_slide_up
                );

        otpCard.startAnimation(cardAnim);

        // Back To Login
        txtBackLogin.setOnClickListener(v -> {

            if ("owner".equals(role)) {

                Intent intent = new Intent(
                        OTPActivity.this,
                        OwnerLoginActivity.class
                );

                startActivity(intent);

            } else if ("admin".equals(role)) {

                Intent intent = new Intent(
                        OTPActivity.this,
                        AdminLoginActivity.class
                );

                startActivity(intent);

            } else {

                Intent intent = new Intent(
                        OTPActivity.this,
                        LoginActivity.class
                );

                startActivity(intent);
            }

            finish();
        });

        // Verify OTP
        btnVerify.setOnClickListener(v -> {

            String otp =
                    etOtp.getText().toString().trim();

            // Empty OTP
            if (TextUtils.isEmpty(otp)) {

                etOtp.setError("Enter OTP");
                etOtp.requestFocus();

                return;
            }

            // Correct OTP
            if (otp.equals("1234")) {

                Toast.makeText(
                        OTPActivity.this,
                        "OTP Verified Successfully",
                        Toast.LENGTH_SHORT
                ).show();

                // =========================
                // OWNER
                // =========================

                if ("owner".equals(role)) {

                    Intent intent = new Intent(
                            OTPActivity.this,
                            OwnerHomeActivity.class
                    );

                    startActivity(intent);
                    finish();

                }

                // =========================
                // ADMIN
                // =========================

                else if ("admin".equals(role)) {

                    Intent intent = new Intent(
                            OTPActivity.this,
                            AdminHomeActivity.class
                    );

                    startActivity(intent);
                    finish();

                }

                // =========================
                // USER
                // =========================

                else {

                    Intent intent = new Intent(
                            OTPActivity.this,
                            UserHomeActivity.class
                    );

                    startActivity(intent);
                    finish();
                }

            } else {

                // Wrong OTP
                etOtp.setError("Invalid OTP");
                etOtp.requestFocus();

                Toast.makeText(
                        OTPActivity.this,
                        "Invalid OTP",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}