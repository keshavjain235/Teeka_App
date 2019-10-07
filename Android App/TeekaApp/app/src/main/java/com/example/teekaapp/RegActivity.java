package com.example.teekaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegActivity extends AppCompatActivity {

    EditText email, pwd, cnfpwd, name, fatname, mobno, dob;
    FirebaseAuth mauth;
    Button signin;

    FirebaseDatabase database;
    DatabaseReference root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        signin = findViewById(R.id.signin);
        email = findViewById(R.id.email);
        pwd = findViewById(R.id.pwd);
        cnfpwd = findViewById(R.id.cnfpwd);
        name = findViewById(R.id.name);
        fatname = findViewById(R.id.fatname);
        mobno = findViewById(R.id.mobno);
        dob = findViewById(R.id.dob);

        mauth = FirebaseAuth.getInstance();
        root = FirebaseDatabase.getInstance().getReference();



        signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if ((email.getText().length() != 0) & (pwd.getText().length() != 0) & (cnfpwd.getText().length() != 0) & (name.getText().length() != 0)
                        & (fatname.getText().length() != 0) & (mobno.getText().length() == 10)) {

                    if(pwd.getText().toString().equals(cnfpwd.getText().toString()))
                    {

                        mauth.createUserWithEmailAndPassword(email.getText().toString(), pwd.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(RegActivity.this, "You have successfully Registered", Toast.LENGTH_SHORT).show();
                                            onAuthSuccess(task.getResult().getUser(),name.getText().toString(),
                                                    fatname.getText().toString(),mobno.getText().toString(), dob.getText().toString());
                                        } else {
                                            Toast.makeText(RegActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                    else {
                        Toast.makeText(RegActivity.this, "Password and Confirm Password don't Match!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(RegActivity.this, "Kindly enter all the details first!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void onAuthSuccess(FirebaseUser user, String name, String fatname, String mobno, String dob) {


        // Write new user
        writeNewUser(user.getUid(), name, user.getEmail(), fatname, mobno, dob);

        // Go to MainActivity
        startActivity(new Intent(RegActivity.this, FirstActivity.class));
        finish();
    }

    private void writeNewUser(String userId, String name, String email, String fatname, String mobno, String dob) {
        User user = new User(userId, name, email, fatname, mobno, dob);

        root.child("users").child(userId).setValue(user);
    }
}


