package com.borrowbuddy.app;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PhotoAdapter
        extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private final Context context;
    private final ArrayList<String> photoList;


    public PhotoAdapter(
            Context context,
            ArrayList<String> photoList
    ) {

        this.context = context;
        this.photoList = photoList;
    }


    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {

        LinearLayout layout =
                new LinearLayout(context);

        layout.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
        );

        layout.setGravity(Gravity.CENTER);

        layout.setOrientation(
                LinearLayout.VERTICAL
        );

        layout.setBackgroundColor(
                Color.LTGRAY
        );


        TextView camera =
                new TextView(context);

        camera.setText("📷");
        camera.setTextSize(55);
        camera.setGravity(Gravity.CENTER);


        TextView photoText =
                new TextView(context);

        photoText.setTextSize(14);
        photoText.setTextColor(Color.DKGRAY);
        photoText.setGravity(Gravity.CENTER);


        layout.addView(camera);

        layout.addView(photoText);


        return new PhotoViewHolder(
                layout,
                photoText
        );
    }


    @Override
    public void onBindViewHolder(
            @NonNull PhotoViewHolder holder,
            int position
    ) {

        holder.photoText.setText(
                "Photo " + (position + 1)
        );
    }


    @Override
    public int getItemCount() {

        return Math.min(
                photoList.size(),
                5
        );
    }


    static class PhotoViewHolder
            extends RecyclerView.ViewHolder {

        TextView photoText;


        public PhotoViewHolder(
                @NonNull LinearLayout itemView,
                TextView photoText
        ) {

            super(itemView);

            this.photoText = photoText;
        }
    }
}