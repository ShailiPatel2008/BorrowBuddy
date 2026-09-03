package com.borrowbuddy.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SplashActivity extends AppCompatActivity {

    private ImageView imgLogo;
    private TextView txtAppName;
    private TextView txtTagline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // =========================
        // APPLY SAVED THEME
        // =========================

        SharedPreferences preferences = getSharedPreferences(
                "BorrowBuddySettings",
                MODE_PRIVATE
        );

        // Default = Light Mode
        boolean isDarkMode = preferences.getBoolean("dark_mode", false);

        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES
            );
        } else {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO
            );
        }

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        // =========================
        // INITIALIZE VIEWS
        // =========================

        imgLogo = findViewById(R.id.imgLogo);
        txtAppName = findViewById(R.id.txtAppName);
        txtTagline = findViewById(R.id.txtTagline);

        // =========================
        // LOGO ANIMATION
        // =========================

        Animation logoAnimation =
                AnimationUtils.loadAnimation(
                        this,
                        R.anim.logo_zoom
                );

        // =========================
        // TEXT ANIMATION
        // =========================

        Animation textAnimation =
                AnimationUtils.loadAnimation(
                        this,
                        R.anim.fade_in
                );

        imgLogo.startAnimation(logoAnimation);
        txtAppName.startAnimation(textAnimation);
        txtTagline.startAnimation(textAnimation);

        // =========================
        // OPEN CHOOSE ROLE
        // =========================

        new Handler(Looper.getMainLooper()).postDelayed(() -> {

            Intent intent = new Intent(
                    SplashActivity.this,
                    ChooseRoleActivity.class
            );

            startActivity(intent);
            finish();

        }, 2500);
    }
}