package com.module.pu_vehicle_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class SplashScreen extends AppCompatActivity {
ImageView bg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        bg = findViewById(R.id.bg);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        Thread splashTread = new Thread(){


            @Override

            public void run() {

                try {

                    sleep(4800); //4800

                    startActivity(new Intent(SplashScreen.this, RegistrationActivity.class));

                    finish();

                } catch (InterruptedException e) {

                    e.printStackTrace();

                }

                super.run();

            }

        };

        splashTread.start();

    }
}