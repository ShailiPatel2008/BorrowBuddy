package com.borrowbuddy.app;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.borrowbuddy.app.adapters.NotificationAdapter;
import com.borrowbuddy.app.models.NotificationModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
public class NotificationListActivity extends AppCompatActivity {

    private MaterialToolbar toolbar;
    private RecyclerView recyclerNotifications;
    private View layoutEmpty;

    private NotificationAdapter adapter;
    private List<NotificationModel> notificationList;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private void loadNotifications() {

        if(auth.getCurrentUser() == null){
            return;
        }

        String uid = auth.getCurrentUser().getUid();

        db.collection("Notifications")
                .whereEqualTo("userId", uid)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {

                    notificationList.clear();

                    for(QueryDocumentSnapshot document : queryDocumentSnapshots){

                        NotificationModel model =
                                document.toObject(NotificationModel.class);

                        notificationList.add(model);
                    }


                    adapter.notifyDataSetChanged();


                    if(notificationList.isEmpty()){

                        layoutEmpty.setVisibility(View.VISIBLE);
                        recyclerNotifications.setVisibility(View.GONE);

                    }else{

                        layoutEmpty.setVisibility(View.GONE);
                        recyclerNotifications.setVisibility(View.VISIBLE);

                    }

                });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        toolbar = findViewById(R.id.toolbar);
        recyclerNotifications = findViewById(R.id.recyclerNotifications);
        layoutEmpty = findViewById(R.id.layoutEmpty);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Notifications");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(v -> finish());

        recyclerNotifications.setLayoutManager(new LinearLayoutManager(this));

        notificationList = new ArrayList<>();

        adapter = new NotificationAdapter(this, notificationList);
        recyclerNotifications.setAdapter(adapter);

        loadNotifications();
    }


}