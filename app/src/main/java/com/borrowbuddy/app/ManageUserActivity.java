package com.borrowbuddy.app;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        btnBack = findViewById(R.id.btnBack);
        recyclerUsers = findViewById(R.id.recyclerUsers);

        btnBack.setOnClickListener(v -> finish());

        recyclerUsers.setLayoutManager(new LinearLayoutManager(this));

        userList = new ArrayList<>();
        adapter = new ManageUserAdapter(this, userList);
        recyclerUsers.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        loadUsers();
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

                        Log.d("FIREBASE_TEST", "Document ID = " + document.getId());
                        Log.d("FIREBASE_TEST", "Name = " + user.getFullname());
                        Log.d("FIREBASE_TEST", "Email = " + user.getEmail());

                        // ADD USER ONLY ONCE
                        userList.add(user);
                    }

                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e ->
                        Log.e("FIREBASE_TEST", "Firestore Error", e)
                );
    }}