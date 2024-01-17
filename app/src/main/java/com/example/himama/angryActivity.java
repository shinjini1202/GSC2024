package com.example.himama;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class angryActivity extends AppCompatActivity {

    WebView webView, webbView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_angry);

        WebView webView = findViewById(R.id.webView);
        WebView webbView= findViewById(R.id.webbView);

        // Replace "VIDEO_ID" with the actual YouTube video ID
        String videoId = "0ZB_DBIMsf4";
        String videoUrl = "https://www.youtube.com/embed/" + videoId;

        String videoId2 = "I-JtHoQtUNY";
        String videoUrl2 = "https://www.youtube.com/embed/" + videoId2;

        // Enable JavaScript for YouTube embeds
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        WebSettings webSettings2 = webbView.getSettings();
        webSettings2.setJavaScriptEnabled(true);

        // Set a custom WebViewClient to handle events
        webView.setWebViewClient(new CustomWebViewClient());
        webbView.setWebViewClient(new CustomWebViewClient());

        // Load YouTube video in the WebView
        webView.loadUrl(videoUrl);
        webbView.loadUrl(videoUrl2);
    }

    private static class CustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // Handle URL loading here
            // Return true if the URL is handled within the WebView, false otherwise
            return false;
        }
    }
}
