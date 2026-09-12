package com.borrowbuddy.app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageButton;
import android.graphics.drawable.ColorDrawable;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ManageBookingActivity extends AppCompatActivity {

    // =========================
    // MAIN LAYOUT
    // =========================

    LinearLayout mainLayout;

    // =========================
    // BOOKING CARDS
    // =========================

    LinearLayout bookingCard1;
    LinearLayout bookingCard2;
    LinearLayout bookingCard3;

    // =========================
    // STATUS
    // =========================

    TextView txtStatus1;
    TextView txtStatus2;
    TextView txtStatus3;

    // =========================
    // VIEW DETAILS
    // =========================

    TextView btnViewDetails1;
    TextView btnViewDetails2;
    TextView btnViewDetails3;

    // =========================
    // APPROVE
    // =========================

    TextView btnApprove1;
    TextView btnApprove2;
    TextView btnApprove3;

    // =========================
    // REJECT
    // =========================

    TextView btnReject1;
    TextView btnReject2;
    TextView btnReject3;

    // =========================
    // FILTER BUTTONS
    // =========================

    TextView btnAll;
    TextView btnPending;
    TextView btnApproved;
    TextView btnActive;
    TextView btnCompleted;
    TextView btnRejected;

    // =========================
    // BACK
    // =========================

    ImageButton btnBack;

    // =========================
    // BOOKING STATUS
    // =========================

    String status1 = "Pending";
    String status2 = "Pending";
    String status3 = "Pending";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_manage_booking
        );



        // =================================================
        // FIND MAIN LAYOUT
        // =================================================

        mainLayout =
                findViewById(R.id.mainLayout);


        // =================================================
        // FIND BOOKING CARDS
        // =================================================

        bookingCard1 =
                findViewById(R.id.bookingCard1);

        bookingCard2 =
                findViewById(R.id.bookingCard2);

        bookingCard3 =
                findViewById(R.id.bookingCard3);


        // =================================================
        // FIND STATUS
        // =================================================

        txtStatus1 =
                findViewById(R.id.txtStatus1);

        txtStatus2 =
                findViewById(R.id.txtStatus2);

        txtStatus3 =
                findViewById(R.id.txtStatus3);


        // =================================================
        // FIND VIEW DETAILS BUTTONS
        // =================================================

        btnViewDetails1 =
                findViewById(R.id.btnViewDetails1);

        btnViewDetails2 =
                findViewById(R.id.btnViewDetails2);

        btnViewDetails3 =
                findViewById(R.id.btnViewDetails3);


        // =================================================
        // FIND APPROVE BUTTONS
        // =================================================

        btnApprove1 =
                findViewById(R.id.btnApprove1);

        btnApprove2 =
                findViewById(R.id.btnApprove2);

        btnApprove3 =
                findViewById(R.id.btnApprove3);


        // =================================================
        // FIND REJECT BUTTONS
        // =================================================

        btnReject1 =
                findViewById(R.id.btnReject1);

        btnReject2 =
                findViewById(R.id.btnReject2);

        btnReject3 =
                findViewById(R.id.btnReject3);


        // =================================================
        // FIND FILTER BUTTONS
        // =================================================

        btnAll =
                findViewById(R.id.btnAll);

        btnPending =
                findViewById(R.id.btnPending);

        btnApproved =
                findViewById(R.id.btnApproved);

        btnActive =
                findViewById(R.id.btnActive);

        btnCompleted =
                findViewById(R.id.btnCompleted);

        btnRejected =
                findViewById(R.id.btnRejected);


        // =================================================
        // BACK
        // =================================================

        btnBack =
                findViewById(R.id.btnBack);


        btnBack.setOnClickListener(v -> {
            finish();
        });


        // =================================================
        // VIEW DETAILS - BOOKING 1
        // =================================================

        btnViewDetails1.setOnClickListener(v -> {

            openBookingDetails(
                    "BB001",
                    "Camera",
                    "Electronics",
                    "Rahul",
                    "Priya",
                    "20 Aug - 22 Aug",
                    "2",
                    "₹1800",
                    status1
            );

        });


        // =================================================
        // VIEW DETAILS - BOOKING 2
        // =================================================

        btnViewDetails2.setOnClickListener(v -> {

            openBookingDetails(
                    "BB002",
                    "Drill Machine",
                    "Tools",
                    "Priya",
                    "Amit",
                    "21 Aug - 25 Aug",
                    "1",
                    "₹1000",
                    status2
            );

        });


        // =================================================
        // VIEW DETAILS - BOOKING 3
        // =================================================

        btnViewDetails3.setOnClickListener(v -> {

            openBookingDetails(
                    "BB003",
                    "Projector",
                    "Electronics",
                    "Amit",
                    "Rahul",
                    "21 Aug - 24 Aug",
                    "1",
                    "₹800",
                    status3
            );

        });


        // =================================================
        // APPROVE
        // =================================================

        btnApprove1.setOnClickListener(v -> {
            showApproveDialog(1);
        });


        btnApprove2.setOnClickListener(v -> {
            showApproveDialog(2);
        });


        btnApprove3.setOnClickListener(v -> {
            showApproveDialog(3);
        });


        // =================================================
        // REJECT
        // =================================================

        btnReject1.setOnClickListener(v -> {
            showRejectDialog(1);
        });


        btnReject2.setOnClickListener(v -> {
            showRejectDialog(2);
        });


        btnReject3.setOnClickListener(v -> {
            showRejectDialog(3);
        });


        // =================================================
        // ALL FILTER
        // =================================================

        btnAll.setOnClickListener(v -> {

            showAllBookings();

        });


        // =================================================
        // PENDING FILTER
        // =================================================

        btnPending.setOnClickListener(v -> {

            filterBookings("Pending");

        });


        // =================================================
        // APPROVED FILTER
        // =================================================

        btnApproved.setOnClickListener(v -> {

            filterBookings("Approved");

        });


        // =================================================
        // ACTIVE FILTER
        // =================================================

        btnActive.setOnClickListener(v -> {

            filterBookings("Active");

        });


        // =================================================
        // COMPLETED FILTER
        // =================================================

        btnCompleted.setOnClickListener(v -> {

            filterBookings("Completed");

        });


        // =================================================
        // REJECTED FILTER
        // =================================================

        btnRejected.setOnClickListener(v -> {

            filterBookings("Rejected");

        });


        // =================================================
        // INITIAL STATUS
        // =================================================

        updateStatusText();


        // =================================================
        // DARK MODE
        // =================================================

        applyDarkMode();
    }


    // =====================================================
    // OPEN BOOKING DETAILS
    // =====================================================

    private void openBookingDetails(
            String bookingId,
            String itemName,
            String category,
            String borrower,
            String owner,
            String rental,
            String quantity,
            String total,
            String status
    ) {

        Intent intent =
                new Intent(
                        ManageBookingActivity.this,
                        BookingDetailsActivity.class
                );


        intent.putExtra(
                "bookingId",
                bookingId
        );

        intent.putExtra(
                "itemName",
                itemName
        );

        intent.putExtra(
                "category",
                category
        );

        intent.putExtra(
                "borrower",
                borrower
        );

        intent.putExtra(
                "owner",
                owner
        );

        intent.putExtra(
                "rental",
                rental
        );

        intent.putExtra(
                "quantity",
                quantity
        );

        intent.putExtra(
                "total",
                total
        );

        intent.putExtra(
                "status",
                status
        );


        // =========================
        // TRUST SCORE
        // =========================

        if (bookingId.equals("BB001")) {

            intent.putExtra(
                    "trustScore",
                    "4.8"
            );

            intent.putExtra(
                    "reviews",
                    "24"
            );

        } else if (bookingId.equals("BB002")) {

            intent.putExtra(
                    "trustScore",
                    "4.7"
            );

            intent.putExtra(
                    "reviews",
                    "18"
            );

        } else if (bookingId.equals("BB003")) {

            intent.putExtra(
                    "trustScore",
                    "4.6"
            );

            intent.putExtra(
                    "reviews",
                    "15"
            );
        }


        startActivity(intent);
    }


    // =====================================================
    // APPROVE DIALOG
    // =====================================================

    private void showApproveDialog(
            int bookingNumber
    ) {

        AlertDialog dialog =
                new AlertDialog.Builder(
                        ManageBookingActivity.this
                )
                        .setTitle(
                                "Approve Booking?"
                        )
                        .setMessage(
                                "Are you sure you want to approve this booking?"
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

            styleBookingDialog(dialog);

            dialog.getButton(
                    AlertDialog.BUTTON_POSITIVE
            ).setOnClickListener(v -> {


                // =========================================
                // BOOKING 1
                // =========================================

                if (bookingNumber == 1) {

                    status1 = "Approved";

                    txtStatus1.setText(
                            "Status: Approved"
                    );

                    setStatusColor(
                            txtStatus1,
                            status1
                    );

                    bookingCard1.setVisibility(
                            View.GONE
                    );
                }


                // =========================================
                // BOOKING 2
                // =========================================

                else if (bookingNumber == 2) {

                    status2 = "Approved";

                    txtStatus2.setText(
                            "Status: Approved"
                    );

                    setStatusColor(
                            txtStatus2,
                            status2
                    );

                    bookingCard2.setVisibility(
                            View.GONE
                    );
                }


                // =========================================
                // BOOKING 3
                // =========================================

                else if (bookingNumber == 3) {

                    status3 = "Approved";

                    txtStatus3.setText(
                            "Status: Approved"
                    );

                    setStatusColor(
                            txtStatus3,
                            status3
                    );

                    bookingCard3.setVisibility(
                            View.GONE
                    );
                }


                Toast.makeText(
                        ManageBookingActivity.this,
                        "Booking approved successfully",
                        Toast.LENGTH_SHORT
                ).show();


                dialog.dismiss();
            });
        });


        dialog.show();
    }


    // =====================================================
    // REJECT DIALOG
    // =====================================================

    private void showRejectDialog(
            int bookingNumber
    ) {

        AlertDialog dialog =
                new AlertDialog.Builder(
                        ManageBookingActivity.this
                )
                        .setTitle(
                                "Reject Booking?"
                        )
                        .setMessage(
                                "Are you sure you want to reject this booking?"
                        )
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

            styleBookingDialog(dialog);

            dialog.getButton(
                    AlertDialog.BUTTON_POSITIVE
            ).setOnClickListener(v -> {


                // =========================================
                // BOOKING 1
                // =========================================

                if (bookingNumber == 1) {

                    status1 = "Rejected";

                    txtStatus1.setText(
                            "Status: Rejected"
                    );

                    setStatusColor(
                            txtStatus1,
                            status1
                    );

                    bookingCard1.setVisibility(
                            View.GONE
                    );
                }


                // =========================================
                // BOOKING 2
                // =========================================

                else if (bookingNumber == 2) {

                    status2 = "Rejected";

                    txtStatus2.setText(
                            "Status: Rejected"
                    );

                    setStatusColor(
                            txtStatus2,
                            status2
                    );

                    bookingCard2.setVisibility(
                            View.GONE
                    );
                }


                // =========================================
                // BOOKING 3
                // =========================================

                else if (bookingNumber == 3) {

                    status3 = "Rejected";

                    txtStatus3.setText(
                            "Status: Rejected"
                    );

                    setStatusColor(
                            txtStatus3,
                            status3
                    );

                    bookingCard3.setVisibility(
                            View.GONE
                    );
                }


                Toast.makeText(
                        ManageBookingActivity.this,
                        "Booking rejected successfully",
                        Toast.LENGTH_SHORT
                ).show();


                dialog.dismiss();
            });
        });


        dialog.show();
    }

    // =====================================================
