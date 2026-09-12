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

import com.borrowbuddy.app.activities.OwnerDashboardActivity;

public class OTPActivity extends AppCompatActivity {

    private EditText etOtp;
    private Button btnVerify;
    private TextView txtBackLogin;

    private ImageView imgLogo;
    private CardView otpCard;

    private String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_otp);

        // =========================
        // GET ROLE
        // =========================

        role = getIntent().getStringExtra("role");

        // =========================
        // INITIALIZE VIEWS
        // =========================

        etOtp = findViewById(R.id.etOtp);
        btnVerify = findViewById(R.id.btnVerify);
        txtBackLogin = findViewById(R.id.txtBackLogin);

        imgLogo = findViewById(R.id.imgLogo);
        otpCard = findViewById(R.id.otpCard);

        // =========================
        // LOGO ANIMATION
        // =========================

        try {
            Animation logoAnim =
                    AnimationUtils.loadAnimation(
                            this,
                            R.anim.logo_zoom
                    );

            imgLogo.startAnimation(logoAnim);
        } catch (Exception e) {
            // Animation error ignore
        }

        // =========================
        // CARD ANIMATION
        // =========================

        try {
            Animation cardAnim =
                    AnimationUtils.loadAnimation(
                            this,
                            R.anim.card_slide_up
                    );

            otpCard.startAnimation(cardAnim);
        } catch (Exception e) {
            // Animation error ignore
        }

        // =========================
        // BACK TO LOGIN
        // =========================

        txtBackLogin.setOnClickListener(v -> {

            if ("owner".equals(role)) {

                Intent intent = new Intent(
                        OTPActivity.this,
                        OwnerLoginActivity.class
                );

                intent.putExtra("role", "owner");

                startActivity(intent);
                finish();

            } else if ("admin".equals(role)) {

                Intent intent = new Intent(
                        OTPActivity.this,
                        AdminLoginActivity.class
                );

                intent.putExtra("role", "admin");

                startActivity(intent);
                finish();

            } else {

                Intent intent = new Intent(
                        OTPActivity.this,
                        LoginActivity.class
                );

                intent.putExtra("role", "user");

                startActivity(intent);
                finish();
            }
        });

        // =========================
        // VERIFY OTP
        // =========================

        btnVerify.setOnClickListener(v -> {

            String otp =
                    etOtp.getText()
                            .toString()
                            .trim();

            // =========================
            // EMPTY OTP
            // =========================

            if (TextUtils.isEmpty(otp)) {

                etOtp.setError("Enter OTP");
                etOtp.requestFocus();

                return;
            }

            // =========================
            // OTP CHECK
            // =========================

            if (!otp.equals("1234")) {

                etOtp.setError("Invalid OTP");
                etOtp.requestFocus();

                Toast.makeText(
                        OTPActivity.this,
                        "Invalid OTP",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            // =========================
            // OTP SUCCESS
            // =========================

            Toast.makeText(
                    OTPActivity.this,
                    "OTP Verified Successfully",
                    Toast.LENGTH_SHORT
            ).show();

            // =========================
            // OWNER
            // =========================

            if ("owner".equals(role)) {

                try {

                    Intent intent = new Intent(
                            OTPActivity.this,
                            OwnerDashboardActivity.class
                    );

                    startActivity(intent);
                    finish();

                } catch (Exception e) {

                    Toast.makeText(
                            OTPActivity.this,
                            "Owner Error: " + e.getMessage(),
                            Toast.LENGTH_LONG
                    ).show();
                }

            }

            // =========================
            // ADMIN
            // =========================

            else if ("admin".equals(role)) {

                try {

                    Intent intent = new Intent(
                            OTPActivity.this,
                            AdminMainActivity.class
                    );

                    startActivity(intent);
                    finish();

                } catch (Exception e) {

                    Toast.makeText(
                            OTPActivity.this,
                            "Admin Error: " + e.getMessage(),
                            Toast.LENGTH_LONG
                    ).show();
                }

            }

            // =========================
            // USER
            // =========================

            else {

                try {

                    Intent intent = new Intent(
                            OTPActivity.this,
                            HomeActivity.class
                    );

                    startActivity(intent);
                    finish();

                } catch (Exception e) {

                    Toast.makeText(
                            OTPActivity.this,
                            "Home Error: " + e.getMessage(),
                            Toast.LENGTH_LONG
                    ).show();
                }
            }
        });
    }
}