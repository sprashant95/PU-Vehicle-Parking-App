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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.module.pu_vehicle_app.Model.Users;
import com.module.pu_vehicle_app.Prevalent.Prevalent;

import java.util.HashMap;

import io.paperdb.Paper;

public class RegistrationActivity extends AppCompatActivity {
EditText name, midid, phone, email,password;
Button submit;
private ProgressDialog loadingbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#3380cc"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);


        name = findViewById(R.id.etname);
        midid = findViewById(R.id.etmisid);
        phone = findViewById(R.id.etphone);
        email = findViewById(R.id.etemail);
        password = findViewById(R.id.etpassword);

        Paper.init(this);
        loadingbar=new ProgressDialog(this);
        String UserPasswordKey=Paper.book().read(Prevalent.UserPasswordKey);
        String UserPhoneKey=Paper.book().read(Prevalent.UserMisKey);

        if (UserPasswordKey != "" && UserPhoneKey !=""){
            if (!TextUtils.isEmpty(UserPhoneKey) && !TextUtils.isEmpty(UserPasswordKey)){
                AllowAccess(UserPhoneKey,UserPasswordKey);

                loadingbar.setTitle("Loading");
                loadingbar.setMessage("Please wait while we are checking your credentials");
                loadingbar.setCanceledOnTouchOutside(false);
                loadingbar.show();
            }
        }
        submit = findViewById(R.id.registerbtn);
        loadingbar=new ProgressDialog(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }
        });
    }
    private void AllowAccess(final String phone,final String password) {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Users").child(phone).exists())
                {
                    Users users=dataSnapshot.child("Users").child(phone).getValue(Users.class);

                    if (users.getMis().equals(phone)){
                        if (users.getPassword().equals(password)){
                            Toast.makeText(RegistrationActivity.this,"Logged Successfully " +users.getName(),Toast.LENGTH_LONG).show();
                            loadingbar.dismiss();
                            Prevalent.currentOnlineUser=users;
                            Intent intent = new Intent(getApplicationContext(),DashboardActivity.class);
                            intent.putExtra("name",users.getName());
                            intent.putExtra("mis",users.getMis());
                            startActivity(intent);

                        }
                        else Toast.makeText(RegistrationActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(RegistrationActivity.this,"Account Doesn't Exists",Toast.LENGTH_LONG).show();
                    loadingbar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void createAccount() {
        String mainName= name.getText().toString();
        String mainMis= midid.getText().toString();
        String mainEmail= email.getText().toString();
        String mainPhone= phone.getText().toString();
        String mainPassword= password.getText().toString();

        if (TextUtils.isEmpty(mainName)){
            Toast.makeText(RegistrationActivity.this,"Enter the value of name",Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(mainMis)){
            Toast.makeText(RegistrationActivity.this,"Enter the value of MIS",Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(mainEmail)){
            Toast.makeText(RegistrationActivity.this,"Enter the value of Email",Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(mainPhone)){
            Toast.makeText(RegistrationActivity.this,"Enter the value of phone",Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(mainPassword)){
            Toast.makeText(RegistrationActivity.this,"Enter the value of password",Toast.LENGTH_LONG).show();
        }
        else{
            loadingbar.setTitle("Loading");
            loadingbar.setMessage("Please wait while we are checking your credentials");
            loadingbar.setCanceledOnTouchOutside(false);
            loadingbar.show();

            ValidatePhoneNumber(mainName,mainMis,mainEmail,mainPhone,mainPassword);
        }
    }

    private void ValidatePhoneNumber(String mainName, String mainMis, String mainEmail, String mainPhone, String mainPassword) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (!(datasnapshot.child("Users").child(mainMis).exists())){
                    HashMap<String, Object> userdataMap=new HashMap<>();
                    userdataMap.put("phone",mainPhone);
                    userdataMap.put("password",mainPassword);
                    userdataMap.put("name",mainName);
                    userdataMap.put("mis",mainMis);
                    userdataMap.put("email",mainEmail);


                    RootRef.child("Users").child(mainMis).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(RegistrationActivity.this, "Account Created Successfully", Toast.LENGTH_LONG).show();
                                        loadingbar.dismiss();
                                        startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));

                                    }
                                    else{
                                        Toast.makeText(RegistrationActivity.this, "Network Error", Toast.LENGTH_LONG).show();

                                        loadingbar.dismiss();

                                    }
                                }
                            });

                }
                else{
                    Toast.makeText(RegistrationActivity.this, "Already Exists", Toast.LENGTH_LONG).show();
                    loadingbar.dismiss();
                    startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void gotologin(View view) {
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));

    }
}