package com.borrowbuddy.app.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.borrowbuddy.app.R;
import com.borrowbuddy.app.adapters.RequestAdapter;
import com.borrowbuddy.app.models.MyItemRequestModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import android.widget.TextView;

public class MyItemRequestsActivity extends AppCompatActivity {

    private RecyclerView recyclerRequests;

    private RequestAdapter adapter;
    private List<MyItemRequestModel> requestList;

    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_item_requests);

        toolbar = findViewById(R.id.toolbar);
        recyclerRequests = findViewById(R.id.recyclerRequests);

        setSupportActionBar(toolbar);

        // Back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("My Item Request");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(v -> finish());


        recyclerRequests.setLayoutManager(new LinearLayoutManager(this));

        requestList = new ArrayList<>();

        // Dummy Data (will be replaced by Firebase later)

        requestList.add(new MyItemRequestModel(
                "📷 DSLR Camera",
                "Electronics",
                "500",
                "Pending",
                ""
        ));

        requestList.add(new MyItemRequestModel(
                "📚 Engineering Books",
                "Books",
                "50",
                "Approved",
                ""
        ));

        requestList.add(new MyItemRequestModel(
                "🎮 Gaming Console",
                "Gaming",
                "300",
                "Rejected",
                "Image quality not clear"
        ));

        adapter = new RequestAdapter(requestList);
        recyclerRequests.setAdapter(adapter);
    }
}