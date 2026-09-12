package com.borrowbuddy.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import android.widget.ImageButton;
import android.graphics.drawable.ColorDrawable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.ViewGroup;

public class ManageItemActivity extends AppCompatActivity {

    // =========================
    // MAIN LAYOUT
    // =========================

    LinearLayout mainLayout;

    // =========================
    // ITEM CARDS
    // =========================

    LinearLayout itemCard1;
    LinearLayout itemCard2;
    LinearLayout itemCard3;

    // =========================
    // ITEM TEXT
    // =========================

    TextView txtItem1;
    TextView txtItem2;
    TextView txtItem3;

    TextView txtOwner1;
    TextView txtOwner2;
    TextView txtOwner3;

    // =========================
    // BUTTONS
    // =========================

    TextView btnViewDetails1;
    TextView btnViewDetails2;
    TextView btnViewDetails3;

    TextView btnRemoveItem1;
    TextView btnRemoveItem2;
    TextView btnRemoveItem3;

    ImageButton btnBack;

    // =========================
    // SETTINGS
    // =========================

    SharedPreferences settingsPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manage_item);

        // =========================
        // FIND VIEWS
        // =========================

        mainLayout = findViewById(R.id.mainLayout);

        itemCard1 = findViewById(R.id.itemCard1);
        itemCard2 = findViewById(R.id.itemCard2);
        itemCard3 = findViewById(R.id.itemCard3);

        txtItem1 = findViewById(R.id.txtItem1);
        txtItem2 = findViewById(R.id.txtItem2);
        txtItem3 = findViewById(R.id.txtItem3);

        txtOwner1 = findViewById(R.id.txtOwner1);
        txtOwner2 = findViewById(R.id.txtOwner2);
        txtOwner3 = findViewById(R.id.txtOwner3);

        btnViewDetails1 =
                findViewById(R.id.btnViewDetails1);

        btnViewDetails2 =
                findViewById(R.id.btnViewDetails2);

        btnViewDetails3 =
                findViewById(R.id.btnViewDetails3);

        btnRemoveItem1 =
                findViewById(R.id.btnRemoveItem1);

        btnRemoveItem2 =
                findViewById(R.id.btnRemoveItem2);

        btnRemoveItem3 =
                findViewById(R.id.btnRemoveItem3);

        btnBack =
                findViewById(R.id.btnBack);


        // =========================
        // SETTINGS
        // =========================

        settingsPreferences =
                getSharedPreferences(
                        "Settings",
                        MODE_PRIVATE
                );


        // =========================
        // TOOLBAR BACK
        // =========================

        btnBack.setOnClickListener(v -> finish());


        // =========================
        // VIEW DETAILS - CAMERA
        // =========================

        btnViewDetails1.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            ManageItemActivity.this,
                            AdminItemDetailsActivity.class
                    );

            intent.putExtra(
                    "itemName",
                    "Canon Camera"
            );

            intent.putExtra(
                    "ownerName",
                    "Rahul"
            );

            intent.putExtra(
                    "category",
                    "Electronics"
            );

            intent.putExtra(
                    "description",
                    "Canon Camera available for rent."
            );

            intent.putExtra(
                    "price",
                    "₹300/day"
            );

            intent.putExtra(
                    "deposit",
                    "₹1,000"
            );

            intent.putExtra(
                    "pickup",
                    "Hazira, Surat"
            );

            intent.putExtra(
                    "condition",
                    "Good"
            );

            startActivity(intent);
        });


        // =========================
        // VIEW DETAILS - DRILL MACHINE
        // =========================

        btnViewDetails2.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            ManageItemActivity.this,
                            AdminItemDetailsActivity.class
                    );

            intent.putExtra(
                    "itemName",
                    "Drill Machine"
            );

            intent.putExtra(
                    "ownerName",
                    "Priya"
            );

            intent.putExtra(
                    "category",
                    "Tools"
            );

            intent.putExtra(
                    "description",
                    "Drill Machine available for rent."
            );

            intent.putExtra(
                    "price",
                    "₹250/day"
            );

            intent.putExtra(
                    "deposit",
                    "₹800"
            );

            intent.putExtra(
                    "pickup",
                    "Hazira, Surat"
            );

            intent.putExtra(
                    "condition",
                    "Good"
            );

            startActivity(intent);
        });


        // =========================
        // VIEW DETAILS - PROJECTOR
        // =========================

        btnViewDetails3.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            ManageItemActivity.this,
                            AdminItemDetailsActivity.class
                    );

            intent.putExtra(
                    "itemName",
                    "Projector"
            );

            intent.putExtra(
                    "ownerName",
                    "Amit"
            );

            intent.putExtra(
                    "category",
                    "Electronics"
            );

            intent.putExtra(
                    "description",
                    "Projector available for rent."
            );

            intent.putExtra(
                    "price",
                    "₹400/day"
            );

            intent.putExtra(
                    "deposit",
                    "₹1,200"
            );

            intent.putExtra(
                    "pickup",
                    "Hazira, Surat"
            );

            intent.putExtra(
                    "condition",
                    "Good"
            );

            startActivity(intent);
        });


        btnRemoveItem1.setOnClickListener(v -> {

            AlertDialog dialog =
                    new AlertDialog.Builder(ManageItemActivity.this)
                            .setTitle("Remove Item?")
                            .setMessage("Are you sure you want to remove this item?")
                            .setNegativeButton("CANCEL", null)
                            .setPositiveButton("REMOVE", (dialogInterface, which) -> {

                                itemCard1.setVisibility(
                                        View.GONE
                                );

                                Toast.makeText(
                                        ManageItemActivity.this,
                                        "Item removed successfully",
                                        Toast.LENGTH_SHORT
                                ).show();
                            })
                            .create();

            dialog.setOnShowListener(d -> {

                styleRemoveDialog(dialog);
            });

            dialog.show();
        });


        // =========================
