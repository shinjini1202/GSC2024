package com.example.himama;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class quizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Find the button by its ID
        View startNowButton3 = findViewById(R.id.startNowButton3);
        View startNowButton2 = findViewById(R.id.startNowButton2);
        View startNowButton1 = findViewById(R.id.startNowButton1);

        // Set an OnClickListener on the button for third question
        startNowButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to ThirdQActivity
                Intent intent3 = new Intent(quizActivity.this, thirdqActivity.class);

                // Start the ThirdQActivity
                startActivity(intent3);

            }
        });

        // Set an OnClickListener on the button for second question
        startNowButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to SecondQActivity
                Intent intent2 = new Intent(quizActivity.this, secondqActivity.class);

                // Start the SecondQActivity
                startActivity(intent2);

            }
        });

        // Set an OnClickListener on the button for first question
        startNowButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to FirstQActivity
                Intent intent = new Intent(quizActivity.this, firstqActivity.class);

                // Start the FirstQActivity
                startActivity(intent);

            }
        });
    }
}
