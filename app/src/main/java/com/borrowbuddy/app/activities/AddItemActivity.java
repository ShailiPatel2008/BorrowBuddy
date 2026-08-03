package com.borrowbuddy.app.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.app.DatePickerDialog;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import java.util.List;
import java.util.Locale;

import android.widget.ImageView;
import android.app.AlertDialog;
import android.content.Intent;
import android.provider.MediaStore;
import android.net.Uri;
import android.graphics.Bitmap;

import com.borrowbuddy.app.R;

public class AddItemActivity extends AppCompatActivity {

    EditText etItemName, etDescription, etRentalPrice,
            etSecurityDeposit,
            etAvailability, etContact;

    Spinner spCategory;

    RadioGroup rgCondition;

    Button btnUploadImage, btnListItem, btnCurrentLocation;

    EditText etPickupLocation;;

    LocationManager locationManager;

    private static final int LOCATION_PERMISSION_REQUEST = 101;

    ImageView imgItem;

    Uri selectedImageUri = null;

    private static final int IMAGE_PICK_REQUEST = 200;
    private static final int CAMERA_REQUEST = 201;

    private static final int CAMERA_PERMISSION_REQUEST = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        com.google.android.material.appbar.MaterialToolbar toolbar =
                findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Submit Item Request");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Initialize Views
        etItemName = findViewById(R.id.etItemName);
        etDescription = findViewById(R.id.etDescription);
        etRentalPrice = findViewById(R.id.etRentalPrice);
        etSecurityDeposit = findViewById(R.id.etSecurityDeposit);
        btnCurrentLocation = findViewById(R.id.btnCurrentLocation);
        etPickupLocation = findViewById(R.id.etPickupLocation);
        etAvailability = findViewById(R.id.etAvailability);
        etContact = findViewById(R.id.etContact);

        spCategory = findViewById(R.id.spCategory);
        rgCondition = findViewById(R.id.rgCondition);

        btnUploadImage = findViewById(R.id.btnUploadImage);
        btnListItem = findViewById(R.id.btnListItem);

        imgItem = findViewById(R.id.imgItem);

        btnCurrentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                checkLocationPermission();

            }
        });

        etAvailability.setOnClickListener(view -> {

            Calendar calendar = Calendar.getInstance();

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    AddItemActivity.this,
                    (datePicker, selectedYear, selectedMonth, selectedDay) -> {

                        String selectedDate = selectedDay + "/" +
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

        // Spinner Data
        String[] categories = {
                "Electronics",
                "Tools",
                "Books",
                "Sports Equipment",
                "Furniture",
                "Kitchen",
                "Others"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                categories
        );

        spCategory.setAdapter(adapter);

        // Upload Image Button
        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String[] options = {
                        "📷 Camera",
                        "🖼️ Photos"
                };


                AlertDialog.Builder builder =
                        new AlertDialog.Builder(AddItemActivity.this);


                builder.setTitle("Upload Item Image");


                builder.setItems(options, (dialog, which) -> {


                    if (which == 0) {

                        if (ActivityCompat.checkSelfPermission(
                                AddItemActivity.this,
                                Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {


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

                        // Open Gallery

                        Intent galleryIntent =
                                new Intent(Intent.ACTION_PICK);

                        galleryIntent.setType("image/*");

                        startActivityForResult(
                                galleryIntent,
                                IMAGE_PICK_REQUEST
                        );

                    }

                });


                builder.show();

            }
        });

        // List Item Button
        btnListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateFields();
            }
        });

    }

    private void validateFields() {

        if (etItemName.getText().toString().trim().isEmpty()) {
            etItemName.setError("Enter Item Name");
            etItemName.requestFocus();
            return;
        }

        if (etDescription.getText().toString().trim().isEmpty()) {
            etDescription.setError("Enter Description");
            etDescription.requestFocus();
            return;
        }

        if (etRentalPrice.getText().toString().trim().isEmpty()) {
            etRentalPrice.setError("Enter Rental Price");
            etRentalPrice.requestFocus();
            return;
        }

        if (etSecurityDeposit.getText().toString().trim().isEmpty()) {
            etSecurityDeposit.setError("Enter Security Deposit");
            etSecurityDeposit.requestFocus();
            return;
        }


        if (etPickupLocation.getText().toString().trim().isEmpty()) {

            etPickupLocation.setError("Enter Pickup Location");
            etPickupLocation.requestFocus();

            return;
        }

        if (etAvailability.getText().toString().trim().isEmpty()) {
            etAvailability.setError("Select Available From Date");
            etAvailability.requestFocus();
            return;
        }

        if (etContact.getText().toString().trim().isEmpty()) {
            etContact.setError("Enter Contact Number");
            etContact.requestFocus();
            return;
        }

        if (rgCondition.getCheckedRadioButtonId() == -1) {

            Toast.makeText(this,
                    "Please select item condition",
                    Toast.LENGTH_SHORT).show();

            return;
        }

        if (selectedImageUri == null) {

            Toast.makeText(this,
                    "Please upload item image",
                    Toast.LENGTH_SHORT).show();

            return;
        }

        Toast.makeText(this,
                "Item request submitted successfully. Waiting for admin approval.",
                Toast.LENGTH_LONG).show();


    }

    private void openCamera() {

        Intent cameraIntent =
                new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(
                cameraIntent,
                CAMERA_REQUEST
        );

    }

    private void checkLocationPermission() {

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {


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


    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(
                requestCode,
                permissions,
                grantResults);


        if (requestCode == LOCATION_PERMISSION_REQUEST) {

            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                getCurrentLocation();

            } else {

                Toast.makeText(
                        this,
                        "Location Permission Denied",
                        Toast.LENGTH_SHORT
                ).show();

            }
        }

        if (requestCode == CAMERA_PERMISSION_REQUEST) {

            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                openCamera();

            } else {

                Toast.makeText(this,
                        "Camera Permission Denied",
                        Toast.LENGTH_SHORT).show();

            }

        }
    }


    private void getCurrentLocation() {

        Toast.makeText(this,
                "Getting Location...",
                Toast.LENGTH_SHORT).show();


        locationManager =
                (LocationManager) getSystemService(LOCATION_SERVICE);


        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            return;
        }


        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                1000,
                1,
                new LocationListener() {

                    @Override
                    public void onLocationChanged(Location location) {


                        double latitude =
                                location.getLatitude();

                        double longitude =
                                location.getLongitude();


                        convertLocationToAddress(
                                latitude,
                                longitude
                        );


                        locationManager.removeUpdates(this);

                    }
                });

    }


    private void convertLocationToAddress(
            double latitude,
            double longitude) {


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
                    addresses.size() > 0) {


                String address =
                        addresses.get(0)
                                .getAddressLine(0);


                etPickupLocation.setText(address);


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

    @Override
    protected void onActivityResult(
            int requestCode,
            int resultCode,
            Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK || data == null) {
            return;
        }

        if (requestCode == IMAGE_PICK_REQUEST) {

            selectedImageUri = data.getData();

            if (selectedImageUri != null) {
                imgItem.setImageURI(selectedImageUri);
            }

        } else if (requestCode == CAMERA_REQUEST) {

            Bundle extras = data.getExtras();

            if (extras != null) {

                Bitmap imageBitmap = (Bitmap) extras.get("data");

                if (imageBitmap != null) {

                    Toast.makeText(this,
                            "Camera image received",
                            Toast.LENGTH_SHORT).show();

                    imgItem.setImageBitmap(imageBitmap);

                    // Only for validation
                    selectedImageUri = Uri.parse("camera_image");
                }
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
