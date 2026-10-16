package com.borrowbuddy.app;

import android.app.Dialog;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ItemImageAdapter
        extends RecyclerView.Adapter<ItemImageAdapter.ViewHolder> {

    private List<String> images;

    public ItemImageAdapter(List<String> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position) {

        String imageUrl = images.get(position);

        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .into(holder.imageView);

        holder.imageView.setOnClickListener(v -> {

            Dialog dialog = new Dialog(v.getContext());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

            ImageView fullImage = new ImageView(v.getContext());
            fullImage.setBackgroundColor(Color.BLACK);
            fullImage.setScaleType(ImageView.ScaleType.FIT_CENTER);

            Glide.with(v.getContext())
                    .load(imageUrl)
                    .into(fullImage);

            fullImage.setOnClickListener(view -> dialog.dismiss());

            dialog.setContentView(fullImage);

            dialog.show();

            Window window = dialog.getWindow();

            if (window != null) {

                window.setBackgroundDrawableResource(android.R.color.black);

                window.setLayout(
                        WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.MATCH_PARENT
                );
            }
        });
    }

    @Override
    public int getItemCount() {
        return images == null ? 0 : images.size();
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.itemImage);
        }
    }
}