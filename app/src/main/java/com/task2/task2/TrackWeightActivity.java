package com.task2.task2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TrackWeightActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    User user;
    long count;
    ArrayAdapter arrayAdapter;
    ArrayList values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_weight);

        //final ScrollView scroll = findViewById(R.id.scrollview);
        final TextView textv = new TextView(this);
        final ListView listView = findViewById(R.id.listview);

        values = new ArrayList();

        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        user = (User) getIntent().getSerializableExtra("User");
        Button record = findViewById(R.id.save3);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //this code was directly copied from a friend
                Iterable<DataSnapshot> list = snapshot.child(user.getUsername()).child("dailyweight").getChildren();
                count = snapshot.child(user.getUsername()).child("dailyweight").getChildrenCount();

                Log.i("Child Count", "--------------> " + count);
                for (Iterator<DataSnapshot> i = list.iterator(); i.hasNext();) {
                    DataSnapshot item = i.next();
                    values.add(item.getValue());
                    //Log.i("Long is", "----------------> " + item.getValue());
                }
                //this code was directly copied from a friend

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        arrayAdapter = new ArrayAdapter(TrackWeightActivity.this, android.R.layout.simple_list_item_1, values);
        listView.setAdapter(arrayAdapter);

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText weig = (EditText) findViewById(R.id.weight3);

                //user.setDailyweight(Double.parseDouble(weig.getText().toString()));
                String pattern = "dd-MM-yyyy";
                String dateInString = new SimpleDateFormat(pattern).format(new Date());


                mDatabase.child(user.getUsername()).child("dailyweight").child(Long.toString(count+1)).setValue(weig.getText().toString() + " - on the " + dateInString);

                finish();
                startActivity(getIntent());
            }
        });
    }
}