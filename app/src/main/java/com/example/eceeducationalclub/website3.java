package com.example.eceeducationalclub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class website3 extends AppCompatActivity {
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
        setContentView(R.layout.activity_website3);
        v1=(WebView)findViewById(R.id.vasaviEcap);
        WebSettings webSettings=v1.getSettings();
        webSettings.setJavaScriptEnabled(true);
        v1.loadUrl("http://srivasaviengg.ac.in/");
        v1.setWebViewClient(new WebViewClient());

    }
}