// REMOVE DRILL MACHINE
// =========================

        btnRemoveItem2.setOnClickListener(v -> {

            AlertDialog dialog =
                    new AlertDialog.Builder(ManageItemActivity.this)
                            .setTitle("Remove Item?")
                            .setMessage("Are you sure you want to remove this item?")
                            .setNegativeButton("CANCEL", null)
                            .setPositiveButton("REMOVE", (dialogInterface, which) -> {

                                itemCard2.setVisibility(
                                        View.GONE
                                );

                                Toast.makeText(
                                        ManageItemActivity.this,
                                        "Item removed successfully",
                                        Toast.LENGTH_SHORT
                                ).show();
                            })
                            .create();

            dialog.setOnShowListener(d -> {

                styleRemoveDialog(dialog);
            });

            dialog.show();
        });


        // =========================
// REMOVE PROJECTOR
// =========================

        btnRemoveItem3.setOnClickListener(v -> {

            AlertDialog dialog =
                    new AlertDialog.Builder(ManageItemActivity.this)
                            .setTitle("Remove Item?")
                            .setMessage("Are you sure you want to remove this item?")
                            .setNegativeButton("CANCEL", null)
                            .setPositiveButton("REMOVE", (dialogInterface, which) -> {

                                itemCard3.setVisibility(
                                        View.GONE
                                );

                                Toast.makeText(
                                        ManageItemActivity.this,
                                        "Item removed successfully",
                                        Toast.LENGTH_SHORT
                                ).show();
                            })
                            .create();

            dialog.setOnShowListener(d -> {

                styleRemoveDialog(dialog);
            });

            dialog.show();
        });


        // =========================
        // GREY BUTTONS
        // =========================

        setGreyButton(btnViewDetails1);
        setGreyButton(btnViewDetails2);
        setGreyButton(btnViewDetails3);

        setGreyButton(btnRemoveItem1);
        setGreyButton(btnRemoveItem2);
        setGreyButton(btnRemoveItem3);


        // =========================
        // DARK MODE
        // =========================

        applyDarkMode();
    }


    // =====================================================
    // GREY BUTTON
    // =====================================================

    private void setGreyButton(
            TextView button) {

        GradientDrawable drawable =
                new GradientDrawable();

        drawable.setColor(
                Color.rgb(
                        128,
                        128,
                        128
                )
        );

        drawable.setCornerRadius(
                dp(8)
        );

        button.setBackground(
                drawable
        );

        button.setTextColor(
                Color.WHITE
        );
    }

    // =====================================================
