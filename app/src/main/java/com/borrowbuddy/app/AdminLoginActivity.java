package com.borrowbuddy.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdminLoginActivity extends AppCompatActivity {

    private TextInputEditText etAdminEmail;
    private TextInputEditText etAdminPassword;
    private MaterialButton btnAdminLogin;
    private TextView txtBack;

    // Firebase
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

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

                                            if ("admin".equals(role)) {

                                                Toast.makeText(
                                                        AdminLoginActivity.this,
                                                        "Admin Login Successful",
                                                        Toast.LENGTH_SHORT
                                                ).show();

                                                Intent intent = new Intent(
                                                        AdminLoginActivity.this,
                                                        AdminMainActivity.class
                                                );

                                                startActivity(intent);
                                                finish();

                                            } else {

                                                FirebaseAuth.getInstance().signOut();

                                                Toast.makeText(
                                                        AdminLoginActivity.this,
                                                        "This account is not registered as an Admin.",
                                                        Toast.LENGTH_LONG
                                                ).show();
                                            }

                                        } else {

                                            FirebaseAuth.getInstance().signOut();

                                            Toast.makeText(
                                                    AdminLoginActivity.this,
                                                    "User data not found.",
                                                    Toast.LENGTH_LONG
                                            ).show();
                                        }

                                    });

                        } else {

                            Toast.makeText(
                                    AdminLoginActivity.this,
                                    task.getException().getMessage(),
                                    Toast.LENGTH_LONG
                            ).show();

                        }

                    });

        });

        // Back
        txtBack.setOnClickListener(v -> finish());

    }
}