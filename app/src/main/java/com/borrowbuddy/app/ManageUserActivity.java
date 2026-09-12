package com.borrowbuddy.app;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ManageUserActivity extends AppCompatActivity {

    LinearLayout mainLayout;

    ImageButton btnBack;
    TextView txtToolbarTitle;

    LinearLayout userCard1;
    LinearLayout userCard2;
    LinearLayout userCard3;

    TextView statusUser1;
    TextView statusUser2;
    TextView statusUser3;

    Button btnViewProfile1;
    Button btnViewProfile2;
    Button btnViewProfile3;

    Button btnBlockUser1;
    Button btnBlockUser2;
    Button btnBlockUser3;

    private final int GREY = Color.rgb(128, 128, 128);
    private final int PURPLE = Color.rgb(108, 74, 182);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manage_users);

        // =========================
        // MAIN LAYOUT
        // =========================

        mainLayout = findViewById(R.id.mainLayout);

        // =========================
        // TOOLBAR
        // =========================

        btnBack = findViewById(R.id.btnBack);
        txtToolbarTitle = findViewById(R.id.txtToolbarTitle);

        // =========================
        // USER CARDS
        // =========================

        userCard1 = findViewById(R.id.userCard1);
        userCard2 = findViewById(R.id.userCard2);
        userCard3 = findViewById(R.id.userCard3);

        // =========================
        // STATUS
        // =========================

        statusUser1 = findViewById(R.id.statusUser1);
        statusUser2 = findViewById(R.id.statusUser2);
        statusUser3 = findViewById(R.id.statusUser3);

        // =========================
        // VIEW PROFILE
        // =========================

        btnViewProfile1 = findViewById(R.id.btnViewProfile1);
        btnViewProfile2 = findViewById(R.id.btnViewProfile2);
        btnViewProfile3 = findViewById(R.id.btnViewProfile3);

        // =========================
        // BLOCK BUTTONS
        // =========================

        btnBlockUser1 = findViewById(R.id.btnBlockUser1);
        btnBlockUser2 = findViewById(R.id.btnBlockUser2);
        btnBlockUser3 = findViewById(R.id.btnBlockUser3);

        // =========================
        // BACK
        // =========================

        btnBack.setOnClickListener(v -> finish());

        // =========================
        // RAHUL PROFILE
        // =========================

        btnViewProfile1.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ManageUserActivity.this,
                    UserProfileActivity.class
            );

            intent.putExtra("userName", "Rahul");
            intent.putExtra("userEmail", "rahul@gmail.com");
            intent.putExtra("userPhone", "9876543210");
            intent.putExtra("trustScore", "4.8");

            startActivity(intent);
        });

        // =========================
        // PRIYA PROFILE
        // =========================

        btnViewProfile2.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ManageUserActivity.this,
                    UserProfileActivity.class
            );

            intent.putExtra("userName", "Priya");
            intent.putExtra("userEmail", "priya@gmail.com");
            intent.putExtra("userPhone", "9876543211");
            intent.putExtra("trustScore", "4.7");

            startActivity(intent);
        });

        // =========================
        // AMIT PROFILE
        // =========================

        btnViewProfile3.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ManageUserActivity.this,
                    UserProfileActivity.class
            );

            intent.putExtra("userName", "Amit");
            intent.putExtra("userEmail", "amit@gmail.com");
            intent.putExtra("userPhone", "9876543212");
            intent.putExtra("trustScore", "4.6");

            startActivity(intent);
        });

        // =========================
        // BLOCK / UNBLOCK
        // =========================

        btnBlockUser1.setOnClickListener(v ->
                blockUnblockUser(
                        statusUser1,
                        btnBlockUser1
                )
        );

        btnBlockUser2.setOnClickListener(v ->
                blockUnblockUser(
                        statusUser2,
                        btnBlockUser2
                )
        );

        btnBlockUser3.setOnClickListener(v ->
                blockUnblockUser(
                        statusUser3,
                        btnBlockUser3
                )
        );

        // =========================
        // INITIAL DARK MODE
        // =========================

        applyDarkMode();
    }

    // =========================================
    // BLOCK / UNBLOCK
    // =========================================

    private void blockUnblockUser(
            TextView status,
            Button button) {

        boolean alreadyBlocked =
                status.getText()
                        .toString()
                        .contains("Blocked");

        if (!alreadyBlocked) {

            showConfirmationDialog(
                    "Block User",
                    "Are you sure you want to Block User?",
                    () -> {

                        status.setText("Status: Blocked");

                        status.setTextColor(
                                Color.rgb(220, 60, 60)
                        );

                        button.setText("Unblock User");

                        makeGreyButton(button);

                        Toast.makeText(
                                ManageUserActivity.this,
                                "User blocked successfully",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
            );

        } else {

            showConfirmationDialog(
                    "Unblock User",
                    "Are you sure you want to Unblock User?",
                    () -> {

                        status.setText("Status: Active");

                        status.setTextColor(
                                Color.rgb(76, 175, 80)
                        );

                        button.setText("Block User");

                        makeGreyButton(button);

                        Toast.makeText(
                                ManageUserActivity.this,
                                "User unblocked successfully",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
            );
        }
    }

    // =========================================
    // CONFIRMATION DIALOG
    // =========================================

    private void showConfirmationDialog(
            String title,
            String message,
            Runnable action) {

        boolean darkMode =
                getSharedPreferences(
                        "Settings",
                        MODE_PRIVATE
                ).getBoolean(
                        "dark_mode",
                        false
                );

        AlertDialog dialog =
                new AlertDialog.Builder(this)
                        .setTitle(title)
                        .setMessage(message)
                        .setNegativeButton("CANCEL", null)
                        .setPositiveButton("YES", null)
                        .create();

        dialog.setOnShowListener(d -> {

            // =========================
            // DIALOG BACKGROUND
            // =========================

            if (dialog.getWindow() != null) {

                if (darkMode) {

                    dialog.getWindow().setBackgroundDrawable(
                            new ColorDrawable(
                                    Color.rgb(55, 55, 55)
                            )
                    );

                } else {

                    dialog.getWindow().setBackgroundDrawable(
                            new ColorDrawable(
                                    Color.WHITE
                            )
                    );
                }
            }

            // =========================
            // TITLE
            // =========================

            int titleId =
                    getResources().getIdentifier(
                            "alertTitle",
                            "id",
                            "android"
                    );

            TextView titleText =
                    dialog.findViewById(titleId);

            if (titleText != null) {

                if (darkMode) {
                    titleText.setTextColor(Color.WHITE);
                } else {
                    titleText.setTextColor(Color.BLACK);
                }
            }

            // =========================
            // MESSAGE
            // =========================

            TextView messageText =
                    dialog.findViewById(
                            android.R.id.message
                    );

            if (messageText != null) {

                if (darkMode) {
                    messageText.setTextColor(Color.WHITE);
                } else {
                    messageText.setTextColor(Color.BLACK);
                }
            }

            // =========================
            // CANCEL BUTTON
            // =========================

            Button cancelButton =
                    dialog.getButton(
                            AlertDialog.BUTTON_NEGATIVE
                    );

            if (cancelButton != null) {

                if (darkMode) {
                    cancelButton.setTextColor(Color.WHITE);
                } else {
                    cancelButton.setTextColor(PURPLE);
                }
            }

            // =========================
            // YES BUTTON
            // =========================

            Button yesButton =
                    dialog.getButton(
                            AlertDialog.BUTTON_POSITIVE
                    );

            if (yesButton != null) {

                if (darkMode) {
                    yesButton.setTextColor(Color.WHITE);
                } else {
                    yesButton.setTextColor(PURPLE);
                }

                yesButton.setOnClickListener(v -> {

                    if (action != null) {
                        action.run();
                    }

                    dialog.dismiss();
                });
            }
        });

        dialog.show();
    }

    // =========================================
    // RESUME
    // =========================================

    @Override
    protected void onResume() {
        super.onResume();

        applyDarkMode();
    }

    // =========================================
    // DARK MODE
    // =========================================

    private void applyDarkMode() {

        SharedPreferences preferences =
                getSharedPreferences(
                        "Settings",
                        MODE_PRIVATE
                );

        boolean darkMode =
                preferences.getBoolean(
                        "dark_mode",
                        false
                );

        if (darkMode) {

            // MAIN BACKGROUND
            mainLayout.setBackgroundColor(
                    Color.rgb(18, 18, 18)
            );

            // CARDS
            userCard1.setBackgroundColor(
                    Color.rgb(35, 35, 35)
            );

            userCard2.setBackgroundColor(
                    Color.rgb(35, 35, 35)
            );

            userCard3.setBackgroundColor(
                    Color.rgb(35, 35, 35)
            );

            // TEXT
            setTextColorRecursive(
                    mainLayout,
                    Color.WHITE
            );

        } else {

            // MAIN BACKGROUND
            mainLayout.setBackgroundColor(
                    Color.WHITE
            );

            // CARDS
            userCard1.setBackgroundColor(
                    Color.rgb(238, 238, 238)
            );

            userCard2.setBackgroundColor(
                    Color.rgb(238, 238, 238)
            );

            userCard3.setBackgroundColor(
                    Color.rgb(238, 238, 238)
            );

            // TEXT
            setTextColorRecursive(
                    mainLayout,
                    Color.BLACK
            );
        }

        // STATUS
        setStatusColor(statusUser1);
        setStatusColor(statusUser2);
        setStatusColor(statusUser3);

        // =========================
        // FORCE GREY BUTTON
        // =========================

        makeGreyButton(btnViewProfile1);
        makeGreyButton(btnViewProfile2);
        makeGreyButton(btnViewProfile3);

        makeGreyButton(btnBlockUser1);
        makeGreyButton(btnBlockUser2);
        makeGreyButton(btnBlockUser3);

        // =========================
        // PURPLE TOOLBAR
        // =========================

        View toolbar =
                mainLayout.getChildAt(0);

        if (toolbar != null) {

            toolbar.setBackgroundColor(
                    Color.rgb(108, 74, 182)
            );

            setToolbarWhite(toolbar);
        }
    }

    // =========================================
    // GREY BUTTON
    // =========================================

    private void makeGreyButton(Button button) {

        if (button == null) {
            return;
        }

        // Remove Android default tint
        button.setBackgroundTintList(null);

        // Force custom grey drawable
        button.setBackgroundResource(
                R.drawable.grey_button
        );

        // Extra force grey tint
        button.setBackgroundTintList(
                ColorStateList.valueOf(GREY)
        );

        button.setTextColor(Color.WHITE);

        button.setAllCaps(false);
    }

    // =========================================
    // STATUS COLOR
    // =========================================

    private void setStatusColor(TextView status) {

        if (status == null) {
            return;
        }

        if (status.getText()
                .toString()
                .contains("Blocked")) {

            status.setTextColor(
                    Color.rgb(220, 60, 60)
            );

        } else {

            status.setTextColor(
                    Color.rgb(76, 175, 80)
            );
        }
    }

    // =========================================
    // TEXT COLOR
    // =========================================

    private void setTextColorRecursive(
            View view,
            int color) {

        if (view instanceof TextView) {

            TextView text =
                    (TextView) view;

            // Buttons separately handled
            if (!(view instanceof Button)
                    && view != btnBack
                    && view != txtToolbarTitle
                    && view != statusUser1
                    && view != statusUser2
                    && view != statusUser3) {

                text.setTextColor(color);
            }
        }

        if (view instanceof ViewGroup) {

            ViewGroup group =
                    (ViewGroup) view;

            for (int i = 0;
                 i < group.getChildCount();
                 i++) {

                setTextColorRecursive(
                        group.getChildAt(i),
                        color
                );
            }
        }
    }

    // =========================================
    // TOOLBAR WHITE
    // =========================================

    private void setToolbarWhite(View view) {

        if (view instanceof TextView) {

            ((TextView) view).setTextColor(
                    Color.WHITE
            );
        }

        if (view instanceof ViewGroup) {

            ViewGroup group =
                    (ViewGroup) view;

            for (int i = 0;
                 i < group.getChildCount();
                 i++) {

                setToolbarWhite(
                        group.getChildAt(i)
                );
            }
        }
    }
}