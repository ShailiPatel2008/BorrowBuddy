package com.borrowbuddy.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.borrowbuddy.app.R;
import com.borrowbuddy.app.models.RentalHistoryModel;

import java.util.List;

import android.content.Intent;
import com.borrowbuddy.app.activities.BorrowerRatingActivity;
import android.widget.Button;

public class RentalHistoryAdapter extends RecyclerView.Adapter<RentalHistoryAdapter.ViewHolder> {

    Context context;
    List<RentalHistoryModel> historyList;

    public RentalHistoryAdapter(Context context, List<RentalHistoryModel> historyList) {
        this.context = context;
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_rental_history, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RentalHistoryModel model = historyList.get(position);

        holder.txtItemName.setText(model.getItemName());
        holder.txtBorrowedBy.setText("Borrowed By: " + model.getBorrowerName());
        holder.txtRentalPeriod.setText("Rental Period: " + model.getRentalPeriod());
        holder.txtTotalEarned.setText("Total Earned: ₹" + model.getTotalEarned());
        holder.txtStatus.setText(model.getStatus());
        holder.btnRateBorrower.setOnClickListener(v -> {

            Intent intent =
                    new Intent(context, BorrowerRatingActivity.class);

            context.startActivity(intent);

        });

        // Firebase image will be loaded here later
        holder.imgItem.setImageResource(android.R.drawable.ic_menu_gallery);
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgItem;
        TextView txtItemName, txtBorrowedBy, txtRentalPeriod,
                txtTotalEarned, txtStatus;
        Button btnRateBorrower;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgItem = itemView.findViewById(R.id.imgItem);
            txtItemName = itemView.findViewById(R.id.txtItemName);
            txtBorrowedBy = itemView.findViewById(R.id.txtBorrowedBy);
            txtRentalPeriod = itemView.findViewById(R.id.txtRentalPeriod);
            txtTotalEarned = itemView.findViewById(R.id.txtTotalEarned);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            btnRateBorrower = itemView.findViewById(R.id.btnRateBorrower);

        }
    }
}