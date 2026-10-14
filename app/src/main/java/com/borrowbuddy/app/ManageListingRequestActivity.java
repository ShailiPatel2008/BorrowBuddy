package com.borrowbuddy.app;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageButton;
import android.graphics.drawable.ColorDrawable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.DocumentSnapshot;
import java.util.ArrayList;
public class ManageListingRequestActivity extends AppCompatActivity {

    LinearLayout mainLayout;
    private FirebaseFirestore db;
    LinearLayout requestCard1;
    LinearLayout requestCard2;
    LinearLayout requestCard3;
    private ArrayList<DocumentSnapshot> requestList = new ArrayList<>();
    TextView txtHeader;
    TextView txtPendingCount;

    TextView txtItemName1;
    TextView txtOwner1;
    TextView txtCategory1;
    TextView txtPrice1;
    TextView txtStatus1;

    TextView txtItemName2;
    TextView txtOwner2;
    TextView txtCategory2;
    TextView txtPrice2;
    TextView txtStatus2;

    TextView txtItemName3;
    TextView txtOwner3;
    TextView txtCategory3;
    TextView txtPrice3;
    TextView txtStatus3;

    Button btnViewDetails1;
    Button btnApprove1;
    Button btnReject1;

    Button btnViewDetails2;
    Button btnApprove2;
    Button btnReject2;

    Button btnViewDetails3;
    Button btnApprove3;
    Button btnReject3;

