package com.task2.task2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class PhysiologicalInfoActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physiological_info);

        EditText h = findViewById(R.id.height);
        EditText w = findViewById(R.id.weight);
        TextView first = findViewById(R.id.firstname);
        TextView last = findViewById(R.id.lastname);
        final Button target = findViewById(R.id.targets);
        final Button track = findViewById(R.id.targets2);

        user = (User) getIntent().getSerializableExtra("User");
        Log.i("Data", user.firstName + " in Physio");
        first.setText(user.getFirstName());
        last.setText(user.getLastName());

        if (user.getSystem().toLowerCase().equals("metric")) {
            h.setText(user.getHeight());
            TextView hu = findViewById(R.id.hunit);
            hu.setText("m");
            w.setText(user.getWeight());
            TextView wu = findViewById(R.id.wunit);
            wu.setText("kg");
        }
        if (user.getSystem().toLowerCase().equals("imperial")){
            h.setText((int) Math.round(Integer.parseInt(user.getHeight()) * 39.36));
            TextView hu = findViewById(R.id.hunit);
            hu.setText("in");
            w.setText((int) Math.round(Integer.parseInt(user.getWeight()) * 2.2046));
            TextView wu = findViewById(R.id.wunit);
            wu.setText("pounds");
        }


        Button save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText weig = (EditText) findViewById(R.id.weight);
                EditText heig = (EditText) findViewById(R.id.height);

                user.setWeight(weig.getText().toString());
                user.setHeight(heig.getText().toString());

                mDatabase = FirebaseDatabase.getInstance().getReference("users");

                mDatabase.child(user.getUsername()).child("weight").setValue(weig.getText().toString());
                mDatabase.child(user.getUsername()).child("height").setValue(heig.getText().toString());

                finish();
                startActivity(getIntent());
            }
        });

        target.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    // Get a reference to our posts
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference users = database.getReference("users");

                    // Attach a listener to read the data at posts reference
                    Query getUser = users.orderByChild("username").equalTo(user.getUsername());
                    getUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String passwordEntered = snapshot.child(user.getUsername()).child("password").getValue().toString();
                            String u = user.getUsername();
                                Log.i("Data", " --------------> User Found - MainActivity");
                                String first = snapshot.child(u).child("firstName").getValue(String.class);
                                String last = snapshot.child(u).child("lastName").getValue(String.class);
                                String targetWeight = snapshot.child(u).child("targetweight").getValue(String.class);
                                String calorieintake = snapshot.child(u).child("calorieintake").getValue(String.class);
                                String system = snapshot.child(u).child("system").getValue(String.class);
                                user.setFirstName(first);
                                user.setLastName(last);
                                user.setTargetweight(targetWeight);
                                user.setCalorieintake(calorieintake);
                                user.setSystem(system);

                                Intent intent = new Intent(getBaseContext(), TargetActivity.class);
                                intent.putExtra("User", user);
                                startActivity(intent);

                                //YOU NEED TO CHANGE ALL ATTRIBUTES TO STRING!!!!!!!!!!!!!!!
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
            }
        });

        track.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // Get a reference to our posts
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference users = database.getReference("users");

                // Attach a listener to read the data at posts reference
                Query getUser = users.orderByChild("username").equalTo(user.getUsername());
                getUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String passwordEntered = snapshot.child(user.getUsername()).child("password").getValue().toString();
                        String u = user.getUsername();
                        Log.i("Data", " --------------> User Found - MainActivity");
                        String first = snapshot.child(u).child("firstName").getValue(String.class);
                        String last = snapshot.child(u).child("lastName").getValue(String.class);
                        String targetWeight = snapshot.child(u).child("targetweight").getValue(String.class);
                        String calorieintake = snapshot.child(u).child("calorieintake").getValue(String.class);
                        String system = snapshot.child(u).child("system").getValue(String.class);
                        user.setFirstName(first);
                        user.setLastName(last);
                        user.setTargetweight(targetWeight);
                        user.setCalorieintake(calorieintake);
                        user.setSystem(system);

                        Intent intent = new Intent(getBaseContext(), TrackWeightActivity.class);
                        intent.putExtra("User", user);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }


}