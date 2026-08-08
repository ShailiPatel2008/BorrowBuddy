package com.borrowbuddy.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class ForgotPasswordActivity extends AppCompatActivity {

    private TextInputEditText etForgotEmail;
    private MaterialButton btnSend;
    private TextView txtBackLogin;

    private ImageView imgLogo;
    private CardView forgotCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // Views
        etForgotEmail = findViewById(R.id.etForgotEmail);
        btnSend = findViewById(R.id.btnSend);
        txtBackLogin = findViewById(R.id.txtBackLogin);

        imgLogo = findViewById(R.id.imgLogo);
        forgotCard = findViewById(R.id.forgotCard);

        // Logo Animation
        Animation logoAnim = AnimationUtils.loadAnimation(this, R.anim.logo_zoom);
        imgLogo.startAnimation(logoAnim);

        // Card Animation
        Animation cardAnim = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        forgotCard.startAnimation(cardAnim);

        // Continue Button
        btnSend.setOnClickListener(v -> {

            btnSend.startAnimation(
                    AnimationUtils.loadAnimation(
                            ForgotPasswordActivity.this,
                            R.anim.button_scale));

            String email = etForgotEmail.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                etForgotEmail.setError("Enter Email Address");
                etForgotEmail.requestFocus();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etForgotEmail.setError("Enter Valid Email Address");
                etForgotEmail.requestFocus();
                return;
            }

            Toast.makeText(
                    ForgotPasswordActivity.this,
                    "Email Verified Successfully!",
                    Toast.LENGTH_SHORT
            ).show();

            Intent intent = new Intent(
                    ForgotPasswordActivity.this,
                    CreateNewPasswordActivity.class
            );

            intent.putExtra("email", email);
            startActivity(intent);
            finish();
        });

        // Back to Login
        txtBackLogin.setOnClickListener(v -> {

            txtBackLogin.startAnimation(
                    AnimationUtils.loadAnimation(
                            ForgotPasswordActivity.this,
                            R.anim.button_scale));

            finish();

        });

    }
}