// DIALOG STYLE
// =====================================================

    private void styleRemoveDialog(AlertDialog dialog) {

        boolean darkMode =
                settingsPreferences.getBoolean(
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
                    findDialogTitle(
                            dialog.getWindow().getDecorView()
                    );
        }

        if (titleText != null) {

            if (darkMode) {
                titleText.setTextColor(Color.WHITE);
            } else {
                titleText.setTextColor(Color.BLACK);
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
                messageText.setTextColor(Color.WHITE);
            } else {
                messageText.setTextColor(Color.BLACK);
            }
        }

        // =========================
        // CANCEL BUTTON
        // =========================

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

        // =========================
        // REMOVE BUTTON
        // =========================

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
// FIND DIALOG TITLE
// =====================================================

    private TextView findDialogTitle(View view) {

        if (view instanceof TextView) {

            TextView textView =
                    (TextView) view;

            if (textView.getText() != null &&
                    textView.getText()
                            .toString()
                            .equals("Remove Item?")) {

                return textView;
            }
        }

        if (view instanceof ViewGroup) {

            ViewGroup group =
                    (ViewGroup) view;

            for (int i = 0;
                 i < group.getChildCount();
                 i++) {

                TextView result =
                        findDialogTitle(
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
    // DARK MODE
    // =====================================================

    private void applyDarkMode() {

        boolean darkMode =
                settingsPreferences.getBoolean(
                        "dark_mode",
                        false
                );


        // =========================
        // TOOLBAR
        // =========================

        View toolbar =
                (View) btnBack.getParent();

        if (toolbar != null) {

            toolbar.setBackgroundColor(
                    Color.rgb(
                            108,
                            74,
                            182
                    )
            );
        }


        // =========================
        // DARK MODE ON
        // =========================

        if (darkMode) {

            mainLayout.setBackgroundColor(
                    Color.rgb(
                            18,
                            18,
                            18
                    )
            );

            setCardDark(itemCard1);
            setCardDark(itemCard2);
            setCardDark(itemCard3);

            setTextWhite(txtItem1);
            setTextWhite(txtItem2);
            setTextWhite(txtItem3);

            setTextWhite(txtOwner1);
            setTextWhite(txtOwner2);
            setTextWhite(txtOwner3);
        }


        // =========================
        // LIGHT MODE
        // =========================

        else {

            mainLayout.setBackgroundColor(
                    Color.rgb(
                            246,
                            243,
                            248
                    )
            );

            setCardLight(itemCard1);
            setCardLight(itemCard2);
            setCardLight(itemCard3);

            setTextBlack(txtItem1);
            setTextBlack(txtItem2);
            setTextBlack(txtItem3);

            setTextBlack(txtOwner1);
            setTextBlack(txtOwner2);
            setTextBlack(txtOwner3);
        }


        // Buttons always grey
        setGreyButton(btnViewDetails1);
        setGreyButton(btnViewDetails2);
        setGreyButton(btnViewDetails3);

        setGreyButton(btnRemoveItem1);
        setGreyButton(btnRemoveItem2);
        setGreyButton(btnRemoveItem3);
    }


    // =====================================================
    // DARK CARD
    // =====================================================

    private void setCardDark(
            View card) {

        GradientDrawable drawable =
                new GradientDrawable();

        drawable.setColor(
                Color.rgb(
                        35,
                        35,
                        35
                )
        );

        drawable.setCornerRadius(
                dp(12)
        );

        card.setBackground(
                drawable
        );
    }


    // =====================================================
    // LIGHT CARD
    // =====================================================

    private void setCardLight(
            View card) {

        GradientDrawable drawable =
                new GradientDrawable();

        drawable.setColor(
                Color.WHITE
        );

        drawable.setCornerRadius(
                dp(12)
        );

        card.setBackground(
                drawable
        );
    }


    // =====================================================
    // WHITE TEXT
    // =====================================================

    private void setTextWhite(
            TextView textView) {

        textView.setTextColor(
                Color.WHITE
        );
    }


    // =====================================================
    // BLACK TEXT
    // =====================================================

    private void setTextBlack(
            TextView textView) {

        textView.setTextColor(
                Color.rgb(
                        34,
                        34,
                        34
                )
        );
    }


    // =====================================================
    // DP
    // =====================================================

    private float dp(float value) {

        return value *
                getResources()
                        .getDisplayMetrics()
                        .density;
    }


    // =====================================================
    // ON RESUME
    // =====================================================

    @Override
    protected void onResume() {

        super.onResume();

        if (settingsPreferences == null) {

            settingsPreferences =
                    getSharedPreferences(
                            "Settings",
                            MODE_PRIVATE
                    );
        }

        applyDarkMode();
    }
}