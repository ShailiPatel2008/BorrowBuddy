package com.borrowbuddy.app;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class ManageUserActivity extends AppCompatActivity {

    private RecyclerView recyclerUsers;
    private ImageButton btnBack;

    private ArrayList<UserModel> userList;
    private ManageUserAdapter adapter;

    private FirebaseFirestore db;

    // Dark mode
    private LinearLayout mainLayout;
    private ScrollView scrollView;
    private TextView txtUsersTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        mainLayout = findViewById(R.id.mainLayout);
        scrollView = findViewById(R.id.scrollView);
        txtUsersTitle = findViewById(R.id.txtUsersTitle);

        btnBack = findViewById(R.id.btnBack);
        recyclerUsers = findViewById(R.id.recyclerUsers);

        btnBack.setOnClickListener(v -> finish());

        recyclerUsers.setLayoutManager(new LinearLayoutManager(this));

        userList = new ArrayList<>();
        adapter = new ManageUserAdapter(this, userList);
        recyclerUsers.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        loadUsers();
        applyDarkMode();
    }

    @Override
    protected void onResume() {
        super.onResume();
        applyDarkMode();
    }

    private void loadUsers() {

        db.collection("users")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {

                    userList.clear();

                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {

                        UserModel user = new UserModel();

                        user.setUserid(document.getId());
                        user.setFull_name(document.getString("full_name"));
                        user.setEmail(document.getString("email"));
                        user.setPhone(document.getString("phone"));

                        Double rate = document.getDouble("rate");
                        if (rate != null) {
                            user.setRate(rate);
                        }

                        Boolean active = document.getBoolean("is_active");
                        if (active != null) {
                            user.setIs_active(active);
                        }

                        Log.d("FIREBASE_TEST", document.getData().toString());

                        userList.add(user);
                    }

                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e ->
                        Log.e("FIREBASE_TEST", "Firestore Error", e)
                );
    }

    private void applyDarkMode() {

        SharedPreferences preferences =
                getSharedPreferences("Settings", MODE_PRIVATE);

        boolean darkMode =
                preferences.getBoolean("dark_mode", false);

        if (darkMode) {

            mainLayout.setBackgroundColor(Color.rgb(18, 18, 18));
            scrollView.setBackgroundColor(Color.rgb(18, 18, 18));
            txtUsersTitle.setTextColor(Color.WHITE);

        } else {

            mainLayout.setBackgroundColor(Color.WHITE);
            scrollView.setBackgroundColor(Color.WHITE);
            txtUsersTitle.setTextColor(Color.BLACK);
        }
    }
}