package com.borrowbuddy.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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

    private TextView settingsIcon;
    private LinearLayout headerLayout;
    private LinearLayout homeRootLayout;
    private TextView headerTitle;
    private ScrollView homeScrollView;
    private LinearLayout homeMainLayout;

    private TextView helloText;
    private View searchBar;
    private TextView searchText;

    private TextView browseCategoriesTitle;
    private TextView popularItemsTitle;

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
        headerTitle = findViewById(R.id.headerTitle);
        homeRootLayout =
                findViewById(R.id.homeRootLayout);
        settingsIcon = findViewById(R.id.settingsIcon);

        homeScrollView = findViewById(R.id.homeScrollView);
        homeMainLayout = findViewById(R.id.homeMainLayout);

        helloText = findViewById(R.id.helloText);
        searchBar = findViewById(R.id.searchBar);
        searchText = findViewById(R.id.searchText);

        browseCategoriesTitle =
                findViewById(R.id.browseCategoriesTitle);

        popularItemsTitle =
                findViewById(R.id.popularItemsTitle);

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

            searchNav.setOnClickListener(v -> {

                Intent intent =
                        new Intent(
                                HomeActivity.this,
                                MyActivityActivity.class
                        );

                startActivity(intent);
            });
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

        // Main background

        if (headerLayout != null) {
            headerLayout.setBackgroundColor(PURPLE);
        }

        if (homeScrollView != null) {
            homeScrollView.setBackgroundColor(
                    Color.rgb(18, 18, 18)
            );
        }

        if (homeMainLayout != null) {
            homeMainLayout.setBackgroundColor(
                    Color.rgb(18, 18, 18)
            );
        }

        // Greeting

        if (helloText != null) {
            helloText.setTextColor(WHITE);
        }

        // Header

        if (headerTitle != null) {
            headerTitle.setTextColor(WHITE);
        }

        if (settingsIcon != null) {
            settingsIcon.setTextColor(WHITE);
        }

        // =====================================================
        // SECTION TITLES
        // =====================================================

        if (browseCategoriesTitle != null) {
            browseCategoriesTitle.setTextColor(WHITE);
        }

        if (popularItemsTitle != null) {
            popularItemsTitle.setTextColor(WHITE);
        }

        // =====================================================
        // SEARCH BAR
        // =====================================================

        if (searchBar != null) {
            searchBar.setBackgroundColor(
                    Color.rgb(45, 45, 45)
            );
        }

        if (searchText != null) {
            searchText.setTextColor(
                    Color.LTGRAY
            );
        }

        // =====================================================
        // CATEGORY CARDS
        // =====================================================

        setCardBackgroundDark(electronicsCard);
        setCardBackgroundDark(toolsCard);
        setCardBackgroundDark(booksCard);
        setCardBackgroundDark(sportsCard);
        setCardBackgroundDark(furnitureCard);
        setCardBackgroundDark(kitchenCard);

        // =====================================================
        // POPULAR ITEM CARDS
        // =====================================================

        setCardBackgroundDark(bicycleCard);
        setCardBackgroundDark(gamingCard);

        // View All

        if (viewAllButton != null) {
            viewAllButton.setTextColor(
                    Color.rgb(186, 104, 200)
            );
        }

        // =====================================================
        // CATEGORY + POPULAR ITEM TEXT
        // =====================================================

        setChildTextColor(
                electronicsCard,
                WHITE
        );

        setChildTextColor(
                toolsCard,
                WHITE
        );

        setChildTextColor(
                booksCard,
                WHITE
        );

        setChildTextColor(
                sportsCard,
                WHITE
        );

        setChildTextColor(
                furnitureCard,
                WHITE
        );

        setChildTextColor(
                kitchenCard,
                WHITE
        );

        setChildTextColor(
                bicycleCard,
                WHITE
        );

        setChildTextColor(
                gamingCard,
                WHITE
        );

        // =====================================================
        // BOTTOM NAVIGATION
        // =====================================================

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

        if (homeRootLayout != null) {
            homeRootLayout.setBackgroundColor(
                    Color.rgb(18, 18, 18)
            );
        }

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

        if (settingsIcon != null) {
            settingsIcon.setTextColor(WHITE);
        }

        // =====================================================
        // SECTION TITLES
        // =====================================================

        if (browseCategoriesTitle != null) {
            browseCategoriesTitle.setTextColor(
                    Color.rgb(34, 34, 34)
            );
        }

        if (popularItemsTitle != null) {
            popularItemsTitle.setTextColor(
                    Color.rgb(34, 34, 34)
            );
        }

        // =====================================================
        // SEARCH BAR
        // =====================================================

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

        // =====================================================
        // CATEGORY CARDS
        // =====================================================

        setCardBackgroundLight(electronicsCard);
        setCardBackgroundLight(toolsCard);
        setCardBackgroundLight(booksCard);
        setCardBackgroundLight(sportsCard);
        setCardBackgroundLight(furnitureCard);
        setCardBackgroundLight(kitchenCard);

        // =====================================================
        // POPULAR ITEM CARDS
        // =====================================================

        setCardBackgroundLight(bicycleCard);
        setCardBackgroundLight(gamingCard);

        // Category + popular item text

        setChildTextColor(
                electronicsCard,
                Color.rgb(51, 51, 51)
        );

        setChildTextColor(
                toolsCard,
                Color.rgb(51, 51, 51)
        );

        setChildTextColor(
                booksCard,
                Color.rgb(51, 51, 51)
        );

        setChildTextColor(
                sportsCard,
                Color.rgb(51, 51, 51)
        );

        setChildTextColor(
                furnitureCard,
                Color.rgb(51, 51, 51)
        );

        setChildTextColor(
                kitchenCard,
                Color.rgb(51, 51, 51)
        );

        setChildTextColor(
                bicycleCard,
                Color.rgb(34, 34, 34)
        );

        setChildTextColor(
                gamingCard,
                Color.rgb(34, 34, 34)
        );

        if (viewAllButton != null) {
            viewAllButton.setTextColor(PURPLE);
        }

        // =====================================================
        // BOTTOM NAVIGATION
        // =====================================================

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

        if (homeRootLayout != null) {
            homeRootLayout.setBackgroundColor(
                    Color.WHITE
            );
        }

        setAllBottomIconsColor(PURPLE);
    }


    // =========================================================
    // CARD BACKGROUND - DARK
    // =========================================================

    private void setCardBackgroundDark(View card) {

        if (card != null) {

            card.setBackgroundColor(
                    Color.rgb(35, 35, 35)
            );
        }
    }


    // =========================================================
    // CARD BACKGROUND - LIGHT
    // =========================================================

    private void setCardBackgroundLight(View card) {

        if (card != null) {

            if (card == bicycleCard ||
                    card == gamingCard) {

                card.setBackgroundColor(
                        Color.rgb(247, 247, 247)
                );

            } else {

                card.setBackgroundColor(
                        Color.rgb(245, 240, 255)
                );
            }
        }
    }


    // =========================================================
    // CHANGE TEXT INSIDE CARD
    // =========================================================

    private void setChildTextColor(
            View view,
            int color
    ) {

        if (view instanceof TextView) {

            ((TextView) view).setTextColor(color);
        }

        if (view instanceof ViewGroup) {

            ViewGroup group =
                    (ViewGroup) view;

            for (int i = 0;
                 i < group.getChildCount();
                 i++) {

                setChildTextColor(
                        group.getChildAt(i),
                        color
                );
            }
        }
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

