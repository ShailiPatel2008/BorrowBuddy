package com.borrowbuddy.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

            intent.putExtra("userName", user.getFullname());
            intent.putExtra("userEmail", user.getEmail());
            intent.putExtra("userPhone", user.getPhone());
            intent.putExtra("trustScore", user.getTrustscore());

            context.startActivity(intent);
        });



        holder.btnBlockUser.setOnClickListener(v -> {

            boolean blocked = "Blocked".equalsIgnoreCase(user.getStatus());

            boolean newActiveState = blocked;

            String title = blocked ? "Unblock User" : "Block User";

            String message = blocked
                    ? "Are you sure you want to unblock this user?"
                    : "Are you sure you want to block this user?";

            new AlertDialog.Builder(context)
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton("Yes", (dialog, which) -> {

                        db.collection("users")
                                .document(user.getUserid())
                                .update("is_active", newActiveState)
                                .addOnSuccessListener(unused -> {

                                    user.setStatus(newActiveState ? "Active" : "Blocked");

                                    notifyItemChanged(holder.getAdapterPosition());

                                })
                                .addOnFailureListener(e -> {
                                    e.printStackTrace();
                                });

                    })
                    .setNegativeButton("Cancel", null)
                    .show();

        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        TextView txtUserName;
        TextView txtEmail;
        TextView txtTrustScore;
        TextView txtStatus;

        Button btnViewProfile;
        Button btnBlockUser;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            txtUserName = itemView.findViewById(R.id.txtUserName);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            txtTrustScore = itemView.findViewById(R.id.txtTrustScore);
            txtStatus = itemView.findViewById(R.id.txtStatus);

            btnViewProfile = itemView.findViewById(R.id.btnViewProfile);
            btnBlockUser = itemView.findViewById(R.id.btnBlockUser);
        }
    }
}