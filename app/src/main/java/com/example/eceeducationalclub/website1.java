package com.example.eceeducationalclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class website1 extends AppCompatActivity {
    WebView v1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                Toast.makeText(this, "refreshed", Toast.LENGTH_SHORT).show();
                v1.reload();
                return true;
            case R.id.home:
                Intent i1 = new Intent(website1.this, MainActivity.class);
                i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(i1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onBackPressed() {
        if (v1.canGoBack()) {
            v1.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website1);
        v1 = (WebView) findViewById(R.id.Eclub);
        WebSettings webSettings = v1.getSettings();
        webSettings.setJavaScriptEnabled(true);
        v1.loadUrl("https://sites.google.com/view/ece-educational-club/home");
        v1.setWebViewClient(new WebViewClient());


    }

}