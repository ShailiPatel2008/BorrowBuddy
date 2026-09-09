package com.borrowbuddy.app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ItemUpdateRequestsActivity extends AppCompatActivity {

    LinearLayout mainLayout;

    LinearLayout requestCard1;
    LinearLayout requestCard2;

    TextView btnBack;
    TextView txtPageTitle;
    TextView txtPendingRequests;

    TextView txtItemName1;
    TextView txtOwner1;
    TextView txtChanges1;
    TextView txtDate1;
    TextView btnViewRequest1;

    TextView txtItemName2;
    TextView txtOwner2;
    TextView txtChanges2;
    TextView txtDate2;
    TextView btnViewRequest2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_item_update_requests
        );


        // =========================
        // FIND VIEWS
        // =========================

        mainLayout = findViewById(
                R.id.mainLayout
        );

        requestCard1 = findViewById(
                R.id.requestCard1
        );

        requestCard2 = findViewById(
                R.id.requestCard2
        );

        btnBack = findViewById(
                R.id.btnBack
        );

        txtPageTitle = findViewById(
                R.id.txtPageTitle
        );

        txtPendingRequests = findViewById(
                R.id.txtPendingRequests
        );


        // CAMERA

        txtItemName1 = findViewById(
                R.id.txtItemName1
        );

        txtOwner1 = findViewById(
                R.id.txtOwner1
        );

        txtChanges1 = findViewById(
                R.id.txtChanges1
        );

        txtDate1 = findViewById(
                R.id.txtDate1
        );

        btnViewRequest1 = findViewById(
                R.id.btnViewRequest1
        );


        // BICYCLE

        txtItemName2 = findViewById(
                R.id.txtItemName2
        );

        txtOwner2 = findViewById(
                R.id.txtOwner2
        );

        txtChanges2 = findViewById(
                R.id.txtChanges2
        );

        txtDate2 = findViewById(
                R.id.txtDate2
        );

        btnViewRequest2 = findViewById(
                R.id.btnViewRequest2
        );


        // =========================
        // BACK
        // =========================

        btnBack.setOnClickListener(
                v -> finish()
        );


        // =====================================================
        // CAMERA VIEW REQUEST
        // =====================================================

        btnViewRequest1.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ItemUpdateRequestsActivity.this,
                    UpdateRequestDetailsActivity.class
            );

            intent.putExtra(
                    "requestId",
                    1
            );

            intent.putExtra(
                    "itemName",
                    "DSLR Camera"
            );

            intent.putExtra(
                    "owner",
                    "Amit Patel"
            );

            intent.putExtra(
                    "category",
                    "Electronics"
            );

            intent.putExtra(
                    "description",
                    "Professional DSLR camera"
            );

            intent.putExtra(
                    "price",
                    "₹500/day → ₹700/day"
            );

            intent.putExtra(
                    "quantity",
                    "2 → 3"
            );

            intent.putExtra(
                    "condition",
                    "Good → New"
            );

            intent.putExtra(
                    "deposit",
                    "₹2,000"
            );

            intent.putExtra(
                    "pickup",
                    "Hazira, Surat"
            );

            startActivityForResult(
                    intent,
                    1
            );
        });


        // =====================================================
        // BICYCLE VIEW REQUEST
        // =====================================================

        btnViewRequest2.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ItemUpdateRequestsActivity.this,
                    UpdateRequestDetailsActivity.class
            );

            intent.putExtra(
                    "requestId",
                    2
            );

            intent.putExtra(
                    "itemName",
                    "Bicycle"
            );

            intent.putExtra(
                    "owner",
                    "Rahul"
            );

            intent.putExtra(
                    "category",
                    "Sports"
            );

            intent.putExtra(
                    "description",
                    "Mountain bicycle for daily use"
            );

            intent.putExtra(
                    "price",
                    "₹300/day → ₹400/day"
            );

            intent.putExtra(
                    "quantity",
                    "1 → 2"
            );

            intent.putExtra(
                    "condition",
                    "Good → Excellent"
            );

            intent.putExtra(
                    "deposit",
                    "₹1,000"
            );

            intent.putExtra(
                    "pickup",
                    "Hazira, Surat"
            );

            startActivityForResult(
                    intent,
                    2
            );
        });


        applyDarkMode();
    }


    // =====================================================
    // RESUME
    // =====================================================

    @Override
    protected void onResume() {
        super.onResume();

        applyDarkMode();
    }


    // =====================================================
    // DARK MODE
    // =====================================================

    private void applyDarkMode() {

        boolean darkMode =
                getSharedPreferences(
                        "Settings",
                        MODE_PRIVATE
                ).getBoolean(
                        "dark_mode",
                        false
                );


        if (darkMode) {

            mainLayout.setBackgroundColor(
                    Color.rgb(16, 16, 16)
            );

            requestCard1.setBackgroundColor(
                    Color.rgb(35, 35, 35)
            );

            requestCard2.setBackgroundColor(
                    Color.rgb(35, 35, 35)
            );

            txtPendingRequests.setTextColor(
                    Color.WHITE
            );

            txtItemName1.setTextColor(
                    Color.WHITE
            );

            txtOwner1.setTextColor(
                    Color.WHITE
            );

            txtChanges1.setTextColor(
                    Color.WHITE
            );

            txtDate1.setTextColor(
                    Color.WHITE
            );

            txtItemName2.setTextColor(
                    Color.WHITE
            );

            txtOwner2.setTextColor(
                    Color.WHITE
            );

            txtChanges2.setTextColor(
                    Color.WHITE
            );

            txtDate2.setTextColor(
                    Color.WHITE
            );

        } else {

            mainLayout.setBackgroundColor(
                    Color.rgb(246, 243, 248)
            );

            requestCard1.setBackgroundColor(
                    Color.WHITE
            );

            requestCard2.setBackgroundColor(
                    Color.WHITE
            );

            txtPendingRequests.setTextColor(
                    Color.BLACK
            );

            txtItemName1.setTextColor(
                    Color.BLACK
            );

            txtOwner1.setTextColor(
                    Color.BLACK
            );

            txtChanges1.setTextColor(
                    Color.BLACK
            );

            txtDate1.setTextColor(
                    Color.BLACK
            );

            txtItemName2.setTextColor(
                    Color.BLACK
            );

            txtOwner2.setTextColor(
                    Color.BLACK
            );

            txtChanges2.setTextColor(
                    Color.BLACK
            );

            txtDate2.setTextColor(
                    Color.BLACK
            );
        }


        txtPageTitle.setTextColor(
                Color.WHITE
        );

        btnBack.setTextColor(
                Color.WHITE
        );
    }


    // =====================================================
    // AFTER APPROVE / REJECT
    // =====================================================

    @Override
    protected void onActivityResult(
            int requestCode,
            int resultCode,
            Intent data
    ) {

        super.onActivityResult(
                requestCode,
                resultCode,
                data
        );


        if (resultCode == RESULT_OK) {

            if (requestCode == 1) {

                // Camera card remove
                requestCard1.setVisibility(
                        View.GONE
                );

            } else if (requestCode == 2) {

                // Bicycle card remove
                requestCard2.setVisibility(
                        View.GONE
                );
            }


            updatePendingCount();
        }
    }


    // =====================================================
    // UPDATE COUNT
    // =====================================================

    private void updatePendingCount() {

        int count = 0;


        if (requestCard1.getVisibility()
                == View.VISIBLE) {

            count++;
        }


        if (requestCard2.getVisibility()
                == View.VISIBLE) {

            count++;
        }


        txtPendingRequests.setText(
                "Pending Requests: " + count
        );
    }
}