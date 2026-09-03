package com.borrowbuddy.app.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.borrowbuddy.app.R;
import com.borrowbuddy.app.activities.RequestDetailsActivity;
import com.borrowbuddy.app.models.RequestModel;

import java.util.List;
import java.util.ArrayList;

public class RequestStatusAdapter
        extends RecyclerView.Adapter<RequestStatusAdapter.ViewHolder> {

    private Context context;
    private List<RequestModel> requestList;

    public RequestStatusAdapter(
            Context context,
            List<RequestModel> requestList) {

        this.context = context;
        this.requestList = requestList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(
                        R.layout.item_request_status,
                        parent,
                        false
                );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position) {

        RequestModel request = requestList.get(position);

        // Item name
        holder.tvItemName.setText(
                request.getItemName()
        );

        // Customer name
        holder.tvCustomerName.setText(
                request.getCustomerName()
        );

        // Rental period
        holder.tvDates.setText(
                "Rental Period: " + request.getRentalPeriod()
        );

        // Quantity
        holder.tvQuantity.setText(
                "Quantity: " + request.getQuantity()
        );

        // Status
        holder.tvStatus.setText(
                request.getStatus()
        );

        // Temporary item image
        holder.imgRequestItem.setImageResource(
                android.R.drawable.ic_menu_gallery
        );

        // View Details
        holder.btnViewDetails.setOnClickListener(v -> {

            Intent intent = new Intent(
                    context,
                    RequestDetailsActivity.class
            );

            intent.putExtra(
                    "itemName",
                    request.getItemName()
            );

            intent.putExtra(
                    "customerName",
                    request.getCustomerName()
            );

            intent.putExtra(
                    "customerMobile",
                    request.getCustomerMobile()
            );

            intent.putExtra(
                    "rentalPeriod",
                    request.getRentalPeriod()
            );

            intent.putExtra(
                    "rent",
                    request.getRent()
            );

            intent.putExtra(
                    "quantity",
                    request.getQuantity()
            );

            intent.putExtra(
                    "status",
                    request.getStatus()
            );

            intent.putExtra(
                    "itemStatus",
                    request.getItemStatus()
            );

            ArrayList<String> imageUrls =
                    new ArrayList<>();

            if (request.getImageUrls() != null) {

                imageUrls.addAll(
                        request.getImageUrls()
                );
            }

            intent.putStringArrayListExtra(
                    "imageUrls",
                    imageUrls
            );


            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return requestList.size();
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        ImageView imgRequestItem;

        TextView tvItemName;
        TextView tvCustomerName;
        TextView tvDates;
        TextView tvQuantity;
        TextView tvStatus;

        Button btnViewDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgRequestItem =
                    itemView.findViewById(
                            R.id.imgRequestItem
                    );

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