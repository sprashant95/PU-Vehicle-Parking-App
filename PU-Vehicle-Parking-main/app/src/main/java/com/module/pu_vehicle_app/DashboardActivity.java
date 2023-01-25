package com.module.pu_vehicle_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.module.pu_vehicle_app.Model.Users;
import com.module.pu_vehicle_app.Prevalent.Prevalent;

import io.paperdb.Paper;

public class DashboardActivity extends AppCompatActivity {
    String name,mis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#3380cc"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        mis = intent.getStringExtra("mis");

    }

    public void gotomain(View view) {
        Intent intent =  new Intent(getApplicationContext(),MainActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("mis",mis);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu3, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.exit) {
            Toast.makeText(DashboardActivity.this, "Exit Successfully", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();

        }
        if(id == R.id.aboutus)
        {
            startActivity(new Intent(getApplicationContext(),AboutUs.class));
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Press again", Toast.LENGTH_SHORT).show();
        finish();
        super.onBackPressed();
    }
    public void gotosearch(View view) {
        startActivity(new Intent(getApplicationContext(),SearchActivity.class));
    }
}