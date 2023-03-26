package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LearnLanguage extends AppCompatActivity {
    private WebView myWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_language);

        myWebView = findViewById(R.id.webView);

        //adding website url
        WebSettings websettings= myWebView.getSettings();
        websettings.setJavaScriptEnabled(true);
        myWebView.loadUrl("https://www.duolingo.com");
        myWebView.setWebViewClient(new WebViewClient());
    }


    //added onBackPressed so that it can function as a webapp and come and go back to different webpages
    @Override
    public void onBackPressed() {
        if (myWebView.canGoBack()) {
            myWebView.goBack();
        }else {
            //goto dashboard
            super.onBackPressed();
        }


    }
}

