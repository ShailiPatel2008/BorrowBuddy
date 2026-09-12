package com.borrowbuddy.app;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;


public class SearchFilterActivity extends AppCompatActivity {

    // ==================================================
    // MAIN
    // ==================================================

    ConstraintLayout searchFilterLayout;


    // ==================================================
    // HEADER
    // ==================================================

    MaterialToolbar toolbar;


    // ==================================================
    // SEARCH
    // ==================================================

    MaterialCardView searchCard;
    EditText searchEditText;


    // ==================================================
    // FILTER
    // ==================================================

    MaterialCardView filterCard;

    LinearLayout filterHeader;
    LinearLayout filterOptions;

    TextView filterTitle;
    TextView filterArrow;

    TextView priceFilter;
    TextView locationFilter;
    TextView categoryFilter;

    Spinner priceSpinner;
    Spinner locationSpinner;
    Spinner categorySpinner;

    Switch availableSwitch;

    Button applyFilterButton;


    // ==================================================
    // RESULTS
    // ==================================================

    RecyclerView resultRecyclerView;

    SearchAdapter searchAdapter;


    // ==================================================
    // DATA
    // ==================================================

    ArrayList<SearchItem> allItems =
            new ArrayList<>();

    ArrayList<SearchItem> filteredItems =
            new ArrayList<>();


    // ==================================================
    // DARK MODE
    // ==================================================

    private static final String PREF_NAME =
            "BorrowBuddy";

    private static final String DARK_MODE =
            "darkMode";

    private static final int PURPLE =
            Color.rgb(106, 27, 154);


    // ==================================================
    // ON CREATE
    // ==================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_filter);


        // ==================================================
        // MATERIAL TOOLBAR
        // ==================================================

        toolbar =
                findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {

            getSupportActionBar().setTitle(
                    "Search & Filter"
            );

            getSupportActionBar().setDisplayHomeAsUpEnabled(
                    true
            );
        }

        toolbar.setNavigationOnClickListener(v -> finish());


        // ==================================================
        // STATUS BAR
        // ==================================================

        Window window = getWindow();

        getWindow().setStatusBarColor(Color.rgb(108, 74, 182));
        getWindow().setNavigationBarColor(Color.rgb(248, 249, 250));
        window.getDecorView().setSystemUiVisibility(0);


        // ==================================================
        // FIND VIEWS
        // ==================================================

        searchFilterLayout =
                findViewById(R.id.searchFilterLayout);


        // ==================================================
        // SEARCH
        // ==================================================

        searchCard =
                findViewById(R.id.searchCard);

        searchEditText =
                findViewById(R.id.searchEditText);


        // ==================================================
        // FILTER
        // ==================================================

        filterCard =
                findViewById(R.id.filterCard);

        filterHeader =
                findViewById(R.id.filterHeader);

        filterOptions =
                findViewById(R.id.filterOptions);

        filterTitle =
                findViewById(R.id.filterTitle);

        filterArrow =
                findViewById(R.id.filterArrow);

        priceFilter =
                findViewById(R.id.priceFilter);

        locationFilter =
                findViewById(R.id.locationFilter);

        categoryFilter =
                findViewById(R.id.categoryFilter);

        priceSpinner =
                findViewById(R.id.priceSpinner);

        locationSpinner =
                findViewById(R.id.locationSpinner);

        categorySpinner =
                findViewById(R.id.categorySpinner);

        availableSwitch =
                findViewById(R.id.availableSwitch);

        applyFilterButton =
                findViewById(R.id.applyFilterButton);


        // ==================================================
        // RECYCLER VIEW
        // ==================================================

        resultRecyclerView =
                findViewById(R.id.resultRecyclerView);

        resultRecyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );


        // ==================================================
        // LOAD ITEMS
        // ==================================================

        loadItems();


        // ==================================================
        // SETUP SPINNERS
        // ==================================================

        setupSpinners();


        // ==================================================
        // ADAPTER
        // ==================================================

        searchAdapter =
                new SearchAdapter(filteredItems);

        resultRecyclerView.setAdapter(
                searchAdapter
        );


        // ==================================================
        // DARK MODE
        // ==================================================

        applyDarkMode();


        // ==================================================
        // OPEN / CLOSE FILTER
        // ==================================================

        filterHeader.setOnClickListener(v -> {

            if (filterOptions.getVisibility() == View.GONE) {

                // OPEN FILTERS

                filterOptions.setVisibility(
                        View.VISIBLE
                );

                filterArrow.setText("▲");

            } else {

                // CLOSE FILTERS

                filterOptions.setVisibility(
                        View.GONE
                );

                filterArrow.setText("▼");
            }

        });


        // ==================================================
        // APPLY FILTER BUTTON
        // ==================================================

        applyFilterButton.setOnClickListener(v -> {

            applyFilters();

            // Close filter section

            filterOptions.setVisibility(
                    View.GONE
            );

            filterArrow.setText("▼");

            Toast.makeText(
                    SearchFilterActivity.this,
                    "Filters applied",
                    Toast.LENGTH_SHORT
            ).show();

        });


        // ==================================================
        // SEARCH
        // ==================================================

        searchEditText.addTextChangedListener(
                new TextWatcher() {

                    @Override
                    public void beforeTextChanged(
                            CharSequence s,
                            int start,
                            int count,
                            int after) {
                    }


                    @Override
                    public void onTextChanged(
                            CharSequence s,
                            int start,
                            int before,
                            int count) {

                        applyFilters();

                    }


                    @Override
                    public void afterTextChanged(
                            Editable s) {
                    }

                }
        );

    }


    // ==================================================
    // LOAD SAMPLE ITEMS
    // ==================================================

    private void loadItems() {

        allItems.clear();


        // CAMERA

        allItems.add(
                new SearchItem(
                        "Camera",
                        "₹300/day",
                        "Ahmedabad",
                        "★★★★",
                        0
                )
        );


        // LAPTOP

        allItems.add(
                new SearchItem(
                        "Laptop",
                        "₹500/day",
                        "Ahmedabad",
                        "★★★★",
                        0
                )
        );


        // FOOTBALL

        allItems.add(
                new SearchItem(
                        "Football",
                        "₹150/day",
                        "Ahmedabad",
                        "★★★★",
                        0
                )
        );


        filteredItems.clear();

        filteredItems.addAll(allItems);

    }


    // ==================================================
    // SETUP SPINNERS
    // ==================================================

    private void setupSpinners() {


        // ==================================================
        // PRICE
        // ==================================================

        String[] prices = {

                "Any Price",
                "Below ₹200",
                "₹200 - ₹400",
                "Above ₹400"

        };


        ArrayAdapter<String> priceAdapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        prices
                );

        priceAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        priceSpinner.setAdapter(
                priceAdapter
        );


        // ==================================================
        // LOCATION
        // ==================================================

        String[] locations = {

                "Any Location",
                "Ahmedabad",
                "Gandhinagar",
                "Vadodara"

        };


        ArrayAdapter<String> locationAdapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        locations
                );

        locationAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        locationSpinner.setAdapter(
                locationAdapter
        );


        // ==================================================
        // CATEGORY
        // ==================================================

        String[] categories = {

                "Any Category",
                "Camera",
                "Laptop",
                "Football"

        };


        ArrayAdapter<String> categoryAdapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        categories
                );

        categoryAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        categorySpinner.setAdapter(
                categoryAdapter
        );

    }


    // ==================================================
    // APPLY FILTERS
    // ==================================================

    private void applyFilters() {


        String search =
                searchEditText
                        .getText()
                        .toString()
                        .trim()
                        .toLowerCase();


        String selectedPrice =
                priceSpinner
                        .getSelectedItem()
                        .toString();


        String selectedLocation =
                locationSpinner
                        .getSelectedItem()
                        .toString();


        String selectedCategory =
                categorySpinner
                        .getSelectedItem()
                        .toString();


        filteredItems.clear();


        // ==================================================
        // CHECK EVERY ITEM
        // ==================================================

        for (SearchItem item : allItems) {


            // ==================================================
            // SEARCH
            // ==================================================

            if (!search.isEmpty()
                    &&
                    !item.name
                            .toLowerCase()
                            .contains(search)) {

                continue;
            }


            // ==================================================
            // LOCATION
            // ==================================================

            if (!selectedLocation.equals(
                    "Any Location")) {

                if (!item.location.equalsIgnoreCase(
                        selectedLocation)) {

                    continue;
                }
            }


            // ==================================================
            // CATEGORY
            // ==================================================

            if (!selectedCategory.equals(
                    "Any Category")) {

                if (!item.name.equalsIgnoreCase(
                        selectedCategory)) {

                    continue;
                }
            }


            // ==================================================
            // PRICE
            // ==================================================

            int price =
                    getPrice(item.price);


            if (selectedPrice.equals(
                    "Below ₹200")) {

                if (price >= 200) {
                    continue;
                }
            }


            if (selectedPrice.equals(
                    "₹200 - ₹400")) {

                if (price < 200 ||
                        price > 400) {

                    continue;
                }
            }


            if (selectedPrice.equals(
                    "Above ₹400")) {

                if (price <= 400) {
                    continue;
                }
            }


            // ==================================================
            // AVAILABLE
            // ==================================================

            // Currently all sample items
            // are considered available.


            // ==================================================
            // ADD ITEM
            // ==================================================

            filteredItems.add(item);

        }


        // ==================================================
        // UPDATE RECYCLER VIEW
        // ==================================================

        searchAdapter.notifyDataSetChanged();

    }


    // ==================================================
    // GET PRICE
    // ==================================================

    private int getPrice(String priceText) {

        try {

            String number =
                    priceText
                            .replace("₹", "")
                            .replace("/day", "")
                            .trim();

            return Integer.parseInt(number);

        } catch (Exception e) {

            return 0;

        }

    }


    // ==================================================
    // DARK MODE
    // ==================================================

    private void applyDarkMode() {


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


        // ==================================================
        // HEADER
        // ==================================================

        toolbar.setBackgroundColor(
                Color.rgb(108, 74, 182)
        );

        toolbar.setTitleTextColor(
                Color.WHITE
        );


        // ==================================================
        // STATUS BAR
        // ==================================================

        getWindow().setStatusBarColor(Color.rgb(108, 74, 182));

        getWindow().setNavigationBarColor(Color.rgb(248, 249, 250));

        getWindow()
                .getDecorView()
                .setSystemUiVisibility(0);


        // ==================================================
        // APPLY BUTTON
        // ==================================================

        applyFilterButton.setBackgroundTintList(
                ColorStateList.valueOf(PURPLE)
        );

        applyFilterButton.setTextColor(
                Color.WHITE
        );


        // ==================================================
        // DARK MODE
        // ==================================================

        if (darkMode) {


            // MAIN

            searchFilterLayout.setBackgroundColor(
                    Color.BLACK
            );


            // SEARCH

            searchCard.setCardBackgroundColor(
                    Color.rgb(43, 41, 50)
            );

            searchEditText.setTextColor(
                    Color.WHITE
            );

            searchEditText.setHintTextColor(
                    Color.LTGRAY
            );


            // FILTER

            filterCard.setCardBackgroundColor(
                    Color.rgb(30, 30, 30)
            );

            filterTitle.setTextColor(
                    Color.WHITE
            );

            filterArrow.setTextColor(
                    Color.WHITE
            );

            priceFilter.setTextColor(
                    Color.WHITE
            );

            locationFilter.setTextColor(
                    Color.WHITE
            );

            categoryFilter.setTextColor(
                    Color.WHITE
            );

            availableSwitch.setTextColor(
                    Color.WHITE
            );


        } else {


            // MAIN

            searchFilterLayout.setBackgroundColor(
                    Color.rgb(248, 249, 250)
            );


            // SEARCH

            searchCard.setCardBackgroundColor(
                    Color.WHITE
            );

            searchEditText.setTextColor(
                    Color.BLACK
            );

            searchEditText.setHintTextColor(
                    Color.GRAY
            );


            // FILTER

            filterCard.setCardBackgroundColor(
                    Color.WHITE
            );

            filterTitle.setTextColor(
                    Color.BLACK
            );

            filterArrow.setTextColor(
                    Color.BLACK
            );

            priceFilter.setTextColor(
                    Color.BLACK
            );

            locationFilter.setTextColor(
                    Color.BLACK
            );

            categoryFilter.setTextColor(
                    Color.BLACK
            );

            availableSwitch.setTextColor(
                    Color.BLACK
            );

        }

    }


    // ==================================================
    // ON RESUME
    // ==================================================

    @Override
    protected void onResume() {

        super.onResume();


        if (searchFilterLayout != null) {

            applyDarkMode();

        }

    }

}