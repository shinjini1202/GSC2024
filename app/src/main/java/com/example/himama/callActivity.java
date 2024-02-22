package com.example.himama;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Objects;

public class callActivity extends AppCompatActivity {

    private Button addbutton, btnGuardian, btnHospital;
    private FirebaseFirestore firestore;
    private static final int REQUEST_CALL_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance();

        // Initialize buttons
        addbutton = findViewById(R.id.addbutton);
        btnGuardian = findViewById(R.id.btnGuardian);
        btnHospital = findViewById(R.id.btnHospital);

        // Set onClickListener for addbutton to navigate to NumbersActivity
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to NumbersActivity
                Intent intent = new Intent(callActivity.this, Numbers.class);
                startActivity(intent);
            }
        });

        // Set onClickListeners for btnGuardian and btnHospital
        btnGuardian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchLocalGuardianNumberAndCall();
            }
        });

        btnHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchHospitalNumberAndCall();
            }
        });
    }

    private void fetchLocalGuardianNumberAndCall() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String uid = currentUser.getUid();
            firestore.collection("emergencyNumbers").document(uid)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                String localGuardianNumber = Objects.requireNonNull(documentSnapshot.getString("localGuardianNumber"));
                                makePhoneCall(localGuardianNumber);
                            } else {
                                Toast.makeText(callActivity.this, "Local guardian's number not found", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(callActivity.this, "Failed to fetch local guardian's number", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            // User is not authenticated
            Toast.makeText(callActivity.this, "Please sign in again", Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchHospitalNumberAndCall() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String uid = currentUser.getUid();
            // Fetch hospital's number from Firestore based on the user's UID
            // Similar to fetchLocalGuardianNumberAndCall() method
        } else {
            // User is not authenticated
            Toast.makeText(callActivity.this, "Please sign in again", Toast.LENGTH_SHORT).show();
        }
    }

    private void makePhoneCall(String phoneNumber) {
        if (ContextCompat.checkSelfPermission(callActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(callActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PERMISSION);
        } else {
            // Permission is already granted, proceed with making the call
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(intent);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, make the call
                // You can call makePhoneCall() method here if you want to make the call immediately after granting the permission
            } else {
                // Permission denied, show a message or handle accordingly
                Toast.makeText(this, "Permission denied to make phone calls", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
