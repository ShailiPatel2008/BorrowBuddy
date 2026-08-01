package com.borrowbuddy.app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Open Add Item Screen
        Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
        startActivity(intent);

        finish();
    }
}