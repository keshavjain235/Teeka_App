package com.example.teekaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Act1 extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference root;

    FloatingActionButton add;
    RecyclerView rc;
    EditText topic, desc;

    List<Todo> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act1);

        add = findViewById(R.id.add);
        topic = findViewById(R.id.topic);
        desc = findViewById(R.id.desc);

        //init the recycler view object
        rc = findViewById(R.id.rc);
        rc.setLayoutManager(new LinearLayoutManager(Act1.this));

        list = new ArrayList<>();
        database = FirebaseDatabase.getInstance();

        root = database.getReference("todo");

//        rc = findViewById(R.id.rc);
//        rc.setLayoutManager(new LinearLayoutManager(Act1.this));
//        Adapter a = new Adapter(Act1.this,names);
//        rc.setAdapter(a);



//        root.child(key).setValue(new Todo("math","today i have to do chapter 12","not done"));

//        root.setValue(new Todo("math","today i have to do chapter 12","not done"));


        //receiving the data from server
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot d:dataSnapshot.getChildren()){

                    Todo t = d.getValue(Todo.class);
                    list.add(t);

                }

                rc.setAdapter(new Adapter(Act1.this,list));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(Act1.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String key = root.push().getKey();
                root.child(key).setValue(new Todo(topic.getText().toString(),desc.getText().toString(),"not done"))
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()) {
                                    topic.setText("");
                                    desc.setText("");
                                    Toast.makeText(Act1.this, "Added", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}