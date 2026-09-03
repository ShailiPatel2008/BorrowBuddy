package com.borrowbuddy.app.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.borrowbuddy.app.R;
import com.borrowbuddy.app.adapters.MyListingsAdapter;
import com.borrowbuddy.app.models.ListingModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MyListingsActivity extends AppCompatActivity {

    private RecyclerView rvListings;

    private FloatingActionButton fabAddItem;

    private MaterialToolbar toolbar;

    private MyListingsAdapter adapter;

    private List<ListingModel> listingList;

    private LinearLayout layoutEmptyListings;


    @Override
    protected void onCreate(
            Bundle savedInstanceState
    ) {

        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_my_listings
        );


        // =========================
        // INITIALIZE VIEWS
        // =========================

        toolbar =
                findViewById(R.id.toolbar);

        rvListings =
                findViewById(R.id.rvListings);

        fabAddItem =
                findViewById(R.id.fabAddItem);

        layoutEmptyListings =
                findViewById(
                        R.id.layoutEmptyListings
                );


        // =========================
        // TOOLBAR
        // =========================

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {

            getSupportActionBar()
                    .setTitle("My Listings");

            getSupportActionBar()
                    .setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setTitleTextColor(
                Color.WHITE
        );

        if (toolbar.getNavigationIcon()
                != null) {

            toolbar.getNavigationIcon()
                    .setTint(Color.WHITE);
        }

        toolbar.setNavigationOnClickListener(
                v -> finish()
        );


        // =========================
        // RECYCLER VIEW
        // =========================

        rvListings.setLayoutManager(
                new LinearLayoutManager(this)
        );

        rvListings.setHasFixedSize(false);


        // =========================
        // LIST
        // =========================

        listingList =
                new ArrayList<>();


        // =========================
        // DEMO LISTING 1
        // =========================

        listingList.add(
                new ListingModel(
                        "Drill Machine",
                        "Tools",
                        "Heavy-duty electric drill for home and construction work.",
                        "150",
                        "Adajan, Surat",
                        "9876543210",
                        "",
                        "Available",
                        3
                )
        );


        // =========================
        // DEMO LISTING 2
        // =========================

        listingList.add(
                new ListingModel(
                        "DSLR Camera",
                        "Electronics",
                        "Canon DSLR camera with lens and charger.",
                        "500",
                        "Vesu, Surat",
                        "9876543210",
                        "",
                        "Rented",
                        1
                )
        );


        // =========================
        // ADAPTER
        // =========================

        adapter =
                new MyListingsAdapter(
                        this,
                        listingList
                );

        rvListings.setAdapter(
                adapter
        );


        // =========================
        // EMPTY STATE
        // =========================

        updateEmptyState();


        // =========================
        // FLOATING ADD BUTTON
        // =========================

        fabAddItem.setOnClickListener(
                v -> {

                    Intent intent =
                            new Intent(
                                    MyListingsActivity.this,
                                    AddItemActivity.class
                            );

                    startActivity(intent);
                }
        );


        // =========================
        // EMPTY SCREEN ADD BUTTON
        // =========================

        findViewById(
                R.id.btnEmptyAddItem
        ).setOnClickListener(
                v -> {

                    Intent intent =
                            new Intent(
                                    MyListingsActivity.this,
                                    AddItemActivity.class
                            );

                    startActivity(intent);
                }
        );
    }


    // =========================
    // EMPTY STATE
    // =========================

    public void updateEmptyState() {

        if (listingList.isEmpty()) {

            rvListings.setVisibility(
                    View.GONE
            );

            layoutEmptyListings.setVisibility(
                    View.VISIBLE
            );

        } else {

            rvListings.setVisibility(
                    View.VISIBLE
            );

            layoutEmptyListings.setVisibility(
                    View.GONE
            );
        }
    }


    // =========================
    // BACK BUTTON
    // =========================

    @Override
    public boolean onSupportNavigateUp() {

        finish();

        return true;
    }
}