// STYLE BOOKING DIALOG
// =====================================================

    private void styleBookingDialog(AlertDialog dialog) {

        boolean darkMode =
                getSharedPreferences(
                        "Settings",
                        MODE_PRIVATE
                ).getBoolean(
                        "dark_mode",
                        false
                );

        // =========================
        // DIALOG BACKGROUND
        // =========================

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


        // =========================
        // TITLE
        // =========================

        int titleId =
                getResources().getIdentifier(
                        "alertTitle",
                        "id",
                        "android"
                );

        TextView titleText =
                dialog.findViewById(titleId);

        if (titleText == null &&
                dialog.getWindow() != null) {

            titleText =
                    findBookingDialogTitle(
                            dialog.getWindow()
                                    .getDecorView()
                    );
        }

        if (titleText != null) {

            if (darkMode) {

                titleText.setTextColor(
                        Color.WHITE
                );

            } else {

                titleText.setTextColor(
                        Color.BLACK
                );
            }
        }


        // =========================
        // MESSAGE
        // =========================

        TextView messageText =
                dialog.findViewById(
                        android.R.id.message
                );

        if (messageText != null) {

            if (darkMode) {

                messageText.setTextColor(
                        Color.WHITE
                );

            } else {

                messageText.setTextColor(
                        Color.BLACK
                );
            }
        }


        // =========================
        // BUTTONS
        // =========================

        if (dialog.getButton(
                AlertDialog.BUTTON_NEGATIVE
        ) != null) {

            if (darkMode) {

                dialog.getButton(
                        AlertDialog.BUTTON_NEGATIVE
                ).setTextColor(
                        Color.WHITE
                );

            } else {

                dialog.getButton(
                        AlertDialog.BUTTON_NEGATIVE
                ).setTextColor(
                        Color.rgb(
                                108,
                                74,
                                182
                        )
                );
            }
        }


        if (dialog.getButton(
                AlertDialog.BUTTON_POSITIVE
        ) != null) {

            if (darkMode) {

                dialog.getButton(
                        AlertDialog.BUTTON_POSITIVE
                ).setTextColor(
                        Color.WHITE
                );

            } else {

                dialog.getButton(
                        AlertDialog.BUTTON_POSITIVE
                ).setTextColor(
                        Color.rgb(
                                108,
                                74,
                                182
                        )
                );
            }
        }
    }

    private TextView findBookingDialogTitle(View view) {

        if (view instanceof TextView) {

            TextView textView =
                    (TextView) view;

            String text =
                    textView.getText() != null
                            ? textView.getText().toString()
                            : "";

            if (text.equals("Approve Booking?")
                    || text.equals("Reject Booking?")) {

                return textView;
            }
        }

        if (view instanceof android.view.ViewGroup) {

            android.view.ViewGroup group =
                    (android.view.ViewGroup) view;

            for (int i = 0;
                 i < group.getChildCount();
                 i++) {

                TextView result =
                        findBookingDialogTitle(
                                group.getChildAt(i)
                        );

                if (result != null) {
                    return result;
                }
            }
        }

        return null;
    }


    // =====================================================
    // SHOW ALL
    // =====================================================

    private void showAllBookings() {

        bookingCard1.setVisibility(
                View.VISIBLE
        );

        bookingCard2.setVisibility(
                View.VISIBLE
        );

        bookingCard3.setVisibility(
                View.VISIBLE
        );
    }


    // =====================================================
    // FILTER
    // =====================================================

    private void filterBookings(
            String selectedStatus
    ) {

        // ================================================
        // BOOKING 1
        // ================================================

        if (status1.equalsIgnoreCase(
                selectedStatus
        )) {

            bookingCard1.setVisibility(
                    View.VISIBLE
            );

        } else {

            bookingCard1.setVisibility(
                    View.GONE
            );
        }


        // ================================================
        // BOOKING 2
        // ================================================

        if (status2.equalsIgnoreCase(
                selectedStatus
        )) {

            bookingCard2.setVisibility(
                    View.VISIBLE
            );

        } else {

            bookingCard2.setVisibility(
                    View.GONE
            );
        }


        // ================================================
        // BOOKING 3
        // ================================================

        if (status3.equalsIgnoreCase(
                selectedStatus
        )) {

            bookingCard3.setVisibility(
                    View.VISIBLE
            );

        } else {

            bookingCard3.setVisibility(
                    View.GONE
            );
        }
    }


    // =====================================================
    // UPDATE STATUS TEXT
    // =====================================================

    private void updateStatusText() {

        txtStatus1.setText(
                "Status: " + status1
        );

        txtStatus2.setText(
                "Status: " + status2
        );

        txtStatus3.setText(
                "Status: " + status3
        );


        setStatusColor(
                txtStatus1,
                status1
        );

        setStatusColor(
                txtStatus2,
                status2
        );

        setStatusColor(
                txtStatus3,
                status3
        );
    }


    // =====================================================
    // STATUS COLOR
    // =====================================================

    private void setStatusColor(
            TextView statusText,
            String status
    ) {

        if (status.equalsIgnoreCase(
                "Rejected"
        )) {

            statusText.setTextColor(
                    Color.RED
            );

        } else if (
                status.equalsIgnoreCase("Approved")
                        ||
                        status.equalsIgnoreCase("Active")
                        ||
                        status.equalsIgnoreCase("Completed")
        ) {

            statusText.setTextColor(
                    Color.rgb(
                            0,
                            150,
                            0
                    )
            );

        } else {

            statusText.setTextColor(
                    Color.rgb(
                            255,
                            140,
                            0
                    )
            );
        }
    }


    // =====================================================
