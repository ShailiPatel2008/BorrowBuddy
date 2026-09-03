package com.borrowbuddy.app.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.borrowbuddy.app.R;
import com.borrowbuddy.app.adapters.EditItemImageAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Dialog;
import android.view.Window;
import android.view.WindowManager;


public class EditItemActivity extends AppCompatActivity {

    // =========================================================
    // FORM FIELDS
    // =========================================================

    private TextInputEditText etItemName;
    private TextInputEditText etDescription;
    private TextInputEditText etRentalPrice;
    private TextInputEditText etSecurityDeposit;
    private TextInputEditText etPickupLocation;
    private TextInputEditText etContact;

    private Spinner spCategory;

    private RadioGroup rgCondition;

    private RadioButton rbNew;
    private RadioButton rbGood;
    private RadioButton rbFair;

    private Button btnUpdateItem;
    private Button btnEditCurrentLocation;
    private Button btnChangeImage;
    private TextInputEditText etQuantity;

    // =========================================================
    // VIEWPAGER
    // =========================================================

    private ViewPager2 vpEditItemImages;
    private TextView tvEditImageIndicator;

    // =========================================================
    // LOCATION
    // =========================================================

    private LocationManager locationManager;

    // =========================================================
    // PERMISSIONS
    // =========================================================

    private static final int LOCATION_PERMISSION_REQUEST = 401;
    private static final int IMAGE_PICK_REQUEST = 402;
    private static final int CAMERA_REQUEST = 403;
    private static final int CAMERA_PERMISSION_REQUEST = 404;

    private static final int MAX_PHOTOS = 5;

    // =========================================================
    // IMAGE LIST
    // =========================================================

    /*
     * Gallery photo = Uri
     * Camera photo  = Bitmap
     */
    private ArrayList<Object> selectedImages =
            new ArrayList<>();

    private EditItemImageAdapter imageAdapter;

    // =========================================================
    // ON CREATE
    // =========================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_item);

        // =====================================================
        // TOOLBAR
        // =====================================================

        MaterialToolbar toolbar =
                findViewById(R.id.toolbarEditItem);

