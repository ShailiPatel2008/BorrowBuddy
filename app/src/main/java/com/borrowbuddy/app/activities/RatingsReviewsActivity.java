package com.borrowbuddy.app.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.borrowbuddy.app.R;
import com.google.android.material.appbar.MaterialToolbar;

public class RatingsReviewsActivity extends AppCompatActivity {

    MaterialToolbar toolbar;

    TextView reviewerName1;
    TextView reviewRating1;
    TextView reviewText1;
    TextView itemName1;
    TextView reviewDate1;

    TextView reviewerName2;
    TextView reviewRating2;
    TextView reviewText2;
    TextView itemName2;
    TextView reviewDate2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings_reviews);

        // Toolbar
        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("My Reviews");
        toolbar.setTitleTextColor(android.graphics.Color.WHITE);

        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        // Review 1
        reviewerName1 = findViewById(R.id.review1)
                .findViewById(R.id.tvReviewerName);

        reviewRating1 = findViewById(R.id.review1)
                .findViewById(R.id.tvReviewRating);

        reviewText1 = findViewById(R.id.review1)
                .findViewById(R.id.tvReviewText);

        itemName1 = findViewById(R.id.review1)
                .findViewById(R.id.tvItemName);

        reviewDate1 = findViewById(R.id.review1)
                .findViewById(R.id.tvReviewDate);


        // Review 2
        reviewerName2 = findViewById(R.id.review2)
                .findViewById(R.id.tvReviewerName);

        reviewRating2 = findViewById(R.id.review2)
                .findViewById(R.id.tvReviewRating);

        reviewText2 = findViewById(R.id.review2)
                .findViewById(R.id.tvReviewText);

        itemName2 = findViewById(R.id.review2)
                .findViewById(R.id.tvItemName);

        reviewDate2 = findViewById(R.id.review2)
                .findViewById(R.id.tvReviewDate);


        // Sample Review 1
        reviewerName1.setText("Rahul");
        reviewRating1.setText("⭐⭐⭐⭐⭐");
        reviewText1.setText(
                "The owner was very helpful and the item was in good condition."
        );
        itemName1.setText("Item: Electric Drill");
        reviewDate1.setText("Rental: 10 Aug 2026");


        // Sample Review 2
        reviewerName2.setText("Priya");
        reviewRating2.setText("⭐⭐⭐⭐☆");
        reviewText2.setText(
                "Good item and smooth rental experience."
        );
        itemName2.setText("Item: Projector");
        reviewDate2.setText("Rental: 05 Aug 2026");
    }
}