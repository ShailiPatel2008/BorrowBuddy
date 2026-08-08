package com.borrowbuddy.app.activities;

import android.os.Bundle;

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

import android.content.Intent;
import android.graphics.Color;
import com.google.android.material.appbar.MaterialToolbar;

public class MyListingsActivity extends AppCompatActivity {

    private RecyclerView rvListings;
    private FloatingActionButton fabAddItem;
    private MaterialToolbar toolbar;

    private MyListingsAdapter adapter;
    private List<ListingModel> listingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_listings);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(Color.WHITE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setTint(Color.WHITE);
        }

        // Initialize Views
        toolbar = findViewById(R.id.toolbar);
        rvListings = findViewById(R.id.rvListings);
        fabAddItem = findViewById(R.id.fabAddItem);

        setSupportActionBar(toolbar);

        // Back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("My Listings");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(v -> finish());

        // RecyclerView
        rvListings.setLayoutManager(new LinearLayoutManager(this));

        listingList = new ArrayList<>();

        // Dummy Data (Frontend Only)
        listingList.add(new ListingModel(
                "Drill Machine",
                "Tools",
                "Heavy-duty electric drill for home and construction work.",
                "150",
                "Adajan, Surat",
                "9876543210",
                "",
                "Available"
        ));

        listingList.add(new ListingModel(
                "DSLR Camera",
                "Electronics",
                "Canon DSLR camera with lens and charger.",
                "500",
                "Vesu, Surat",
                "9876543210",
                "",
                "Rented"
        ));

        adapter = new MyListingsAdapter(this, listingList);
        rvListings.setAdapter(adapter);

        // Floating Button
        fabAddItem.setOnClickListener(v -> {
            Intent intent = new Intent(MyListingsActivity.this, AddItemActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
