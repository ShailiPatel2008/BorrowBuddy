package com.borrowbuddy.app;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ManageSettings extends AppCompatActivity {

    // ================= VIEWS =================

    View mainLayout;

    View notificationBox;
    View darkModeBox;

    TextView btnBack;

    Switch switchNotification;
    Switch switchDarkMode;

    TextView btnChangePassword;
    TextView btnAdminProfile;
    TextView btnLogout;

    TextView txtNotificationTitle;
    TextView txtNotificationSubtitle;

    TextView txtDarkModeTitle;
    TextView txtDarkModeSubtitle;

    TextView txtAccountTitle;


    // ================= PREFERENCES =================

    SharedPreferences settingsPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manage_settings);


        // =====================================================
        // FIND VIEWS
        // =====================================================

        mainLayout = findViewById(R.id.mainLayout);

        notificationBox =
                findViewById(R.id.notificationBox);

        darkModeBox =
                findViewById(R.id.darkModeBox);

        btnBack =
                findViewById(R.id.btnBack);

        switchNotification =
                findViewById(R.id.switchNotification);

        switchDarkMode =
                findViewById(R.id.switchDarkMode);

        btnChangePassword =
                findViewById(R.id.btnChangePassword);

        btnAdminProfile =
                findViewById(R.id.btnAdminProfile);

        btnLogout =
                findViewById(R.id.btnLogout);

        txtNotificationTitle =
                findViewById(R.id.txtNotificationTitle);

        txtNotificationSubtitle =
                findViewById(R.id.txtNotificationSubtitle);

        txtDarkModeTitle =
                findViewById(R.id.txtDarkModeTitle);

        txtDarkModeSubtitle =
                findViewById(R.id.txtDarkModeSubtitle);

        txtAccountTitle =
                findViewById(R.id.txtAccountTitle);


        // =====================================================
        // SETTINGS PREFERENCES
        // =====================================================

        settingsPreferences =
                getSharedPreferences(
                        "Settings",
                        MODE_PRIVATE
                );


        // =====================================================
        // LOAD SAVED SETTINGS
        // =====================================================

        boolean notificationEnabled =
                settingsPreferences.getBoolean(
                        "notifications",
                        true
                );

        boolean darkMode =
                settingsPreferences.getBoolean(
                        "dark_mode",
                        false
                );


        // Listener remove while loading
        switchNotification.setOnCheckedChangeListener(null);
        switchDarkMode.setOnCheckedChangeListener(null);

        switchNotification.setChecked(
                notificationEnabled
        );

        switchDarkMode.setChecked(
                darkMode
        );


        // =====================================================
        // APPLY DARK MODE
        // =====================================================

        applyDarkMode();


        // =====================================================
        // BACK
        // =====================================================

        btnBack.setOnClickListener(v -> finish());


        // =====================================================
        // NOTIFICATION
        // =====================================================

        switchNotification.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(
                            CompoundButton buttonView,
                            boolean isChecked) {

                        settingsPreferences.edit()
                                .putBoolean(
                                        "notifications",
                                        isChecked
                                )
                                .apply();


                        if (isChecked) {

                            Toast.makeText(
                                    ManageSettings.this,
                                    "Notifications turned ON",
                                    Toast.LENGTH_SHORT
                            ).show();

                        } else {

                            Toast.makeText(
                                    ManageSettings.this,
                                    "Notifications turned OFF",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
                }
        );


        // =====================================================
        // DARK MODE
        // =====================================================

        switchDarkMode.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(
                            CompoundButton buttonView,
                            boolean isChecked) {

                        settingsPreferences.edit()
                                .putBoolean(
                                        "dark_mode",
                                        isChecked
                                )
                                .apply();


                        applyDarkMode();


                        if (isChecked) {

                            Toast.makeText(
                                    ManageSettings.this,
                                    "Dark Mode turned ON",
                                    Toast.LENGTH_SHORT
                            ).show();

                        } else {

                            Toast.makeText(
                                    ManageSettings.this,
                                    "Dark Mode turned OFF",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
                }
        );


        // =====================================================
        // CHANGE PASSWORD
        // =====================================================

        btnChangePassword.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ManageSettings.this,
                    ChangePasswordActivity.class
            );

            startActivity(intent);
        });


        // =====================================================
        // ADMIN PROFILE
        // =====================================================

        btnAdminProfile.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ManageSettings.this,
                    AdminProfileActivity.class
            );

            startActivity(intent);
        });


        // =====================================================
        // LOGOUT
        // =====================================================

        btnLogout.setOnClickListener(v ->
                showLogoutDialog()
        );
    }


    // =========================================================
    // ON RESUME
    // =========================================================

    @Override
    protected void onResume() {
        super.onResume();

        if (settingsPreferences != null) {

            boolean notificationEnabled =
                    settingsPreferences.getBoolean(
                            "notifications",
                            true
                    );

            boolean darkMode =
                    settingsPreferences.getBoolean(
                            "dark_mode",
                            false
                    );


            // Remove listeners temporarily
            switchNotification.setOnCheckedChangeListener(null);
            switchDarkMode.setOnCheckedChangeListener(null);


            switchNotification.setChecked(
                    notificationEnabled
            );

            switchDarkMode.setChecked(
                    darkMode
            );


            // Notification listener again
            switchNotification.setOnCheckedChangeListener(
                    (buttonView, isChecked) -> {

                        settingsPreferences.edit()
                                .putBoolean(
                                        "notifications",
                                        isChecked
                                )
                                .apply();


                        if (isChecked) {

                            Toast.makeText(
                                    ManageSettings.this,
                                    "Notifications turned ON",
                                    Toast.LENGTH_SHORT
                            ).show();

                        } else {

                            Toast.makeText(
                                    ManageSettings.this,
                                    "Notifications turned OFF",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
            );


            // Dark mode listener again
            switchDarkMode.setOnCheckedChangeListener(
                    (buttonView, isChecked) -> {

                        settingsPreferences.edit()
                                .putBoolean(
                                        "dark_mode",
                                        isChecked
                                )
                                .apply();


                        applyDarkMode();


                        if (isChecked) {

                            Toast.makeText(
                                    ManageSettings.this,
                                    "Dark Mode turned ON",
                                    Toast.LENGTH_SHORT
                            ).show();

                        } else {

                            Toast.makeText(
                                    ManageSettings.this,
                                    "Dark Mode turned OFF",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
            );
        }


        applyDarkMode();
    }


    // =========================================================
    // LOGOUT DIALOG
    // =========================================================

    private void showLogoutDialog() {

        AlertDialog dialog =
                new AlertDialog.Builder(this)
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
                                (d, which) -> {

                                    Toast.makeText(
                                            ManageSettings.this,
                                            "Logged out successfully",
                                            Toast.LENGTH_SHORT
                                    ).show();

                                    finish();
                                }
                        )
                        .create();


        dialog.setOnShowListener(d -> {

            dialog.getButton(
                    AlertDialog.BUTTON_POSITIVE
            ).setTextColor(Color.WHITE);

            dialog.getButton(
                    AlertDialog.BUTTON_NEGATIVE
            ).setTextColor(Color.WHITE);
        });


        dialog.show();
    }


    // =========================================================
    // APPLY DARK MODE
    // =========================================================

    private void applyDarkMode() {

        if (mainLayout == null) {
            return;
        }


        boolean darkMode =
                settingsPreferences.getBoolean(
                        "dark_mode",
                        false
                );


        if (darkMode) {

            // =================================================
            // DARK BACKGROUND
            // =================================================

            mainLayout.setBackgroundColor(
                    Color.rgb(16, 16, 16)
            );


            // =================================================
            // DARK BOXES
            // =================================================

            notificationBox.setBackgroundColor(
                    Color.rgb(35, 35, 35)
            );

            darkModeBox.setBackgroundColor(
                    Color.rgb(35, 35, 35)
            );


            // =================================================
            // TEXT WHITE
            // =================================================

            txtNotificationTitle.setTextColor(
                    Color.WHITE
            );

            txtNotificationSubtitle.setTextColor(
                    Color.LTGRAY
            );


            txtDarkModeTitle.setTextColor(
                    Color.WHITE
            );

            txtDarkModeSubtitle.setTextColor(
                    Color.LTGRAY
            );


            txtAccountTitle.setTextColor(
                    Color.WHITE
            );


            // =================================================
            // SWITCH TEXT
            // =================================================

            switchNotification.setTextColor(
                    Color.WHITE
            );

            switchDarkMode.setTextColor(
                    Color.WHITE
            );


        } else {

            // =================================================
            // LIGHT BACKGROUND
            // =================================================

            mainLayout.setBackgroundColor(
                    Color.rgb(246, 243, 248)
            );


            // =================================================
            // WHITE BOXES
            // =================================================

            notificationBox.setBackgroundColor(
                    Color.WHITE
            );

            darkModeBox.setBackgroundColor(
                    Color.WHITE
            );


            // =================================================
            // TEXT BLACK
            // =================================================

            txtNotificationTitle.setTextColor(
                    Color.BLACK
            );

            txtNotificationSubtitle.setTextColor(
                    Color.rgb(119, 119, 119)
            );


            txtDarkModeTitle.setTextColor(
                    Color.BLACK
            );

            txtDarkModeSubtitle.setTextColor(
                    Color.rgb(119, 119, 119)
            );


            txtAccountTitle.setTextColor(
                    Color.BLACK
            );


            // =================================================
            // SWITCH TEXT
            // =================================================

            switchNotification.setTextColor(
                    Color.BLACK
            );

            switchDarkMode.setTextColor(
                    Color.BLACK
            );
        }


        // =====================================================
        // GREY BUTTONS
        // =====================================================

        setGreyButton(btnChangePassword);
        setGreyButton(btnAdminProfile);
        setGreyButton(btnLogout);


        // =====================================================
        // TOOLBAR ALWAYS PURPLE
        // =====================================================

        View toolbar =
                (View) btnBack.getParent();

        if (toolbar != null) {

            toolbar.setBackgroundColor(
                    Color.rgb(171, 71, 188)
            );
        }


        btnBack.setTextColor(
                Color.WHITE
        );
    }


    // =========================================================
    // GREY BUTTON
    // =========================================================

    private void setGreyButton(TextView button) {

        GradientDrawable drawable =
                new GradientDrawable();

        drawable.setColor(
                Color.rgb(128, 128, 128)
        );

        drawable.setCornerRadius(
                8 * getResources()
                        .getDisplayMetrics()
                        .density
        );

        button.setBackground(drawable);

        button.setTextColor(
                Color.WHITE
        );

        button.setStateListAnimator(null);
    }

}