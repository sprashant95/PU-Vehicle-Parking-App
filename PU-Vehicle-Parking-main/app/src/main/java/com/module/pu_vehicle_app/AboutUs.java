package com.module.pu_vehicle_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AboutUs extends AppCompatActivity {
ImageView rohit , prashant, dheeraj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        rohit = findViewById(R.id.linkedinR);
        prashant = findViewById(R.id.linkedinP);
        dheeraj = findViewById(R.id.linkedinF);

   rohit.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           String url = "https://www.linkedin.com/in/rohit-singh-1132531b5/";

           Intent i = new Intent(Intent.ACTION_VIEW);
           i.setData(Uri.parse(url));
           startActivity(i);
       }
   });

        prashant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.linkedin.com/in/prashant-sahatiya/";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        dheeraj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.linkedin.com/in/dheerajindia/";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });


    }
}