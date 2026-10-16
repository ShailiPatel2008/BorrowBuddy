package com.borrowbuddy.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.UploadCallback;
import com.cloudinary.android.callback.ErrorInfo;
import java.util.Map;
import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {

    private MaterialToolbar toolbar;
    private Uri selectedProfileImage;
    private ImageView profileImage;

    private TextView changePhotoButton;

    private TextView editProfileTitle;
    private TextView editProfileDescription;

    private EditText editName;
    private EditText editEmail;
    private EditText editPhone;
    private EditText editLocation;

    private Button saveButton;

    private MaterialCardView nameCard;
    private MaterialCardView emailCard;
    private MaterialCardView phoneCard;
    private MaterialCardView locationCard;

    private View mainLayout;

    private SharedPreferences preferences;
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    private static final String PREF_NAME =
            "BorrowBuddy";

    private static final String DARK_MODE =
            "darkMode";

    private static final int PICK_IMAGE =
            100;

    private static final int PURPLE =
            Color.rgb(106, 27, 154);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_edit_profile
        );

        preferences =
                getSharedPreferences(
                        PREF_NAME,
                        MODE_PRIVATE
                );
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        toolbar =
                findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(
                Color.WHITE
        );

        toolbar.setNavigationIconTint(
                Color.WHITE
        );

        if (getSupportActionBar() != null) {

            getSupportActionBar()
                    .setTitle("Edit Profile");

            getSupportActionBar()
                    .setDisplayHomeAsUpEnabled(
                            true
                    );
        }

        toolbar.setNavigationOnClickListener(
                v -> finish()
        );

        getWindow().setStatusBarColor(Color.rgb(108, 74, 182));

        getWindow()
                .getDecorView()
                .setSystemUiVisibility(0);

        initializeViews();

        loadProfileData();

        setupClickListeners();

        applyTheme();
    }

    private void initializeViews() {

        profileImage =
                findViewById(
                        R.id.profileImage
                );

        changePhotoButton =
                findViewById(
                        R.id.changePhotoButton
                );

        editProfileTitle =
                findViewById(
                        R.id.editProfileTitle
                );

        editProfileDescription =
                findViewById(
                        R.id.editProfileDescription
                );

        editName =
                findViewById(
                        R.id.editName
                );

        editEmail =
                findViewById(
                        R.id.editEmail
                );

        editPhone =
                findViewById(
                        R.id.editPhone
                );

        editLocation =
                findViewById(
                        R.id.editLocation
                );

        saveButton =
                findViewById(
                        R.id.saveButton
                );

        nameCard =
                findViewById(
                        R.id.nameCard
                );

        emailCard =
                findViewById(
                        R.id.emailCard
                );

        phoneCard =
                findViewById(
                        R.id.phoneCard
                );

        locationCard =
                findViewById(
                        R.id.locationCard
                );

        mainLayout =
                findViewById(
                        R.id.editProfileMainLayout
                );
    }

    private String getProfileValue(
            String profileKey,
            String oldUserKey,
            String defaultValue
    ) {

        String value =
                preferences.getString(
                        profileKey,
                        ""
                );

        if (value == null ||
                value.isEmpty()) {

            value =
                    preferences.getString(
                            oldUserKey,
                            defaultValue
                    );
        }

        return value;
    }

    private void loadProfileData() {

        FirebaseUser user = auth.getCurrentUser();

        if (user == null)
            return;

        db.collection("users")
                .document(user.getUid())
                .get()
                .addOnSuccessListener(document -> {

                    if(document.exists()){

                        editName.setText(
                                document.getString("full_name")
                        );

                        editEmail.setText(
                                document.getString("email")
                        );

                        editPhone.setText(
                                document.getString("phone")
                        );

                        editLocation.setText(
                                document.getString("location")
                        );
                        String imageUrl = document.getString("profileImageUrl");

                        if(imageUrl != null && !imageUrl.isEmpty()) {

                            com.bumptech.glide.Glide
                                    .with(EditProfileActivity.this)
                                    .load(imageUrl)
                                    .into(profileImage);
                        }

                    }

                });

        loadProfilePhoto();
    }

    private void loadProfilePhoto() {

        String photoUri =
                preferences.getString(
                        "profilePhotoUri",
                        ""
                );

        if (photoUri != null &&
                !photoUri.isEmpty()) {

            try {

                profileImage.setImageURI(
                        Uri.parse(photoUri)
                );

            } catch (Exception e) {

                profileImage.setImageDrawable(
                        null
                );
            }
        }
    }

    private void setupClickListeners() {

        changePhotoButton.setOnClickListener(
                v -> openGallery()
        );

        saveButton.setOnClickListener(
                v -> saveProfile()
        );
    }

    private void openGallery() {

        Intent intent =
                new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media
                                .EXTERNAL_CONTENT_URI
                );

        intent.setType("image/*");

        startActivityForResult(
                intent,
                PICK_IMAGE
        );
    }

    @Override
    protected void onActivityResult(
            int requestCode,
            int resultCode,
            Intent data
    ) {

        super.onActivityResult(
                requestCode,
                resultCode,
                data
        );

        if (requestCode == PICK_IMAGE &&
                resultCode == RESULT_OK &&
                data != null) {

            Uri selectedImage =
                    data.getData();

            if (selectedImage != null) {

                selectedProfileImage = selectedImage;

                profileImage.setImageURI(selectedImage);

                Toast.makeText(
                        this,
                        "Photo selected successfully",
                        Toast.LENGTH_SHORT
                ).show();

            }
        }
    }
    private void saveUserProfile(String uid, String imageUrl) {


        Map<String,Object> updates = new HashMap<>();

        updates.put("full_name",
                editName.getText().toString().trim());

        updates.put("email",
                editEmail.getText().toString().trim());

        updates.put("phone",
                editPhone.getText().toString().trim());

        updates.put("location",
                editLocation.getText().toString().trim());


        if(imageUrl != null){
            updates.put("profileImageUrl", imageUrl);
        }


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

                })
                .addOnFailureListener(e -> {

                    Toast.makeText(
                            this,
                            "Update Failed",
                            Toast.LENGTH_SHORT
                    ).show();

                });

    }
    private void uploadProfileImage(String uid) {

        if (selectedProfileImage == null) {
            saveUserProfile(uid, null);
            return;
        }


        MediaManager.get()
                .upload(selectedProfileImage)
                .unsigned("borrowbuddy_upload")
                .callback(new UploadCallback() {

                    @Override
                    public void onStart(String requestId) {

                    }


                    @Override
                    public void onProgress(String requestId,
                                           long bytes,
                                           long totalBytes) {

                    }


                    @Override
                    public void onSuccess(String requestId,
                                          Map resultData) {

                        String imageUrl =
                                resultData.get("secure_url").toString();


                        saveUserProfile(uid, imageUrl);
                    }


                    @Override
                    public void onError(String requestId,
                                        ErrorInfo error) {

                        Toast.makeText(
                                EditProfileActivity.this,
                                "Image upload failed",
                                Toast.LENGTH_SHORT
                        ).show();

                    }


                    @Override
                    public void onReschedule(String requestId,
                                             ErrorInfo error) {

                    }

                })
                .dispatch();
    }
    private void saveProfile() {

        String name =
                editName
                        .getText()
                        .toString()
                        .trim();

        String email =
                editEmail
                        .getText()
                        .toString()
                        .trim();

        String phone =
                editPhone
                        .getText()
                        .toString()
                        .trim();

        String location =
                editLocation
                        .getText()
                        .toString()
                        .trim();

        // Name validation

        if (name.isEmpty()) {

            editName.setError(
                    "Enter your name"
            );

            editName.requestFocus();

            return;
        }

        // Email validation

        if (email.isEmpty()) {

            editEmail.setError(
                    "Enter your email"
            );

            editEmail.requestFocus();

            return;
        }

        if (!android.util.Patterns
                .EMAIL_ADDRESS
                .matcher(email)
                .matches()) {

            editEmail.setError(
                    "Enter a valid email"
            );

            editEmail.requestFocus();

            return;
        }

        // Phone validation

        if (phone.isEmpty()) {

            editPhone.setError(
                    "Enter your phone number"
            );

            editPhone.requestFocus();

            return;
        }

        if (phone.length() < 10) {

            editPhone.setError(
                    "Enter a valid phone number"
            );

            editPhone.requestFocus();

            return;
        }

        // Location validation

        if (location.isEmpty()) {

            editLocation.setError(
                    "Enter your location"
            );

            editLocation.requestFocus();

            return;
        }

        // Save the unified profile data

        FirebaseUser currentUser = auth.getCurrentUser();

        if (currentUser == null) {
            Toast.makeText(
                    this,
                    "User not logged in",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        uploadProfileImage(currentUser.getUid());
    }

    private void applyTheme() {

        boolean isDarkMode =
                preferences.getBoolean(
                        DARK_MODE,
                        false
                );

        if (isDarkMode) {

            applyDarkMode();

        } else {

            applyLightMode();
        }
    }

    private void applyDarkMode() {

        mainLayout.setBackgroundColor(
                Color.BLACK
        );

        nameCard.setCardBackgroundColor(
                Color.rgb(25, 25, 25)
        );

        emailCard.setCardBackgroundColor(
                Color.rgb(25, 25, 25)
        );

        phoneCard.setCardBackgroundColor(
                Color.rgb(25, 25, 25)
        );

        locationCard.setCardBackgroundColor(
                Color.rgb(25, 25, 25)
        );

        nameCard.setStrokeColor(
                Color.WHITE
        );

        emailCard.setStrokeColor(
                Color.WHITE
        );

        phoneCard.setStrokeColor(
                Color.WHITE
        );

        locationCard.setStrokeColor(
                Color.WHITE
        );

        nameCard.setStrokeWidth(1);
        emailCard.setStrokeWidth(1);
        phoneCard.setStrokeWidth(1);
        locationCard.setStrokeWidth(1);

        changeAllTextColor(
                mainLayout,
                Color.WHITE
        );

        toolbar.setBackgroundColor(
                Color.rgb(108, 74, 182)
        );

        toolbar.setTitleTextColor(
                Color.WHITE
        );

        toolbar.setNavigationIconTint(
                Color.WHITE
        );

        editName.setTextColor(
                Color.WHITE
        );

        editEmail.setTextColor(
                Color.WHITE
        );

        editPhone.setTextColor(
                Color.WHITE
        );

        editLocation.setTextColor(
                Color.WHITE
        );

        editName.setHintTextColor(
                Color.LTGRAY
        );

        editEmail.setHintTextColor(
                Color.LTGRAY
        );

        editPhone.setHintTextColor(
                Color.LTGRAY
        );

        editLocation.setHintTextColor(
                Color.LTGRAY
        );

        changePhotoButton.setTextColor(
                Color.rgb(206, 147, 216)
        );

        saveButton.setTextColor(
                Color.WHITE
        );

        saveButton.setBackgroundColor(
                PURPLE
        );

        getWindow().setStatusBarColor(Color.rgb(108, 74, 182));
    }

    private void applyLightMode() {

        mainLayout.setBackgroundColor(
                Color.rgb(248, 249, 250)
        );

        nameCard.setCardBackgroundColor(
                Color.WHITE
        );

        emailCard.setCardBackgroundColor(
                Color.WHITE
        );

        phoneCard.setCardBackgroundColor(
                Color.WHITE
        );

        locationCard.setCardBackgroundColor(
                Color.WHITE
        );

        nameCard.setStrokeColor(
                Color.rgb(221, 221, 221)
        );

        emailCard.setStrokeColor(
                Color.rgb(221, 221, 221)
        );

        phoneCard.setStrokeColor(
                Color.rgb(221, 221, 221)
        );

        locationCard.setStrokeColor(
                Color.rgb(221, 221, 221)
        );

        nameCard.setStrokeWidth(1);
        emailCard.setStrokeWidth(1);
        phoneCard.setStrokeWidth(1);
        locationCard.setStrokeWidth(1);

        changeAllTextColor(
                mainLayout,
                Color.BLACK
        );

        toolbar.setBackgroundColor(
                Color.rgb(108, 74, 182)
        );

        toolbar.setTitleTextColor(
                Color.WHITE
        );

        toolbar.setNavigationIconTint(
                Color.WHITE
        );

        editName.setTextColor(
                Color.BLACK
        );

        editEmail.setTextColor(
                Color.BLACK
        );

        editPhone.setTextColor(
                Color.BLACK
        );

        editLocation.setTextColor(
                Color.BLACK
        );

        editName.setHintTextColor(
                Color.GRAY
        );

        editEmail.setHintTextColor(
                Color.GRAY
        );

        editPhone.setHintTextColor(
                Color.GRAY
        );

        editLocation.setHintTextColor(
                Color.GRAY
        );

        changePhotoButton.setTextColor(
                PURPLE
        );

        saveButton.setTextColor(
                Color.WHITE
        );

        saveButton.setBackgroundColor(
                PURPLE
        );

        getWindow().setStatusBarColor(Color.rgb(108, 74, 182));
    }

    private void changeAllTextColor(
            View view,
            int color
    ) {

        // Do not change toolbar title

        if (view == toolbar) {
            return;
        }

        if (view instanceof TextView) {

            TextView textView =
                    (TextView) view;

            textView.setTextColor(
                    color
            );
        }

        if (view instanceof ViewGroup) {

            ViewGroup group =
                    (ViewGroup) view;

            for (int i = 0;
                 i < group.getChildCount();
                 i++) {

                changeAllTextColor(
                        group.getChildAt(i),
                        color
                );
            }
        }
    }
}
