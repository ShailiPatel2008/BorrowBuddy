package com.borrowbuddy.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class AdminLoginActivity extends AppCompatActivity {

    private TextInputEditText etAdminEmail, etAdminPassword;
    private MaterialButton btnAdminLogin;
    private TextView txtBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        // Initialize Views
        etAdminEmail = findViewById(R.id.etAdminEmail);
        etAdminPassword = findViewById(R.id.etAdminPassword);
        btnAdminLogin = findViewById(R.id.btnAdminLogin);
        txtBack = findViewById(R.id.txtBack);

        // Login Button
        btnAdminLogin.setOnClickListener(v -> {

            String email = etAdminEmail.getText().toString().trim();
            String password = etAdminPassword.getText().toString().trim();

            // Email Empty
            if (TextUtils.isEmpty(email)) {
                etAdminEmail.setError("Enter Admin Email");
                etAdminEmail.requestFocus();
                return;
            }

            // Email Format
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etAdminEmail.setError("Enter Valid Email");
                etAdminEmail.requestFocus();
                return;
            }

            // Password Empty
            if (TextUtils.isEmpty(password)) {
                etAdminPassword.setError("Enter Password");
                etAdminPassword.requestFocus();
                return;
            }

            // Admin Login
            if (email.equals("admin@gmail.com") &&
                    password.equals("123456")) {

                Toast.makeText(
                        AdminLoginActivity.this,
                        "Admin Login Successful",
                        Toast.LENGTH_SHORT
                ).show();

                Intent intent = new Intent(
                        AdminLoginActivity.this,
                        AdminDashboardActivity.class
                );

                startActivity(intent);
                finish();

            } else {

                Toast.makeText(
                        AdminLoginActivity.this,
                        "Invalid Admin Credentials",
                        Toast.LENGTH_SHORT
                ).show();

            }

        });

        // Back
        txtBack.setOnClickListener(v -> finish());

    }
}