package com.module.pu_vehicle_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.module.pu_vehicle_app.Model.Users;
import com.module.pu_vehicle_app.Prevalent.Prevalent;
import com.rey.material.widget.CheckBox;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {
EditText misid, password;
Button submit;
    private CheckBox checkBox;
    private ProgressDialog loadingbar;
     String parentDbName="Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        misid = findViewById(R.id.etmisid);
        password = findViewById(R.id.etpassword);
        submit = findViewById(R.id.loginbtn);
        loadingbar=new ProgressDialog(LoginActivity.this);
        checkBox=findViewById(R.id.checkbox);

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#3380cc"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUser();
            }
        });

        Paper.init(this);

    }

    private void LoginUser() {
        String mainMis= misid.getText().toString();
        String mainPassword= password.getText().toString();

        if (TextUtils.isEmpty(mainMis)){
            Toast.makeText(LoginActivity.this,"Enter the value of MIS id",Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(mainPassword)){
            Toast.makeText(LoginActivity.this,"Enter the value of Password",Toast.LENGTH_LONG).show();
        }
        else{
            loadingbar.setTitle("Loading");
            loadingbar.setMessage("Please wait while we are checking your credentials");
            loadingbar.setCanceledOnTouchOutside(false);
            loadingbar.show();

            AllowAccessToAccount(mainMis,mainPassword);
        }
    }

    private void AllowAccessToAccount(String mainMis, String mainPassword) {
        if (checkBox.isChecked()){

            Paper.book().write(Prevalent.UserMisKey,mainMis);
            Paper.book().write(Prevalent.UserPasswordKey,mainPassword);
        }

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(parentDbName).child(mainMis).exists())
                {
                    Users users=dataSnapshot.child(parentDbName).child(mainMis).getValue(Users.class);

                    if (users.getMis().equals(mainMis)){
                        if (users.getPassword().equals(mainPassword)){


                            Toast.makeText(LoginActivity.this,"Logged Successfully, " + users.getName(),Toast.LENGTH_LONG).show();
                            loadingbar.dismiss();
                            Prevalent.currentOnlineUser = users;

                            Intent intent = new Intent(getApplicationContext(),DashboardActivity.class);
                            intent.putExtra("name",users.getName());
                            intent.putExtra("mis",users.getMis());
                            startActivity(intent);
                        }
                        else Toast.makeText(LoginActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                    }

                }else{

                    Toast.makeText(LoginActivity.this,"Account Doesn't Exists",Toast.LENGTH_LONG).show();
                    loadingbar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void gotoregister(View view) {
        startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));
    }

    public void gotomain(View view) {
        startActivity(new Intent(getApplicationContext(),DashboardActivity.class));

    }
}