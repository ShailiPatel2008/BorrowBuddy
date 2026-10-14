package com.borrowbuddy.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ManageUserAdapter extends RecyclerView.Adapter<ManageUserAdapter.UserViewHolder> {

    private Context context;
    private ArrayList<UserModel> userList;
    private FirebaseFirestore db;

    public ManageUserAdapter(Context context, ArrayList<UserModel> userList) {
        this.context = context;
        this.userList = userList;
        db = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_user, parent, false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        UserModel user = userList.get(position);

        SharedPreferences preferences =
                context.getSharedPreferences("Settings", Context.MODE_PRIVATE);

        boolean darkMode =
                preferences.getBoolean("dark_mode", false);

        if (darkMode) {

            holder.userCard.setBackgroundColor(Color.rgb(35,35,35));

            holder.txtUserName.setTextColor(Color.WHITE);
            holder.txtEmail.setTextColor(Color.WHITE);
            holder.txtTrustScore.setTextColor(Color.WHITE);

        } else {

            holder.userCard.setBackgroundColor(Color.rgb(238,238,238));

            holder.txtUserName.setTextColor(Color.BLACK);
            holder.txtEmail.setTextColor(Color.BLACK);
            holder.txtTrustScore.setTextColor(Color.BLACK);
        }

        holder.txtUserName.setText(user.getFullname());
        holder.txtEmail.setText("Email: " + user.getEmail());
        holder.txtTrustScore.setText("Trust Score: " + user.getTrustscore());
        holder.txtStatus.setText("Status: " + user.getStatus());

        if ("Blocked".equalsIgnoreCase(user.getStatus())) {
            holder.btnBlockUser.setText("Unblock User");
            holder.txtStatus.setTextColor(Color.RED);
        } else {
            holder.btnBlockUser.setText("Block User");
            holder.txtStatus.setTextColor(Color.rgb(76,175,80));
        }

        holder.btnViewProfile.setOnClickListener(v -> {

            Intent intent = new Intent(context, UserProfileActivity.class);
            intent.putExtra("USER_ID", user.getUserid());
            context.startActivity(intent);

        });

        holder.btnBlockUser.setOnClickListener(v -> {

            boolean blocked = "Blocked".equalsIgnoreCase(user.getStatus());

            boolean newActiveState = blocked;

            String title = blocked ? "Unblock User" : "Block User";

            String message = blocked
                    ? "Are you sure you want to unblock this user?"
                    : "Are you sure you want to block this user?";

            AlertDialog dialog = new AlertDialog.Builder(context)
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton("Yes", (dialogInterface, which) -> {

                        db.collection("users")
                                .document(user.getUserid())
                                .update("is_active", newActiveState)
                                .addOnSuccessListener(unused -> {

                                    user.setStatus(newActiveState ? "Active" : "Blocked");

                                    notifyItemChanged(holder.getAdapterPosition());

                                })
                                .addOnFailureListener(e -> e.printStackTrace());

                    })
                    .setNegativeButton("Cancel", null)
                    .create();

            dialog.show();



// Dialog background
            if (dialog.getWindow() != null) {
                if (darkMode) {
                    dialog.getWindow().setBackgroundDrawable(
                            new android.graphics.drawable.ColorDrawable(
                                    Color.rgb(55, 55, 55)
                            )
                    );
                } else {
                    dialog.getWindow().setBackgroundDrawable(
                            new android.graphics.drawable.ColorDrawable(
                                    Color.WHITE
                            )
                    );
                }
            }

// Title
            int titleId = context.getResources().getIdentifier(
                    "alertTitle",
                    "id",
                    "android"
            );

            TextView titleText = dialog.findViewById(titleId);

            if (titleText != null) {
                titleText.setTextColor(darkMode ? Color.WHITE : Color.BLACK);
            }

// Message
            TextView messageText = dialog.findViewById(android.R.id.message);

            if (messageText != null) {
                messageText.setTextColor(darkMode ? Color.WHITE : Color.BLACK);
            }

// Buttons
            dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                    .setTextColor(darkMode ? Color.WHITE : Color.rgb(108,74,182));

            dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                    .setTextColor(darkMode ? Color.WHITE : Color.rgb(108,74,182));

        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        LinearLayout userCard;

        TextView txtUserName;
        TextView txtEmail;
        TextView txtTrustScore;
        TextView txtStatus;

        Button btnViewProfile;
        Button btnBlockUser;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            userCard = itemView.findViewById(R.id.userCard);

            txtUserName = itemView.findViewById(R.id.txtUserName);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            txtTrustScore = itemView.findViewById(R.id.txtTrustScore);
            txtStatus = itemView.findViewById(R.id.txtStatus);

            btnViewProfile = itemView.findViewById(R.id.btnViewProfile);
            btnBlockUser = itemView.findViewById(R.id.btnBlockUser);
        }
    }
}