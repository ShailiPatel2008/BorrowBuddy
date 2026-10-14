package com.borrowbuddy.app.activities;
import android.widget.Toast;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.borrowbuddy.app.R;
import com.borrowbuddy.app.adapters.MyListingsAdapter;
import com.borrowbuddy.app.models.ListingModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
public class MyListingsActivity extends AppCompatActivity {

    private RecyclerView rvListings;

    private FloatingActionButton fabAddItem;

    private MaterialToolbar toolbar;

    private MyListingsAdapter adapter;

    private List<ListingModel> listingList;

    private LinearLayout layoutEmptyListings;
    private FirebaseFirestore db;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(
            Bundle savedInstanceState
    ) {

        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_my_listings
        );
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        // =========================
        // INITIALIZE VIEWS
        // =========================

        toolbar =
                findViewById(R.id.toolbar);

        rvListings =
                findViewById(R.id.rvListings);

        fabAddItem =
                findViewById(R.id.fabAddItem);

        layoutEmptyListings =
                findViewById(
                        R.id.layoutEmptyListings
                );


        // =========================
        // TOOLBAR
        // =========================

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {

            getSupportActionBar()
                    .setTitle("My Listings");

            getSupportActionBar()
                    .setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setTitleTextColor(
                Color.WHITE
        );

        if (toolbar.getNavigationIcon()
                != null) {

            toolbar.getNavigationIcon()
                    .setTint(Color.WHITE);
        }

        toolbar.setNavigationOnClickListener(
                v -> finish()
        );


        // =========================
        // RECYCLER VIEW
        // =========================

        rvListings.setLayoutManager(
                new LinearLayoutManager(this)
        );

        rvListings.setHasFixedSize(false);


        // =========================
        // LIST
        // =========================

        listingList =
                new ArrayList<>();



        // =========================
        // ADAPTER
        // =========================

        adapter =
                new MyListingsAdapter(
                        this,
                        listingList
                );

        rvListings.setAdapter(
                adapter
        );


        // =========================
        // EMPTY STATE
        // =========================

        updateEmptyState();
        loadMyListings();

        // =========================
        // FLOATING ADD BUTTON
        // =========================

        fabAddItem.setOnClickListener(
                v -> {

                    Intent intent =
                            new Intent(
                                    MyListingsActivity.this,
                                    AddItemActivity.class
                            );

                    startActivity(intent);
                }
        );


        // =========================
        // EMPTY SCREEN ADD BUTTON
        // =========================

        findViewById(
                R.id.btnEmptyAddItem
        ).setOnClickListener(
                v -> {

                    Intent intent =
                            new Intent(
                                    MyListingsActivity.this,
                                    AddItemActivity.class
                            );

                    startActivity(intent);
                }
        );
    }


    // =========================
    // EMPTY STATE
    // =========================

    public void updateEmptyState() {

        if (listingList.isEmpty()) {

            rvListings.setVisibility(
                    View.GONE
            );

            layoutEmptyListings.setVisibility(
                    View.VISIBLE
            );

        } else {

            rvListings.setVisibility(
                    View.VISIBLE
            );

            layoutEmptyListings.setVisibility(
                    View.GONE
            );
        }
    }
    private void loadMyListings() {

        FirebaseUser user = auth.getCurrentUser();

        if (user == null) {
            return;
        }

        String ownerId = user.getUid();

        db.collection("itemRequests")
                .whereEqualTo("ownerId", ownerId)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {

                    listingList.clear();

                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {

                        ListingModel listing = new ListingModel();
                        listing.setDocumentId(document.getId());
                        listing.setItemName(
                                document.getString("itemName")
                        );

                        listing.setCategory(
                                document.getString("category")
                        );

                        listing.setDescription(
                                document.getString("description")
                        );

                        Long price =
                                document.getLong("pricePerDay");

                        listing.setPrice(
                                price == null ? "" : String.valueOf(price)
                        );

                        Long deposit =
                                document.getLong("securitydeposit");

                        listing.setSecurityDeposit(
                                deposit == null ? "" : String.valueOf(deposit)
                        );

                        listing.setLocation(
                                document.getString("pickuplocation")
                        );

                        listing.setMobileNumber(
                                document.getString("contactnumber")
                        );

                        Long quantity =
                                document.getLong("quantity");

                        listing.setQuantity(
                                quantity == null ? 0 : quantity.intValue()
                        );

                        listing.setStatus(
                                document.getString("status")
                        );

                        listingList.add(listing);
                    }

                    adapter.notifyDataSetChanged();
                    updateEmptyState();

                })
                .addOnFailureListener(e ->

                        Toast.makeText(
                                MyListingsActivity.this,
                                e.getMessage(),
                                Toast.LENGTH_SHORT
                        ).show()
                );
    }

    // =========================
    // BACK BUTTON
    // =========================

    @Override
    public boolean onSupportNavigateUp() {

        finish();

        return true;
    }
}