package com.task2.task2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingActivity extends AppCompatActivity {

    User user;
    private DatabaseReference mDatabase;
    RadioButton m;
    RadioButton i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        TextView first = findViewById(R.id.firstname2);
        TextView last = findViewById(R.id.lastname2);
        m = findViewById(R.id.metric);
        i = findViewById(R.id.imperial);
        RadioGroup rg = findViewById(R.id.rbGroup);

        user = (User) getIntent().getSerializableExtra("User");

        first.setText(user.getFirstName());
        last.setText(user.getLastName());

        String status = user.getSystem();
        Log.i("System", "------------------> " + status);

        if (m.getText().toString().equals(status) || m.getText().toString().toLowerCase().equals(status)){
            Log.i("Status", "----------------> Metric");
            m.setChecked(true);
        }
        if (i.getText().toString().equals(status) || i.getText().toString().toLowerCase().equals(status)){
            Log.i("Status", "----------------> Imperial");
            i.setChecked(true);
        }

        m.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                user.setSystem(m.getText().toString());

                mDatabase = FirebaseDatabase.getInstance().getReference("users");

                mDatabase.child(user.getUsername()).child("system").setValue(m.getText().toString());

                finish();
                startActivity(getIntent());
            }
        });

        i.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                user.setSystem(i.getText().toString());

                mDatabase = FirebaseDatabase.getInstance().getReference("users");

                mDatabase.child(user.getUsername()).child("system").setValue(i.getText().toString());

                finish();
                startActivity(getIntent());
            }
        });
    }
}