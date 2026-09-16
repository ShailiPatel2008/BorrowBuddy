package com.borrowbuddy.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class OwnerLoginActivity extends AppCompatActivity {

    private TextInputEditText etOwnerEmail;
    private TextInputEditText etOwnerPassword;

    private MaterialButton btnOwnerLogin;
    private Button btnOwnerCreate;

    private TextView txtOwnerForgot;
    private TextView txtOwnerBack;

    // Firebase
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_login);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Initialize Views
        etOwnerEmail = findViewById(R.id.etOwnerEmail);
        etOwnerPassword = findViewById(R.id.etOwnerPassword);

        btnOwnerLogin = findViewById(R.id.btnOwnerLogin);
        btnOwnerCreate = findViewById(R.id.btnOwnerCreate);

        txtOwnerForgot = findViewById(R.id.txtOwnerForgot);
        txtOwnerBack = findViewById(R.id.txtOwnerBack);

        // =========================
        // OWNER LOGIN
        // =========================

        btnOwnerLogin.setOnClickListener(v -> {

            String email = etOwnerEmail.getText().toString().trim();
            String password = etOwnerPassword.getText().toString().trim();

            if (email.isEmpty()) {
                etOwnerEmail.setError("Enter Owner Email");
                etOwnerEmail.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                etOwnerPassword.setError("Enter Password");
                etOwnerPassword.requestFocus();
                return;
            }

            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {

                        if (task.isSuccessful()) {

                            String uid = auth.getCurrentUser().getUid();

                            db.collection("users")
                                    .document(uid)
                                    .get()
                                    .addOnSuccessListener(documentSnapshot -> {

                                        if (documentSnapshot.exists()) {

                                            String role = documentSnapshot.getString("role");

                                            if ("owner".equals(role)) {

                                                Toast.makeText(
                                                        OwnerLoginActivity.this,
                                                        "Owner Login Successful",
                                                        Toast.LENGTH_SHORT
                                                ).show();

                                                Intent intent = new Intent(
                                                        OwnerLoginActivity.this,
                                                        OTPActivity.class
                                                );

                                                intent.putExtra("role", "owner");

                                                startActivity(intent);
                                                finish();

                                            } else {

                                                FirebaseAuth.getInstance().signOut();

                                                Toast.makeText(
                                                        OwnerLoginActivity.this,
                                                        "This account is not registered as an Owner.",
                                                        Toast.LENGTH_LONG
                                                ).show();
                                            }

                                        }

                                    });

                        } else {

                            Toast.makeText(
                                    OwnerLoginActivity.this,
                                    task.getException().getMessage(),
                                    Toast.LENGTH_LONG
                            ).show();

                        }

                    });

        });

        // =========================
        // CREATE ACCOUNT
        // =========================

        btnOwnerCreate.setOnClickListener(v -> {

            Intent intent = new Intent(
                    OwnerLoginActivity.this,
                    RegisterActivity.class
            );

            intent.putExtra("role", "owner");

            startActivity(intent);

        });

        // =========================
        // FORGOT PASSWORD
        // =========================

        txtOwnerForgot.setOnClickListener(v -> {

            Intent intent = new Intent(
                    OwnerLoginActivity.this,
                    ForgotPasswordActivity.class
            );

            startActivity(intent);

        });

        // =========================
        // BACK
        // =========================

        txtOwnerBack.setOnClickListener(v -> finish());

    }
}