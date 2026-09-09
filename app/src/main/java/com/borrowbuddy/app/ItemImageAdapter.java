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

import java.util.List;

public class ItemImageAdapter
        extends RecyclerView.Adapter<ItemImageAdapter.ViewHolder> {

    private List<Integer> images;

    public ItemImageAdapter(List<Integer> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.item_image,
                        parent,
                        false
                );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position) {

        if (images != null &&
                position < images.size()) {

            int imageResource = images.get(position);

            holder.imageView.setImageResource(
                    imageResource
            );

            // =========================
            // FULL SCREEN IMAGE
            // =========================

            holder.imageView.setOnClickListener(v -> {

                final Dialog dialog =
                        new Dialog(v.getContext());

                dialog.requestWindowFeature(
                        Window.FEATURE_NO_TITLE
                );

                ImageView fullImageView =
                        new ImageView(v.getContext());

                fullImageView.setLayoutParams(
                        new ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                        )
                );

                fullImageView.setScaleType(
                        ImageView.ScaleType.FIT_CENTER
                );

                fullImageView.setBackgroundColor(
                        Color.BLACK
                );

                fullImageView.setImageResource(
                        imageResource
                );

                dialog.setContentView(
                        fullImageView
                );

                // Tap image to close full screen
                fullImageView.setOnClickListener(
                        view -> dialog.dismiss()
                );

                dialog.show();

                Window window =
                        dialog.getWindow();

                if (window != null) {

                    window.setBackgroundDrawableResource(
                            android.R.color.black
                    );

                    window.setLayout(
                            WindowManager.LayoutParams.MATCH_PARENT,
                            WindowManager.LayoutParams.MATCH_PARENT
                    );
                }
            });
        }
    }

    @Override
    public int getItemCount() {

        if (images == null) {
            return 0;
        }

        return images.size();
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ViewHolder(
                @NonNull View itemView) {

            super(itemView);

            imageView = itemView.findViewById(
                    R.id.itemImage
            );
        }
    }
}
