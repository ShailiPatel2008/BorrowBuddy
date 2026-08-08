package com.borrowbuddy.app.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.borrowbuddy.app.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import com.google.android.material.appbar.MaterialToolbar;

public class ChangePasswordActivity extends AppCompatActivity {

    private TextInputEditText etCurrentPassword;
    private TextInputEditText etNewPassword;
    private TextInputEditText etConfirmPassword;
    private MaterialButton btnUpdatePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        etCurrentPassword = findViewById(R.id.etCurrentPassword);
        etNewPassword = findViewById(R.id.etNewPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnUpdatePassword = findViewById(R.id.btnUpdatePassword);

        btnUpdatePassword.setOnClickListener(v -> {

            String current = etCurrentPassword.getText().toString().trim();
            String newPass = etNewPassword.getText().toString().trim();
            String confirm = etConfirmPassword.getText().toString().trim();

            if (current.isEmpty()) {
                etCurrentPassword.setError("Enter Current Password");
                return;
            }

            if (newPass.isEmpty()) {
                etNewPassword.setError("Enter New Password");
                return;
            }

            if (confirm.isEmpty()) {
                etConfirmPassword.setError("Confirm Your Password");
                return;
            }

            if (!newPass.equals(confirm)) {
                etConfirmPassword.setError("Passwords do not match");
                return;
            }

            if (newPass.length() < 6) {
                etNewPassword.setError("Password must be at least 6 characters");
                return;
            }

            Toast.makeText(ChangePasswordActivity.this,
                    "Password Updated Successfully",
                    Toast.LENGTH_SHORT).show();

            finish();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}