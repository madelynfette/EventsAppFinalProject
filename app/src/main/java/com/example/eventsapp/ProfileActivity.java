package com.example.eventsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    Button returnHomeButton;
    TextView name_TV;
    TextView email_TV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set layout for profile activity
        setContentView(R.layout.activity_profile);
        returnHomeButton = findViewById(R.id.returntohomebutton);

        email_TV = findViewById(R.id.emailTV);
        //receive email intent
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        email_TV.setText(email);

    }
    //todo load the list of my events in the listview, make options to delete them from the listview


    //return the user to the home screen
    View.OnClickListener homeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //sets layout of home activity
            setContentView(R.layout.activityhome);
            //starts home activity
            Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    };
}