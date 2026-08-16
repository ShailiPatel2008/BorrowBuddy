package com.borrowbuddy.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME = 2500;

    private ImageView imgLogo;
    private TextView txtAppName;
    private TextView txtTagline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        imgLogo = findViewById(R.id.imgLogo);
        txtAppName = findViewById(R.id.txtAppName);
        txtTagline = findViewById(R.id.txtTagline);

        // Logo animation
        Animation logoAnim = AnimationUtils.loadAnimation(
                this,
                R.anim.logo_zoom
        );

        // Text animation
        Animation textAnim = AnimationUtils.loadAnimation(
                this,
                R.anim.fade_in
        );

        imgLogo.startAnimation(logoAnim);
        txtAppName.startAnimation(textAnim);
        txtTagline.startAnimation(textAnim);

        // Open Choose Role
        new Handler(Looper.getMainLooper()).postDelayed(() -> {

            Intent intent = new Intent(
                    SplashActivity.this,
                    ChooseRoleActivity.class
            );

            startActivity(intent);
            finish();

        }, SPLASH_TIME);
    }
}