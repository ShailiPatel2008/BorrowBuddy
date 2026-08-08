package com.borrowbuddy.app.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.borrowbuddy.app.R;
import com.borrowbuddy.app.adapters.RentalHistoryAdapter;
import com.borrowbuddy.app.models.RentalHistoryModel;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RentalHistoryActivity extends AppCompatActivity {

    RecyclerView recyclerRentalHistory;

    LinearLayout layoutEmpty;

    TextView txtTotalRentals;
    TextView txtCompletedRentals;

    RentalHistoryAdapter adapter;

    List<RentalHistoryModel> historyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_history);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Rental History");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerRentalHistory = findViewById(R.id.recyclerRentalHistory);

        layoutEmpty = findViewById(R.id.layoutEmpty);

        txtTotalRentals = findViewById(R.id.txtTotalRentals);

        txtCompletedRentals = findViewById(R.id.txtCompletedRentals);

        recyclerRentalHistory.setLayoutManager(
                new LinearLayoutManager(this)
        );

        historyList = new ArrayList<>();

        // Demo Data (Firebase later)

        historyList.add(
                new RentalHistoryModel(
                        "DSLR Camera",
                        "Shaili Patel",
                        "05 Aug - 10 Aug",
                        "2500",
                        "Completed",
                        5.0f,
                        ""
                )
        );

        historyList.add(
                new RentalHistoryModel(
                        "Bicycle",
                        "Amit Kumar",
                        "15 Jul - 18 Jul",
                        "800",
                        "Completed",
                        4.0f,
                        ""
                )
        );

        adapter = new RentalHistoryAdapter(
                this,
                historyList
        );

        txtTotalRentals.setText(
                "Total Rentals: " + historyList.size()
        );


        int completedCount = 0;

        for(RentalHistoryModel item : historyList){

            if(item.getStatus().equalsIgnoreCase("Completed")){
                completedCount++;
            }

        }


        txtCompletedRentals.setText(
                "Completed: " + completedCount
        );

        if(historyList.isEmpty()){

            recyclerRentalHistory.setVisibility(View.GONE);
            layoutEmpty.setVisibility(View.VISIBLE);

        }
        else{

            recyclerRentalHistory.setVisibility(View.VISIBLE);
            layoutEmpty.setVisibility(View.GONE);

            recyclerRentalHistory.setAdapter(adapter);

        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}