    final int PURPLE = Color.rgb(171, 71, 188);
    final int GREY = Color.rgb(128, 128, 128);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_listing_request);
        db = FirebaseFirestore.getInstance();
        // Main Layout
        mainLayout = findViewById(R.id.mainLayout);

        // Header
        txtHeader = findViewById(R.id.txtHeader);
        txtPendingCount = findViewById(R.id.txtPendingCount);

        // Cards
        requestCard1 = findViewById(R.id.requestCard1);
        requestCard2 = findViewById(R.id.requestCard2);
        requestCard3 = findViewById(R.id.requestCard3);

        // Request 1
        txtItemName1 = findViewById(R.id.txtItemName1);
        txtOwner1 = findViewById(R.id.txtOwner1);
        txtCategory1 = findViewById(R.id.txtCategory1);
        txtPrice1 = findViewById(R.id.txtPrice1);
        txtStatus1 = findViewById(R.id.txtStatus1);

        btnViewDetails1 = findViewById(R.id.btnViewDetails1);
        btnApprove1 = findViewById(R.id.btnApprove1);
        btnReject1 = findViewById(R.id.btnReject1);

        // Request 2
        txtItemName2 = findViewById(R.id.txtItemName2);
        txtOwner2 = findViewById(R.id.txtOwner2);
        txtCategory2 = findViewById(R.id.txtCategory2);
        txtPrice2 = findViewById(R.id.txtPrice2);
        txtStatus2 = findViewById(R.id.txtStatus2);

        btnViewDetails2 = findViewById(R.id.btnViewDetails2);
        btnApprove2 = findViewById(R.id.btnApprove2);
        btnReject2 = findViewById(R.id.btnReject2);

        // Request 3
        txtItemName3 = findViewById(R.id.txtItemName3);
        txtOwner3 = findViewById(R.id.txtOwner3);
        txtCategory3 = findViewById(R.id.txtCategory3);
        txtPrice3 = findViewById(R.id.txtPrice3);
        txtStatus3 = findViewById(R.id.txtStatus3);

        btnViewDetails3 = findViewById(R.id.btnViewDetails3);
        btnApprove3 = findViewById(R.id.btnApprove3);
        btnReject3 = findViewById(R.id.btnReject3);

        // Back
        ImageButton btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> finish());

        // Grey buttons
        setGreyButton(btnViewDetails1);
        setGreyButton(btnApprove1);
        setGreyButton(btnReject1);

        setGreyButton(btnViewDetails2);
        setGreyButton(btnApprove2);
        setGreyButton(btnReject2);

        setGreyButton(btnViewDetails3);
        setGreyButton(btnApprove3);
        setGreyButton(btnReject3);

        // =========================
        // VIEW DETAILS - REQUEST 1
        // =========================

        btnViewDetails1.setOnClickListener(v -> {

            if(requestList.size() > 0){
                openDetails(requestList.get(0));
            }

        });

        // =========================
        // VIEW DETAILS - REQUEST 2
        // =========================
        btnViewDetails2.setOnClickListener(v -> {

            if(requestList.size() > 1){
                openDetails(requestList.get(1));
            }

        });



        // =========================
        // VIEW DETAILS - REQUEST 3
        // =========================

        btnViewDetails3.setOnClickListener(v -> {

            if(requestList.size() > 2){
                openDetails(requestList.get(2));
            }

        });


        // Approve
        btnApprove1.setOnClickListener(v ->
                showApproveDialog(requestCard1)
        );

        btnApprove2.setOnClickListener(v ->
                showApproveDialog(requestCard2)
        );

        btnApprove3.setOnClickListener(v ->
                showApproveDialog(requestCard3)
        );

        // Reject
        btnReject1.setOnClickListener(v ->
                showRejectConfirmDialog(requestCard1)
        );

        btnReject2.setOnClickListener(v ->
                showRejectConfirmDialog(requestCard2)
        );

        btnReject3.setOnClickListener(v ->
                showRejectConfirmDialog(requestCard3)
        );

        applyDarkMode();
        loadListingRequests();
    }

    @Override
    protected void onResume() {
        super.onResume();
        applyDarkMode();
    }

    // =================================================
    // APPROVE
    // =================================================

    private void showApproveDialog(LinearLayout card) {

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Approve Listing?")
                .setMessage("Are you sure you want to approve this item?")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Approve", null)
                .create();

        dialog.setOnShowListener(d -> {

            styleListingDialog(dialog);
            Button approve =
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE);

            Button cancel =
                    dialog.getButton(AlertDialog.BUTTON_NEGATIVE);

            approve.setOnClickListener(v -> {

                int position = 0;

                if (card == requestCard2) {
                    position = 1;
                } else if (card == requestCard3) {
                    position = 2;
                }

                if (position < requestList.size()) {

                    DocumentSnapshot doc = requestList.get(position);

                    db.collection("itemRequests")
                            .document(doc.getId())
                            .update("status", "approved")
                            .addOnSuccessListener(unused -> {

                                loadListingRequests();
                                updatePendingCount();

                                Toast.makeText(
                                        this,
                                        "Listing Approved",
                                        Toast.LENGTH_SHORT
                                ).show();

                                dialog.dismiss();
                            });
                }
            });
        });

        dialog.show();
    }

    // =================================================
    // REJECT CONFIRMATION
    // =================================================

    private void showRejectConfirmDialog(LinearLayout card) {

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Reject Listing?")
                .setMessage("Are you sure you want to reject this item?")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Reject", null)
                .create();

        dialog.setOnShowListener(d -> {

            styleListingDialog(dialog);

            Button reject =
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE);

            Button cancel =
                    dialog.getButton(AlertDialog.BUTTON_NEGATIVE);


            reject.setOnClickListener(v -> {

                dialog.dismiss();

                showReasonDialog(card);
            });
        });

        dialog.show();
    }

    // =================================================
    // REASON
    // =================================================

    private void showReasonDialog(LinearLayout card) {

        EditText input = new EditText(this);

        input.setHint("Enter reason");
        input.setMinLines(2);
        input.setInputType(
                InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_FLAG_MULTI_LINE
        );

        LinearLayout box = new LinearLayout(this);

        box.setOrientation(LinearLayout.VERTICAL);
        box.setPadding(20, 5, 20, 5);

        box.addView(input);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Reason for rejection:")
                .setView(box)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Reject", null)
                .create();

        dialog.setOnShowListener(d -> {

            styleListingDialog(dialog);

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

            Button reject =
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE);

            Button cancel =
                    dialog.getButton(AlertDialog.BUTTON_NEGATIVE);


            reject.setOnClickListener(v -> {

                String reason =
                        input.getText().toString().trim();

                if (reason.isEmpty()) {
                    input.setError("Please enter rejection reason");
                    return;
                }

                int position = 0;

                if (card == requestCard2) {
                    position = 1;
                } else if (card == requestCard3) {
                    position = 2;
                }

                if (position < requestList.size()) {

                    DocumentSnapshot doc = requestList.get(position);

                    db.collection("itemRequests")
                            .document(doc.getId())
                            .update(
                                    "status", "rejected",
                                    "rejectionReason", reason
                            )
                            .addOnSuccessListener(unused -> {

                                loadListingRequests();
                                updatePendingCount();

                                Toast.makeText(
                                        this,
                                        "Listing Rejected",
                                        Toast.LENGTH_SHORT
                                ).show();

                                dialog.dismiss();
                            });
                }
            });
        });

        dialog.show();
    }
    private void loadListingRequests() {

        db.collection("itemRequests")
                .whereEqualTo("status", "pending")
                .limit(3)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    Toast.makeText(
                            this,
                            "Documents = " + queryDocumentSnapshots.size(),
                            Toast.LENGTH_LONG
                    ).show();
                    int index = 0;
                    requestList.clear();
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        requestList.add(document);
                        String itemName =
                                document.getString("itemName");

                        String owner =
                                document.getString("ownerName");

                        String category =
                                document.getString("category");

                        Long price =
                                document.getLong("pricePerDay");

                        if (index == 0) {

                            txtItemName1.setText(itemName);

                            txtOwner1.setText("Owner : " + owner);

                            txtCategory1.setText("Category : " + category);

                            txtPrice1.setText("₹" + price + " / day");

                        }

                        else if (index == 1) {

                            txtItemName2.setText(itemName);

                            txtOwner2.setText("Owner : " + owner);

                            txtCategory2.setText("Category : " + category);

                            txtPrice2.setText("₹" + price + " / day");

                        }

                        else if (index == 2) {

                            txtItemName3.setText(itemName);

                            txtOwner3.setText("Owner : " + owner);

                            txtCategory3.setText("Category : " + category);

                            txtPrice3.setText("₹" + price + " / day");

                        }

                        index++;
                    }

                    txtPendingCount.setText(String.valueOf(index));

                })
                .addOnFailureListener(e ->
                        Toast.makeText(
                                this,
                                e.getMessage(),
                                Toast.LENGTH_SHORT
                        ).show());
    }
    private void openDetails(DocumentSnapshot document){

        Intent intent = new Intent(
                ManageListingRequestActivity.this,
                ListingRequestDetailsActivity.class
        );

        intent.putExtra(
                "itemName",
                document.getString("itemName")
        );

        intent.putExtra(
                "ownerName",
                document.getString("ownerName")
        );

        intent.putExtra(
                "category",
                document.getString("category")
        );

        intent.putExtra(
                "description",
                document.getString("description")
        );

        Long price = document.getLong("pricePerDay");

        intent.putExtra(
                "price",
                price == null ? "" : String.valueOf(price)
        );

        Long deposit = document.getLong("securitydeposit");

        intent.putExtra(
                "deposit",
                deposit == null ? "" : String.valueOf(deposit)
        );

        intent.putExtra(
                "pickup",
                document.getString("pickuplocation")
        );

        intent.putExtra(
                "availableFrom",
                document.getString("availableFrom")
        );

        intent.putExtra(
                "contact",
                document.getString("contactnumber")
        );

        intent.putExtra(
                "condition",
                document.getString("condition")
        );

        startActivity(intent);

    }
    // =================================================
