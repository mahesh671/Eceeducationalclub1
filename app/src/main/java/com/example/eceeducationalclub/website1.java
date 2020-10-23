package com.example.eceeducationalclub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class website1 extends AppCompatActivity {
WebView v1;

    @Override
    public void onBackPressed() {
        if(v1.canGoBack())
        {
            v1.goBack();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website1);
    v1=(WebView)findViewById(R.id.Eclub);
        WebSettings webSettings=v1.getSettings();
        webSettings.setJavaScriptEnabled(true);
        v1.loadUrl("https://sites.google.com/view/ece-educational-club/home");
        v1.setWebViewClient(new WebViewClient());

    }
}