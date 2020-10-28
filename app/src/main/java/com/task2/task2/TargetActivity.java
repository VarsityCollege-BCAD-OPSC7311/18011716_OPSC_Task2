package com.task2.task2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TargetActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        EditText h = findViewById(R.id.height2);
        EditText w = findViewById(R.id.weight2);
        TextView first = findViewById(R.id.firstname3);
        TextView last = findViewById(R.id.lastname3);
        Button save = findViewById(R.id.save2);

        user = (User) getIntent().getSerializableExtra("User");
        Log.i("Data", user.firstName + " in Target");
        first.setText(user.getFirstName());
        last.setText(user.getLastName());

        h.setText(user.getCalorieintake());
        //Log.i("Calorie", " ___________________________> " + user.getCalorieintake());

        if (user.getSystem().toLowerCase().equals("metric")) {
            w.setText(user.getTargetweight());
            TextView wu = findViewById(R.id.wunit2);
            wu.setText("kg");
        }
        if (user.getSystem().toLowerCase().equals("imperial")){
            w.setText((int) Math.round(Integer.parseInt(user.getTargetweight()) * 2.2046));
            TextView wu = findViewById(R.id.wunit2);
            wu.setText("pounds");
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText weig = (EditText) findViewById(R.id.weight2);
                EditText heig = (EditText) findViewById(R.id.height2);

                user.setTargetweight(weig.getText().toString());
                user.setCalorieintake(heig.getText().toString());

                mDatabase = FirebaseDatabase.getInstance().getReference("users");

                mDatabase.child(user.getUsername()).child("targetweight").setValue(weig.getText().toString());
                mDatabase.child(user.getUsername()).child("calorieintake").setValue(heig.getText().toString());

                finish();
                startActivity(getIntent());
            }
        });
    }
}