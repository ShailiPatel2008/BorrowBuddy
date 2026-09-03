package com.borrowbuddy.app.activities;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.borrowbuddy.app.R;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class RequestStatusActivity extends AppCompatActivity {

    private RecyclerView recyclerRequests;

    private ArrayList<RentalRequest> requestList;
    private RequestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_request_status);

        // =========================
        // TOOLBAR
        // =========================

        MaterialToolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Rental Requests");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(v -> finish());

        // =========================
        // RECYCLER VIEW
        // =========================

        recyclerRequests = findViewById(R.id.recyclerRequests);

        recyclerRequests.setLayoutManager(
                new LinearLayoutManager(this)
        );

        // =========================
        // SAMPLE REQUEST DATA
        // =========================

        requestList = new ArrayList<>();

        requestList.add(
                new RentalRequest(
                        "BB001",
                        "Rahul",
                        "rahul@gmail.com",
                        "Camera",
                        "Electronics",
                        "20 Aug 2026",
                        "22 Aug 2026",
                        "2",
                        "₹300",
                        "₹1000",
                        "20 Aug 2026",
                        "9876543210",
                        "Pending"
                )
        );

        requestList.add(
                new RentalRequest(
                        "BB002",
                        "Priya",
                        "priya@gmail.com",
                        "Drill Machine",
                        "Tools",
                        "21 Aug 2026",
                        "25 Aug 2026",
                        "1",
                        "₹250",
                        "₹800",
                        "21 Aug 2026",
                        "9876501234",
                        "Pending"
                )
        );

        // =========================
        // ADAPTER
        // =========================

        adapter = new RequestAdapter();

        recyclerRequests.setAdapter(adapter);
    }

    // =========================================================
    // RENTAL REQUEST MODEL
    // =========================================================

    public class RentalRequest {

        String bookingId;
        String customerName;
        String customerEmail;
        String itemName;
        String category;
        String startDate;
        String endDate;
        String quantity;
        String pricePerDay;
        String securityDeposit;
        String bookingDate;
        String contactNumber;
        String status;

        public RentalRequest(
                String bookingId,
                String customerName,
                String customerEmail,
                String itemName,
                String category,
                String startDate,
                String endDate,
                String quantity,
                String pricePerDay,
                String securityDeposit,
                String bookingDate,
                String contactNumber,
                String status
        ) {

            this.bookingId = bookingId;
            this.customerName = customerName;
            this.customerEmail = customerEmail;
            this.itemName = itemName;
            this.category = category;
            this.startDate = startDate;
            this.endDate = endDate;
            this.quantity = quantity;
            this.pricePerDay = pricePerDay;
            this.securityDeposit = securityDeposit;
            this.bookingDate = bookingDate;
            this.contactNumber = contactNumber;
            this.status = status;
        }
    }

    // =========================================================
    // ADAPTER
    // =========================================================

    private class RequestAdapter
            extends RecyclerView.Adapter<RequestAdapter.RequestViewHolder> {

        @Override
        public RequestViewHolder onCreateViewHolder(
                android.view.ViewGroup parent,
                int viewType
        ) {

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(
                            R.layout.item_request_status,
                            parent,
                            false
                    );

            return new RequestViewHolder(view);
        }

        @Override
        public void onBindViewHolder(
                RequestViewHolder holder,
                int position
        ) {

            RentalRequest request =
                    requestList.get(position);

            // Basic information shown on card

            holder.tvItemName.setText(
                    request.itemName
            );

            holder.tvCustomerName.setText(
                    "Customer: " + request.customerName
            );

            holder.tvDates.setText(
                    "Dates: " +
                            request.startDate +
                            " - " +
                            request.endDate
            );

            holder.tvQuantity.setText(
                    "Quantity: " + request.quantity
            );

            holder.tvStatus.setText(
                    "Status: " + request.status
            );

            // =========================
            // VIEW DETAILS
            // =========================

            holder.btnViewDetails.setOnClickListener(v -> {

                showRequestDetails(request);
            });
        }

        @Override
        public int getItemCount() {

            return requestList.size();
        }

        class RequestViewHolder
                extends RecyclerView.ViewHolder {

            TextView tvItemName;
            TextView tvCustomerName;
            TextView tvDates;
            TextView tvQuantity;
            TextView tvStatus;

            Button btnViewDetails;

            public RequestViewHolder(View itemView) {

                super(itemView);

                tvItemName =
                        itemView.findViewById(
                                R.id.tvItemName
                        );

                tvCustomerName =
                        itemView.findViewById(
                                R.id.tvCustomerName
                        );

                tvDates =
                        itemView.findViewById(
                                R.id.tvDates
                        );

                tvQuantity =
                        itemView.findViewById(
                                R.id.tvQuantity
                        );

                tvStatus =
                        itemView.findViewById(
                                R.id.tvStatus
                        );

                btnViewDetails =
                        itemView.findViewById(
                                R.id.btnViewDetails
                        );
            }
        }
    }

    // =========================================================
    // SHOW COMPLETE REQUEST DETAILS
    // =========================================================

    private void showRequestDetails(
            RentalRequest request
    ) {

        View dialogView =
                getLayoutInflater().inflate(
                        R.layout.dialog_request_details,
                        null
                );

        TextView tvDetails =
                dialogView.findViewById(
                        R.id.tvDetails
                );

        Button btnApprove =
                dialogView.findViewById(
                        R.id.btnApprove
                );

        Button btnReject =
                dialogView.findViewById(
                        R.id.btnReject
                );

        String details =
                "Booking ID: " + request.bookingId +
                        "\n\n" +

                        "Customer Name: " + request.customerName +
                        "\n\n" +

                        "Email: " + request.customerEmail +
                        "\n\n" +

                        "Contact Number: " + request.contactNumber +
                        "\n\n" +

                        "Item: " + request.itemName +
                        "\n\n" +

                        "Category: " + request.category +
                        "\n\n" +

                        "Quantity: " + request.quantity +
                        "\n\n" +

                        "Start Date: " + request.startDate +
                        "\n\n" +

                        "End Date: " + request.endDate +
                        "\n\n" +

                        "Price Per Day: " + request.pricePerDay +
                        "\n\n" +

                        "Security Deposit: " + request.securityDeposit +
                        "\n\n" +

                        "Booking Date: " + request.bookingDate +
                        "\n\n" +

                        "Status: " + request.status;

        tvDetails.setText(details);

        AlertDialog dialog =
                new AlertDialog.Builder(this)
                        .setView(dialogView)
                        .create();

        // =========================
        // APPROVE
        // =========================

        btnApprove.setOnClickListener(v -> {

            new AlertDialog.Builder(this)
                    .setTitle("Approve Request")
                    .setMessage(
                            "Are you sure you want to approve this rental request?"
                    )
                    .setNegativeButton(
                            "Cancel",
                            null
                    )
                    .setPositiveButton(
                            "Approve",
                            (d, which) -> {

                                int position =
                                        requestList.indexOf(request);

                                if (position != -1) {

                                    requestList.remove(position);

                                    adapter.notifyItemRemoved(
                                            position
                                    );
                                }

                                dialog.dismiss();

                                Toast.makeText(
                                        this,
                                        "Rental request approved",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                    )
                    .show();
        });

        // =========================
        // REJECT
        // =========================

        btnReject.setOnClickListener(v -> {

            new AlertDialog.Builder(this)
                    .setTitle("Reject Request")
                    .setMessage(
                            "Are you sure you want to reject this rental request?"
                    )
                    .setNegativeButton(
                            "Cancel",
                            null
                    )
                    .setPositiveButton(
                            "Reject",
                            (d, which) -> {

                                int position =
                                        requestList.indexOf(request);

                                if (position != -1) {

                                    requestList.remove(position);

                                    adapter.notifyItemRemoved(
                                            position
                                    );
                                }

                                dialog.dismiss();

                                Toast.makeText(
                                        this,
                                        "Rental request rejected",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                    )
                    .show();
        });

        dialog.show();
    }
}