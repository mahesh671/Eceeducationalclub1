package com.example.eceeducationalclub;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EasySplashScreen config = new EasySplashScreen(SplashScreenActivity.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(2000)
                .withBackgroundColor(Color.parseColor("#1a1b29"))
                .withHeaderText("Welcome! To")
                .withAfterLogoText("ECE Educationalclub App")
                .withBackgroundColor(Color.WHITE)

                .withLogo(R.mipmap.ic_launcher_foreground);

        config.getAfterLogoTextView().setTextColor(Color.BLACK);
        config.getHeaderTextView().setTextColor(Color.RED);
        config.getHeaderTextView().setTextSize(52);
        config.getAfterLogoTextView().setTextSize(25);
        config.getHeaderTextView().setShadowLayer(5,5,5,Color.BLACK);

        View easySplashScreen=config.create();
        setContentView(easySplashScreen);
    }
}