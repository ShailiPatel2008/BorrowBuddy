package com.borrowbuddy.app;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class AdminChangePasswordActivity extends AppCompatActivity {

    View mainLayout;

    ImageButton btnBack;
    TextView btnChangePassword;

    TextView txtTitle;
    TextView txtSubtitle;

    TextView lblCurrentPassword;
    TextView lblNewPassword;
    TextView lblConfirmPassword;

    EditText edtCurrentPassword;
    EditText edtNewPassword;
    EditText edtConfirmPassword;

    SharedPreferences passwordPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_change_password);


        // ================= FIND VIEWS =================

        mainLayout = findViewById(R.id.mainLayout);

        btnBack = findViewById(R.id.btnBack);

        btnChangePassword =
                findViewById(R.id.btnChangePassword);

        txtTitle =
                findViewById(R.id.txtTitle);

        txtSubtitle =
                findViewById(R.id.txtSubtitle);

        lblCurrentPassword =
                findViewById(R.id.lblCurrentPassword);

        lblNewPassword =
                findViewById(R.id.lblNewPassword);

        lblConfirmPassword =
                findViewById(R.id.lblConfirmPassword);

        edtCurrentPassword =
                findViewById(R.id.edtCurrentPassword);

        edtNewPassword =
                findViewById(R.id.edtNewPassword);

        edtConfirmPassword =
                findViewById(R.id.edtConfirmPassword);


        // ================= PASSWORD STORAGE =================

        passwordPreferences =
                getSharedPreferences(
                        "AdminPassword",
                        MODE_PRIVATE
                );


        // ================= DEFAULT PASSWORD =================

        if (!passwordPreferences.contains("password")) {

            passwordPreferences.edit()
                    .putString(
                            "password",
                            "admin123"
                    )
                    .apply();
        }


        // ================= DARK MODE =================

        applyDarkMode();


        // ================= BACK =================

        btnBack.setOnClickListener(v -> finish());


        // ================= CHANGE PASSWORD =================

        btnChangePassword.setOnClickListener(
                v -> changePassword()
        );
    }


    // =========================================================
    // CHANGE PASSWORD
    // =========================================================

    private void changePassword() {

        String currentPassword =
                edtCurrentPassword
                        .getText()
                        .toString()
                        .trim();

        String newPassword =
                edtNewPassword
                        .getText()
                        .toString()
                        .trim();

        String confirmPassword =
                edtConfirmPassword
                        .getText()
                        .toString()
                        .trim();


        // ================= CURRENT PASSWORD =================

        if (TextUtils.isEmpty(currentPassword)) {

            edtCurrentPassword.setError(
                    "Enter current password"
            );

            edtCurrentPassword.requestFocus();
            return;
        }


        // ================= NEW PASSWORD =================

        if (TextUtils.isEmpty(newPassword)) {

            edtNewPassword.setError(
                    "Enter new password"
            );

            edtNewPassword.requestFocus();
            return;
        }


        // ================= PASSWORD LENGTH =================

        if (newPassword.length() < 6) {

            edtNewPassword.setError(
                    "Password must be at least 6 characters"
            );

            edtNewPassword.requestFocus();
            return;
        }


        // ================= CONFIRM PASSWORD =================

        if (TextUtils.isEmpty(confirmPassword)) {

            edtConfirmPassword.setError(
                    "Confirm your new password"
            );

            edtConfirmPassword.requestFocus();
            return;
        }


        // ================= CHECK CURRENT PASSWORD =================

        String savedPassword =
                passwordPreferences.getString(
                        "password",
                        "admin123"
                );

        if (!currentPassword.equals(savedPassword)) {

            edtCurrentPassword.setError(
                    "Current password is incorrect"
            );

            edtCurrentPassword.requestFocus();

            Toast.makeText(
                    AdminChangePasswordActivity.this,
                    "Current password is incorrect",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }


        // ================= CHECK NEW PASSWORD =================

        if (currentPassword.equals(newPassword)) {

            edtNewPassword.setError(
                    "New password must be different"
            );

            edtNewPassword.requestFocus();
            return;
        }


        // ================= CONFIRM MATCH =================

        if (!newPassword.equals(confirmPassword)) {

            edtConfirmPassword.setError(
                    "Passwords do not match"
            );

            edtConfirmPassword.requestFocus();

            Toast.makeText(
                    AdminChangePasswordActivity.this,
                    "Passwords do not match",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }


        // ================= SAVE NEW PASSWORD =================

        passwordPreferences.edit()
                .putString(
                        "password",
                        newPassword
                )
                .apply();


        // ================= SUCCESS =================

        Toast.makeText(
                AdminChangePasswordActivity.this,
                "Password changed successfully",
                Toast.LENGTH_SHORT
        ).show();


        // Clear fields
        edtCurrentPassword.setText("");
        edtNewPassword.setText("");
        edtConfirmPassword.setText("");


        // Back to Settings
        finish();
    }


    // =========================================================
    // ON RESUME
    // =========================================================

    @Override
    protected void onResume() {
        super.onResume();

        applyDarkMode();
    }


    // =========================================================
    // DARK MODE
    // =========================================================

    private void applyDarkMode() {

        boolean darkMode =
                getSharedPreferences(
                        "Settings",
                        MODE_PRIVATE
                ).getBoolean(
                        "dark_mode",
                        false
                );


        if (darkMode) {

            // Background
            mainLayout.setBackgroundColor(
                    Color.rgb(16, 16, 16)
            );


            // Text
            txtTitle.setTextColor(
                    Color.WHITE
            );

            txtSubtitle.setTextColor(
                    Color.LTGRAY
            );

            lblCurrentPassword.setTextColor(
                    Color.WHITE
            );

            lblNewPassword.setTextColor(
                    Color.WHITE
            );

            lblConfirmPassword.setTextColor(
                    Color.WHITE
            );


            // EditTexts
            edtCurrentPassword.setTextColor(
                    Color.WHITE
            );

            edtNewPassword.setTextColor(
                    Color.WHITE
            );

            edtConfirmPassword.setTextColor(
                    Color.WHITE
            );


            edtCurrentPassword.setHintTextColor(
                    Color.LTGRAY
            );

            edtNewPassword.setHintTextColor(
                    Color.LTGRAY
            );

            edtConfirmPassword.setHintTextColor(
                    Color.LTGRAY
            );


            edtCurrentPassword.setBackgroundColor(
                    Color.rgb(45, 45, 45)
            );

            edtNewPassword.setBackgroundColor(
                    Color.rgb(45, 45, 45)
            );

            edtConfirmPassword.setBackgroundColor(
                    Color.rgb(45, 45, 45)
            );

        } else {

            // Background
            mainLayout.setBackgroundColor(
                    Color.rgb(246, 243, 248)
            );


            // Text
            txtTitle.setTextColor(
                    Color.BLACK
            );

            txtSubtitle.setTextColor(
                    Color.rgb(119, 119, 119)
            );

            lblCurrentPassword.setTextColor(
                    Color.BLACK
            );

            lblNewPassword.setTextColor(
                    Color.BLACK
            );

            lblConfirmPassword.setTextColor(
                    Color.BLACK
            );


            // EditTexts
            edtCurrentPassword.setTextColor(
                    Color.BLACK
            );

            edtNewPassword.setTextColor(
                    Color.BLACK
            );

            edtConfirmPassword.setTextColor(
                    Color.BLACK
            );


            edtCurrentPassword.setHintTextColor(
                    Color.rgb(119, 119, 119)
            );

            edtNewPassword.setHintTextColor(
                    Color.rgb(119, 119, 119)
            );

            edtConfirmPassword.setHintTextColor(
                    Color.rgb(119, 119, 119)
            );


            edtCurrentPassword.setBackgroundColor(
                    Color.WHITE
            );

            edtNewPassword.setBackgroundColor(
                    Color.WHITE
            );

            edtConfirmPassword.setBackgroundColor(
                    Color.WHITE
            );
        }


        // Toolbar
        View toolbar =
                (View) btnBack.getParent();

        if (toolbar != null) {

            toolbar.setBackgroundColor(
                    Color.rgb(108, 74, 182)
            );
        }

        btnChangePassword.setTextColor(Color.WHITE);
    }
}