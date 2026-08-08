package com.borrowbuddy.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private ImageView imgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Initialize Logo
        imgLogo = findViewById(R.id.imgLogo);

        // Load Animation
        Animation logoAnim = AnimationUtils.loadAnimation(this, R.anim.logo_zoom);

        // Start Animation
        imgLogo.startAnimation(logoAnim);

        // Open Login Screen after 3 seconds
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, ChooseRoleActivity.class);
            startActivity(intent);
            finish();
        }, 3000);
    }
}