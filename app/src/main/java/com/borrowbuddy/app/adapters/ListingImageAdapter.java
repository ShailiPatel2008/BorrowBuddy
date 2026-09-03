package com.borrowbuddy.app.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.borrowbuddy.app.R;

import java.util.List;

public class ListingImageAdapter
        extends RecyclerView.Adapter<ListingImageAdapter.ImageViewHolder> {

    private List<String> imageUrls;

    public ListingImageAdapter(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_listing_image, parent, false);

        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ImageViewHolder holder,
            int position
    ) {

        String imageUrl = imageUrls.get(position);

        holder.imageView.setImageURI(
                Uri.parse(imageUrl)
        );
    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }

    public static class ImageViewHolder
            extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ImageViewHolder(
                @NonNull View itemView
        ) {

            super(itemView);

            imageView =
                    itemView.findViewById(
                            R.id.imgListing
                    );
        }
    }
}