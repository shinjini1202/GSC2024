package com.example.himama;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
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
import java.util.Random;

public class annoyedActivity extends AppCompatActivity {

    private List<String> videoLinks;
    private int currentLinkIndex;
    private List<WebView> webViews;
    private Button moreButton;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annoyed);

        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance();

        // Initialize web views
        WebView webView1 = findViewById(R.id.webView1);
        WebView webView2 = findViewById(R.id.webView2);
        WebView webView3 = findViewById(R.id.webView3);

        webViews = new ArrayList<>();
        webViews.add(webView1);
        webViews.add(webView2);
        webViews.add(webView3);

        // Initialize more button
        moreButton = findViewById(R.id.moreBtn);
        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchAndDisplayVideos();
            }
        });

        // Initialize video links list
        videoLinks = new ArrayList<>();
        currentLinkIndex = -1;

        // Fetch and display initial set of videos
        fetchAndDisplayVideos();
    }

    private void fetchAndDisplayVideos() {
        // Clear previous video links
        videoLinks.clear();

        // Fetch new video links from Firestore
        firestore.collection("Moodboard")
                .document("Annoyed")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            // Get video links array from Firestore
                            List<String> links = (List<String>) documentSnapshot.get("Links");
                            if (links != null) {
                                videoLinks.addAll(links);
                                // Shuffle the list of video links
                                Collections.shuffle(videoLinks);
                                // Display the first 3 video links
                                displayNextVideos();
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle failure to fetch video links
                        Toast.makeText(annoyedActivity.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void displayNextVideos() {
        // Increment current link index
        currentLinkIndex++;

        // Check if all links have been displayed
        if (currentLinkIndex + 3 > videoLinks.size()) {
            // Shuffle the list of video links and reset the current link index
            Collections.shuffle(videoLinks);
            currentLinkIndex = 0;
        }

        // Load the next 3 video links into the web views
        for (int i = 0; i < 3; i++) {
            final String videoLink = videoLinks.get(currentLinkIndex + i);
            final WebView webView = webViews.get(i);
            webView.setWebViewClient(new WebViewClient());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(videoLink);
            webView.setWebViewClient(new WebViewClient() {
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
        }

        // Scroll to the top of the activity after loading new videos
        findViewById(android.R.id.content).scrollTo(0, 0);
    }
}
