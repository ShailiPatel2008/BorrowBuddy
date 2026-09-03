package com.borrowbuddy.app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.borrowbuddy.app.R;

import java.util.List;

public class RequestImageAdapter
        extends RecyclerView.Adapter<RequestImageAdapter.ImageViewHolder> {

    private final List<String> imageList;
    private final OnImageClickListener listener;

    // =========================================================
    // IMAGE CLICK LISTENER
    // =========================================================

    public interface OnImageClickListener {
        void onImageClick(String imageUri);
    }

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public RequestImageAdapter(
            List<String> imageList,
            OnImageClickListener listener) {

        this.imageList = imageList;
        this.listener = listener;
    }

    // =========================================================
    // CREATE VIEW HOLDER
    // =========================================================

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.item_request_image,
                        parent,
                        false
                );

        return new ImageViewHolder(view);
    }

    // =========================================================
    // BIND IMAGE
    // =========================================================

    @Override
    public void onBindViewHolder(
            @NonNull ImageViewHolder holder,
            int position) {

        String imageUriString = imageList.get(position);

        /*
         * Currently the project may contain local Uri strings.
         * Convert the String back to Uri and display it.
         */

        if (imageUriString != null &&
                !imageUriString.isEmpty()) {

            try {

                holder.imageView.setImageURI(
                        android.net.Uri.parse(imageUriString)
                );

            } catch (Exception e) {

                holder.imageView.setImageResource(
                        android.R.drawable.ic_menu_gallery
                );
            }

        } else {

            holder.imageView.setImageResource(
                    android.R.drawable.ic_menu_gallery
            );
        }

        // =====================================================
        // OPEN FULL SCREEN IMAGE
        // =====================================================

        holder.imageView.setOnClickListener(v -> {

            if (listener != null &&
                    imageUriString != null &&
                    !imageUriString.isEmpty()) {

                listener.onImageClick(imageUriString);
            }
        });
    }

    // =========================================================
    // ITEM COUNT
    // =========================================================

    @Override
    public int getItemCount() {

        return imageList == null
                ? 0
                : imageList.size();
    }

    // =========================================================
    // VIEW HOLDER
    // =========================================================

    public static class ImageViewHolder
            extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ImageViewHolder(
                @NonNull View itemView) {

            super(itemView);

            imageView = itemView.findViewById(
                    R.id.imgRequest
            );
        }
    }
}