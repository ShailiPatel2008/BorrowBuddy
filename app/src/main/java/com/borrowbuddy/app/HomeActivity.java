package com.borrowbuddy.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private static final int PURPLE = Color.rgb(106, 27, 154);
    private static final int WHITE = Color.WHITE;
    private static final int BLACK = Color.BLACK;

    private static final String PREF_NAME = "BorrowBuddy";
    private static final String DARK_MODE = "darkMode";

    private LinearLayout headerLayout;
    private TextView backButton;
    private TextView headerTitle;
    private TextView settingsIcon;

    private ScrollView homeScrollView;
    private LinearLayout homeMainLayout;

    private TextView helloText;
    private View searchBar;
    private TextView searchText;

    private View electronicsCard;
    private View toolsCard;
    private View booksCard;
    private View sportsCard;
    private View furnitureCard;
    private View kitchenCard;

    private View bicycleCard;
    private View gamingCard;
    private TextView viewAllButton;

    private View bottomNavigationCard;
    private LinearLayout bottomNavigation;

    private TextView homeNav;
    private TextView searchNav;
    private TextView bookingsNav;
    private TextView profileNav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        initializeViews();

        setupHeader();
        setupSearch();
        setupCategories();
        setupPopularItems();
        setupBottomNavigation();

        applySavedTheme();
    }


    // =========================================================
    // INITIALIZE
    // =========================================================

    private void initializeViews() {

        headerLayout = findViewById(R.id.headerLayout);
        backButton = findViewById(R.id.backButton);
        headerTitle = findViewById(R.id.headerTitle);
        settingsIcon = findViewById(R.id.settingsIcon);

        homeScrollView = findViewById(R.id.homeScrollView);
        homeMainLayout = findViewById(R.id.homeMainLayout);

        helloText = findViewById(R.id.helloText);
        searchBar = findViewById(R.id.searchBar);
        searchText = findViewById(R.id.searchText);

        electronicsCard = findViewById(R.id.electronicsCard);
        toolsCard = findViewById(R.id.toolsCard);
        booksCard = findViewById(R.id.booksCard);
        sportsCard = findViewById(R.id.sportsCard);
        furnitureCard = findViewById(R.id.furnitureCard);
        kitchenCard = findViewById(R.id.kitchenCard);

        bicycleCard = findViewById(R.id.bicycleCard);
        gamingCard = findViewById(R.id.gamingCard);
        viewAllButton = findViewById(R.id.viewAllButton);

        bottomNavigationCard =
                findViewById(R.id.bottomNavigationCard);

        bottomNavigation =
                findViewById(R.id.bottomNavigation);

        homeNav =
                findViewById(R.id.homeNav);

        searchNav =
                findViewById(R.id.searchNav);

        bookingsNav =
                findViewById(R.id.bookingsNav);

        profileNav =
                findViewById(R.id.profileNav);
    }


    // =========================================================
    // HEADER
    // =========================================================

    private void setupHeader() {

        if (backButton != null) {

            backButton.setOnClickListener(v -> finish());
        }

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
    }


    // =========================================================
    // SEARCH
    // =========================================================

    private void setupSearch() {

        if (searchBar != null) {

            searchBar.setOnClickListener(v ->
                    openSearchFilter("")
            );
        }

        if (searchText != null) {

            searchText.setOnClickListener(v ->
                    openSearchFilter("")
            );
        }
    }


    // =========================================================
    // CATEGORIES
    // =========================================================

    private void setupCategories() {

        if (electronicsCard != null) {

            electronicsCard.setOnClickListener(v ->
                    openSearchFilter("Electronics")
            );
        }

        if (toolsCard != null) {

            toolsCard.setOnClickListener(v ->
                    openSearchFilter("Tools")
            );
        }

        if (booksCard != null) {

            booksCard.setOnClickListener(v ->
                    openSearchFilter("Books")
            );
        }

        if (sportsCard != null) {

            sportsCard.setOnClickListener(v ->
                    openSearchFilter("Sports")
            );
        }

        if (furnitureCard != null) {

            furnitureCard.setOnClickListener(v ->
                    openSearchFilter("Furniture")
            );
        }

        if (kitchenCard != null) {

            kitchenCard.setOnClickListener(v ->
                    openSearchFilter("Kitchen")
            );
        }
    }


    // =========================================================
    // SEARCH FILTER
    // =========================================================

    private void openSearchFilter(String category) {

        Intent intent =
                new Intent(
                        HomeActivity.this,
                        SearchFilterActivity.class
                );

        if (category != null && !category.isEmpty()) {

            intent.putExtra(
                    "selectedCategory",
                    category
            );
        }

        startActivity(intent);
    }


    // =========================================================
    // POPULAR ITEMS
    // =========================================================

    private void setupPopularItems() {

        if (bicycleCard != null) {

            bicycleCard.setOnClickListener(v -> {

                Intent intent =
                        new Intent(
                                HomeActivity.this,
                                ItemDetailsActivity.class
                        );

                intent.putExtra(
                        "itemName",
                        "Bicycle"
                );

                startActivity(intent);
            });
        }


        if (gamingCard != null) {

            gamingCard.setOnClickListener(v -> {

                Intent intent =
                        new Intent(
                                HomeActivity.this,
                                ItemDetailsActivity.class
                        );

                intent.putExtra(
                        "itemName",
                        "Gaming Console"
                );

                startActivity(intent);
            });
        }


        if (viewAllButton != null) {

            viewAllButton.setOnClickListener(v ->
                    openSearchFilter("")
            );
        }
    }


    // =========================================================
    // BOTTOM NAVIGATION
    // =========================================================

    private void setupBottomNavigation() {

        if (homeNav != null) {

            homeNav.setOnClickListener(v -> {

                if (homeScrollView != null) {

                    homeScrollView.smoothScrollTo(
                            0,
                            0
                    );
                }

                applyBottomNavigationTheme();
            });
        }


        if (searchNav != null) {

            searchNav.setOnClickListener(v ->
                    openSearchFilter("")
            );
        }


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


    // =========================================================
    // SAVED THEME
    // =========================================================

    private void applySavedTheme() {

        SharedPreferences preferences =
                getSharedPreferences(
                        PREF_NAME,
                        MODE_PRIVATE
                );

        boolean isDarkMode =
                preferences.getBoolean(
                        DARK_MODE,
                        false
                );

        if (isDarkMode) {

            applyDarkMode();

        } else {

            applyLightMode();
        }
    }


    // =========================================================
    // DARK MODE
    // =========================================================

    private void applyDarkMode() {

        if (headerLayout != null) {
            headerLayout.setBackgroundColor(PURPLE);
        }

        if (homeScrollView != null) {
            homeScrollView.setBackgroundColor(Color.rgb(18, 18, 18));
        }

        if (homeMainLayout != null) {
            homeMainLayout.setBackgroundColor(Color.rgb(18, 18, 18));
        }

        if (helloText != null) {
            helloText.setTextColor(WHITE);
        }

        if (headerTitle != null) {
            headerTitle.setTextColor(WHITE);
        }

        if (backButton != null) {
            backButton.setTextColor(WHITE);
        }

        if (settingsIcon != null) {
            settingsIcon.setTextColor(WHITE);
        }

        // Bottom navigation
        if (bottomNavigationCard != null) {
            ((androidx.cardview.widget.CardView)
                    bottomNavigationCard)
                    .setCardBackgroundColor(
                            Color.rgb(25, 25, 25)
                    );
        }

        if (bottomNavigation != null) {
            bottomNavigation.setBackgroundColor(
                    Color.TRANSPARENT
            );
        }

        // ALL ICONS WHITE
        setAllBottomIconsColor(WHITE);
    }


    // =========================================================
    // LIGHT MODE
    // =========================================================

    private void applyLightMode() {

        if (headerLayout != null) {
            headerLayout.setBackgroundColor(PURPLE);
        }

        if (homeScrollView != null) {
            homeScrollView.setBackgroundColor(
                    Color.WHITE
            );
        }

        if (homeMainLayout != null) {
            homeMainLayout.setBackgroundColor(
                    Color.WHITE
            );
        }

        if (helloText != null) {
            helloText.setTextColor(
                    Color.rgb(34, 34, 34)
            );
        }

        if (headerTitle != null) {
            headerTitle.setTextColor(WHITE);
        }

        if (backButton != null) {
            backButton.setTextColor(WHITE);
        }

        if (settingsIcon != null) {
            settingsIcon.setTextColor(WHITE);
        }

        // Bottom navigation
        if (bottomNavigationCard != null) {
            ((androidx.cardview.widget.CardView)
                    bottomNavigationCard)
                    .setCardBackgroundColor(
                            WHITE
                    );
        }

        if (bottomNavigation != null) {
            bottomNavigation.setBackgroundColor(
                    Color.TRANSPARENT
            );
        }

        // ALL ICONS PURPLE
        setAllBottomIconsColor(PURPLE);
    }


    // =========================================================
    // ALL BOTTOM ICON COLORS
    // =========================================================

    private void setAllBottomIconsColor(int color) {

        if (homeNav != null) {
            homeNav.setTextColor(color);
        }

        if (searchNav != null) {
            searchNav.setTextColor(color);
        }

        if (bookingsNav != null) {
            bookingsNav.setTextColor(color);
        }

        if (profileNav != null) {
            profileNav.setTextColor(color);
        }
    }


    // =========================================================
    // BOTTOM NAV THEME
    // =========================================================

    private void applyBottomNavigationTheme() {

        SharedPreferences preferences =
                getSharedPreferences(
                        PREF_NAME,
                        MODE_PRIVATE
                );

        boolean isDarkMode =
                preferences.getBoolean(
                        DARK_MODE,
                        false
                );

        if (isDarkMode) {

            if (bottomNavigationCard != null) {

                ((androidx.cardview.widget.CardView)
                        bottomNavigationCard)
                        .setCardBackgroundColor(
                                Color.rgb(25, 25, 25)
                        );
            }

            if (bottomNavigation != null) {

                bottomNavigation.setBackgroundColor(
                        Color.TRANSPARENT
                );
            }

            setAllBottomIconsColor(WHITE);

        } else {

            if (bottomNavigationCard != null) {

                ((androidx.cardview.widget.CardView)
                        bottomNavigationCard)
                        .setCardBackgroundColor(
                                WHITE
                        );
            }

            if (bottomNavigation != null) {

                bottomNavigation.setBackgroundColor(
                        Color.TRANSPARENT
                );
            }

            setAllBottomIconsColor(PURPLE);
        }
    }


    // =========================================================
    // RESUME
    // =========================================================

    @Override
    protected void onResume() {

        super.onResume();

        // Settings se wapas aane par theme dobara apply
        applySavedTheme();
    }
}