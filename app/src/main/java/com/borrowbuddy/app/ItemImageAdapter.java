package com.borrowbuddy.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

            holder.imageView.setImageResource(
                    images.get(position)
            );
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