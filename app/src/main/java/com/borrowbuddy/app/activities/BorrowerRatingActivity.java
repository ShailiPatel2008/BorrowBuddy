package com.borrowbuddy.app.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.borrowbuddy.app.R;


public class BorrowerRatingActivity extends AppCompatActivity {


    RatingBar ratingBarBorrower;
    EditText etReview;
    Button btnSubmitRating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_borrower_rating);


        ratingBarBorrower =
                findViewById(R.id.ratingBarBorrower);

        etReview =
                findViewById(R.id.etReview);

        btnSubmitRating =
                findViewById(R.id.btnSubmitRating);



        btnSubmitRating.setOnClickListener(view -> {


            float rating =
                    ratingBarBorrower.getRating();


            String review =
                    etReview.getText().toString();



            if(rating == 0){

                Toast.makeText(
                        this,
                        "Please give rating",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }


            if(review.trim().isEmpty()){

                etReview.setError(
                        "Write review"
                );

                return;
            }


            Toast.makeText(
                    this,
                    "Rating Submitted",
                    Toast.LENGTH_SHORT
            ).show();


            finish();

        });

    }
}