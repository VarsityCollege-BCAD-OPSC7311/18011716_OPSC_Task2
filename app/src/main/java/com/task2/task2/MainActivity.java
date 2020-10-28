package com.task2.task2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    public static LoggedIn isLoggedIn;
    String online;
    User active;
    String username;
    String password;
    String first = "";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (isLoggedIn.isLoggedI()) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu, menu);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item1){

            if (username != null || password != null){
                // Get a reference to our posts
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference users = database.getReference("users");

                // Attach a listener to read the data at posts reference
                Query getUser = users.orderByChild("username").equalTo(username);
                getUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String passwordEntered = snapshot.child(username).child("password").getValue().toString();
                        String u = username;

                        if (passwordEntered.equals(password)){
                            Log.i("Data", " --------------> User Found - MainActivity");
                            String first = snapshot.child(u).child("firstName").getValue(String.class);
                            String last = snapshot.child(u).child("lastName").getValue(String.class);
                            String weight = snapshot.child(u).child("weight").getValue(String.class);
                            String height = snapshot.child(u).child("height").getValue(String.class);
                            String system = snapshot.child(u).child("system").getValue(String.class);
                            active.setUsername(u);
                            active.setPassword(passwordEntered);
                            active.setFirstName(first);
                            active.setLastName(last);
                            active.setWeight(weight);
                            active.setHeight(height);
                            active.setSystem(system);

                            Intent intent = new Intent(getBaseContext(), RecordMealActivity.class);
                            intent.putExtra("User", active);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }
        if (item.getItemId() == R.id.item2){

            if (username != null || password != null){
                // Get a reference to our posts
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference users = database.getReference("users");

                // Attach a listener to read the data at posts reference
                Query getUser = users.orderByChild("username").equalTo(username);
                getUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String passwordEntered = snapshot.child(username).child("password").getValue().toString();
                        String u = username;

                        if (passwordEntered.equals(password)){
                            Log.i("Data", " --------------> User Found - MainActivity");
                            String first = snapshot.child(u).child("firstName").getValue(String.class);
                            String last = snapshot.child(u).child("lastName").getValue(String.class);
                            String weight = snapshot.child(u).child("weight").getValue(String.class);
                            String height = snapshot.child(u).child("height").getValue(String.class);
                            String system = snapshot.child(u).child("system").getValue(String.class);
                            active.setUsername(u);
                            active.setPassword(passwordEntered);
                            active.setFirstName(first);
                            active.setLastName(last);
                            active.setWeight(weight);
                            active.setHeight(height);
                            active.setSystem(system);

                            Intent intent = new Intent(getBaseContext(), PhysiologicalInfoActivity.class);
                            intent.putExtra("User", active);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }

        if (item.getItemId() == R.id.item3){

            if (username != null || password != null){
                // Get a reference to our posts
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference users = database.getReference("users");

                // Attach a listener to read the data at posts reference
                Query getUser = users.orderByChild("username").equalTo(username);
                getUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String passwordEntered = snapshot.child(username).child("password").getValue().toString();
                        String u = username;

                        if (passwordEntered.equals(password)){
                            Log.i("Data", " --------------> User Found - MainActivity");
                            String first = snapshot.child(u).child("firstName").getValue(String.class);
                            String last = snapshot.child(u).child("lastName").getValue(String.class);
                            String system = snapshot.child(u).child("system").getValue(String.class);
                            active.setUsername(u);
                            active.setPassword(passwordEntered);
                            active.setFirstName(first);
                            active.setLastName(last);
                            active.setSystem(system);

                            Intent intent = new Intent(getBaseContext(), SettingActivity.class);
                            intent.putExtra("User", active);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView welcome1 = findViewById(R.id.welcome1);
        TextView welcome2 = findViewById(R.id.welcome2);

        active = new User();

        online = getIntent().getStringExtra("status");
        username = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");

        isLoggedIn = new LoggedIn();
        isLoggedIn.setLoggedI(Boolean.parseBoolean(online));

        final Button login = findViewById(R.id.login);
        final Button register = findViewById(R.id.register);
        final Button logout = findViewById(R.id.logout);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        if (username != null && password != null) {

            // Get a reference to our posts
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference users = database.getReference("users");

            // Attach a listener to read the data at posts reference
            Query getUser = users.orderByChild("username").equalTo(username);

            register.setVisibility(View.INVISIBLE);
            login.setVisibility(View.INVISIBLE);
            logout.setVisibility(View.VISIBLE);

            getUser.addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            active = dataSnapshot.getValue(User.class);
                            //active.setFirstName(dataSnapshot.child(username).child("firstName").getValue(String.class));
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

            welcome2.setTextSize(66);
            welcome2.setText("Welcome");
            welcome1.setVisibility(View.INVISIBLE);

        } else {
            register.setVisibility(View.VISIBLE);
            login.setVisibility(View.VISIBLE);
            logout.setVisibility(View.INVISIBLE);
        }

    }
}