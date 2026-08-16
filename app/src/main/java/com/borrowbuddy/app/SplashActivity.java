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

        Animation logoAnimation =
                AnimationUtils.loadAnimation(
                        this,
                        R.anim.logo_zoom
                );

        Animation textAnimation =
                AnimationUtils.loadAnimation(
                        this,
                        R.anim.fade_in
                );

        imgLogo.startAnimation(logoAnimation);
        txtAppName.startAnimation(textAnimation);
        txtTagline.startAnimation(textAnimation);

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