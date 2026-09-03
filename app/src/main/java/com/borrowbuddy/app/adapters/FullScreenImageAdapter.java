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

public class FullScreenImageAdapter
        extends RecyclerView.Adapter<FullScreenImageAdapter.ImageViewHolder> {

    private final ArrayList<Object> imageList;

    public FullScreenImageAdapter(
            ArrayList<Object> imageList) {

        this.imageList = imageList;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(
                                R.layout.item_full_screen_image,
                                parent,
                                false
                        );

        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ImageViewHolder holder,
            int position) {

        Object image =
                imageList.get(position);

        if (image instanceof Uri) {

            holder.imageView.setImageURI(
                    (Uri) image
            );

        } else if (image instanceof Bitmap) {

            holder.imageView.setImageBitmap(
                    (Bitmap) image
            );
        }
    }

    @Override
    public int getItemCount() {

        return imageList.size();
    }

    static class ImageViewHolder
            extends RecyclerView.ViewHolder {

        ImageView imageView;

        ImageViewHolder(
                @NonNull View itemView) {

            super(itemView);

            imageView =
                    itemView.findViewById(
                            R.id.imgFullScreen
                    );
        }
    }
}

