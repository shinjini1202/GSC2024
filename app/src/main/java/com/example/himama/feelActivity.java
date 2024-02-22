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
    Button btnHappy;
    Button btnSad;
    Button btnAnnoyed;
    Button btnAnxious;
    Button btnQuiz;
    Button btnChat;
    QuotesProvider quotesProvider;  // Include QuotesProvider

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feel);

        edtFeel = findViewById(R.id.edtFeel);
        edtQuote=findViewById(R.id.quoteEdt);
        btnEmergency = findViewById(R.id.btnEmergency);
        btnAngry=findViewById(R.id.btnAngry);
        btnHappy=findViewById(R.id.btnHappy);
        btnSad=findViewById(R.id.btnSad);
        btnAnnoyed=findViewById(R.id.btnIrritated);
        btnAnxious=findViewById(R.id.btnAnxious);
        btnQuiz=findViewById(R.id.btnQuiz);
        btnChat=findViewById(R.id.btnChat);
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
        btnHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(feelActivity.this, happyActivity.class);
                startActivity(intent3);
            }
        });
        btnSad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(feelActivity.this, sadActivity.class);
                startActivity(intent4);
            }
        });
        btnAnnoyed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5 = new Intent(feelActivity.this, annoyedActivity.class);
                startActivity(intent5);
            }
        });
        btnAnxious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent6 = new Intent(feelActivity.this, anxiousActivity.class);
                startActivity(intent6);
            }
        });

        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent7 = new Intent(feelActivity.this, quizActivity.class);
                startActivity(intent7);
            }
        });
        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent8 = new Intent(feelActivity.this, chatActivity.class);
                startActivity(intent8);
            }
        });
    }
}
