package com.borrowbuddy.app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.borrowbuddy.app.activities.ChangePasswordActivity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingsActivity extends AppCompatActivity {

    private MaterialCardView editProfileCard;
    private MaterialCardView notificationCard;
    private MaterialCardView darkModeCard;
    private MaterialCardView changePasswordCard;
    private MaterialCardView termsCard;
    private MaterialCardView helpCard;
    private MaterialCardView logoutCard;

    private SwitchMaterial notificationSwitch;
    private SwitchMaterial darkModeSwitch;

    private ScrollView settingsScrollView;
    private MaterialToolbar settingsToolbar;
    private TextView settingsToolbarTitle;
    private ImageButton backButton;

    private static final String PREF_NAME = "BorrowBuddy";
    private static final String DARK_MODE = "darkMode";
    private static final String NOTIFICATIONS = "notifications";

    private static final int PURPLE = Color.rgb(106, 27, 154);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        // Hide default ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Status Bar
        getWindow().setStatusBarColor(Color.rgb(108, 74, 182));
        getWindow().getDecorView().setSystemUiVisibility(0);

        // ==========================================
        // TOOLBAR
        // ==========================================

        settingsToolbar = findViewById(R.id.settingsToolbar);
        settingsToolbarTitle = findViewById(R.id.settingsToolbarTitle);
        backButton = findViewById(R.id.btnBack);

        // ==========================================
        // CARDS
        // ==========================================

        editProfileCard = findViewById(R.id.cardEditProfile);
        notificationCard = findViewById(R.id.cardNotifications);
        darkModeCard = findViewById(R.id.cardDarkMode);
        changePasswordCard = findViewById(R.id.cardChangePassword);
        helpCard = findViewById(R.id.cardHelpSupport);
        termsCard = findViewById(R.id.cardTerms);
        logoutCard = findViewById(R.id.cardLogout);

        // ==========================================
        // SWITCHES
        // ==========================================

        notificationSwitch =
                findViewById(R.id.switchNotifications);

        darkModeSwitch =
                findViewById(R.id.switchDarkMode);

        // ==========================================
        // SCROLL VIEW
        // ==========================================

        settingsScrollView =
                findViewById(R.id.settingsScroll);

        // ==========================================
        // BACK BUTTON
        // ==========================================

        if (backButton != null) {

            backButton.setOnClickListener(v -> finish());

        }

        // ==========================================
        // SHARED PREFERENCES
        // ==========================================

        android.content.SharedPreferences preferences =
                getSharedPreferences(
                        PREF_NAME,
                        MODE_PRIVATE
                );

        boolean isDarkMode =
                preferences.getBoolean(
                        DARK_MODE,
                        false
                );

        boolean notificationsEnabled =
                preferences.getBoolean(
                        NOTIFICATIONS,
                        true
                );

        // ==========================================
        // NOTIFICATION SWITCH
        // ==========================================

        if (notificationSwitch != null) {

            notificationSwitch.setChecked(
                    notificationsEnabled
            );

            notificationSwitch.setOnCheckedChangeListener(
                    (buttonView, isChecked) -> {

                        getSharedPreferences(
                                PREF_NAME,
                                MODE_PRIVATE
                        )
                                .edit()
                                .putBoolean(
                                        NOTIFICATIONS,
                                        isChecked
                                )
                                .apply();

                        if (isChecked) {

                            Toast.makeText(
                                    SettingsActivity.this,
                                    "Notifications ON",
                                    Toast.LENGTH_SHORT
                            ).show();

                        } else {

                            Toast.makeText(
                                    SettingsActivity.this,
                                    "Notifications OFF",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
            );
        }

        // ==========================================
        // DARK MODE SWITCH
        // ==========================================

        if (darkModeSwitch != null) {

            darkModeSwitch.setChecked(
                    isDarkMode
            );

            darkModeSwitch.setOnCheckedChangeListener(
                    (buttonView, isChecked) -> {

                        getSharedPreferences(
                                PREF_NAME,
                                MODE_PRIVATE
                        )
                                .edit()
                                .putBoolean(
                                        DARK_MODE,
                                        isChecked
                                )
                                .apply();

                        if (isChecked) {

                            applyDarkMode();

                            Toast.makeText(
                                    SettingsActivity.this,
                                    "Dark Mode ON",
                                    Toast.LENGTH_SHORT
                            ).show();

                        } else {

                            applyLightMode();

                            Toast.makeText(
                                    SettingsActivity.this,
                                    "Dark Mode OFF",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
            );
        }

        // ==========================================
        // APPLY SAVED MODE
        // ==========================================

        if (isDarkMode) {

            applyDarkMode();

        } else {

            applyLightMode();

        }

        // ==========================================
        // EDIT PROFILE
        // ==========================================

        if (editProfileCard != null) {

            editProfileCard.setOnClickListener(v -> {

                Intent intent =
                        new Intent(
                                SettingsActivity.this,
                                EditProfileActivity.class
                        );

                startActivity(intent);

            });
        }

        // ==========================================
        // NOTIFICATIONS
        // ==========================================

        if (notificationCard != null) {

            notificationCard.setOnClickListener(v -> {

                Intent intent =
                        new Intent(
                                SettingsActivity.this,
                                NotificationActivity.class
                        );

                startActivity(intent);

            });
        }

        // ==========================================
        // DARK MODE CARD
        // ==========================================

        if (darkModeCard != null) {

            darkModeCard.setOnClickListener(v -> {

                if (darkModeSwitch != null) {

                    darkModeSwitch.setChecked(
                            !darkModeSwitch.isChecked()
                    );

                }

            });
        }

        // ==========================================
        // CHANGE PASSWORD
        // ==========================================

        if (changePasswordCard != null) {

            changePasswordCard.setOnClickListener(v -> {

                Intent intent =
                        new Intent(
                                SettingsActivity.this,
                                ChangePasswordActivity.class
                        );

                startActivity(intent);

            });
        }

        // ==========================================
        // HELP & SUPPORT
        // ==========================================

        if (helpCard != null) {

            helpCard.setOnClickListener(v -> {

                Intent intent =
                        new Intent(
                                SettingsActivity.this,
                                HelpsSupportActivity.class
                        );

                startActivity(intent);

            });
        }

        // ==========================================
        // TERMS & CONDITIONS
        // ==========================================

        if (termsCard != null) {

            termsCard.setOnClickListener(v -> {

                Intent intent =
                        new Intent(
                                SettingsActivity.this,
                                TermsActivity.class
                        );

                startActivity(intent);

            });
        }

        // ==========================================
// LOGOUT
// ==========================================

        if (logoutCard != null) {

            logoutCard.setOnClickListener(v -> {

                new AlertDialog.Builder(
                        SettingsActivity.this
                )
                        .setTitle("Logout")
                        .setMessage(
                                "Are you sure you want to logout?"
                        )
                        .setNegativeButton(
                                "CANCEL",
                                null
                        )
                        .setPositiveButton(
                                "LOGOUT",
                                (dialog, which) -> {

                                    Toast.makeText(
                                            SettingsActivity.this,
                                            "Logged out successfully",
                                            Toast.LENGTH_SHORT
                                    ).show();

                                    Intent intent = new Intent(
                                            SettingsActivity.this,
                                            ChooseRoleActivity.class
                                    );

                                    intent.setFlags(
                                            Intent.FLAG_ACTIVITY_NEW_TASK |
                                                    Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    );

                                    startActivity(intent);
                                }
                        )
                        .show();
            });
        }
    }

    // ==========================================
    // DARK MODE
    // ==========================================

    private void applyDarkMode() {

        if (settingsScrollView != null) {

            settingsScrollView.setBackgroundColor(
                    Color.BLACK
            );

            View child =
                    settingsScrollView.getChildAt(0);

            if (child != null) {

                child.setBackgroundColor(
                        Color.BLACK
                );

                changeAllTextColor(
                        child,
                        Color.WHITE
                );
            }
        }

        setCardsColor(Color.BLACK);

        if (settingsToolbar != null) {

            settingsToolbar.setBackgroundColor(
                    Color.rgb(108, 74, 182)
            );
        }

        if (settingsToolbarTitle != null) {

            settingsToolbarTitle.setTextColor(
                    Color.WHITE
            );
        }

        if (backButton != null) {

            backButton.setColorFilter(
                    Color.WHITE
            );
        }

        getWindow().setStatusBarColor(Color.rgb(108, 74, 182));

        getWindow()
                .getDecorView()
                .setSystemUiVisibility(0);
    }

    // ==========================================
    // LIGHT MODE
    // ==========================================

    private void applyLightMode() {

        int lightBackground =
                Color.rgb(248, 249, 250);

        if (settingsScrollView != null) {

            settingsScrollView.setBackgroundColor(
                    lightBackground
            );

            View child =
                    settingsScrollView.getChildAt(0);

            if (child != null) {

                child.setBackgroundColor(
                        lightBackground
                );

                changeAllTextColor(
                        child,
                        Color.BLACK
                );
            }
        }

        setCardsColor(Color.WHITE);

        if (settingsToolbar != null) {

            settingsToolbar.setBackgroundColor(
                    Color.rgb(108, 74, 182)
            );
        }

        if (settingsToolbarTitle != null) {

            settingsToolbarTitle.setTextColor(
                    Color.WHITE
            );
        }

        if (backButton != null) {

            backButton.clearColorFilter();
        }

        getWindow().setStatusBarColor(Color.rgb(108, 74, 182));

        getWindow()
                .getDecorView()
                .setSystemUiVisibility(0);
    }

    // ==========================================
    // CARD COLOR
    // ==========================================

    private void setCardsColor(int color) {

        if (editProfileCard != null) {

            editProfileCard.setCardBackgroundColor(
                    color
            );
        }

        if (notificationCard != null) {

            notificationCard.setCardBackgroundColor(
                    color
            );
        }

        if (darkModeCard != null) {

            darkModeCard.setCardBackgroundColor(
                    color
            );
        }

        if (changePasswordCard != null) {

            changePasswordCard.setCardBackgroundColor(
                    color
            );
        }

        if (helpCard != null) {

            helpCard.setCardBackgroundColor(
                    color
            );
        }

        if (termsCard != null) {

            termsCard.setCardBackgroundColor(
                    color
            );
        }

        if (logoutCard != null) {

            logoutCard.setCardBackgroundColor(
                    color
            );
        }
    }

    // ==========================================
    // CHANGE TEXT COLOR
    // ==========================================

    private void changeAllTextColor(
            View view,
            int color
    ) {

        if (view instanceof TextView) {

            ((TextView) view).setTextColor(
                    color
            );
        }

        if (view instanceof ViewGroup) {

            ViewGroup group =
                    (ViewGroup) view;

            for (
                    int i = 0;
                    i < group.getChildCount();
                    i++
            ) {

                changeAllTextColor(
                        group.getChildAt(i),
                        color
                );
            }
        }
    }
}