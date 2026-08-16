package com.borrowbuddy.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class AdminLoginActivity extends AppCompatActivity {

    private TextInputEditText etAdminEmail;
    private TextInputEditText etAdminPassword;
    private MaterialButton btnAdminLogin;
    private TextView txtBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        etAdminEmail = findViewById(R.id.etAdminEmail);
        etAdminPassword = findViewById(R.id.etAdminPassword);
        btnAdminLogin = findViewById(R.id.btnAdminLogin);
        txtBack = findViewById(R.id.txtBack);

        btnAdminLogin.setOnClickListener(v -> {

            String email = etAdminEmail.getText().toString().trim();
            String password = etAdminPassword.getText().toString().trim();

            if (email.isEmpty()) {
                etAdminEmail.setError("Enter Admin Email");
                etAdminEmail.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                etAdminPassword.setError("Enter Password");
                etAdminPassword.requestFocus();
                return;
            }

            // Admin Login
            if (email.equals("admin@gmail.com")
                    && password.equals("123456")) {

                Toast.makeText(
                        AdminLoginActivity.this,
                        "Admin Login Successful",
                        Toast.LENGTH_SHORT
                ).show();

                // Directly open Welcome Admin page
                Intent intent = new Intent(
                        AdminLoginActivity.this,
                        AdminHomeActivity.class
                );

                startActivity(intent);
                finish();

            } else {

                Toast.makeText(
                        AdminLoginActivity.this,
                        "Invalid Admin Email or Password",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        // Back to Choose Role
        txtBack.setOnClickListener(v -> finish());
    }
}