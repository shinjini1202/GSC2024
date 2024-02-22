package com.example.himama;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Numbers extends AppCompatActivity {

    private EditText editLocalGuardianNumber, editHospitalNumber;
    private Button buttonSave;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance();

        // Find views by their IDs
        editLocalGuardianNumber = findViewById(R.id.editLocalGuardianNumber);
        editHospitalNumber = findViewById(R.id.editHospitalNumber);
        buttonSave = findViewById(R.id.buttonSave);

        // Set click listener for the save button
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEmergencyNumbers();
            }
        });
    }

    private void saveEmergencyNumbers() {
        // Get the entered local guardian number and hospital number
        String localGuardianNumber = editLocalGuardianNumber.getText().toString();
        String hospitalNumber = editHospitalNumber.getText().toString();

        // Check if the user is authenticated
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            // User is authenticated, get the UID
            String uid = currentUser.getUid();

            // Create a map to represent the data structure
            Map<String, Object> data = new HashMap<>();
            data.put("localGuardianNumber", localGuardianNumber);
            data.put("hospitalNumber", hospitalNumber);

            // Update or set the document in Firestore with the user's UID as the document ID
            firestore.collection("emergencyNumbers").document(uid)
                    .set(data)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Data saved successfully
                            Toast.makeText(Numbers.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Failed to save data
                            Toast.makeText(Numbers.this, "Failed to save", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            // User is not authenticated
            Toast.makeText(Numbers.this, "Please sign in again", Toast.LENGTH_SHORT).show();
        }
    }
}
