package com.fluffy.samrith.university_managment_system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fluffy.samrith.university_managment_system.college.CollegeActivity;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.login);

        login.setOnClickListener(view->{
            Intent i = new Intent(this,CollegeActivity.class);
            startActivity(i);
        });

    }

    public void signup(View v){
        Toast.makeText(this, "You need to process some paper work to get an account", Toast.LENGTH_SHORT).show();
    }
}
