package com.borrowbuddy.app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.borrowbuddy.app.activities.MyListingsActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(MainActivity.this, MyListingsActivity.class);
        startActivity(intent);
        finish();
    }
}
