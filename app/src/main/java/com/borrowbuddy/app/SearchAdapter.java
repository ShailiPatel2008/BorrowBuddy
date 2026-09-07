package com.borrowbuddy.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class SearchAdapter
        extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private final ArrayList<SearchItem> itemList;

    public SearchAdapter(ArrayList<SearchItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.item_search_result,
                        parent,
                        false
                );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position) {

        SearchItem item = itemList.get(position);

        // =========================
        // ITEM DATA
        // =========================

        holder.itemName.setText(item.name);
        holder.itemPrice.setText(item.price);
        holder.itemLocation.setText(item.location);
        holder.itemRating.setText(item.rating);

        holder.itemImage.setImageResource(item.image);

        // =========================
        // DARK MODE
        // =========================

        SharedPreferences preferences =
                holder.itemView.getContext()
                        .getSharedPreferences(
                                "BorrowBuddy",
                                Context.MODE_PRIVATE
                        );

        boolean darkMode =
                preferences.getBoolean(
                        "darkMode",
                        false
                );

        if (darkMode) {

            holder.itemCard.setCardBackgroundColor(
                    Color.rgb(43, 41, 50)
            );

            holder.itemName.setTextColor(Color.WHITE);
            holder.itemPrice.setTextColor(Color.WHITE);
            holder.itemLocation.setTextColor(Color.WHITE);

        } else {

            holder.itemCard.setCardBackgroundColor(
                    Color.WHITE
            );

            holder.itemName.setTextColor(Color.BLACK);
            holder.itemPrice.setTextColor(Color.BLACK);
            holder.itemLocation.setTextColor(Color.BLACK);
        }

        // Rating always yellow
        holder.itemRating.setTextColor(
                Color.rgb(255, 193, 7)
        );

        // =========================
        // OPEN ITEM DETAILS
        // =========================

        holder.itemCard.setOnClickListener(v -> {

            Context context = v.getContext();

            Intent intent = new Intent(
                    context,
                    ItemDetailsActivity.class
            );

            intent.putExtra(
                    "name",
                    item.name
            );

            intent.putExtra(
                    "price",
                    item.price
            );

            intent.putExtra(
                    "location",
                    item.location
            );

            intent.putExtra(
                    "rating",
                    item.rating
            );

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    // =========================
    // VIEW HOLDER
    // =========================

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        MaterialCardView itemCard;

        ImageView itemImage;

        TextView itemName;
        TextView itemPrice;
        TextView itemLocation;
        TextView itemRating;

        public ViewHolder(
                @NonNull View itemView) {

            super(itemView);

            itemCard = itemView.findViewById(
                    R.id.itemCard
            );

            itemImage = itemView.findViewById(
                    R.id.itemImage
            );

            itemName = itemView.findViewById(
                    R.id.itemName
            );

            itemPrice = itemView.findViewById(
                    R.id.itemPrice
            );

            itemLocation = itemView.findViewById(
                    R.id.itemLocation
            );

            itemRating = itemView.findViewById(
                    R.id.itemRating
            );
        }
    }
}