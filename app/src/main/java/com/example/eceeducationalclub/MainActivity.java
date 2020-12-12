package com.example.eceeducationalclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public class MainActivity extends AppCompatActivity implements UpdateHelper.OnUpdateCheckListener {
    Button b1, b2, b3;
    private FirebaseRemoteConfig remoteConfig;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.web1);
        b2 = (Button) findViewById(R.id.web2);
        b3 = (Button) findViewById(R.id.web3);

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(MainActivity.this, website1.class);
                startActivity(i1);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(MainActivity.this, website2.class);
                startActivity(i2);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(MainActivity.this, website3.class);
                startActivity(i3);
            }
        });

        UpdateHelper.with(this)
                .onUpdateCheck(this)
                .check();
    }

    @Override
    public void onUpdateCheckListener(String urlApp) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("NEW UPDATE IS AVAILABLE")
                .setMessage("Please Update for Better Experience")
                .setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "" + urlApp, Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create();
        alertDialog.show();
    }
}