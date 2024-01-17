package com.example.himama;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yourapp.utils.QuotesProvider;

public class feelActivity extends AppCompatActivity {
    Button btnEmergency;
    TextView edtFeel;
    TextView edtQuote;
    Button btnAngry;
    Button btnQuiz;
    QuotesProvider quotesProvider;  // Include QuotesProvider

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feel);

        edtFeel = findViewById(R.id.edtFeel);
        edtQuote=findViewById(R.id.quoteEdt);
        btnEmergency = findViewById(R.id.btnEmergency);
        btnAngry=findViewById(R.id.btnAngry);
        btnQuiz=findViewById(R.id.btnQuiz);
        quotesProvider = new QuotesProvider();  // Instantiate QuotesProvider

        // Set a random quote to the TextView
        edtQuote.setText(quotesProvider.getRandomQuote());

        btnEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(feelActivity.this, callActivity.class);
                startActivity(intent);
            }
        });

        btnAngry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(feelActivity.this, angryActivity.class);
                startActivity(intent2);
            }
        });

        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(feelActivity.this, quizActivity.class);
                startActivity(intent3);
            }
        });
    }
}
