package com.borrowbuddy.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AdminDashboardActivity extends AppCompatActivity {

    // ================= MAIN =================

    LinearLayout mainLayout;

    // ================= TOOLBAR =================

    ImageView btnToolbarSettings;
    ImageView btnToolbarProfile;

    TextView txtDashboardTitle;

    // ================= WELCOME =================

    TextView txtWelcomeAdmin;
    TextView txtWelcomeSubtitle;

    // ================= DASHBOARD CARDS =================

    LinearLayout btnManageUsers;
    LinearLayout btnListingRequests;
    LinearLayout btnManageItems;
    LinearLayout btnItemUpdateRequests;
    LinearLayout btnManageBookings;
    LinearLayout btnAdminProfile;

    // ================= SETTINGS =================

    SharedPreferences settingsPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_dashboard);


        // =====================================================
        // FIND VIEWS
        // =====================================================

        mainLayout = findViewById(R.id.mainLayout);

        btnToolbarSettings =
                findViewById(R.id.btnToolbarSettings);

        btnToolbarProfile =
                findViewById(R.id.btnToolbarProfile);

        txtDashboardTitle =
                findViewById(R.id.txtDashboardTitle);

        txtWelcomeAdmin =
                findViewById(R.id.txtWelcomeAdmin);

        txtWelcomeSubtitle =
                findViewById(R.id.txtWelcomeSubtitle);

        btnManageUsers =
                findViewById(R.id.btnManageUsers);

        btnListingRequests =
                findViewById(R.id.btnListingRequests);

        btnManageItems =
                findViewById(R.id.btnManageItems);

        btnItemUpdateRequests =
                findViewById(R.id.btnItemUpdateRequests);

        btnManageBookings =
                findViewById(R.id.btnManageBookings);

        btnAdminProfile =
                findViewById(R.id.btnAdminProfile);


        // =====================================================
        // SETTINGS
        // =====================================================

        settingsPreferences =
                getSharedPreferences(
                        "Settings",
                        MODE_PRIVATE
                );


        // =====================================================
        // APPLY DARK MODE
        // =====================================================

        applyDarkMode();


        // =====================================================
        // SETTINGS BUTTON
        // =====================================================

        btnToolbarSettings.setOnClickListener(v -> {

            Intent intent = new Intent(
                    AdminDashboardActivity.this,
                    ManageSettings.class
            );

            startActivity(intent);
        });


        // =====================================================
        // TOOLBAR ADMIN PROFILE
        // =====================================================

        btnToolbarProfile.setOnClickListener(v -> {

            Intent intent = new Intent(
                    AdminDashboardActivity.this,
                    AdminProfileActivity.class
            );

            startActivity(intent);
        });


        // =====================================================
        // MANAGE USERS
        // =====================================================

        btnManageUsers.setOnClickListener(v -> {

            Intent intent = new Intent(
                    AdminDashboardActivity.this,
                    ManageUserActivity.class
            );

            startActivity(intent);
        });


        // =====================================================
        // LISTING REQUESTS
        // =====================================================

        btnListingRequests.setOnClickListener(v -> {

            Intent intent = new Intent(
                    AdminDashboardActivity.this,
                    ManageListingRequestActivity.class
            );

            startActivity(intent);
        });


        // =====================================================
        // MANAGE ITEMS
        // =====================================================

        btnManageItems.setOnClickListener(v -> {

            Intent intent = new Intent(
                    AdminDashboardActivity.this,
                    ManageItemActivity.class
            );

            startActivity(intent);
        });


        // =====================================================
        // ITEM UPDATE REQUESTS
        // =====================================================

        btnItemUpdateRequests.setOnClickListener(v -> {

            Intent intent = new Intent(
                    AdminDashboardActivity.this,
                    ItemUpdateRequestsActivity.class
            );

            startActivity(intent);
        });


        // =====================================================
        // MANAGE BOOKINGS
        // =====================================================

        btnManageBookings.setOnClickListener(v -> {

            Intent intent = new Intent(
                    AdminDashboardActivity.this,
                    ManageBookingActivity.class
            );

            startActivity(intent);
        });


        // =====================================================
        // ADMIN PROFILE CARD
        // =====================================================

        btnAdminProfile.setOnClickListener(v -> {

            Intent intent = new Intent(
                    AdminDashboardActivity.this,
                    AdminProfileActivity.class
            );

            startActivity(intent);
        });
    }


    // =========================================================
    // ON RESUME
    // =========================================================

    @Override
    protected void onResume() {
        super.onResume();

        if (settingsPreferences == null) {

            settingsPreferences =
                    getSharedPreferences(
                            "Settings",
                            MODE_PRIVATE
                    );
        }

        applyDarkMode();
    }


    // =========================================================
    // APPLY DARK MODE
    // =========================================================

    private void applyDarkMode() {

        if (mainLayout == null ||
                settingsPreferences == null) {
            return;
        }


        boolean darkMode =
                settingsPreferences.getBoolean(
                        "dark_mode",
                        false
                );


        // =====================================================
        // TOOLBAR
        // =====================================================

        txtDashboardTitle.setTextColor(
                Color.WHITE
        );

        btnToolbarSettings.setColorFilter(
                Color.WHITE
        );

        btnToolbarProfile.setColorFilter(
                Color.WHITE
        );


        // =====================================================
        // DARK MODE ON
        // =====================================================

        if (darkMode) {

            // Main background
            mainLayout.setBackgroundColor(
                    Color.rgb(16, 16, 16)
            );


            // -------------------------------------------------
            // Welcome Admin = WHITE
            // -------------------------------------------------

            txtWelcomeAdmin.setTextColor(
                    Color.WHITE
            );


            // Subtitle
            txtWelcomeSubtitle.setTextColor(
                    Color.LTGRAY
            );


            // -------------------------------------------------
            // Cards
            // -------------------------------------------------

            setCardDark(btnManageUsers);
            setCardDark(btnListingRequests);
            setCardDark(btnManageItems);
            setCardDark(btnItemUpdateRequests);
            setCardDark(btnManageBookings);
            setCardDark(btnAdminProfile);
        }


        // =====================================================
        // DARK MODE OFF
        // =====================================================

        else {

            // Main background
            mainLayout.setBackgroundColor(
                    Color.rgb(246, 243, 248)
            );


            // -------------------------------------------------
            // Welcome Admin = BLACK
            // -------------------------------------------------

            txtWelcomeAdmin.setTextColor(
                    Color.BLACK
            );


            // Subtitle
            txtWelcomeSubtitle.setTextColor(
                    Color.rgb(119, 119, 119)
            );


            // -------------------------------------------------
            // Cards
            // -------------------------------------------------

            setCardLight(btnManageUsers);
            setCardLight(btnListingRequests);
            setCardLight(btnManageItems);
            setCardLight(btnItemUpdateRequests);
            setCardLight(btnManageBookings);
            setCardLight(btnAdminProfile);
        }
    }


    // =========================================================
    // DARK CARD
    // =========================================================

    private void setCardDark(LinearLayout card) {

        GradientDrawable drawable =
                new GradientDrawable();

        drawable.setColor(
                Color.rgb(35, 35, 35)
        );

        drawable.setCornerRadius(
                dp(14)
        );

        drawable.setStroke(
                (int) dp(1),
                Color.rgb(60, 60, 60)
        );

        card.setBackground(drawable);

        // Card ni andar na TextViews ne white
        setChildTextColor(
                card,
                Color.WHITE
        );
    }


    // =========================================================
    // LIGHT CARD
    // =========================================================

    private void setCardLight(LinearLayout card) {

        GradientDrawable drawable =
                new GradientDrawable();

        drawable.setColor(
                Color.WHITE
        );

        drawable.setCornerRadius(
                dp(14)
        );

        drawable.setStroke(
                (int) dp(1),
                Color.rgb(224, 224, 224)
        );

        card.setBackground(drawable);

        // Card ni andar na TextViews black/grey
        setCardChildrenLight(card);
    }


    // =========================================================
    // DARK CARD TEXT
    // =========================================================

    private void setChildTextColor(
            LinearLayout parent,
            int color) {

        for (int i = 0; i < parent.getChildCount(); i++) {

            View child =
                    parent.getChildAt(i);

            if (child instanceof TextView) {

                TextView textView =
                        (TextView) child;

                // Right arrow grey/white
                textView.setTextColor(color);
            }
        }
    }


    // =========================================================
    // LIGHT CARD TEXT
    // =========================================================

    private void setCardChildrenLight(
            LinearLayout parent) {

        for (int i = 0; i < parent.getChildCount(); i++) {

            View child =
                    parent.getChildAt(i);

            if (child instanceof TextView) {

                TextView textView =
                        (TextView) child;

                String text =
                        textView.getText().toString();

                if (text.equals("›")) {

                    textView.setTextColor(
                            Color.rgb(
                                    119,
                                    119,
                                    119
                            )
                    );

                } else {

                    textView.setTextColor(
                            Color.rgb(
                                    34,
                                    34,
                                    34
                            )
                    );
                }
            }
        }
    }


    // =========================================================
    // DP
    // =========================================================

    private float dp(float value) {

        return value *
                getResources()
                        .getDisplayMetrics()
                        .density;
    }
}