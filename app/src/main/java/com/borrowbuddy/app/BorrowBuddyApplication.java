package com.borrowbuddy.app;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

public class BorrowBuddyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferences preferences =
                getSharedPreferences("BorrowBuddySettings", MODE_PRIVATE);

        boolean darkMode =
                preferences.getBoolean("dark_mode", false);

        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES
            );
        } else {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO
            );
        }
    }
}