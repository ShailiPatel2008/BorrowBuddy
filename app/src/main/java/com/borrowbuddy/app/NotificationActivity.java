package com.borrowbuddy.app;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class NotificationActivity extends AppCompatActivity {

    private static final int PURPLE = Color.rgb(106, 27, 154);

    private ScrollView notificationScrollView;
    private ViewGroup notificationMainLayout;
    private View notificationHeader;

    private TextView backButton;
    private TextView headerTitle;

    private MaterialCardView notificationSettingCard;
    private MaterialCardView bookingUpdatesCard;
    private MaterialCardView reminderCard;
    private MaterialCardView generalNotificationCard;

    private Switch notificationSwitch;
    private Switch bookingUpdatesSwitch;
    private Switch reminderSwitch;
    private Switch generalNotificationSwitch;

    private final String PREF_NAME = "BorrowBuddy";
    private final String DARK_MODE = "darkMode";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_notification);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // ==============================
        // STATUS BAR PURPLE
        // ==============================

        getWindow().setStatusBarColor(PURPLE);

        // White icons on status bar
        getWindow().getDecorView().setSystemUiVisibility(0);


        // ==============================
        // FIND VIEWS
        // ==============================

        notificationScrollView =
                findViewById(R.id.notificationScrollView);

        notificationMainLayout =
                findViewById(R.id.notificationMainLayout);

        notificationHeader =
                findViewById(R.id.notificationHeader);

        backButton =
                findViewById(R.id.backButton);

        headerTitle =
                findViewById(R.id.headerTitle);

        notificationSettingCard =
                findViewById(R.id.notificationSettingCard);

        bookingUpdatesCard =
                findViewById(R.id.bookingUpdatesCard);

        reminderCard =
                findViewById(R.id.reminderCard);

        generalNotificationCard =
                findViewById(R.id.generalNotificationCard);

        notificationSwitch =
                findViewById(R.id.notificationSwitch);

        bookingUpdatesSwitch =
                findViewById(R.id.bookingUpdatesSwitch);

        reminderSwitch =
                findViewById(R.id.reminderSwitch);

        generalNotificationSwitch =
                findViewById(R.id.generalNotificationSwitch);


        // ==============================
        // BACK BUTTON
        // ==============================

        backButton.setOnClickListener(v -> finish());


        // ==============================
        // LOAD DARK MODE
        // ==============================

        SharedPreferences preferences =
                getSharedPreferences(
                        PREF_NAME,
                        MODE_PRIVATE
                );

        boolean darkMode =
                preferences.getBoolean(
                        DARK_MODE,
                        false
                );


        if (darkMode) {
            applyDarkMode();
        } else {
            applyLightMode();
        }


        // ==============================
        // MAIN NOTIFICATION SWITCH
        // ==============================

        notificationSwitch.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {

                    bookingUpdatesSwitch.setEnabled(isChecked);
                    reminderSwitch.setEnabled(isChecked);
                    generalNotificationSwitch.setEnabled(isChecked);
                }
        );
    }


    // ==================================================
    // LIGHT MODE
    // ==================================================

    private void applyLightMode() {

        // Screen background
        notificationScrollView.setBackgroundColor(
                Color.rgb(248, 249, 250)
        );

        notificationMainLayout.setBackgroundColor(
                Color.rgb(248, 249, 250)
        );


        // ==============================
        // HEADER DARK PURPLE
        // ==============================

        notificationHeader.setBackgroundColor(
                PURPLE
        );

        backButton.setTextColor(Color.WHITE);
        headerTitle.setTextColor(Color.WHITE);


        // ==============================
        // CARDS WHITE
        // ==============================

        setCardLight(notificationSettingCard);
        setCardLight(bookingUpdatesCard);
        setCardLight(reminderCard);
        setCardLight(generalNotificationCard);


        // ==============================
        // TEXT
        // ==============================

        setTextColorRecursive(
                notificationMainLayout,
                Color.BLACK
        );


        // Header again white
        backButton.setTextColor(Color.WHITE);
        headerTitle.setTextColor(Color.WHITE);


        // ==============================
        // STATUS BAR
        // ==============================

        getWindow().setStatusBarColor(PURPLE);
        getWindow().getDecorView().setSystemUiVisibility(0);
    }


    // ==================================================
    // DARK MODE
    // ==================================================

    private void applyDarkMode() {

        // Screen background
        notificationScrollView.setBackgroundColor(
                Color.BLACK
        );

        notificationMainLayout.setBackgroundColor(
                Color.BLACK
        );


        // ==============================
        // HEADER DARK PURPLE
        // ==============================

        notificationHeader.setBackgroundColor(
                PURPLE
        );

        backButton.setTextColor(Color.WHITE);
        headerTitle.setTextColor(Color.WHITE);


        // ==============================
        // CARDS BLACK
        // ==============================

        setCardDark(notificationSettingCard);
        setCardDark(bookingUpdatesCard);
        setCardDark(reminderCard);
        setCardDark(generalNotificationCard);


        // ==============================
        // TEXT WHITE
        // ==============================

        setTextColorRecursive(
                notificationMainLayout,
                Color.WHITE
        );


        // Header must remain white
        backButton.setTextColor(Color.WHITE);
        headerTitle.setTextColor(Color.WHITE);


        // ==============================
        // STATUS BAR
        // ==============================

        getWindow().setStatusBarColor(PURPLE);
        getWindow().getDecorView().setSystemUiVisibility(0);
    }


    // ==================================================
    // LIGHT CARD
    // ==================================================

    private void setCardLight(
            MaterialCardView card
    ) {

        card.setCardBackgroundColor(
                Color.WHITE
        );

        card.setStrokeColor(
                Color.rgb(224, 224, 224)
        );

        card.setStrokeWidth(1);
    }


    // ==================================================
    // DARK CARD
    // ==================================================

    private void setCardDark(
            MaterialCardView card
    ) {

        card.setCardBackgroundColor(
                Color.BLACK
        );

        card.setStrokeColor(
                Color.rgb(80, 80, 80)
        );

        card.setStrokeWidth(1);
    }


    // ==================================================
    // CHANGE TEXT COLOR
    // ==================================================

    private void setTextColorRecursive(
            View view,
            int color
    ) {

        if (view instanceof TextView) {

            TextView textView =
                    (TextView) view;

            // Header ko skip karo
            if (textView.getId() != R.id.backButton
                    &&
                    textView.getId() != R.id.headerTitle) {

                textView.setTextColor(color);
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


    // ==================================================
    // APPLY AGAIN WHEN RETURNING
    // ==================================================

    @Override
    protected void onResume() {

        super.onResume();

        SharedPreferences preferences =
                getSharedPreferences(
                        PREF_NAME,
                        MODE_PRIVATE
                );

        boolean darkMode =
                preferences.getBoolean(
                        DARK_MODE,
                        false
                );

        if (darkMode) {
            applyDarkMode();
        } else {
            applyLightMode();
        }
    }
}