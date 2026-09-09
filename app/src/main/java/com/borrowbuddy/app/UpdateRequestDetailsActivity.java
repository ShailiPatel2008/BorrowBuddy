package com.borrowbuddy.app;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateRequestDetailsActivity extends AppCompatActivity {

    LinearLayout mainLayout;
    LinearLayout bottomActionBar;

    TextView btnBack;
    TextView txtToolbarTitle;

    ImageView imgItem;

    TextView txtItemName;
    TextView txtOwner;
    TextView txtCategory;
    TextView txtDescription;
    TextView txtPrice;
    TextView txtQuantity;
    TextView txtCondition;
    TextView txtDeposit;
    TextView txtPickup;

    TextView btnReject;
    TextView btnApprove;

    int requestId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_update_request_details
        );

        mainLayout = findViewById(R.id.mainLayout);
        bottomActionBar = findViewById(R.id.bottomActionBar);

        btnBack = findViewById(R.id.btnBack);
        txtToolbarTitle = findViewById(R.id.txtToolbarTitle);

        imgItem = findViewById(R.id.imgItem);

        txtItemName = findViewById(R.id.txtItemName);
        txtOwner = findViewById(R.id.txtOwner);
        txtCategory = findViewById(R.id.txtCategory);
        txtDescription = findViewById(R.id.txtDescription);
        txtPrice = findViewById(R.id.txtPrice);
        txtQuantity = findViewById(R.id.txtQuantity);
        txtCondition = findViewById(R.id.txtCondition);
        txtDeposit = findViewById(R.id.txtDeposit);
        txtPickup = findViewById(R.id.txtPickup);

        btnReject = findViewById(R.id.btnReject);
        btnApprove = findViewById(R.id.btnApprove);

        requestId = getIntent().getIntExtra(
                "requestId",
                1
        );

        loadRequestData();

        btnBack.setOnClickListener(v -> finish());

        btnApprove.setOnClickListener(
                v -> showApproveDialog()
        );

        btnReject.setOnClickListener(
                v -> showRejectDialog()
        );

        applyDarkMode();
    }

    @Override
    protected void onResume() {
        super.onResume();
        applyDarkMode();
    }

    private void loadRequestData() {

        if (requestId == 1) {

            txtItemName.setText("Item Name: DSLR Camera");
            txtOwner.setText("Owner: Amit Patel");
            txtCategory.setText("Category: Electronics");
            txtDescription.setText(
                    "Description: Professional DSLR camera"
            );
            txtPrice.setText(
                    "Price: ₹500/day → ₹700/day"
            );
            txtQuantity.setText(
                    "Quantity: 2 → 3"
            );
            txtCondition.setText(
                    "Condition: Good → New"
            );
            txtDeposit.setText(
                    "Security Deposit: ₹2,000"
            );
            txtPickup.setText(
                    "Pickup Location: Hazira, Surat"
            );

        } else {

            txtItemName.setText("Item Name: Bicycle");
            txtOwner.setText("Owner: Rahul");
            txtCategory.setText("Category: Sports");
            txtDescription.setText(
                    "Description: Mountain bicycle for daily use"
            );
            txtPrice.setText(
                    "Price: ₹300/day → ₹400/day"
            );
            txtQuantity.setText(
                    "Quantity: 1 → 2"
            );
            txtCondition.setText(
                    "Condition: Good → Excellent"
            );
            txtDeposit.setText(
                    "Security Deposit: ₹1,000"
            );
            txtPickup.setText(
                    "Pickup Location: Hazira, Surat"
            );
        }
    }

    private void showApproveDialog() {

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Approve Update?")
                .setMessage(
                        "Are you sure you want to approve this update?"
                )
                .setNegativeButton(
                        "CANCEL",
                        null
                )
                .setPositiveButton(
                        "APPROVE",
                        null
                )
                .create();

        dialog.setOnShowListener(d -> {

            dialog.getButton(
                    AlertDialog.BUTTON_POSITIVE
            ).setTextColor(Color.WHITE);

            dialog.getButton(
                    AlertDialog.BUTTON_NEGATIVE
            ).setTextColor(Color.WHITE);

            dialog.getButton(
                    AlertDialog.BUTTON_POSITIVE
            ).setOnClickListener(v -> {

                Toast.makeText(
                        UpdateRequestDetailsActivity.this,
                        "Update approved successfully",
                        Toast.LENGTH_SHORT
                ).show();

                Intent result = new Intent();

                result.putExtra(
                        "requestId",
                        requestId
                );

                setResult(
                        RESULT_OK,
                        result
                );

                dialog.dismiss();
                finish();
            });
        });

        dialog.show();
    }

    private void showRejectDialog() {

        final android.widget.EditText input =
                new android.widget.EditText(this);

        input.setHint(
                "Enter rejection reason..."
        );

        input.setSingleLine(false);

        int padding = 25;

        input.setPadding(
                padding,
                padding,
                padding,
                padding
        );

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Reason for rejection:")
                .setView(input)
                .setNegativeButton(
                        "CANCEL",
                        null
                )
                .setPositiveButton(
                        "REJECT",
                        null
                )
                .create();

        dialog.setOnShowListener(d -> {

            dialog.getButton(
                    AlertDialog.BUTTON_POSITIVE
            ).setTextColor(Color.WHITE);

            dialog.getButton(
                    AlertDialog.BUTTON_NEGATIVE
            ).setTextColor(Color.WHITE);

            dialog.getButton(
                    AlertDialog.BUTTON_POSITIVE
            ).setOnClickListener(v -> {

                String reason =
                        input.getText()
                                .toString()
                                .trim();

                if (reason.isEmpty()) {

                    Toast.makeText(
                            UpdateRequestDetailsActivity.this,
                            "Please enter rejection reason.",
                            Toast.LENGTH_SHORT
                    ).show();

                    return;
                }

                Toast.makeText(
                        UpdateRequestDetailsActivity.this,
                        "Update rejected successfully",
                        Toast.LENGTH_SHORT
                ).show();

                Intent result = new Intent();

                result.putExtra(
                        "requestId",
                        requestId
                );

                result.putExtra(
                        "rejectionReason",
                        reason
                );

                setResult(
                        RESULT_OK,
                        result
                );

                dialog.dismiss();
                finish();
            });
        });

        dialog.show();
    }

    private void applyDarkMode() {

        boolean darkMode = getSharedPreferences(
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

            bottomActionBar.setBackgroundColor(
                    Color.BLACK
            );

            setAllTextColor(Color.WHITE);

        } else {

            mainLayout.setBackgroundColor(
                    Color.rgb(246, 243, 248)
            );

            bottomActionBar.setBackgroundColor(
                    Color.WHITE
            );

            setAllTextColor(Color.BLACK);
        }

        txtToolbarTitle.setTextColor(Color.WHITE);
        btnBack.setTextColor(Color.WHITE);
    }

    private void setAllTextColor(int color) {

        txtItemName.setTextColor(color);
        txtOwner.setTextColor(color);
        txtCategory.setTextColor(color);
        txtDescription.setTextColor(color);
        txtPrice.setTextColor(color);
        txtQuantity.setTextColor(color);
        txtCondition.setTextColor(color);
        txtDeposit.setTextColor(color);
        txtPickup.setTextColor(color);
    }
}