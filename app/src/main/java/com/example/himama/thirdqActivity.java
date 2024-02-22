package com.example.himama;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class thirdqActivity extends AppCompatActivity {

    private TextView textViewQuestion;
    private Button buttonOption1, buttonOption2, buttonOption3, buttonNext;
    private List<Button> optionButtons;
    private TextView textViewExplanation;

    private FirebaseFirestore firestore;

    private List<String> questionIds;
    private int currentQuestionIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thirdq);

        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance();

        // Initialize UI elements
        textViewQuestion = findViewById(R.id.textViewQuestion);
        buttonOption1 = findViewById(R.id.buttonOption1);
        buttonOption2 = findViewById(R.id.buttonOption2);
        buttonOption3 = findViewById(R.id.buttonOption3);
        buttonNext = findViewById(R.id.buttonNext);
        textViewExplanation = findViewById(R.id.textViewExplanation);

        // Initialize option buttons list
        optionButtons = new ArrayList<>();
        optionButtons.add(buttonOption1);
        optionButtons.add(buttonOption2);
        optionButtons.add(buttonOption3);

        // Initialize question IDs list
        questionIds = new ArrayList<>();
        questionIds.add("Question1");
        questionIds.add("Question2");
        questionIds.add("Question3");
        questionIds.add("Question4");
        questionIds.add("Question5");
        questionIds.add("Question6");
        questionIds.add("Question7");
        questionIds.add("Question8");
        questionIds.add("Question9");
        questionIds.add("Question10");

        // Shuffle question IDs list
        Collections.shuffle(questionIds);

        // Initialize current question index
        currentQuestionIndex = -1;

        // Set onClickListener for "Next" button
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear explanation
                textViewExplanation.setText("");
                // Fetch and display next question
                fetchNextQuestion();
            }
        });

        // Fetch and display next question
        fetchNextQuestion();
    }

    private void fetchNextQuestion() {
        // Increment current question index
        currentQuestionIndex++;

        // Check if all questions have been answered
        if (currentQuestionIndex >= questionIds.size()) {
            // Shuffle question IDs list
            Collections.shuffle(questionIds);
            // Reset current question index
            currentQuestionIndex = 0;
        }

        // Fetch question and options from Firestore
        firestore.collection("QuizQuestions")
                .document("3rdTrimester")
                .collection("Questions3")
                .document(questionIds.get(currentQuestionIndex))
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            // Display question
                            String question = documentSnapshot.getString("Question");
                            textViewQuestion.setText(question);

                            // Display options
                            List<String> options = (List<String>) documentSnapshot.get("Options");
                            for (int i = 0; i < options.size(); i++) {
                                optionButtons.get(i).setText(options.get(i));
                            }

                            // Set onClickListeners for option buttons
                            for (Button button : optionButtons) {
                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String selectedOption = ((Button) v).getText().toString();
                                        String correctOption = documentSnapshot.getString("Correct");

                                        // Check if selected option is correct
                                        if (selectedOption.equals(correctOption)) {
                                            Toast.makeText(thirdqActivity.this, "Correct", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(thirdqActivity.this, "Incorrect", Toast.LENGTH_LONG).show();
                                        }
                                        // Display explanation
                                        String explanation = documentSnapshot.getString("Explanation");
                                        textViewExplanation.setText(explanation);

                                        // Show "Next" button
                                        buttonNext.setVisibility(View.VISIBLE);
                                    }
                                });
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(thirdqActivity.this, "Failed to fetch question", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