// SET CARD TEXT COLOR
// =====================================================

    private void setCardTextColor(
            View view,
            int color
    ) {

        if (view instanceof TextView) {

            TextView textView =
                    (TextView) view;

            // Keep all buttons WHITE in both light and dark mode
            if (textView == btnViewDetails1 ||
                    textView == btnViewDetails2 ||
                    textView == btnViewDetails3 ||
                    textView == btnApprove1 ||
                    textView == btnApprove2 ||
                    textView == btnApprove3 ||
                    textView == btnReject1 ||
                    textView == btnReject2 ||
                    textView == btnReject3) {

                textView.setTextColor(
                        Color.WHITE
                );

            } else {

                textView.setTextColor(
                        color
                );
            }

        } else if (
                view instanceof LinearLayout
        ) {

            LinearLayout layout =
                    (LinearLayout) view;

            for (
                    int i = 0;
                    i < layout.getChildCount();
                    i++
            ) {

                View child =
                        layout.getChildAt(i);

                setCardTextColor(
                        child,
                        color
                );
            }
        }
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

            // =========================
            // DARK BACKGROUND
            // =========================

            mainLayout.setBackgroundColor(
                    Color.rgb(
                            16,
                            16,
                            16
                    )
            );


            // =========================
            // DARK CARDS
            // =========================

            bookingCard1.setBackgroundColor(
                    Color.rgb(
                            35,
                            35,
                            35
                    )
            );

            bookingCard2.setBackgroundColor(
                    Color.rgb(
                            35,
                            35,
                            35
                    )
            );

            bookingCard3.setBackgroundColor(
                    Color.rgb(
                            35,
                            35,
                            35
                    )
            );


            // =========================
            // CARD TEXT WHITE
            // =========================

            setCardTextColor(
                    bookingCard1,
                    Color.WHITE
            );

            setCardTextColor(
                    bookingCard2,
                    Color.WHITE
            );

            setCardTextColor(
                    bookingCard3,
                    Color.WHITE
            );


            // =========================
            // STATUS COLORS AGAIN
            // =========================

            setStatusColor(
                    txtStatus1,
                    status1
            );

            setStatusColor(
                    txtStatus2,
                    status2
            );

            setStatusColor(
                    txtStatus3,
                    status3
            );


        } else {

            // =========================
            // LIGHT BACKGROUND
            // =========================

            mainLayout.setBackgroundColor(
                    Color.rgb(
                            246,
                            243,
                            248
                    )
            );


            // =========================
            // WHITE CARDS
            // =========================

            bookingCard1.setBackgroundColor(
                    Color.WHITE
            );

            bookingCard2.setBackgroundColor(
                    Color.WHITE
            );

            bookingCard3.setBackgroundColor(
                    Color.WHITE
            );


            // =========================
            // CARD TEXT BLACK
            // =========================

            setCardTextColor(
                    bookingCard1,
                    Color.BLACK
            );

            setCardTextColor(
                    bookingCard2,
                    Color.BLACK
            );

            setCardTextColor(
                    bookingCard3,
                    Color.BLACK
            );


            // =========================
            // STATUS COLORS AGAIN
            // =========================

            setStatusColor(
                    txtStatus1,
                    status1
            );

            setStatusColor(
                    txtStatus2,
                    status2
            );

            setStatusColor(
                    txtStatus3,
                    status3
            );
        }
    }


    // =====================================================
    // ON RESUME
    // =====================================================

    @Override
    protected void onResume() {

        super.onResume();

        applyDarkMode();
    }
}