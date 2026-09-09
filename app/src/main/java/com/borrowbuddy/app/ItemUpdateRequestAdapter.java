package com.borrowbuddy.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemUpdateRequestAdapter
        extends RecyclerView.Adapter<ItemUpdateRequestAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> itemNames;
    private ArrayList<String> owners;
    private ArrayList<String> changes;
    private ArrayList<String> dates;

    public ItemUpdateRequestAdapter(
            Context context,
            ArrayList<String> itemNames,
            ArrayList<String> owners,
            ArrayList<String> changes,
            ArrayList<String> dates) {

        this.context = context;
        this.itemNames = itemNames;
        this.owners = owners;
        this.changes = changes;
        this.dates = dates;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(
                        R.layout.item_update_request,
                        parent,
                        false
                );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position) {

        holder.txtItemName.setText(itemNames.get(position));
        holder.txtOwner.setText("Owner: " + owners.get(position));
        holder.txtChanges.setText("Changes: " + changes.get(position));
        holder.txtDate.setText("Date: " + dates.get(position));

        holder.btnViewRequest.setOnClickListener(v -> {

            if (context instanceof ItemUpdateRequestsActivity) {

                android.content.Intent intent =
                        new android.content.Intent(
                                context,
                                UpdateRequestDetailsActivity.class
                        );

                intent.putExtra(
                        "itemName",
                        itemNames.get(position)
                );

                intent.putExtra(
                        "owner",
                        owners.get(position)
                );

                intent.putExtra(
                        "changes",
                        changes.get(position)
                );

                ((ItemUpdateRequestsActivity) context)
                        .startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemNames.size();
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        TextView txtItemName;
        TextView txtOwner;
        TextView txtChanges;
        TextView txtDate;

        Button btnViewRequest;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtItemName =
                    itemView.findViewById(R.id.txtItemName);

            txtOwner =
                    itemView.findViewById(R.id.txtOwner);

            txtChanges =
                    itemView.findViewById(R.id.txtChanges);

            txtDate =
                    itemView.findViewById(R.id.txtDate);

            btnViewRequest =
                    itemView.findViewById(R.id.btnViewRequest);
        }
    }
}