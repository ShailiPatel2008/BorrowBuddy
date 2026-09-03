package com.borrowbuddy.app.activities;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.borrowbuddy.app.R;
import com.borrowbuddy.app.adapters.RequestStatusAdapter;
import com.borrowbuddy.app.models.RequestModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class RentalRequestsActivity extends AppCompatActivity {

    private RecyclerView recyclerRentalRequests;

    private RequestStatusAdapter adapter;

    private List<RequestModel> requestList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rental_requests);

        // Toolbar
        MaterialToolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Rental Requests");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIconTint(Color.WHITE);

        // RecyclerView
        recyclerRentalRequests =
                findViewById(R.id.recyclerRentalRequests);

        recyclerRentalRequests.setLayoutManager(
                new LinearLayoutManager(this)
        );

        // Request list
        requestList = new ArrayList<>();

        // Temporary demo request 1
        requestList.add(
                new RequestModel(
                        "DSLR Camera",
                        "Rahul",
                        "9876543210",
                        "3 days",
                        "1000",
                        2,
                        "Pending",
                        "Available",
                        Arrays.asList(
                                "image1",
                                "image2",
                                "image3",
                                "image4"
                        )
                )
        );

// Temporary demo request 2
        requestList.add(
                new RequestModel(
                        "Bicycle",
                        "Amit",
                        "9123456780",
                        "5 days",
                        "1500",
                        1,
                        "Pending",
                        "Available",
                        Arrays.asList(
                                "image1",
                                "image2",
                                "image3"
                        )
                )
        );

        // Adapter
        adapter = new RequestStatusAdapter(
                this,
                requestList
        );

        recyclerRentalRequests.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}