        toolbar.setNavigationIconTint(Color.WHITE);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {

            getSupportActionBar().setTitle(
                    "Edit Item"
            );

            getSupportActionBar()
                    .setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(
                v -> finish()
        );

        // =====================================================
        // INITIALIZE VIEWS
        // =====================================================

        etItemName =
                findViewById(R.id.etEditItemName);

        etDescription =
                findViewById(R.id.etEditDescription);

        etRentalPrice =
                findViewById(R.id.etEditRentalPrice);

        etSecurityDeposit =
                findViewById(R.id.etEditSecurityDeposit);

        etPickupLocation =
                findViewById(R.id.etEditPickupLocation);

        etContact =
                findViewById(R.id.etEditContact);

        spCategory =
                findViewById(R.id.spEditCategory);

        rgCondition =
                findViewById(R.id.rgEditCondition);

        rbNew =
                findViewById(R.id.rbEditNew);

        rbGood =
                findViewById(R.id.rbEditGood);

        rbFair =
                findViewById(R.id.rbEditFair);

        btnUpdateItem =
                findViewById(R.id.btnUpdateItem);

        btnEditCurrentLocation =
                findViewById(R.id.btnEditCurrentLocation);

        btnChangeImage =
                findViewById(R.id.btnChangeImage);

        vpEditItemImages =
                findViewById(R.id.vpEditItemImages);

        tvEditImageIndicator =
                findViewById(R.id.tvEditImageIndicator);

        etQuantity = findViewById(R.id.etEditQuantity);
        // =====================================================
        // CATEGORY
        // =====================================================

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

        // =====================================================
        // RECEIVE EXISTING DATA
        // =====================================================

        String itemName =
                getIntent().getStringExtra("itemName");

        String description =
                getIntent().getStringExtra("description");

        String price =
                getIntent().getStringExtra("price");

        String quantity =
                getIntent().getStringExtra("quantity");

        String securityDeposit =
                getIntent().getStringExtra("securityDeposit");


        String location =
                getIntent().getStringExtra("location");

        String mobile =
                getIntent().getStringExtra("mobile");

        String category =
                getIntent().getStringExtra("category");


        // =====================================================
        // FILL EXISTING DATA
        // =====================================================

        if (itemName != null) {
            etItemName.setText(itemName);
        }

        if (description != null) {
            etDescription.setText(description);
        }

        if (price != null) {
            etRentalPrice.setText(price);
        }

        if (quantity != null) {
            etQuantity.setText(quantity);
        }

        if (quantity != null) {
            etQuantity.setText(quantity);
        }

        if (securityDeposit != null) {
            etSecurityDeposit.setText(
                    securityDeposit
            );
        }

        if (location != null) {
            etPickupLocation.setText(location);
        }

        if (mobile != null) {
            etContact.setText(mobile);
        }

        // =====================================================
        // SELECT CATEGORY
        // =====================================================

        if (category != null) {

            for (int i = 0;
                 i < categories.length;
                 i++) {

                if (categories[i].equals(category)) {

                    spCategory.setSelection(i);

                    break;
                }
            }
        }

        // =====================================================
        // VIEWPAGER ADAPTER
        // =====================================================

        imageAdapter =
                new EditItemImageAdapter(
                        selectedImages,
                        image -> showFullImage(image)
                );

        vpEditItemImages.setAdapter(
                imageAdapter
        );

        updateImageIndicator();

        // =====================================================
        // VIEWPAGER PAGE CHANGE
        // =====================================================

        vpEditItemImages.registerOnPageChangeCallback(
                new ViewPager2.OnPageChangeCallback() {

                    @Override
                    public void onPageSelected(
                            int position) {

                        super.onPageSelected(
                                position
                        );

                        updateImageIndicator();
                    }
                }
        );

        // =====================================================
        // CURRENT LOCATION
        // =====================================================

        btnEditCurrentLocation.setOnClickListener(
                v -> checkLocationPermission()
        );

        // =====================================================
        // CHANGE IMAGE
        // =====================================================

        btnChangeImage.setOnClickListener(
                v -> showImageOptions()
        );

        // =====================================================
        // UPDATE
        // =====================================================

        btnUpdateItem.setOnClickListener(
                v -> updateItem()
        );
    }



    // =========================================================
    // IMAGE OPTIONS
    // =========================================================

    private void showImageOptions() {

        String[] options = {

                "📷  Camera",
                "🖼️  Photos"
        };

        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);

        builder.setTitle(
                "Edit Item Photos"
        );

        builder.setItems(
                options,
                (dialog, which) -> {

                    if (which == 0) {

                        // CAMERA

                        if (ActivityCompat.checkSelfPermission(
                                EditItemActivity.this,
                                Manifest.permission.CAMERA
                        ) != PackageManager.PERMISSION_GRANTED) {

                            ActivityCompat.requestPermissions(
                                    EditItemActivity.this,
                                    new String[]{
                                            Manifest.permission.CAMERA
                                    },
                                    CAMERA_PERMISSION_REQUEST
                            );

                        } else {

                            openCamera();
                        }

                    } else {

                        // PHOTOS

                        openGallery();
                    }
                }
        );

