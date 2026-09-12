package com.borrowbuddy.app.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.borrowbuddy.app.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.app.Dialog;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.textfield.TextInputEditText;
public class AddItemActivity extends AppCompatActivity {

    // =========================
    // FORM FIELDS
    // =========================

    EditText etItemName;
    EditText etDescription;
    EditText etRentalPrice;
    EditText etSecurityDeposit;
    EditText etPickupLocation;
    EditText etAvailability;
    EditText etContact;

    Spinner spCategory;
    RadioGroup rgCondition;

    MaterialButton btnUploadImage;
    MaterialButton btnListItem;
    MaterialButton btnCurrentLocation;

    LinearLayout photoContainer;
    TextView txtPhotoCount;

    LocationManager locationManager;

    private TextInputEditText etQuantity;

    // =========================
    // PERMISSION CONSTANTS
    // =========================

    private static final int LOCATION_PERMISSION_REQUEST = 101;
    private static final int IMAGE_PICK_REQUEST = 200;
    private static final int CAMERA_REQUEST = 201;
    private static final int CAMERA_PERMISSION_REQUEST = 300;
    private static final int MAX_PHOTOS = 5;

    // =========================
    // IMAGE LIST
    // =========================

    /*
     * No maximum limit.
     *
     * Each item can be:
     * Uri    -> Gallery photo
     * Bitmap -> Camera photo
     */
    private ArrayList<Object> selectedImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_item);

        // =========================
        // TOOLBAR
        // =========================

        MaterialToolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(Color.WHITE);
        getWindow().setStatusBarColor(
                Color.rgb(108, 74, 182)
        );

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Add Item");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setTint(Color.WHITE);
        }

        // =========================
        // INITIALIZE FORM
        // =========================

        etItemName = findViewById(R.id.etItemName);
        etDescription = findViewById(R.id.etDescription);
        etRentalPrice = findViewById(R.id.etRentalPrice);
        etSecurityDeposit = findViewById(R.id.etSecurityDeposit);
        etPickupLocation = findViewById(R.id.etPickupLocation);
        etAvailability = findViewById(R.id.etAvailability);
        etContact = findViewById(R.id.etContact);

        spCategory = findViewById(R.id.spCategory);
        rgCondition = findViewById(R.id.rgCondition);

        btnUploadImage = findViewById(R.id.btnUploadImage);
        btnListItem = findViewById(R.id.btnListItem);
        btnCurrentLocation = findViewById(R.id.btnCurrentLocation);

        photoContainer = findViewById(R.id.photoContainer);
        txtPhotoCount = findViewById(R.id.txtPhotoCount);
        etQuantity = findViewById(R.id.etQuantity);

        // =========================
        // CURRENT LOCATION
        // =========================

        btnCurrentLocation.setOnClickListener(v -> {
            checkLocationPermission();
        });

        // =========================
        // DATE PICKER
        // =========================

        etAvailability.setOnClickListener(view -> {

            Calendar calendar = Calendar.getInstance();

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog =
                    new DatePickerDialog(
                            AddItemActivity.this,
                            (datePicker, selectedYear,
                             selectedMonth, selectedDay) -> {

                                String selectedDate =
                                        selectedDay + "/" +
                                                (selectedMonth + 1) + "/" +
                                                selectedYear;

                                etAvailability.setText(selectedDate);
                            },
                            year,
                            month,
                            day
                    );

            datePickerDialog.show();
        });

        // =========================
        // CATEGORY
        // =========================

        String[] categories = {
                "Electronics",
                "Tools",
                "Books",
                "Sports Equipment",
                "Furniture",
                "Kitchen",
                "Others"
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        categories
                );

        spCategory.setAdapter(adapter);

        // =========================
        // ADD PHOTOS
        // =========================

        btnUploadImage.setOnClickListener(v -> showImageOptions());

        // =========================
        // SUBMIT
        // =========================

        btnListItem.setOnClickListener(v -> validateFields());

        // Initial photo UI
        refreshImageViews();
    }

    // =========================================================
    // SHOW CAMERA / GALLERY
    // =========================================================

    private void showImageOptions() {

        String[] options = {
                "📷  Camera",
                "🖼️  Photos"
        };

        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);

        builder.setTitle("Add Item Photos");

        builder.setItems(options, (dialog, which) -> {

            if (which == 0) {

                // CAMERA

                if (ActivityCompat.checkSelfPermission(
                        AddItemActivity.this,
                        Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(
                            AddItemActivity.this,
                            new String[]{
                                    Manifest.permission.CAMERA
                            },
                            CAMERA_PERMISSION_REQUEST
                    );

                } else {

                    openCamera();
                }

            } else {

                // GALLERY

                openGallery();
            }
        });

        builder.show();
    }

    // =========================
    // Show full screen
    // =========================
    private void showFullImage(Object image) {
        final Dialog dialog = new Dialog(this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        ImageView imageView = new ImageView(this);

        imageView.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
        );

        imageView.setScaleType(
                ImageView.ScaleType.FIT_CENTER
        );

        imageView.setBackgroundColor(Color.BLACK);

        if (image instanceof Uri) {
            imageView.setImageURI((Uri) image);
        } else if (image instanceof Bitmap) {
            imageView.setImageBitmap((Bitmap) image);
        }

        dialog.setContentView(imageView);

        imageView.setOnClickListener(
                v -> dialog.dismiss()
        );

        dialog.show();

        Window window = dialog.getWindow();

        if (window != null) {

            window.setBackgroundDrawableResource(
                    android.R.color.black
            );

            window.setLayout(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT
            );
        }
    }

    // =========================================================
    // OPEN GALLERY
    // =========================================================

    private void openGallery() {

        Intent galleryIntent =
                new Intent(Intent.ACTION_PICK);

        galleryIntent.setType("image/*");

        galleryIntent.putExtra(
                Intent.EXTRA_ALLOW_MULTIPLE,
                true
        );

        startActivityForResult(
                galleryIntent,
                IMAGE_PICK_REQUEST
        );
    }

    // =========================================================
    // OPEN CAMERA
    // =========================================================

    private void openCamera() {

        Intent cameraIntent =
                new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(
                cameraIntent,
                CAMERA_REQUEST
        );
    }

    // =========================================================
    // ACTIVITY RESULT
    // =========================================================

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

        if (resultCode != RESULT_OK || data == null) {
            return;
        }

        // =========================
        // GALLERY
        // =========================

        if (requestCode == IMAGE_PICK_REQUEST) {

            ClipData clipData = data.getClipData();

            if (clipData != null) {

                int remainingSlots =
                        MAX_PHOTOS - selectedImages.size();

                int photosToAdd =
                        Math.min(
                                clipData.getItemCount(),
                                remainingSlots
                        );

                for (int i = 0; i < photosToAdd; i++) {

                    Uri imageUri =
                            clipData
                                    .getItemAt(i)
                                    .getUri();

                    selectedImages.add(imageUri);
                }

                Toast.makeText(
                        this,
                        photosToAdd + " photos added",
                        Toast.LENGTH_SHORT
                ).show();

                if (clipData.getItemCount() > remainingSlots) {

                    Toast.makeText(
                            this,
                            "Maximum 5 photos allowed",
                            Toast.LENGTH_SHORT
                    ).show();
                }

            } else {

                Uri imageUri = data.getData();

                if (imageUri != null) {

                    if (selectedImages.size() >= MAX_PHOTOS) {

                        Toast.makeText(
                                this,
                                "Maximum 5 photos allowed",
                                Toast.LENGTH_SHORT
                        ).show();

                        return;
                    }

                    selectedImages.add(imageUri);

                    Toast.makeText(
                            this,
                            "Photo added",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }

            refreshImageViews();
        }

        // =========================
        // CAMERA
        // =========================

        else if (requestCode == CAMERA_REQUEST) {

            if (selectedImages.size() >= MAX_PHOTOS) {

                Toast.makeText(
                        this,
                        "Maximum 5 photos allowed",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            Bundle extras = data.getExtras();

            if (extras != null) {

                Bitmap imageBitmap =
                        (Bitmap) extras.get("data");

                if (imageBitmap != null) {

                    selectedImages.add(imageBitmap);

                    refreshImageViews();

                    Toast.makeText(
                            this,
                            "Photo added",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        }
    }

    // =========================================================
    // DISPLAY ALL PHOTOS
    // =========================================================

    private void refreshImageViews() {

        photoContainer.removeAllViews();

        txtPhotoCount.setText(
                selectedImages.size() + " "
                        + (selectedImages.size() == 1
                        ? "photo"
                        : "photos")
        );

        for (int i = 0; i < selectedImages.size(); i++) {

            final int index = i;

            // Outer photo card
            LinearLayout photoLayout =
                    new LinearLayout(this);

            photoLayout.setOrientation(
                    LinearLayout.VERTICAL
            );

            photoLayout.setGravity(
                    Gravity.CENTER
            );

            LinearLayout.LayoutParams
                    photoLayoutParams =
                    new LinearLayout.LayoutParams(
                            dpToPx(105),
                            dpToPx(125)
                    );

            photoLayoutParams.setMargins(
                    dpToPx(5),
                    0,
                    dpToPx(5),
                    0
            );

            photoLayout.setLayoutParams(
                    photoLayoutParams
            );

            // Image
            ImageView imageView =
                    new ImageView(this);

            LinearLayout.LayoutParams
                    imageParams =
                    new LinearLayout.LayoutParams(
                            dpToPx(95),
                            dpToPx(95)
                    );

            imageView.setLayoutParams(
                    imageParams
            );

            imageView.setScaleType(
                    ImageView.ScaleType.CENTER_CROP
            );

            GradientDrawable imageBackground =
                    new GradientDrawable();

            imageBackground.setColor(
                    Color.WHITE
            );

            imageBackground.setCornerRadius(
                    dpToPx(14)
            );

            imageView.setBackground(
                    imageBackground
            );

            imageView.setPadding(
                    dpToPx(2),
                    dpToPx(2),
                    dpToPx(2),
                    dpToPx(2)
            );

            Object image =
                    selectedImages.get(i);

            if (image instanceof Uri) {

                imageView.setImageURI(
                        (Uri) image
                );

            } else if (image instanceof Bitmap) {

                imageView.setImageBitmap(
                        (Bitmap) image
                );
            }

            // Open image in full screen when clicked
            imageView.setOnClickListener(
                    v -> showFullImage(image)
            );

            photoLayout.addView(
                    imageView
            );

            // Remove button
            TextView removeButton =
                    new TextView(this);

            removeButton.setText("Remove");
            removeButton.setTextSize(12);
            removeButton.setTextColor(
                    Color.RED
            );
            removeButton.setGravity(
                    Gravity.CENTER
            );
            removeButton.setPadding(
                    0,
                    dpToPx(3),
                    0,
                    0
            );

            removeButton.setOnClickListener(v -> {

                selectedImages.remove(index);

                refreshImageViews();

                Toast.makeText(
                        AddItemActivity.this,
                        "Photo removed",
                        Toast.LENGTH_SHORT
                ).show();
            });

            photoLayout.addView(
                    removeButton
            );

            photoContainer.addView(
                    photoLayout
            );
        }
    }

    // =========================================================
    // VALIDATE FORM
    // =========================================================

    private void validateFields() {

        if (etItemName.getText()
                .toString()
                .trim()
                .isEmpty()) {

            etItemName.setError(
                    "Enter Item Name"
            );

            etItemName.requestFocus();
            return;
        }

        if (etDescription.getText()
                .toString()
                .trim()
                .isEmpty()) {

            etDescription.setError(
                    "Enter Description"
            );

            etDescription.requestFocus();
            return;
        }

        if (etRentalPrice.getText()
                .toString()
                .trim()
                .isEmpty()) {

            etRentalPrice.setError(
                    "Enter Rental Price"
            );

            etRentalPrice.requestFocus();
            return;
        }

        if (etQuantity.getText()
                .toString()
                .trim()
                .isEmpty()) {

            etQuantity.setError(
                    "Enter Quantity"
            );

            etQuantity.requestFocus();
            return;
        }

        int quantity = Integer.parseInt(
                etQuantity.getText()
                        .toString()
                        .trim()
        );

        if (quantity < 1) {

            etQuantity.setError(
                    "Quantity must be at least 1"
            );

            etQuantity.requestFocus();
            return;
        }

        if (etSecurityDeposit.getText()
                .toString()
                .trim()
                .isEmpty()) {

            etSecurityDeposit.setError(
                    "Enter Security Deposit"
            );

            etSecurityDeposit.requestFocus();
            return;
        }

        if (etPickupLocation.getText()
                .toString()
                .trim()
                .isEmpty()) {

            etPickupLocation.setError(
                    "Enter Pickup Location"
            );

            etPickupLocation.requestFocus();
            return;
        }

        if (etAvailability.getText()
                .toString()
                .trim()
                .isEmpty()) {

            etAvailability.setError(
                    "Select Available From Date"
            );

            etAvailability.requestFocus();
            return;
        }

        if (etContact.getText()
                .toString()
                .trim()
                .isEmpty()) {

            etContact.setError(
                    "Enter Contact Number"
            );

            etContact.requestFocus();
            return;
        }

        if (rgCondition.getCheckedRadioButtonId()
                == -1) {

            Toast.makeText(
                    this,
                    "Please select item condition",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        if (selectedImages.isEmpty()) {

            Toast.makeText(
                    this,
                    "Please upload at least one item photo",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        Toast.makeText(
                this,
                "Item request submitted successfully. Waiting for admin approval.",
                Toast.LENGTH_LONG
        ).show();
    }

    // =========================================================
    // LOCATION PERMISSION
    // =========================================================

    private void checkLocationPermission() {

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
                &&
                ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    },
                    LOCATION_PERMISSION_REQUEST
            );

        } else {

            getCurrentLocation();
        }
    }

    // =========================================================
    // PERMISSION RESULT
    // =========================================================

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults
    ) {

        super.onRequestPermissionsResult(
                requestCode,
                permissions,
                grantResults
        );

        // Location
        if (requestCode ==
                LOCATION_PERMISSION_REQUEST) {

            if (grantResults.length > 0 &&
                    (
                            grantResults[0]
                                    == PackageManager.PERMISSION_GRANTED
                                    ||
                                    (
                                            grantResults.length > 1
                                                    &&
                                                    grantResults[1]
                                                            == PackageManager.PERMISSION_GRANTED
                                    )
                    )) {

                getCurrentLocation();

            } else {

                Toast.makeText(
                        this,
                        "Location Permission Denied",
                        Toast.LENGTH_SHORT
                ).show();
            }
        }

        // Camera
        if (requestCode ==
                CAMERA_PERMISSION_REQUEST) {

            if (grantResults.length > 0 &&
                    grantResults[0]
                            == PackageManager.PERMISSION_GRANTED) {

                openCamera();

            } else {

                Toast.makeText(
                        this,
                        "Camera Permission Denied",
                        Toast.LENGTH_SHORT
                ).show();
            }
        }
    }

    // =========================================================
    // GET CURRENT LOCATION
    // =========================================================

    private void getCurrentLocation() {

        Toast.makeText(
                this,
                "Getting Location...",
                Toast.LENGTH_SHORT
        ).show();

        locationManager =
                (LocationManager)
                        getSystemService(
                                LOCATION_SERVICE
                        );

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
                &&
                ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                1000,
                1,
                new LocationListener() {

                    @Override
                    public void onLocationChanged(
                            Location location
                    ) {

                        double latitude =
                                location.getLatitude();

                        double longitude =
                                location.getLongitude();

                        convertLocationToAddress(
                                latitude,
                                longitude
                        );

                        locationManager.removeUpdates(
                                this
                        );
                    }
                }
        );
    }

    // =========================================================
    // CONVERT LOCATION TO ADDRESS
    // =========================================================

    private void convertLocationToAddress(
            double latitude,
            double longitude
    ) {

        Geocoder geocoder =
                new Geocoder(
                        this,
                        Locale.getDefault()
                );

        try {

            List<Address> addresses =
                    geocoder.getFromLocation(
                            latitude,
                            longitude,
                            1
                    );

            if (addresses != null &&
                    !addresses.isEmpty()) {

                String address =
                        addresses
                                .get(0)
                                .getAddressLine(0);

                etPickupLocation.setText(
                        address
                );

                Toast.makeText(
                        this,
                        "Location Added",
                        Toast.LENGTH_SHORT
                ).show();
            }

        } catch (Exception e) {

            Toast.makeText(
                    this,
                    "Unable to get address",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    // =========================================================
    // DP TO PIXELS
    // =========================================================

    private int dpToPx(int dp) {

        float density =
                getResources()
                        .getDisplayMetrics()
                        .density;

        return Math.round(
                dp * density
        );
    }

    // =========================================================
    // BACK BUTTON
    // =========================================================

    @Override
    public boolean onSupportNavigateUp() {

        finish();

        return true;
    }
}