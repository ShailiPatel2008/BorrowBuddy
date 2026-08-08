package com.borrowbuddy.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.borrowbuddy.app.R;
import com.borrowbuddy.app.models.ListingModel;

import java.util.List;

import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import android.graphics.Color;

public class MyListingsAdapter extends RecyclerView.Adapter<MyListingsAdapter.ViewHolder> {

    private Context context;
    private List<ListingModel> listingList;

    public MyListingsAdapter(Context context, List<ListingModel> listingList) {
        this.context = context;
        this.listingList = listingList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_listing, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ListingModel listing = listingList.get(position);

        holder.txtItemName.setText(listing.getItemName());
        holder.txtCategory.setText("Category: " + listing.getCategory());
        holder.txtDescription.setText(listing.getDescription());
        holder.txtPrice.setText("₹" + listing.getPrice() + "/Day");
        holder.txtSecurityDeposit.setText(
                "Security Deposit: ₹" + listing.getSecurityDeposit()
        );
        holder.txtLocation.setText("📍 " + listing.getLocation());
        holder.txtMobile.setText("📞 " + listing.getMobileNumber());
        holder.txtStatus.setText(listing.getStatus());

        if (listing.getStatus().equalsIgnoreCase("Available")) {

            holder.txtStatus.setBackgroundResource(R.drawable.status_available);

            holder.cardListing.setCardBackgroundColor(
                    Color.parseColor("#E8F5E9")
            );
        } else {

            holder.txtStatus.setBackgroundResource(R.drawable.status_rented);

            holder.cardListing.setCardBackgroundColor(
                    Color.parseColor("#FFF8E1")
            );

        }
        // Temporary image
        holder.imgItem.setImageResource(android.R.drawable.ic_menu_gallery);

        // Edit Button
        holder.btnEdit.setOnClickListener(v -> {
            Toast.makeText(context, "Edit feature coming soon", Toast.LENGTH_SHORT).show();
        });

        // Change Status Button
        holder.btnChangeStatus.setOnClickListener(v -> {

            if(holder.txtStatus.getText().toString().equals("Available")){

                holder.txtStatus.setText("Rented");

                holder.txtStatus.setBackgroundResource(
                        R.drawable.status_rented
                );

                holder.cardListing.setCardBackgroundColor(
                        Color.parseColor("#FFF8E1")
                );


            } else {

                holder.txtStatus.setText("Available");

                holder.txtStatus.setBackgroundResource(
                        R.drawable.status_available
                );

                holder.cardListing.setCardBackgroundColor(
                        Color.parseColor("#E8F5E9")
                );

            }

        });

        // Delete Button
        holder.btnDelete.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            builder.setTitle("Delete Item");
            builder.setMessage("Are you sure you want to delete this item?");

            builder.setPositiveButton("Delete", (dialog, which) -> {

                // Backend delete code will be added later
                Toast.makeText(context, "Item deleted (Demo)", Toast.LENGTH_SHORT).show();

            });

            builder.setNegativeButton("Cancel", (dialog, which) -> {
                dialog.dismiss();
            });

            builder.show();

        });

    }

    @Override
    public int getItemCount() {
        return listingList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgItem;

        TextView txtItemName,
                txtCategory,
                txtDescription,
                txtPrice,
                txtSecurityDeposit,
                txtLocation,
                txtMobile,

                txtStatus;

        MaterialCardView cardListing;

        Button btnEdit,
                btnDelete,
                btnChangeStatus;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgItem = itemView.findViewById(R.id.imgItem);

            txtItemName = itemView.findViewById(R.id.txtItemName);
            txtCategory = itemView.findViewById(R.id.txtCategory);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtSecurityDeposit = itemView.findViewById(R.id.txtSecurityDeposit);
            txtLocation = itemView.findViewById(R.id.txtLocation);
            txtMobile = itemView.findViewById(R.id.txtMobile);
            cardListing = itemView.findViewById(R.id.cardListing);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnChangeStatus = itemView.findViewById(R.id.btnChangeStatus);
        }
    }
}
