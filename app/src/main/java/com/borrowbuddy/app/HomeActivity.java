package com.borrowbuddy.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    // ================= HEADER =================
    TextView backButton;
    TextView headerTitle;
    TextView settingsIcon;

    // ================= GREETING =================
    TextView helloText;

    // ================= SEARCH =================
    View searchBar;
    TextView searchText;
    TextView viewAllButton;

    // ================= CATEGORIES =================
    View electronicsCard;
    View toolsCard;
    View booksCard;
    View sportsCard;
    View furnitureCard;
    View kitchenCard;

    // ================= POPULAR ITEMS =================
    View bicycleCard;
    View gamingCard;

    // ================= BOTTOM NAVIGATION =================
    View homeNav;
    View activityNav;
    View bookingsNav;
    View profileNav;

    ScrollView homeScrollView;

    // ================= DARK MODE =================
    SharedPreferences preferences;

    private static final String PREF_NAME = "BorrowBuddy";
    private static final String DARK_MODE = "darkMode";

    // Purple
    private static final int PURPLE =
            Color.rgb(106, 27, 154);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        // =================================================
        // STATUS BAR PURPLE
        // =================================================

        Window window = getWindow();
        window.setStatusBarColor(PURPLE);

        // White status bar icons
        window.getDecorView().setSystemUiVisibility(0);

        // =================================================
        // SHARED PREFERENCES
        // =================================================

        preferences =
                getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        // =================================================
        // FIND VIEWS
        // =================================================

        // Header
        backButton = findViewById(R.id.backButton);
        headerTitle = findViewById(R.id.headerTitle);
        settingsIcon = findViewById(R.id.settingsIcon);

        // Greeting
        helloText = findViewById(R.id.helloText);

        // Search
        searchBar = findViewById(R.id.searchBar);
        searchText = findViewById(R.id.searchText);
        viewAllButton = findViewById(R.id.viewAllButton);

        // Scroll
        homeScrollView =
                findViewById(R.id.homeScrollView);

        // Categories
        electronicsCard =
                findViewById(R.id.electronicsCard);

        toolsCard =
                findViewById(R.id.toolsCard);

        booksCard =
                findViewById(R.id.booksCard);

        sportsCard =
                findViewById(R.id.sportsCard);

        furnitureCard =
                findViewById(R.id.furnitureCard);

        kitchenCard =
                findViewById(R.id.kitchenCard);

        // Popular
        bicycleCard =
                findViewById(R.id.bicycleCard);

        gamingCard =
                findViewById(R.id.gamingCard);

        // Bottom navigation
        homeNav =
                findViewById(R.id.homeNav);

        activityNav =
                findViewById(R.id.activityNav);

        bookingsNav =
                findViewById(R.id.bookingsNav);

        profileNav =
                findViewById(R.id.profileNav);

        // =================================================
        // APPLY THEME
        // =================================================

        applyTheme();

        // =================================================
        // HEADER CLICKS
        // =================================================

        // Back
        if (backButton != null) {

            backButton.setOnClickListener(v -> {
                finish();
            });
        }

        // Settings
        if (settingsIcon != null) {

            settingsIcon.setOnClickListener(v -> {

                Intent intent =
                        new Intent(
                                HomeActivity.this,
                                SettingsActivity.class
                        );

                startActivity(intent);
            });
        }

        // =================================================
        // SEARCH
        // =================================================

        if (searchBar != null) {

            searchBar.setOnClickListener(v -> {
                openSearch();
            });
        }

        if (searchText != null) {

            searchText.setOnClickListener(v -> {
                openSearch();
            });
        }

        // View All
        if (viewAllButton != null) {

            viewAllButton.setOnClickListener(v -> {
                openSearch();
            });
        }

        // =================================================
        // CATEGORY CLICKS
        // =================================================

        if (electronicsCard != null) {

            electronicsCard.setOnClickListener(v -> {
                openCategory("Electronics");
            });
        }

        if (toolsCard != null) {

            toolsCard.setOnClickListener(v -> {
                openCategory("Tools");
            });
        }

        if (booksCard != null) {

            booksCard.setOnClickListener(v -> {
                openCategory("Books");
            });
        }

        if (sportsCard != null) {

            sportsCard.setOnClickListener(v -> {
                openCategory("Sports");
            });
        }

        if (furnitureCard != null) {

            furnitureCard.setOnClickListener(v -> {
                openCategory("Furniture");
            });
        }

        if (kitchenCard != null) {

            kitchenCard.setOnClickListener(v -> {
                openCategory("Kitchen");
            });
        }

        // =================================================
        // POPULAR ITEMS
        // =================================================

        if (bicycleCard != null) {

            bicycleCard.setOnClickListener(v -> {
                openItem("Bicycle");
            });
        }

        if (gamingCard != null) {

            gamingCard.setOnClickListener(v -> {
                openItem("Gaming Console");
            });
        }

        // =================================================
        // BOTTOM NAVIGATION
        // =================================================

        // Home
        if (homeNav != null) {

            homeNav.setOnClickListener(v -> {

                if (homeScrollView != null) {

                    homeScrollView.smoothScrollTo(0, 0);
                }

                setBottomNavigation();
            });
        }

        // My Activity
        if (activityNav != null) {

            activityNav.setOnClickListener(v -> {

                Intent intent =
                        new Intent(
                                HomeActivity.this,
                                MyActivityActivity.class
                        );

                startActivity(intent);
            });
        }

        // My Bookings
        if (bookingsNav != null) {

            bookingsNav.setOnClickListener(v -> {

                Intent intent =
                        new Intent(
                                HomeActivity.this,
                                MyBookingsActivity.class
                        );

                startActivity(intent);
            });
        }

        // My Profile
        if (profileNav != null) {

            profileNav.setOnClickListener(v -> {

                Intent intent =
                        new Intent(
                                HomeActivity.this,
                                MyProfileActivity.class
                        );

                startActivity(intent);
            });
        }
    }

    // =====================================================
    // SEARCH
    // =====================================================

    private void openSearch() {

        Intent intent =
                new Intent(
                        HomeActivity.this,
                        SearchFilterActivity.class
                );

        startActivity(intent);
    }

    // =====================================================
    // CATEGORY
    // =====================================================

    private void openCategory(String category) {

        Intent intent =
                new Intent(
                        HomeActivity.this,
                        SearchFilterActivity.class
                );

        intent.putExtra("category", category);

        startActivity(intent);
    }

    // =====================================================
    // ITEM DETAILS
    // =====================================================

    private void openItem(String itemName) {

        Intent intent =
                new Intent(
                        HomeActivity.this,
                        ItemDetailsActivity.class
                );

        intent.putExtra("itemName", itemName);

        startActivity(intent);
    }

    // =====================================================
    // APPLY THEME
    // =====================================================

    private void applyTheme() {

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

        // Header always purple
        setHeader();

        // Status bar always purple
        getWindow().setStatusBarColor(PURPLE);

        // Bottom navigation
        setBottomNavigation();
    }

    // =====================================================
    // DARK MODE
    // =====================================================

    private void applyDarkMode() {

        LinearLayout mainLayout =
                findViewById(R.id.homeMainLayout);

        if (mainLayout != null) {

            mainLayout.setBackgroundColor(
                    Color.BLACK
            );
        }

        // Greeting
        if (helloText != null) {

            helloText.setTextColor(
                    Color.WHITE
            );
        }

        // Search
        if (searchBar != null) {

            searchBar.setBackgroundColor(
                    Color.rgb(45, 45, 45)
            );
        }

        if (searchText != null) {

            searchText.setTextColor(
                    Color.WHITE
            );
        }

        // Categories
        setBackground(
                R.id.electronicsCard,
                Color.rgb(35, 30, 45)
        );

        setBackground(
                R.id.toolsCard,
                Color.rgb(35, 30, 45)
        );

        setBackground(
                R.id.booksCard,
                Color.rgb(35, 30, 45)
        );

        setBackground(
                R.id.sportsCard,
                Color.rgb(35, 30, 45)
        );

        setBackground(
                R.id.furnitureCard,
                Color.rgb(35, 30, 45)
        );

        setBackground(
                R.id.kitchenCard,
                Color.rgb(35, 30, 45)
        );

        // Popular cards
        setBackground(
                R.id.bicycleCard,
                Color.rgb(35, 35, 35)
        );

        setBackground(
                R.id.gamingCard,
                Color.rgb(35, 35, 35)
        );

        // All normal text white
        setTextColor(
                R.id.homeMainLayout,
                Color.WHITE
        );

        // Search placeholder stays white
        if (searchText != null) {

            searchText.setTextColor(
                    Color.WHITE
            );
        }

        // View all purple
        if (viewAllButton != null) {

            viewAllButton.setTextColor(
                    PURPLE
            );
        }
    }

    // =====================================================
    // LIGHT MODE
    // =====================================================

    private void applyLightMode() {

        LinearLayout mainLayout =
                findViewById(R.id.homeMainLayout);

        if (mainLayout != null) {

            mainLayout.setBackgroundColor(
                    Color.WHITE
            );
        }

        // Greeting
        if (helloText != null) {

            helloText.setTextColor(
                    Color.rgb(34, 34, 34)
            );
        }

        // Search
        if (searchBar != null) {

            searchBar.setBackgroundColor(
                    Color.rgb(242, 242, 242)
            );
        }

        if (searchText != null) {

            searchText.setTextColor(
                    Color.rgb(119, 119, 119)
            );
        }

        // Categories
        setBackground(
                R.id.electronicsCard,
                Color.rgb(245, 240, 255)
        );

        setBackground(
                R.id.toolsCard,
                Color.rgb(245, 240, 255)
        );

        setBackground(
                R.id.booksCard,
                Color.rgb(245, 240, 255)
        );

        setBackground(
                R.id.sportsCard,
                Color.rgb(245, 240, 255)
        );

        setBackground(
                R.id.furnitureCard,
                Color.rgb(245, 240, 255)
        );

        setBackground(
                R.id.kitchenCard,
                Color.rgb(245, 240, 255)
        );

        // Popular
        setBackground(
                R.id.bicycleCard,
                Color.rgb(247, 247, 247)
        );

        setBackground(
                R.id.gamingCard,
                Color.rgb(247, 247, 247)
        );

        // Normal text dark
        setTextColor(
                R.id.homeMainLayout,
                Color.rgb(34, 34, 34)
        );

        // View All purple
        if (viewAllButton != null) {

            viewAllButton.setTextColor(
                    PURPLE
            );
        }
    }

    // =====================================================
    // HEADER
    // =====================================================

    private void setHeader() {

        View headerLayout =
                findViewById(R.id.headerLayout);

        if (headerLayout != null) {

            headerLayout.setBackgroundColor(
                    PURPLE
            );
        }

        if (backButton != null) {

            backButton.setTextColor(
                    Color.WHITE
            );
        }

        if (headerTitle != null) {

            headerTitle.setTextColor(
                    Color.WHITE
            );
        }

        if (settingsIcon != null) {

            settingsIcon.setTextColor(
                    Color.WHITE
            );
        }
    }

    // =====================================================
    // BOTTOM NAVIGATION
    // =====================================================

    private void setBottomNavigation() {

        ViewGroup bottomNavigation =
                findViewById(
                        R.id.bottomNavigation
                );

        if (bottomNavigation != null) {

            bottomNavigation.setBackgroundColor(
                    Color.WHITE
            );
        }

        View bottomCard =
                findViewById(
                        R.id.bottomNavigationCard
                );

        if (bottomCard != null) {

            bottomCard.setBackgroundColor(
                    Color.WHITE
            );
        }

        // Icons purple
        if (homeNav instanceof TextView) {

            ((TextView) homeNav).setTextColor(
                    PURPLE
            );
        }

        if (activityNav instanceof TextView) {

            ((TextView) activityNav).setTextColor(
                    PURPLE
            );
        }

        if (bookingsNav instanceof TextView) {

            ((TextView) bookingsNav).setTextColor(
                    PURPLE
            );
        }

        if (profileNav instanceof TextView) {

            ((TextView) profileNav).setTextColor(
                    PURPLE
            );
        }
    }

    // =====================================================
    // SET BACKGROUND
    // =====================================================

    private void setBackground(
            int id,
            int color
    ) {

        View view =
                findViewById(id);

        if (view != null) {

            view.setBackgroundColor(color);
        }
    }

    // =====================================================
    // SET TEXT COLOR
    // =====================================================

    private void setTextColor(
            int parentId,
            int color
    ) {

        View parent =
                findViewById(parentId);

        if (parent instanceof ViewGroup) {

            changeTextColors(
                    (ViewGroup) parent,
                    color
            );
        }
    }

    private void changeTextColors(
            ViewGroup parent,
            int color
    ) {

        for (int i = 0;
             i < parent.getChildCount();
             i++) {

            View child =
                    parent.getChildAt(i);

            if (child instanceof TextView) {

                TextView textView =
                        (TextView) child;

                textView.setTextColor(color);

            } else if (child instanceof ViewGroup) {

                changeTextColors(
                        (ViewGroup) child,
                        color
                );
            }
        }
    }

    // =====================================================
    // REFRESH AFTER SETTINGS
    // =====================================================

    @Override
    protected void onResume() {

        super.onResume();

        if (preferences != null) {

            applyTheme();
        }
    }
}