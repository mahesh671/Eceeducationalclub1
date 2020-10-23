package com.example.eceeducationalclub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {
Button b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    b1=(Button)findViewById(R.id.web1);
    b2=(Button)findViewById(R.id.web2);
    b3=(Button)findViewById(R.id.web3);
    b1.setOnClickListener(new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            Intent i1=new Intent(MainActivity.this,website1.class);
            startActivity(i1);
        }
    });
        b2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i2=new Intent(MainActivity.this,website2.class);
                startActivity(i2);
            }
        });
        b3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i3=new Intent(MainActivity.this,website3.class);
                startActivity(i3);
            }
        });
    }

}