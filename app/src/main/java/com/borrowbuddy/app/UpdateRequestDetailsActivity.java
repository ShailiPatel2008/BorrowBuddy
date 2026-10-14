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
import android.graphics.drawable.ColorDrawable;
import android.app.Dialog;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
public class UpdateRequestDetailsActivity extends AppCompatActivity {

    LinearLayout mainLayout;
    LinearLayout bottomActionBar;

    ImageView btnBack;
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

    String requestId;

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
        imgItem.setOnClickListener(v ->
                showFullScreenImage(imgItem)
        );

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

        requestId = getIntent().getStringExtra("requestId");

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

        FirebaseFirestore.getInstance()
                .collection("itemUpdateRequests")
                .document(requestId)
                .get()
                .addOnSuccessListener(document -> {

                    if (!document.exists()) return;

                    txtItemName.setText("Item Name: " +
                            document.getString("itemName"));

                    txtOwner.setText("Owner: " +
                            document.getString("ownerName"));

                    txtCategory.setText("Category: " +
                            document.getString("category"));

                    txtDescription.setText("Description: " +
                            document.getString("description"));

                    txtPrice.setText("Price: ₹" +
                            document.getLong("pricePerDay") + "/day");

                    txtQuantity.setText("Quantity: " +
                            document.getLong("quantity"));

                    txtCondition.setText("Condition: " +
                            document.getString("condition"));

                    txtDeposit.setText("Security Deposit: ₹" +
                            document.getLong("securitydeposit"));

                    txtPickup.setText("Pickup Location: " +
                            document.getString("pickuplocation"));

                });

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

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {

            FirebaseFirestore db = FirebaseFirestore.getInstance();

            String requestId =
                    getIntent().getStringExtra("requestId");

            db.collection("itemUpdateRequests")
                    .document(requestId)
                    .get()
                    .addOnSuccessListener(request -> {

                        if (!request.exists()) {

                            Toast.makeText(
                                    UpdateRequestDetailsActivity.this,
                                    "Request not found",
                                    Toast.LENGTH_SHORT
                            ).show();

                            return;
                        }

                        String itemId =
                                request.getString("itemId");

                        HashMap<String, Object> item = new HashMap<>();

                        item.put("itemName",
                                request.getString("itemName"));

                        item.put("description",
                                request.getString("description"));

                        item.put("category",
                                request.getString("category"));

                        item.put("pricePerDay",
                                request.getLong("pricePerDay"));

                        item.put("securitydeposit",
                                request.getLong("securitydeposit"));

                        item.put("pickuplocation",
                                request.getString("pickuplocation"));

                        item.put("contactnumber",
                                request.getString("contactnumber"));

                        item.put("quantity",
                                request.getLong("quantity"));

                        item.put("condition",
                                request.getString("condition"));

                        db.collection("items")
                                .document(itemId)
                                .update(item)
                                .addOnSuccessListener(unused -> {

                                    db.collection("itemUpdateRequests")
                                            .document(requestId)
                                            .update("status", "approved")
                                            .addOnSuccessListener(aVoid -> {

                                                Toast.makeText(
                                                        UpdateRequestDetailsActivity.this,
                                                        "Update Approved",
                                                        Toast.LENGTH_SHORT
                                                ).show();

                                                setResult(RESULT_OK);
                                                finish();

                                            });

                                })
                                .addOnFailureListener(e ->

                                        Toast.makeText(
                                                UpdateRequestDetailsActivity.this,
                                                e.getMessage(),
                                                Toast.LENGTH_LONG
                                        ).show());

                    })
                    .addOnFailureListener(e ->

                            Toast.makeText(
                                    UpdateRequestDetailsActivity.this,
                                    e.getMessage(),
                                    Toast.LENGTH_LONG
                            ).show());

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

            styleUpdateDialog(dialog);

            boolean darkMode = getSharedPreferences(
                    "Settings",
                    MODE_PRIVATE
            ).getBoolean(
                    "dark_mode",
                    false
            );

            if (darkMode) {

                input.setHintTextColor(Color.WHITE);
                input.setTextColor(Color.WHITE);

            } else {

                input.setHintTextColor(Color.GRAY);
                input.setTextColor(Color.BLACK);
            }

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

    private void styleUpdateDialog(AlertDialog dialog) {

        boolean darkMode = getSharedPreferences(
                "Settings",
                MODE_PRIVATE
        ).getBoolean(
                "dark_mode",
                false
        );

        // Dialog background
        if (dialog.getWindow() != null) {

            if (darkMode) {

                dialog.getWindow().setBackgroundDrawable(
                        new ColorDrawable(
                                Color.rgb(55, 55, 55)
                        )
                );

            } else {

                dialog.getWindow().setBackgroundDrawable(
                        new ColorDrawable(
                                Color.WHITE
                        )
                );
            }
        }

        // Title
        int titleId = getResources().getIdentifier(
                "alertTitle",
                "id",
                "android"
        );

        TextView titleText =
                dialog.findViewById(titleId);

        if (titleText != null) {

            if (darkMode) {
                titleText.setTextColor(Color.WHITE);
            } else {
                titleText.setTextColor(Color.BLACK);
            }
        }

        // Message
        TextView messageText =
                dialog.findViewById(android.R.id.message);

        if (messageText != null) {

            if (darkMode) {
                messageText.setTextColor(Color.WHITE);
            } else {
                messageText.setTextColor(Color.BLACK);
            }
        }

        // CANCEL button
        if (dialog.getButton(
                AlertDialog.BUTTON_NEGATIVE
        ) != null) {

            if (darkMode) {

                dialog.getButton(
                        AlertDialog.BUTTON_NEGATIVE
                ).setTextColor(Color.WHITE);

            } else {

                dialog.getButton(
                        AlertDialog.BUTTON_NEGATIVE
                ).setTextColor(
                        Color.rgb(108, 74, 182)
                );
            }
        }

        // APPROVE / REJECT button
        if (dialog.getButton(
                AlertDialog.BUTTON_POSITIVE
        ) != null) {

            if (darkMode) {

                dialog.getButton(
                        AlertDialog.BUTTON_POSITIVE
                ).setTextColor(Color.WHITE);

            } else {

                dialog.getButton(
                        AlertDialog.BUTTON_POSITIVE
                ).setTextColor(
                        Color.rgb(108, 74, 182)
                );
            }
        }
    }

    // =====================================================
// FULLSCREEN IMAGE
// =====================================================

    private void showFullScreenImage(ImageView sourceImage) {

        Dialog dialog = new Dialog(this);

        dialog.requestWindowFeature(
                Window.FEATURE_NO_TITLE
        );

        ImageView fullImage = new ImageView(this);

        fullImage.setBackgroundColor(
                Color.BLACK
        );

        fullImage.setScaleType(
                ImageView.ScaleType.FIT_CENTER
        );

        fullImage.setImageDrawable(
                sourceImage.getDrawable()
        );

        fullImage.setOnClickListener(v ->
                dialog.dismiss()
        );

        dialog.setContentView(fullImage);

        dialog.show();

        Window window = dialog.getWindow();

        if (window != null) {

            window.setBackgroundDrawableResource(
                    android.R.color.black
            );

            window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            );

            window.setLayout(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT
            );
        }
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
        btnBack.setColorFilter(Color.WHITE);
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