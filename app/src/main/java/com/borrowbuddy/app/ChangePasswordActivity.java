package com.borrowbuddy.app;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class ChangePasswordActivity extends AppCompatActivity {

    View changePasswordScroll;
    View changePasswordLayout;

    View headerLayout;
    TextView backButton;
    TextView headerTitle;

    TextView titleText;
    TextView subtitleText;

    MaterialCardView passwordCard;

    TextView currentPasswordLabel;
    TextView newPasswordLabel;
    TextView confirmPasswordLabel;

    EditText currentPassword;
    EditText newPassword;
    EditText confirmPassword;

    Button changePasswordButton;

    private static final int PURPLE =
            Color.rgb(106, 27, 154);

    private static final int DARK_CARD =
            Color.rgb(43, 41, 50);

    private static final int DARK_INPUT =
            Color.rgb(55, 55, 55);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_change_password);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Window window = getWindow();

        window.setStatusBarColor(PURPLE);

        window.getDecorView().setSystemUiVisibility(0);


        // ================= FIND VIEWS =================

        changePasswordScroll =
                findViewById(R.id.changePasswordScroll);

        changePasswordLayout =
                findViewById(R.id.changePasswordLayout);

        headerLayout =
                findViewById(R.id.headerLayout);

        backButton =
                findViewById(R.id.backButton);

        headerTitle =
                findViewById(R.id.headerTitle);

        titleText =
                findViewById(R.id.titleText);

        subtitleText =
                findViewById(R.id.subtitleText);

        passwordCard =
                findViewById(R.id.passwordCard);

        currentPasswordLabel =
                findViewById(R.id.currentPasswordLabel);

        newPasswordLabel =
                findViewById(R.id.newPasswordLabel);

        confirmPasswordLabel =
                findViewById(R.id.confirmPasswordLabel);

        currentPassword =
                findViewById(R.id.currentPassword);

        newPassword =
                findViewById(R.id.newPassword);

        confirmPassword =
                findViewById(R.id.confirmPassword);

        changePasswordButton =
                findViewById(R.id.changePasswordButton);


        // ================= BACK =================

        backButton.setOnClickListener(v -> {
            finish();
        });


        // ================= CHANGE PASSWORD =================

        changePasswordButton.setOnClickListener(v -> {

            String current =
                    currentPassword
                            .getText()
                            .toString()
                            .trim();

            String newPass =
                    newPassword
                            .getText()
                            .toString()
                            .trim();

            String confirm =
                    confirmPassword
                            .getText()
                            .toString()
                            .trim();


            // Empty check

            if (current.isEmpty()) {

                currentPassword.setError(
                        "Enter current password"
                );

                currentPassword.requestFocus();

                return;
            }


            if (newPass.isEmpty()) {

                newPassword.setError(
                        "Enter new password"
                );

                newPassword.requestFocus();

                return;
            }


            if (confirm.isEmpty()) {

                confirmPassword.setError(
                        "Confirm new password"
                );

                confirmPassword.requestFocus();

                return;
            }


            // Password length

            if (newPass.length() < 6) {

                newPassword.setError(
                        "Password must be at least 6 characters"
                );

                newPassword.requestFocus();

                return;
            }


            // Password matching

            if (!newPass.equals(confirm)) {

                confirmPassword.setError(
                        "Passwords do not match"
                );

                confirmPassword.requestFocus();

                return;
            }


            // Current and new password same

            if (current.equals(newPass)) {

                newPassword.setError(
                        "New password must be different"
                );

                newPassword.requestFocus();

                return;
            }


            // ================= SUCCESS =================

            Toast.makeText(
                    ChangePasswordActivity.this,
                    "Password changed successfully",
                    Toast.LENGTH_SHORT
            ).show();


            currentPassword.setText("");
            newPassword.setText("");
            confirmPassword.setText("");


            finish();
        });


        // ================= DARK MODE =================

        applyDarkMode();
    }


    // ==================================================
    // DARK MODE
    // ==================================================

    private void applyDarkMode() {

        SharedPreferences preferences =
                getSharedPreferences(
                        "BorrowBuddy",
                        MODE_PRIVATE
                );

        boolean darkMode =
                preferences.getBoolean(
                        "darkMode",
                        false
                );


        // Header always purple

        headerLayout.setBackgroundColor(PURPLE);

        backButton.setTextColor(Color.WHITE);

        headerTitle.setTextColor(Color.WHITE);

        getWindow().setStatusBarColor(PURPLE);

        getWindow()
                .getDecorView()
                .setSystemUiVisibility(0);


        // Button

        changePasswordButton.setBackgroundColor(
                PURPLE
        );

        changePasswordButton.setTextColor(
                Color.WHITE
        );


        if (darkMode) {

            // Background

            changePasswordScroll.setBackgroundColor(
                    Color.BLACK
            );

            changePasswordLayout.setBackgroundColor(
                    Color.BLACK
            );


            // Title

            titleText.setTextColor(
                    Color.WHITE
            );

            subtitleText.setTextColor(
                    Color.LTGRAY
            );


            // Card

            passwordCard.setCardBackgroundColor(
                    DARK_CARD
            );


            // Labels

            currentPasswordLabel.setTextColor(
                    Color.WHITE
            );

            newPasswordLabel.setTextColor(
                    Color.WHITE
            );

            confirmPasswordLabel.setTextColor(
                    Color.WHITE
            );


            // Inputs

            setInputDark(currentPassword);
            setInputDark(newPassword);
            setInputDark(confirmPassword);


        } else {

            // Background

            changePasswordScroll.setBackgroundColor(
                    Color.rgb(248, 249, 250)
            );

            changePasswordLayout.setBackgroundColor(
                    Color.rgb(248, 249, 250)
            );


            // Title

            titleText.setTextColor(
                    Color.BLACK
            );

            subtitleText.setTextColor(
                    Color.rgb(102, 102, 102)
            );


            // Card

            passwordCard.setCardBackgroundColor(
                    Color.WHITE
            );


            // Labels

            currentPasswordLabel.setTextColor(
                    Color.BLACK
            );

            newPasswordLabel.setTextColor(
                    Color.BLACK
            );

            confirmPasswordLabel.setTextColor(
                    Color.BLACK
            );


            // Inputs

            setInputLight(currentPassword);
            setInputLight(newPassword);
            setInputLight(confirmPassword);
        }
    }


    // ==================================================
    // DARK INPUT
    // ==================================================

    private void setInputDark(EditText editText) {

        editText.setBackgroundColor(DARK_INPUT);

        editText.setTextColor(Color.WHITE);

        editText.setHintTextColor(
                Color.LTGRAY
        );
    }


    // ==================================================
    // LIGHT INPUT
    // ==================================================

    private void setInputLight(EditText editText) {

        editText.setBackgroundColor(
                Color.rgb(242, 242, 242)
        );

        editText.setTextColor(Color.BLACK);

        editText.setHintTextColor(
                Color.rgb(119, 119, 119)
        );
    }


    // ==================================================
    // RESUME
    // ==================================================

    @Override
    protected void onResume() {

        super.onResume();

        applyDarkMode();
    }
}