package com.borrowbuddy.app.adapters;

import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.borrowbuddy.app.R;

import java.util.ArrayList;

public class EditItemImageAdapter
        extends RecyclerView.Adapter<EditItemImageAdapter.ImageViewHolder> {

    private final ArrayList<Object> imageList;
    private final OnImageClickListener listener;

    // =========================
    // IMAGE CLICK LISTENER
    // =========================

    public interface OnImageClickListener {
        void onImageClick(Object image);
    }

    // =========================
    // CONSTRUCTOR
    // =========================

    public EditItemImageAdapter(
            ArrayList<Object> imageList,
            OnImageClickListener listener) {

        this.imageList = imageList;
        this.listener = listener;
    }

    // =========================
    // CREATE VIEW HOLDER
    // =========================

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.item_edit_image,
                        parent,
                        false
                );

        return new ImageViewHolder(view);
    }

    // =========================
    // BIND IMAGE
    // =========================

    @Override
    public void onBindViewHolder(
            @NonNull ImageViewHolder holder,
            int position) {

        Object image = imageList.get(position);

        // Clear old image
        holder.imageView.setImageDrawable(null);

        // Gallery image
        if (image instanceof Uri) {

            holder.imageView.setImageURI(
                    (Uri) image
            );

        }

        // Camera image
        else if (image instanceof Bitmap) {

            holder.imageView.setImageBitmap(
                    (Bitmap) image
            );
        }

        // Click image
        holder.imageView.setOnClickListener(v -> {

            if (listener != null) {

                listener.onImageClick(image);
            }
        });
    }

    // =========================
    // NUMBER OF IMAGES
    // =========================

    @Override
    public int getItemCount() {

        return imageList.size();
    }

    // =========================
    // VIEW HOLDER
    // =========================

    static class ImageViewHolder
            extends RecyclerView.ViewHolder {

        ImageView imageView;

        ImageViewHolder(
                @NonNull View itemView) {

            super(itemView);

            imageView =
                    itemView.findViewById(
                            R.id.imgEditPager
                    );
        }
    }
}
