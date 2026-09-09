package com.borrowbuddy.app;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SimplePhotoAdapter
        extends RecyclerView.Adapter<SimplePhotoAdapter.PhotoViewHolder> {

    private final Context context;

    public SimplePhotoAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        LinearLayout layout = new LinearLayout(context);

        layout.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
        );

        layout.setGravity(Gravity.CENTER);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(Color.parseColor("#F3F0FA"));

        TextView camera = new TextView(context);

        camera.setText("📷");
        camera.setTextSize(55);
        camera.setGravity(Gravity.CENTER);

        layout.addView(camera);

        TextView text = new TextView(context);

        text.setText("Item Photo");
        text.setTextSize(16);
        text.setTextColor(Color.DKGRAY);
        text.setGravity(Gravity.CENTER);

        layout.addView(text);

        return new PhotoViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(
            @NonNull PhotoViewHolder holder,
            int position) {

        TextView camera =
                (TextView) holder.layout.getChildAt(0);

        TextView text =
                (TextView) holder.layout.getChildAt(1);

        camera.setText("📷");

        text.setText(
                "Photo " + (position + 1)
        );
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    static class PhotoViewHolder
            extends RecyclerView.ViewHolder {

        LinearLayout layout;

        public PhotoViewHolder(
                @NonNull LinearLayout itemView) {

            super(itemView);

            layout = itemView;
        }
    }
}