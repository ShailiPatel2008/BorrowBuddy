package com.borrowbuddy.app;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfileActivity extends AppCompatActivity {

    LinearLayout mainLayout;
    LinearLayout profileInfoCard;

    ImageButton btnBack;
    TextView txtName;
    TextView txtEmail;
    TextView txtPhone;
    TextView txtTrustScore;

    boolean darkMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_profile);

        mainLayout = findViewById(R.id.mainLayout);
        profileInfoCard = findViewById(R.id.profileInfoCard);

        btnBack = findViewById(R.id.btnBack);
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);
        txtTrustScore = findViewById(R.id.txtTrustScore);

        // User information
        String name = getIntent().getStringExtra("userName");
        String email = getIntent().getStringExtra("userEmail");
        String phone = getIntent().getStringExtra("userPhone");
        String trust = getIntent().getStringExtra("trustScore");

        if (name != null) {
            txtName.setText(name);
        }

        if (email != null) {
            txtEmail.setText(email);
        }

        if (phone != null) {
            txtPhone.setText(phone);
        }

        if (trust != null) {
            txtTrustScore.setText("Trust Score: " + trust + " ⭐");
        }

        btnBack.setOnClickListener(v -> finish());

        applyDarkMode();
    }

    @Override
    protected void onResume() {
        super.onResume();
        applyDarkMode();
    }

    private void applyDarkMode() {

        SharedPreferences preferences =
                getSharedPreferences("Settings", MODE_PRIVATE);

        darkMode = preferences.getBoolean("dark_mode", false);

        if (darkMode) {

            // Main background
            mainLayout.setBackgroundColor(
                    Color.rgb(18, 18, 18)
            );

            // Profile card
            profileInfoCard.setBackgroundColor(
                    Color.rgb(35, 35, 35)
            );

            // Normal text WHITE
            setAllTextColors(
                    mainLayout,
                    Color.WHITE
            );

        } else {

            // Main background
            mainLayout.setBackgroundColor(
                    Color.WHITE
            );

            // Profile card LIGHT GREY
            profileInfoCard.setBackgroundColor(
                    Color.rgb(238, 238, 238)
            );

            // Normal text BLACK
            setAllTextColors(
                    mainLayout,
                    Color.rgb(20, 20, 20)
            );
        }

        // Name
        txtName.setTextColor(
                darkMode ? Color.WHITE : Color.rgb(20, 20, 20)
        );

        // Email
        txtEmail.setTextColor(
                darkMode ? Color.WHITE : Color.rgb(20, 20, 20)
        );

        // Phone
        txtPhone.setTextColor(
                darkMode ? Color.WHITE : Color.rgb(20, 20, 20)
        );

        // Trust
        txtTrustScore.setTextColor(
                darkMode ? Color.WHITE : Color.rgb(20, 20, 20)
        );

        // Toolbar always PURPLE
        View toolbar = mainLayout.getChildAt(0);

        if (toolbar != null) {

            toolbar.setBackgroundColor(
                    Color.rgb(108, 74, 182)
            );

            if (toolbar instanceof ViewGroup) {

                ViewGroup toolbarGroup =
                        (ViewGroup) toolbar;

                for (int i = 0;
                     i < toolbarGroup.getChildCount();
                     i++) {

                    View child =
                            toolbarGroup.getChildAt(i);

                    if (child instanceof TextView) {

                        ((TextView) child)
                                .setTextColor(Color.WHITE);
                    }
                }
            }
        }
    }

    private void setAllTextColors(
            View view,
            int color) {

        if (view instanceof TextView) {

            TextView textView =
                    (TextView) view;

            textView.setTextColor(color);
        }

        if (view instanceof ViewGroup) {

            ViewGroup group =
                    (ViewGroup) view;

            for (int i = 0;
                 i < group.getChildCount();
                 i++) {

                setAllTextColors(
                        group.getChildAt(i),
                        color
                );
            }
        }
    }
}