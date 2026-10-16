package com.borrowbuddy.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.borrowbuddy.app.R;
import com.borrowbuddy.app.models.NotificationModel;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private Context context;
    private List<NotificationModel> notificationList;

    public NotificationAdapter(Context context, List<NotificationModel> notificationList) {
        this.context = context;
        this.notificationList = notificationList;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_notification, parent, false);

        return new NotificationViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {

        NotificationModel model = notificationList.get(position);

        holder.txtTitle.setText(model.getTitle());

        holder.txtMessage.setText(model.getMessage());

        holder.txtTime.setText(model.getTimestamp());
    }


    @Override
    public int getItemCount() {
        return notificationList.size();
    }


    static class NotificationViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle;
        TextView txtMessage;
        TextView txtTime;


        NotificationViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);

            txtMessage = itemView.findViewById(R.id.txtMessage);

            txtTime = itemView.findViewById(R.id.txtTime);
        }
    }
}