        builder.show();
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
                new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE
                );

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
            Intent data) {

        super.onActivityResult(
                requestCode,
                resultCode,
                data
        );

        if (resultCode != RESULT_OK ||
                data == null) {

            return;
        }

        // =====================================================
        // GALLERY
        // =====================================================

        if (requestCode ==
                IMAGE_PICK_REQUEST) {

            ClipData clipData =
                    data.getClipData();

            if (clipData != null) {

                int remainingSlots =
                        MAX_PHOTOS -
                                selectedImages.size();

                int photosToAdd =
                        Math.min(
                                clipData.getItemCount(),
                                remainingSlots
                        );

                for (int i = 0;
                     i < photosToAdd;
                     i++) {

                    Uri imageUri =
                            clipData
                                    .getItemAt(i)
                                    .getUri();

                    selectedImages.add(
                            imageUri
                    );
                }

                Toast.makeText(
                        this,
                        photosToAdd +
                                " photos added",
                        Toast.LENGTH_SHORT
                ).show();

                if (clipData.getItemCount()
                        > remainingSlots) {

                    Toast.makeText(
                            this,
                            "Maximum 5 photos allowed",
                            Toast.LENGTH_SHORT
                    ).show();
                }

            } else {

                Uri imageUri =
                        data.getData();

                if (imageUri != null) {

                    if (selectedImages.size()
                            >= MAX_PHOTOS) {

                        Toast.makeText(
                                this,
                                "Maximum 5 photos allowed",
                                Toast.LENGTH_SHORT
                        ).show();

                        return;
                    }

                    selectedImages.add(
                            imageUri
                    );

                    Toast.makeText(
                            this,
                            "Photo added",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }

            imageAdapter.notifyDataSetChanged();

            updateImageIndicator();
        }

        // =====================================================
        // CAMERA
        // =====================================================

        else if (requestCode ==
                CAMERA_REQUEST) {

            if (selectedImages.size()
                    >= MAX_PHOTOS) {

                Toast.makeText(
                        this,
                        "Maximum 5 photos allowed",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            Bundle extras =
                    data.getExtras();

            if (extras != null) {

                Bitmap imageBitmap =
                        (Bitmap) extras.get("data");

                if (imageBitmap != null) {

                    selectedImages.add(
                            imageBitmap
                    );

                    imageAdapter.notifyDataSetChanged();

                    updateImageIndicator();

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
    // IMAGE INDICATOR
    // =========================================================

    private void updateImageIndicator() {

        int count =
                selectedImages.size();

        if (count == 0) {

            tvEditImageIndicator.setText(
                    "0 photos"
            );

        } else {

            int current =
                    vpEditItemImages.getCurrentItem()
                            + 1;

            tvEditImageIndicator.setText(
                    current +
                            " / " +
                            count
            );
        }
    }

    // =========================================================
// SHOW FULL-SCREEN IMAGE
// =========================================================

    private void showFullImage(Object image) {

        // Create Dialog
        final Dialog dialog = new Dialog(this);

        // Remove default dialog title
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Create ImageView
        ImageView imageView = new ImageView(this);

        // ImageView fills the complete dialog
        imageView.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
        );

        // Keep the complete photo visible
        imageView.setScaleType(
                ImageView.ScaleType.FIT_CENTER
        );

        // Black background around photo
        imageView.setBackgroundColor(Color.BLACK);

        // Set selected image
        if (image instanceof Uri) {

            imageView.setImageURI((Uri) image);

        } else if (image instanceof Bitmap) {

            imageView.setImageBitmap((Bitmap) image);
        }

        // Put ImageView inside Dialog
        dialog.setContentView(imageView);

        // Close when photo is clicked
        imageView.setOnClickListener(
                v -> dialog.dismiss()
        );

        // Show dialog FIRST
        dialog.show();

        // Then make dialog full screen
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
    // UPDATE ITEM
    // =========================================================

    private void updateItem() {

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

        int quantity;

        try {

            quantity = Integer.parseInt(
                    etQuantity.getText()
                            .toString()
                            .trim()
            );

        } catch (NumberFormatException e) {

            etQuantity.setError(
                    "Enter a valid quantity"
            );

            etQuantity.requestFocus();

            return;
        }

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

        Toast.makeText(
                this,
                "Changes submitted successfully. Waiting for admin approval.",
                Toast.LENGTH_SHORT
        ).show();

        finish();
    }

    // =========================================================
    // LOCATION PERMISSION
    // =========================================================

    private void checkLocationPermission() {

        boolean fineLocation =
                ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED;

        boolean coarseLocation =
                ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED;

        if (!fineLocation &&
                !coarseLocation) {

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
            @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(
                requestCode,
                permissions,
                grantResults
        );

        // LOCATION

        if (requestCode ==
                LOCATION_PERMISSION_REQUEST) {

            if (grantResults.length > 0 &&
                    (
                            grantResults[0] ==
                                    PackageManager.PERMISSION_GRANTED
                                    ||
                                    (
                                            grantResults.length > 1 &&
                                                    grantResults[1] ==
                                                            PackageManager.PERMISSION_GRANTED
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

        // CAMERA

        if (requestCode ==
                CAMERA_PERMISSION_REQUEST) {

            if (grantResults.length > 0 &&
                    grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED) {

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

        if (locationManager == null) {

            Toast.makeText(
                    this,
                    "Location service unavailable",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
                &&
                ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(
                    this,
                    "Location permission required",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        try {

            Location lastLocation = null;

            if (locationManager.isProviderEnabled(
                    LocationManager.GPS_PROVIDER)) {

                lastLocation =
                        locationManager.getLastKnownLocation(
                                LocationManager.GPS_PROVIDER
                        );
            }

            if (lastLocation == null &&
                    locationManager.isProviderEnabled(
                            LocationManager.NETWORK_PROVIDER)) {

                lastLocation =
                        locationManager.getLastKnownLocation(
                                LocationManager.NETWORK_PROVIDER
                        );
            }

            if (lastLocation != null) {

                convertLocationToAddress(
                        lastLocation.getLatitude(),
                        lastLocation.getLongitude()
                );

                return;
            }

            LocationListener locationListener =
                    new LocationListener() {

                        @Override
                        public void onLocationChanged(
                                @NonNull Location location) {

                            convertLocationToAddress(
                                    location.getLatitude(),
                                    location.getLongitude()
                            );

                            if (locationManager != null) {

                                locationManager.removeUpdates(
                                        this
                                );
                            }
                        }
                    };

            if (locationManager.isProviderEnabled(
                    LocationManager.GPS_PROVIDER)) {

                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        1000,
                        1,
                        locationListener
                );

            } else if (locationManager.isProviderEnabled(
                    LocationManager.NETWORK_PROVIDER)) {

                locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        1000,
                        1,
                        locationListener
                );

            } else {

                Toast.makeText(
                        this,
                        "Please turn on Location/GPS",
                        Toast.LENGTH_LONG
                ).show();
            }

        } catch (SecurityException e) {

            Toast.makeText(
                    this,
                    "Location permission is required",
                    Toast.LENGTH_LONG
            ).show();

        } catch (Exception e) {

            Toast.makeText(
                    this,
                    "Unable to get current location",
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    // =========================================================
    // CONVERT LOCATION TO ADDRESS
    // =========================================================

    private void convertLocationToAddress(
            double latitude,
            double longitude) {

        try {

            Geocoder geocoder =
                    new Geocoder(
                            this,
                            Locale.getDefault()
                    );

            List<Address> addresses =
                    geocoder.getFromLocation(
                            latitude,
                            longitude,
                            1
                    );

            if (addresses != null &&
                    !addresses.isEmpty()) {

                Address address =
                        addresses.get(0);

                String fullAddress =
                        address.getAddressLine(0);

                if (fullAddress != null &&
                        !fullAddress.isEmpty()) {

                    etPickupLocation.setText(
                            fullAddress
                    );

                    Toast.makeText(
                            this,
                            "Location Added",
                            Toast.LENGTH_SHORT
                    ).show();

                } else {

                    Toast.makeText(
                            this,
                            "Address not found",
                            Toast.LENGTH_SHORT
                    ).show();
                }

            } else {

                Toast.makeText(
                        this,
                        "Address not found",
                        Toast.LENGTH_SHORT
                ).show();
            }

        } catch (Exception e) {

            Toast.makeText(
                    this,
                    "Unable to convert location to address",
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    // =========================================================
    // BACK
    // =========================================================

    @Override
    public boolean onSupportNavigateUp() {

        finish();

        return true;
    }
}

