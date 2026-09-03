package com.borrowbuddy.app.activities;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.borrowbuddy.app.R;
import com.borrowbuddy.app.adapters.FullScreenImageAdapter;

import java.util.ArrayList;

public class FullScreenImageActivity extends AppCompatActivity {

    private ViewPager2 vpFullScreenImages;
    private ImageView btnClose;

    private ArrayList<Object> imageList =
            new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_full_screen_image);

        vpFullScreenImages =
                findViewById(R.id.vpFullScreenImages);

        btnClose =
                findViewById(R.id.btnClose);

        // Receive images
        ArrayList<String> imageUris =
                getIntent().getStringArrayListExtra(
                        "imageUris"
                );

        if (imageUris != null) {

            for (String uriString : imageUris) {

                imageList.add(
                        Uri.parse(uriString)
                );
            }
        }

        // Adapter
        FullScreenImageAdapter adapter =
                new FullScreenImageAdapter(
                        imageList
                );

        vpFullScreenImages.setAdapter(adapter);

        // Open the same image that was clicked
        int selectedPosition =
                getIntent().getIntExtra(
                        "selectedPosition",
                        0
                );

        if (selectedPosition >= 0 &&
                selectedPosition < imageList.size()) {

            vpFullScreenImages.setCurrentItem(
                    selectedPosition,
                    false
            );
        }

        // Close
        btnClose.setOnClickListener(
                v -> finish()
        );
    }

    @Override
    public void onBackPressed() {

        finish();
    }
}
