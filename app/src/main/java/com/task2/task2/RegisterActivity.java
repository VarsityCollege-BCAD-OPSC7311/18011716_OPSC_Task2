package com.task2.task2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Button register = (Button) findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                EditText first = (EditText) findViewById(R.id.firstname);
                EditText last = (EditText) findViewById(R.id.lastname);
                EditText username = (EditText) findViewById(R.id.username);
                EditText password = (EditText) findViewById(R.id.password);

                User user = new User();
                user.setFirstName(first.getText().toString());
                user.setLastName(last.getText().toString());
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());
                user.setCalorieintake("0");
                user.setDailyweight(new ArrayList<Double>());
                user.setHeight("0");
                user.setTargetweight("0");
                user.setWeight("0");
                user.setSystem("metric");

                Toast.makeText(RegisterActivity.this, "Register!", Toast.LENGTH_SHORT).show();

                mDatabase.child("users").child(username.getText().toString()).setValue(user);
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}