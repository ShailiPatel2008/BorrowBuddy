package com.borrowbuddy.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.firestore.FirebaseFirestore;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class ItemDetailsActivity extends AppCompatActivity {

    // ================= TOOLBAR =================

    MaterialToolbar toolbar;


    TextView itemName;
    TextView itemOwner;
    TextView itemCategory;
    TextView itemPrice;
    TextView itemCity;
    TextView itemRating;
    TextView itemDescription;
    TextView itemLocation;

    TextView descriptionTitle;
    TextView locationTitle;

    TextView dot1;
    TextView dot2;
    TextView dot3;
    TextView dot4;
    TextView dot5;

    Button btnRequestBorrow;

    View itemDetailsLayout;

    MaterialCardView itemImageCard;
    MaterialCardView detailsCard;
    ViewPager2 imageViewPager;
    private FirebaseFirestore db;
    private static final int PURPLE =
            Color.rgb(106, 27, 154);

    private static final int DARK_CARD =
            Color.rgb(43, 41, 50);


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_item_details);
        db = FirebaseFirestore.getInstance();

        // ================= TOOLBAR =================

        toolbar =
                findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {

            getSupportActionBar().setTitle(
                    "Item Details"
            );

            getSupportActionBar()
                    .setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(
                v -> finish()
        );


        // ================= STATUS BAR =================

        Window window = getWindow();

        getWindow().setStatusBarColor(Color.rgb(108, 74, 182));

        window.getDecorView().setSystemUiVisibility(0);


        // ================= FIND VIEWS =================

        itemDetailsLayout =
                findViewById(R.id.itemDetailsLayout);

        itemImageCard =
                findViewById(R.id.itemImageCard);

        detailsCard =
                findViewById(R.id.detailsCard);

        imageViewPager =
                findViewById(R.id.imageViewPager);


        itemName =
                findViewById(R.id.itemName);

        itemOwner =
                findViewById(R.id.itemOwner);

        itemCategory =
                findViewById(R.id.itemCategory);

        itemPrice =
                findViewById(R.id.itemPrice);

        itemCity =
                findViewById(R.id.itemCity);

        itemRating =
                findViewById(R.id.itemRating);

        itemDescription =
                findViewById(R.id.itemDescription);

        itemLocation =
                findViewById(R.id.itemLocation);

        descriptionTitle =
                findViewById(R.id.descriptionTitle);

        locationTitle =
                findViewById(R.id.locationTitle);

        btnRequestBorrow =
                findViewById(R.id.btnRequestBorrow);

        dot1 =
                findViewById(R.id.dot1);

        dot2 =
                findViewById(R.id.dot2);

        dot3 =
                findViewById(R.id.dot3);

        dot4 =
                findViewById(R.id.dot4);

        dot5 =
                findViewById(R.id.dot5);


        // ================= GET DATA =================
        // HomeActivity sends "itemName"

        String itemId =
                getIntent().getStringExtra("itemId");
        Toast.makeText(this, itemId, Toast.LENGTH_LONG).show();
        loadItemDetails(itemId);


        // ================= 5 IMAGES =================

        List<Integer> images =
                new ArrayList<>();

        /*
         * Existing image system is kept unchanged.
         * Firebase images can be connected here later.
         */

        images.add(
                R.drawable.ic_launcher_foreground
        );

        images.add(
                R.drawable.ic_launcher_foreground
        );

        images.add(
                R.drawable.ic_launcher_foreground
        );

        images.add(
                R.drawable.ic_launcher_foreground
        );

        images.add(
                R.drawable.ic_launcher_foreground
        );


        ItemImageAdapter adapter =
                new ItemImageAdapter(images);

        imageViewPager.setAdapter(adapter);


        // ================= IMAGE SWIPE =================

        imageViewPager.registerOnPageChangeCallback(
                new ViewPager2.OnPageChangeCallback() {

                    @Override
                    public void onPageSelected(
                            int position) {

                        super.onPageSelected(position);

                        showDot(position);
                    }
                }
        );


        showDot(0);


        // ================= DARK MODE =================

        applyDarkMode();


        // ================= REQUEST BORROW =================

        btnRequestBorrow.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            ItemDetailsActivity.this,
                            BookingActivity.class
                    );

            intent.putExtra(
                    "name",
                    itemName.getText().toString()
            );

            intent.putExtra(
                    "price",
                    itemPrice.getText().toString()
            );

            intent.putExtra(
                    "location",
                    itemLocation.getText().toString()
            );

            intent.putExtra(
                    "rating",
                    itemRating.getText().toString()
            );

            intent.putExtra(
                    "category",
                    itemCategory.getText().toString()
            );
            intent.putExtra(
                    "itemId",
                    itemId
            );
            startActivity(intent);
        });
    }

    private void loadItemDetails(String itemId) {

        db.collection("itemRequests")
                .document(itemId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {

                    if (!documentSnapshot.exists())
                        return;

                    DocumentSnapshot document = documentSnapshot;
                    itemName.setText(
                            document.getString("itemName")
                    );

                    itemOwner.setText(
                            "Owner: " +
                                    document.getString("ownerName")
                    );

                    itemCategory.setText(
                            "Category: " +
                                    document.getString("category")
                    );

                    Long price =
                            document.getLong("pricePerDay");

                    itemPrice.setText(
                            "Price: ₹" + price + "/day"
                    );

                    String location =
                            document.getString("pickuplocation");

                    itemCity.setText(location);

                    itemLocation.setText(location);

                    itemDescription.setText(
                            document.getString("description")
                    );

                    itemRating.setText("★★★★☆");

                });

    }

    // ==================================================
    // 5 DOTS
    // ==================================================

    private void showDot(int position) {

        dot1.setTextColor(Color.GRAY);
        dot2.setTextColor(Color.GRAY);
        dot3.setTextColor(Color.GRAY);
        dot4.setTextColor(Color.GRAY);
        dot5.setTextColor(Color.GRAY);


        if (position == 0) {

            dot1.setTextColor(PURPLE);

        } else if (position == 1) {

            dot2.setTextColor(PURPLE);

        } else if (position == 2) {

            dot3.setTextColor(PURPLE);

        } else if (position == 3) {

            dot4.setTextColor(PURPLE);

        } else if (position == 4) {

            dot5.setTextColor(PURPLE);
        }
    }


    // ==================================================
    // DARK MODE
    // ==================================================

    private void applyDarkMode() {

        SharedPreferences preferences =
                getSharedPreferences(
                        "BorrowBuddy",
                        MODE_PRIVATE
                );


        boolean darkMode =
                preferences.getBoolean(
                        "darkMode",
                        false
                );


        // ================= STATUS BAR =================

        getWindow().setStatusBarColor(Color.rgb(108, 74, 182));

        getWindow()
                .getDecorView()
                .setSystemUiVisibility(0);


        // ================= HEADER =================

        toolbar.setBackgroundColor(
                Color.rgb(108, 74, 182)
        );
        toolbar.setTitleTextColor(
                Color.WHITE
        );


        // ================= DARK / LIGHT =================

        if (darkMode) {

            itemDetailsLayout.setBackgroundColor(
                    Color.BLACK
            );

            itemImageCard.setCardBackgroundColor(
                    DARK_CARD
            );

            detailsCard.setCardBackgroundColor(
                    DARK_CARD
            );

            setMainTextColor(
                    Color.WHITE
            );

        } else {

            itemDetailsLayout.setBackgroundColor(
                    Color.rgb(248, 249, 250)
            );

            itemImageCard.setCardBackgroundColor(
                    Color.WHITE
            );

            detailsCard.setCardBackgroundColor(
                    Color.WHITE
            );

            setMainTextColor(
                    Color.BLACK
            );
        }


        // ================= RATING =================

        itemRating.setTextColor(
                Color.rgb(255, 193, 7)
        );


        // ================= REQUEST BUTTON =================

        btnRequestBorrow.setBackgroundTintList(
                ContextCompat.getColorStateList(
                        this,
                        android.R.color.transparent
                )
        );

        btnRequestBorrow.setBackgroundColor(
                PURPLE
        );

        btnRequestBorrow.setTextColor(
                Color.WHITE
        );


        // ================= DOT =================

        showDot(
                imageViewPager.getCurrentItem()
        );
    }


    // ==================================================
    // TEXT COLOR
    // ==================================================

    private void setMainTextColor(int color) {

        itemName.setTextColor(color);

        itemOwner.setTextColor(color);

        itemCategory.setTextColor(color);

        itemPrice.setTextColor(color);

        itemCity.setTextColor(color);

        itemDescription.setTextColor(color);

        itemLocation.setTextColor(color);

        descriptionTitle.setTextColor(color);

        locationTitle.setTextColor(color);
    }


    // ==================================================
    // RESUME
    // ==================================================

    @Override
    protected void onResume() {

        super.onResume();

        applyDarkMode();
    }
}