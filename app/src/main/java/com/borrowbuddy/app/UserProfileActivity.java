package com.borrowbuddy.app;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class UserProfileActivity extends AppCompatActivity {

    LinearLayout mainLayout;
    LinearLayout profileInfoCard;

    ImageButton btnBack;

    TextView txtName;
    TextView txtEmail;
    TextView txtPhone;
    TextView txtTrustScore;

    boolean darkMode = false;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        db = FirebaseFirestore.getInstance();

        mainLayout = findViewById(R.id.mainLayout);
        profileInfoCard = findViewById(R.id.profileInfoCard);

        btnBack = findViewById(R.id.btnBack);

        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);
        txtTrustScore = findViewById(R.id.txtTrustScore);

        btnBack.setOnClickListener(v -> finish());

        String userId = getIntent().getStringExtra("USER_ID");

        if (userId != null) {

            db.collection("users")
                    .document(userId)
                    .get()
                    .addOnSuccessListener(documentSnapshot -> {

                        if (documentSnapshot.exists()) {

                            txtName.setText(documentSnapshot.getString("full_name"));

                            txtEmail.setText(documentSnapshot.getString("email"));

                            txtPhone.setText(documentSnapshot.getString("phone"));

                            txtTrustScore.setText("5.0 ⭐");
                        }

                    });

        }

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

            mainLayout.setBackgroundColor(Color.rgb(18, 18, 18));
            profileInfoCard.setBackgroundColor(Color.rgb(35, 35, 35));

            setAllTextColors(mainLayout, Color.WHITE);

        } else {

            mainLayout.setBackgroundColor(Color.WHITE);
            profileInfoCard.setBackgroundColor(Color.rgb(238, 238, 238));

            setAllTextColors(mainLayout, Color.rgb(20, 20, 20));
        }

        txtName.setTextColor(darkMode ? Color.WHITE : Color.rgb(20,20,20));
        txtEmail.setTextColor(darkMode ? Color.WHITE : Color.rgb(20,20,20));
        txtPhone.setTextColor(darkMode ? Color.WHITE : Color.rgb(20,20,20));
        txtTrustScore.setTextColor(darkMode ? Color.WHITE : Color.rgb(20,20,20));

        View toolbar = mainLayout.getChildAt(0);

        if (toolbar != null) {

            toolbar.setBackgroundColor(Color.rgb(108,74,182));

            if (toolbar instanceof ViewGroup) {

                ViewGroup toolbarGroup = (ViewGroup) toolbar;

                for (int i = 0; i < toolbarGroup.getChildCount(); i++) {

                    View child = toolbarGroup.getChildAt(i);

                    if (child instanceof TextView) {

                        ((TextView) child).setTextColor(Color.WHITE);

                    }

                }

            }

        }

    }

    private void setAllTextColors(View view, int color) {

        if (view instanceof TextView) {

            ((TextView) view).setTextColor(color);

        }

        if (view instanceof ViewGroup) {

            ViewGroup group = (ViewGroup) view;

            for (int i = 0; i < group.getChildCount(); i++) {

                setAllTextColors(group.getChildAt(i), color);

            }

        }

    }

}