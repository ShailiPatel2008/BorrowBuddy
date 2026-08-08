package com.borrowbuddy.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class AdminHomeActivity extends AppCompatActivity {

    ImageView imgLogo;
    LinearLayout homeCard;
    Button btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        imgLogo = findViewById(R.id.imgLogo);
        homeCard = findViewById(R.id.homeCard);
        btnSettings = findViewById(R.id.btnSettings);

        // Animation
        Animation logoAnim =
                AnimationUtils.loadAnimation(this, R.anim.logo_zoom);

        Animation cardAnim =
                AnimationUtils.loadAnimation(this, R.anim.card_slide_up);

        imgLogo.startAnimation(logoAnim);
        homeCard.startAnimation(cardAnim);

        // Settings Button
        btnSettings.setOnClickListener(v -> {

            Intent intent = new Intent(
                    AdminHomeActivity.this,
                    SettingsActivity.class);

            startActivity(intent);

        });

    }
}