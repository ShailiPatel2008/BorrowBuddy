package com.borrowbuddy.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatDelegate;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private ImageView imgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // =========================
        // LOAD SAVED DARK MODE
        // =========================

        SharedPreferences preferences = getSharedPreferences(
                "BorrowBuddySettings",
                MODE_PRIVATE
        );

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

        setContentView(R.layout.activity_splash);

        // Initialize Logo
        imgLogo = findViewById(R.id.imgLogo);

        // Load Animation
        Animation logoAnim =
                AnimationUtils.loadAnimation(this, R.anim.logo_zoom);

        // Start Animation
        imgLogo.startAnimation(logoAnim);

        // Open Choose Role after 3 seconds
        new Handler(Looper.getMainLooper()).postDelayed(() -> {

            Intent intent = new Intent(
                    SplashActivity.this,
                    ChooseRoleActivity.class
            );

            startActivity(intent);
            finish();

        }, 3000);
    }
}