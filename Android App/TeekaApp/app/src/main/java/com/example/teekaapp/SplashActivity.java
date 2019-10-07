package com.example.teekaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class SplashActivity extends AppCompatActivity {

    EditText emails,pwds;
    Button login;
    TextView signin;

    FirebaseAuth mauth;

    FirebaseDatabase database;
    DatabaseReference tokennode;
    String generatedToken;

    boolean islogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mauth = FirebaseAuth.getInstance();

        emails = findViewById(R.id.email);
        pwds = findViewById(R.id.pwd);

        signin = findViewById(R.id.sign);
        login = findViewById(R.id.login);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, RegActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((emails.getText().length() != 0) & (pwds.getText().length() != 0)) {
                    mauth.signInWithEmailAndPassword(emails.getText().toString(),pwds.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SplashActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), FirstActivity.class));
                            }else {
                                Toast.makeText(SplashActivity.this, "Wrong UserID and Password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mauth.getCurrentUser();
        if(user != null) {
            islogin = true;

            startActivity(new Intent(SplashActivity.this,FirstActivity.class));
            finish();
        }
    }
}