// DIALOG STYLE
// =================================================

    private void styleListingDialog(AlertDialog dialog) {

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
        Button cancel =
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE);

        if (cancel != null) {

            if (darkMode) {

                cancel.setTextColor(Color.WHITE);

            } else {

                cancel.setTextColor(
                        Color.rgb(108, 74, 182)
                );
            }
        }

        // APPROVE / REJECT button
        Button positive =
                dialog.getButton(AlertDialog.BUTTON_POSITIVE);

        if (positive != null) {

            if (darkMode) {

                positive.setTextColor(Color.WHITE);

            } else {

                positive.setTextColor(
                        Color.rgb(108, 74, 182)
                );
            }
        }
    }

    // =================================================
    // COUNT
    // =================================================

    private void updatePendingCount() {

        int count = 0;

        if (requestCard1.getVisibility() == View.VISIBLE) {
            count++;
        }

        if (requestCard2.getVisibility() == View.VISIBLE) {
            count++;
        }

        if (requestCard3.getVisibility() == View.VISIBLE) {
            count++;
        }

        txtPendingCount.setText(String.valueOf(count));
    }

    // =================================================
    // GREY BUTTON
    // =================================================

    private void setGreyButton(Button button) {

        button.setBackgroundTintList(
                ColorStateList.valueOf(GREY)
        );

        button.setTextColor(Color.WHITE);
    }

    // =================================================
    // DARK MODE
    // =================================================

    private void applyDarkMode() {

        boolean darkMode = getSharedPreferences(
                "Settings",
                MODE_PRIVATE
        ).getBoolean("dark_mode", false);

        if (darkMode) {

            mainLayout.setBackgroundColor(
                    Color.rgb(16, 16, 16)
            );

            txtHeader.setTextColor(Color.WHITE);

            setTextWhite(
                    txtItemName1,
                    txtOwner1,
                    txtCategory1,
                    txtPrice1,
                    txtItemName2,
                    txtOwner2,
                    txtCategory2,
                    txtPrice2,
                    txtItemName3,
                    txtOwner3,
                    txtCategory3,
                    txtPrice3
            );

            setCardColor(requestCard1, Color.rgb(32, 32, 32));
            setCardColor(requestCard2, Color.rgb(32, 32, 32));
            setCardColor(requestCard3, Color.rgb(32, 32, 32));

        } else {

            mainLayout.setBackgroundColor(
                    Color.rgb(246, 243, 248)
            );

            txtHeader.setTextColor(Color.BLACK);

            setTextBlack(
                    txtItemName1,
                    txtOwner1,
                    txtCategory1,
                    txtPrice1,
                    txtItemName2,
                    txtOwner2,
                    txtCategory2,
                    txtPrice2,
                    txtItemName3,
                    txtOwner3,
                    txtCategory3,
                    txtPrice3
            );

            setCardColor(requestCard1, Color.WHITE);
            setCardColor(requestCard2, Color.WHITE);
            setCardColor(requestCard3, Color.WHITE);
        }
    }

    private void setTextWhite(TextView... views) {

        for (TextView view : views) {
            if (view != null) {
                view.setTextColor(Color.WHITE);
            }
        }
    }

    private void setTextBlack(TextView... views) {

        for (TextView view : views) {
            if (view != null) {
                view.setTextColor(Color.BLACK);
            }
        }
    }

    private void setCardColor(
            LinearLayout card,
            int color
    ) {

        if (card != null) {
            card.setBackgroundColor(color);
        }
    }
}