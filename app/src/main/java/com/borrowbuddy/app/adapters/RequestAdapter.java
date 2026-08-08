package com.borrowbuddy.app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.borrowbuddy.app.R;
import com.borrowbuddy.app.models.MyItemRequestModel;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder> {

    private List<MyItemRequestModel> requestList;

    public RequestAdapter(List<MyItemRequestModel> requestList) {
        this.requestList = requestList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_request, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MyItemRequestModel request = requestList.get(position);

        holder.txtItemName.setText(request.getItemName());
        holder.txtCategory.setText("Category: " + request.getCategory());
        holder.txtPrice.setText("Price: ₹" + request.getPrice() + "/day");

        String status = request.getStatus();

        if (status.equalsIgnoreCase("Pending")) {
            holder.txtStatus.setText("🟡 Pending");
        } else if (status.equalsIgnoreCase("Approved")) {
            holder.txtStatus.setText("🟢 Approved");
        } else {
            holder.txtStatus.setText("🔴 Rejected");
        }

        if (status.equalsIgnoreCase("Rejected")
                && request.getReason() != null
                && !request.getReason().isEmpty()) {

            holder.txtReason.setVisibility(View.VISIBLE);
            holder.txtReason.setText("Reason: " + request.getReason());

        } else {
            holder.txtReason.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return requestList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtItemName, txtCategory, txtPrice, txtStatus, txtReason;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtItemName = itemView.findViewById(R.id.txtItemName);
            txtCategory = itemView.findViewById(R.id.txtCategory);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            txtReason = itemView.findViewById(R.id.txtReason);
        }
    }
}