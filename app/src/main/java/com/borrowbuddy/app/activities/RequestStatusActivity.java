package com.borrowbuddy.app.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.borrowbuddy.app.R;
import com.borrowbuddy.app.adapters.RequestStatusAdapter;
import com.borrowbuddy.app.models.RequestModel;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;


public class RequestStatusActivity extends AppCompatActivity {


    RecyclerView recyclerRequests;

    RequestStatusAdapter adapter;

    List<RequestModel> requestList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_request_status);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Rental Requests");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }



        recyclerRequests = findViewById(R.id.recyclerRequests);



        recyclerRequests.setLayoutManager(
                new LinearLayoutManager(this)
        );



        requestList = new ArrayList<>();


        // Temporary Data (Firebase later)

        requestList.add(
                new RequestModel(
                        "DSLR Camera",
                        "Shaili Patel",
                        "9876543210",
                        "05 Aug - 10 Aug",
                        "500",
                        "Pending",
                        ""
                )
        );


        requestList.add(
                new RequestModel(
                        "Bicycle",
                        "Amit Kumar",
                        "9876543211",
                        "03 Aug - 05 Aug",
                        "200",
                        "Approved",
                        ""
                )
        );



        adapter = new RequestStatusAdapter(
                this,
                requestList
        );


        recyclerRequests.setAdapter(adapter);


    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}