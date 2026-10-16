package com.borrowbuddy.app;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.UploadCallback;
import com.cloudinary.android.callback.ErrorInfo;
public class AdminEditProfileActivity extends AppCompatActivity {

    View mainLayout;

    ImageButton btnBack;
    private Uri selectedImage;
    TextView btnChangePhoto;
    TextView btnSaveChanges;

    ImageView imgProfile;

    TextView lblFullName;
    TextView lblEmail;
    TextView lblPhone;
    TextView lblLocation;

    EditText edtName;
    EditText edtEmail;
    EditText edtPhone;
    EditText edtLocation;

    private FirebaseAuth auth;
    private FirebaseFirestore db;

    private static final int PICK_IMAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_profile);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Views
        mainLayout = findViewById(R.id.mainLayout);

        btnBack = findViewById(R.id.btnBack);
        btnChangePhoto = findViewById(R.id.btnChangePhoto);
        btnSaveChanges = findViewById(R.id.btnSaveChanges);

        imgProfile = findViewById(R.id.imgProfile);

        lblFullName = findViewById(R.id.lblFullName);
        lblEmail = findViewById(R.id.lblEmail);
        lblPhone = findViewById(R.id.lblPhone);
        lblLocation = findViewById(R.id.lblLocation);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        edtLocation = findViewById(R.id.edtLocation);

        loadProfile();
        applyDarkMode();

        btnBack.setOnClickListener(v -> finish());

        btnChangePhoto.setOnClickListener(v -> {

            Intent intent = new Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(intent, PICK_IMAGE);

        });

        btnSaveChanges.setOnClickListener(v -> saveProfile());
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadProfile();
        applyDarkMode();
    }

    private void loadProfile() {

        FirebaseUser currentUser = auth.getCurrentUser();

        if (currentUser == null) {
            finish();
            return;
        }

        db.collection("users")
                .document(currentUser.getUid())
                .get()
                .addOnSuccessListener(document -> {

                    if (document.exists()) {

                        edtName.setText(document.getString("full_name"));
                        edtEmail.setText(document.getString("email"));
                        edtPhone.setText(document.getString("phone"));
                        edtLocation.setText(document.getString("location"));

                    }

                });

    }

    private void saveProfile() {

        FirebaseUser currentUser = auth.getCurrentUser();

        if (currentUser == null)
            return;

        String name = edtName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String location = edtLocation.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            edtName.setError("Enter Full Name");
            return;
        }

        if (TextUtils.isEmpty(email)) {
            edtEmail.setError("Enter Email");
            return;
        }

        if (TextUtils.isEmpty(phone)) {
            edtPhone.setError("Enter Phone");
            return;
        }

        if (phone.length() != 10) {
            edtPhone.setError("Enter valid 10 digit mobile number");
            return;
        }

        if (TextUtils.isEmpty(location)) {
            edtLocation.setError("Enter Location");
            return;
        }

        Map<String, Object> updates = new HashMap<>();

        updates.put("full_name", name);
        updates.put("email", email);
        updates.put("phone", phone);
        updates.put("location", location);
        if (selectedImage != null) {

            uploadProfileImage(
                    currentUser.getUid(),
                    updates
            );

        } else {

            updateAdminProfile(
                    currentUser.getUid(),
                    updates
            );

        }
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            selectedImage = data.getData();

            imgProfile.setImageURI(selectedImage);

            // Cloudinary upload will be added later
        }
    }
    private void uploadProfileImage(
            String uid,
            Map<String,Object> updates
    ){

        MediaManager.get()
                .upload(selectedImage)
                .unsigned("borrowbuddy_upload")
                .callback(new UploadCallback() {


                    @Override
                    public void onStart(String requestId){}


                    @Override
                    public void onProgress(
                            String requestId,
                            long bytes,
                            long totalBytes){}


                    @Override
                    public void onSuccess(
                            String requestId,
                            Map resultData){

                        String imageUrl =
                                resultData.get("secure_url").toString();


                        updates.put(
                                "profileImageUrl",
                                imageUrl
                        );


                        updateAdminProfile(
                                uid,
                                updates
                        );
                    }


                    @Override
                    public void onError(
                            String requestId,
                            ErrorInfo error){

                        Toast.makeText(
                                AdminEditProfileActivity.this,
                                "Image upload failed",
                                Toast.LENGTH_SHORT
                        ).show();

                    }


                    @Override
                    public void onReschedule(
                            String requestId,
                            ErrorInfo error){}


                })
                .dispatch();
    }
    private void updateAdminProfile(
            String uid,
            Map<String,Object> updates
    ){

        db.collection("users")
                .document(uid)
                .update(updates)
                .addOnSuccessListener(unused -> {

                    Toast.makeText(
                            this,
                            "Profile Updated Successfully",
                            Toast.LENGTH_SHORT
                    ).show();

                    finish();

                });

    }

    private void applyDarkMode() {

        if (mainLayout == null)
            return;

        boolean darkMode = getSharedPreferences(
                "Settings",
                MODE_PRIVATE
        ).getBoolean(
                "dark_mode",
                false
        );

        if (darkMode) {

            mainLayout.setBackgroundColor(Color.rgb(16,16,16));

            lblFullName.setTextColor(Color.WHITE);
            lblEmail.setTextColor(Color.WHITE);
            lblPhone.setTextColor(Color.WHITE);
            lblLocation.setTextColor(Color.WHITE);

            edtName.setTextColor(Color.WHITE);
            edtEmail.setTextColor(Color.WHITE);
            edtPhone.setTextColor(Color.WHITE);
            edtLocation.setTextColor(Color.WHITE);

            edtName.setHintTextColor(Color.LTGRAY);
            edtEmail.setHintTextColor(Color.LTGRAY);
            edtPhone.setHintTextColor(Color.LTGRAY);
            edtLocation.setHintTextColor(Color.LTGRAY);

            edtName.setBackgroundColor(Color.rgb(45,45,45));
            edtEmail.setBackgroundColor(Color.rgb(45,45,45));
            edtPhone.setBackgroundColor(Color.rgb(45,45,45));
            edtLocation.setBackgroundColor(Color.rgb(45,45,45));

        } else {

            mainLayout.setBackgroundColor(Color.rgb(246,243,248));

            lblFullName.setTextColor(Color.BLACK);
            lblEmail.setTextColor(Color.BLACK);
            lblPhone.setTextColor(Color.BLACK);
            lblLocation.setTextColor(Color.BLACK);

            edtName.setTextColor(Color.BLACK);
            edtEmail.setTextColor(Color.BLACK);
            edtPhone.setTextColor(Color.BLACK);
            edtLocation.setTextColor(Color.BLACK);

            edtName.setHintTextColor(Color.GRAY);
            edtEmail.setHintTextColor(Color.GRAY);
            edtPhone.setHintTextColor(Color.GRAY);
            edtLocation.setHintTextColor(Color.GRAY);

            edtName.setBackgroundColor(Color.WHITE);
            edtEmail.setBackgroundColor(Color.WHITE);
            edtPhone.setBackgroundColor(Color.WHITE);
            edtLocation.setBackgroundColor(Color.WHITE);
        }

        btnChangePhoto.setTextColor(Color.WHITE);
        btnSaveChanges.setTextColor(Color.WHITE);

        View toolbar = (View) btnBack.getParent();

        if (toolbar != null) {
            toolbar.setBackgroundColor(Color.rgb(108,74,182));
        }
    }
}