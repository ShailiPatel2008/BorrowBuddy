package com.borrowbuddy.app.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.borrowbuddy.app.R;
import com.borrowbuddy.app.activities.EditItemActivity;
import com.borrowbuddy.app.activities.MyListingsActivity;
import com.borrowbuddy.app.models.ListingModel;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class MyListingsAdapter
        extends RecyclerView.Adapter<MyListingsAdapter.ViewHolder> {

    private Context context;
    private List<ListingModel> listingList;

    public MyListingsAdapter(
            Context context,
            List<ListingModel> listingList
    ) {
        this.context = context;
        this.listingList = listingList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_listing, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position
    ) {

        ListingModel listing = listingList.get(position);

        // -------------------------
        // ITEM INFORMATION
        // -------------------------

        holder.txtItemName.setText(
                listing.getItemName()
        );

        holder.txtCategory.setText(
                listing.getCategory()
        );

        holder.txtDescription.setText(
                listing.getDescription()
        );

        holder.txtPrice.setText(
                "₹" + listing.getPrice() + " / day"
        );

        holder.txtQuantity.setText(
                listing.getQuantity() + " available"
        );

        holder.txtSecurityDeposit.setText(
                "Security deposit: ₹"
                        + listing.getSecurityDeposit()
        );

        holder.txtLocation.setText(
                "📍 " + listing.getLocation()
        );

        holder.txtMobile.setText(
                "📞 " + listing.getMobileNumber()
        );

        holder.txtStatus.setText(
                listing.getStatus()
        );

        // -------------------------
        // PRODUCT IMAGES
        // -------------------------

        List<String> imageUrls =
                listing.getImageUrls();

        if (imageUrls == null ||
                imageUrls.isEmpty()) {

            imageUrls = new ArrayList<>();

            imageUrls.add(
                    "android.resource://"
                            + context.getPackageName()
                            + "/"
                            + android.R.drawable.ic_menu_gallery
            );
        }

        ListingImageAdapter imageAdapter =
                new ListingImageAdapter(imageUrls);

        holder.viewPagerImages.setAdapter(
                imageAdapter
        );

        setupDots(
                holder,
                imageUrls.size()
        );

        // Prevent multiple callbacks from being added
        if (holder.pageChangeCallback != null) {

            holder.viewPagerImages.unregisterOnPageChangeCallback(
                    holder.pageChangeCallback
            );
        }

        holder.pageChangeCallback =
                new ViewPager2.OnPageChangeCallback() {

                    @Override
                    public void onPageSelected(
                            int position
                    ) {

                        updateActiveDot(
                                holder,
                                position
                        );
                    }
                };

        holder.viewPagerImages.registerOnPageChangeCallback(
                holder.pageChangeCallback
        );

        // -------------------------
        // STATUS DESIGN
        // -------------------------

        updateStatusDesign(
                holder,
                listing.getStatus()
        );

        // -------------------------
        // EDIT BUTTON
        // -------------------------

        holder.btnEdit.setOnClickListener(v -> {

            Intent intent = new Intent(
                    context,
                    EditItemActivity.class
            );

            intent.putExtra(
                    "itemName",
                    listing.getItemName()
            );

            intent.putExtra(
                    "category",
                    listing.getCategory()
            );

            intent.putExtra(
                    "description",
                    listing.getDescription()
            );

            intent.putExtra(
                    "price",
                    listing.getPrice()
            );

            intent.putExtra(
                    "securityDeposit",
                    listing.getSecurityDeposit()
            );

            intent.putExtra(
                    "location",
                    listing.getLocation()
            );

            intent.putExtra(
                    "mobile",
                    listing.getMobileNumber()
            );

            intent.putExtra(
                    "quantity",
                    listing.getQuantity()
            );

            // -------------------------
            // SEND EXISTING IMAGES
            // -------------------------

            List<String> currentImages =
                    listing.getImageUrls();

            if (currentImages != null) {

                intent.putStringArrayListExtra(
                        "imageUrls",
                        new ArrayList<>(currentImages)
                );
            }

            context.startActivity(intent);
        });

        // -------------------------
        // CHANGE STATUS
        // -------------------------

        holder.btnChangeStatus.setOnClickListener(v -> {

            String currentStatus =
                    holder.txtStatus.getText().toString();

            if (currentStatus.equalsIgnoreCase(
                    "Available"
            )) {

                holder.txtStatus.setText(
                        "Rented"
                );

                updateStatusDesign(
                        holder,
                        "Rented"
                );

            } else {

                holder.txtStatus.setText(
                        "Available"
                );

                updateStatusDesign(
                        holder,
                        "Available"
                );
            }
        });

        // -------------------------
        // DELETE
        // -------------------------

        holder.btnDelete.setOnClickListener(v -> {

            AlertDialog.Builder builder =
                    new AlertDialog.Builder(context);

            builder.setTitle(
                    "Delete Item"
            );

            builder.setMessage(
                    "Are you sure you want to delete \""
                            + listing.getItemName()
                            + "\"?"
            );

            builder.setPositiveButton(
                    "Delete",
                    (dialog, which) -> {

                        int currentPosition =
                                holder.getAdapterPosition();

                        if (currentPosition !=
                                RecyclerView.NO_POSITION) {

                            listingList.remove(
                                    currentPosition
                            );

                            notifyItemRemoved(
                                    currentPosition
                            );

                            if (listingList.isEmpty()
                                    && context instanceof
                                    MyListingsActivity) {

                                ((MyListingsActivity) context)
                                        .updateEmptyState();
                            }

                            Toast.makeText(
                                    context,
                                    "Item deleted successfully",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
            );

            builder.setNegativeButton(
                    "Cancel",
                    (dialog, which) ->
                            dialog.dismiss()
            );

            builder.show();
        });
    }

    // -------------------------
    // STATUS DESIGN
    // -------------------------

    private void updateStatusDesign(
            ViewHolder holder,
            String status
    ) {

        holder.txtStatus.setText(status);

        if (status.equalsIgnoreCase(
                "Available"
        )) {

            holder.txtStatus.setBackgroundResource(
                    R.drawable.status_available
            );

        } else {

            holder.txtStatus.setBackgroundResource(
                    R.drawable.status_rented
            );
        }
    }

    // -------------------------
    // CREATE IMAGE DOTS
    // -------------------------

    private void setupDots(
            ViewHolder holder,
            int imageCount
    ) {

        holder.dotsContainer.removeAllViews();

        if (imageCount <= 1) {

            holder.dotsContainer.setVisibility(
                    View.GONE
            );

            return;
        }

        holder.dotsContainer.setVisibility(
                View.VISIBLE
        );

        for (int i = 0;
             i < imageCount;
             i++) {

            TextView dot =
                    new TextView(context);

            dot.setText("●");
            dot.setTextSize(10);
            dot.setTextColor(Color.LTGRAY);

            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(
                            dpToPx(12),
                            dpToPx(12)
                    );

            dot.setLayoutParams(params);

            holder.dotsContainer.addView(
                    dot
            );
        }

        updateActiveDot(
                holder,
                0
        );
    }

    // -------------------------
    // UPDATE ACTIVE DOT
    // -------------------------

    private void updateActiveDot(
            ViewHolder holder,
            int position
    ) {

        for (
                int i = 0;
                i < holder.dotsContainer.getChildCount();
                i++
        ) {

            TextView dot =
                    (TextView)
                            holder.dotsContainer
                                    .getChildAt(i);

            if (i == position) {

                dot.setTextColor(
                        Color.WHITE
                );

            } else {

                dot.setTextColor(
                        Color.LTGRAY
                );
            }
        }
    }

    // -------------------------
    // DP TO PX
    // -------------------------

    private int dpToPx(int dp) {

        float density =
                context.getResources()
                        .getDisplayMetrics()
                        .density;

        return Math.round(
                dp * density
        );
    }

    @Override
    public int getItemCount() {

        return listingList.size();
    }

    // =========================
    // VIEW HOLDER
    // =========================

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        ViewPager2 viewPagerImages;

        LinearLayout dotsContainer;

        TextView txtItemName;
        TextView txtCategory;
        TextView txtDescription;
        TextView txtPrice;
        TextView txtQuantity;
        TextView txtSecurityDeposit;
        TextView txtLocation;
        TextView txtMobile;
        TextView txtStatus;

        MaterialCardView cardListing;

        Button btnEdit;
        Button btnDelete;
        Button btnChangeStatus;

        ViewPager2.OnPageChangeCallback
                pageChangeCallback;

        public ViewHolder(
                @NonNull View itemView
        ) {

            super(itemView);

            viewPagerImages =
                    itemView.findViewById(
                            R.id.viewPagerImages
                    );

            dotsContainer =
                    itemView.findViewById(
                            R.id.dotsContainer
                    );

            txtItemName =
                    itemView.findViewById(
                            R.id.txtItemName
                    );

            txtCategory =
                    itemView.findViewById(
                            R.id.txtCategory
                    );

            txtDescription =
                    itemView.findViewById(
                            R.id.txtDescription
                    );

            txtPrice =
                    itemView.findViewById(
                            R.id.txtPrice
                    );

            txtQuantity =
                    itemView.findViewById(
                            R.id.txtQuantity
                    );

            txtSecurityDeposit =
                    itemView.findViewById(
                            R.id.txtSecurityDeposit
                    );

            txtLocation =
                    itemView.findViewById(
                            R.id.txtLocation
                    );

            txtMobile =
                    itemView.findViewById(
                            R.id.txtMobile
                    );

            txtStatus =
                    itemView.findViewById(
                            R.id.txtStatus
                    );

            cardListing =
                    itemView.findViewById(
                            R.id.cardListing
                    );

            btnEdit =
                    itemView.findViewById(
                            R.id.btnEdit
                    );

            btnDelete =
                    itemView.findViewById(
                            R.id.btnDelete
                    );

            btnChangeStatus =
                    itemView.findViewById(
                            R.id.btnChangeStatus
                    );
        }
    }
}

