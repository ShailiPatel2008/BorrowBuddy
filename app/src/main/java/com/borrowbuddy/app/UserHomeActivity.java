package com.borrowbuddy.app;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class UserHomeActivity extends AppCompatActivity {

    ImageView imgLogo;
    LinearLayout homeCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_home);

        // Initialize Views
        imgLogo = findViewById(R.id.imgLogo);
        homeCard = findViewById(R.id.homeCard);

        // Logo Animation
        Animation logoAnim =
                AnimationUtils.loadAnimation(
                        this,
                        R.anim.logo_zoom
                );

        // Card Animation
        Animation cardAnim =
                AnimationUtils.loadAnimation(
                        this,
                        R.anim.card_slide_up
                );

        // Start Animations
        imgLogo.startAnimation(logoAnim);
        homeCard.startAnimation(cardAnim);
    }
}