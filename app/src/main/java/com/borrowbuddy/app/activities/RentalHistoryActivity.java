package com.borrowbuddy.app.activities;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;
import com.borrowbuddy.app.R;
import com.borrowbuddy.app.adapters.RentalHistoryAdapter;
import com.borrowbuddy.app.models.RentalHistoryModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class RentalHistoryActivity extends AppCompatActivity {

    RecyclerView recyclerRentalHistory;
    LinearLayout layoutEmpty;

    TextView txtTotalRentals;
    TextView txtCompletedRentals;

    RentalHistoryAdapter adapter;
    List<RentalHistoryModel> historyList;

    FirebaseFirestore db;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_history);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Rental History");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setTitleTextColor(Color.WHITE);

        Drawable upArrow = toolbar.getNavigationIcon();
        if (upArrow != null) {
            upArrow.setTint(Color.WHITE);
        }

        recyclerRentalHistory = findViewById(R.id.recyclerRentalHistory);
        layoutEmpty = findViewById(R.id.layoutEmpty);
        txtTotalRentals = findViewById(R.id.txtTotalRentals);
        txtCompletedRentals = findViewById(R.id.txtCompletedRentals);

        recyclerRentalHistory.setLayoutManager(
                new LinearLayoutManager(this)
        );

        historyList = new ArrayList<>();

        adapter = new RentalHistoryAdapter(
                this,
                historyList
        );

        recyclerRentalHistory.setAdapter(adapter);

        loadRentalHistory();
    }

    private void loadRentalHistory() {

        if (auth.getCurrentUser() == null) {
            System.out.println("No logged in user");
            return;
        }

        String uid = auth.getCurrentUser().getUid();

        System.out.println("Current UID = " + uid);

        db.collection("Bookings")
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    Toast.makeText(this,
                            "Documents = " + querySnapshot.size(),
                            Toast.LENGTH_LONG).show();
                    System.out.println("Total documents = " + querySnapshot.size());

                    historyList.clear();


                    for (QueryDocumentSnapshot document : querySnapshot) {
                        System.out.println("Document ID = " + document.getId());
                        System.out.println(document.getData());
                        String itemName = document.getString("itemName");

                        String ownerName = document.getString("ownerName");

                        String startDate = document.getString("startDate");

                        String endDate = document.getString("endDate");


                        Long total = document.getLong("totalAmount");

                        String amount = total == null ? "0" : String.valueOf(total);


                        String status = document.getString("status");

                        if(status == null)
                            status = "Booked";


                        if(ownerName == null)
                            ownerName = "Unknown Owner";


                        historyList.add(
                                new RentalHistoryModel(
                                        itemName,
                                        ownerName,
                                        startDate + " - " + endDate,
                                        amount,
                                        status,
                                        0f,
                                        ""
                                )
                        );
                        System.out.println("Added: " + itemName);
                    }
                    adapter.notifyDataSetChanged();
                    txtTotalRentals.setText("Total Rentals: " + historyList.size());

                    if(historyList.isEmpty()){
                        layoutEmpty.setVisibility(View.VISIBLE);
                        recyclerRentalHistory.setVisibility(View.GONE);
                    }else{
                        layoutEmpty.setVisibility(View.GONE);
                        recyclerRentalHistory.setVisibility(View.VISIBLE);
                    }
                });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}