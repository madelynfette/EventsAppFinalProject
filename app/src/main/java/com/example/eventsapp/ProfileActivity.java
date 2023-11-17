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
    ListView myEventsLV;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //todo figure out why this isnt working
        //returnHomeButton = findViewById(returntohomebutton);
        name_TV = findViewById(R.id.nameTV);
        email_TV = findViewById(R.id.emailTV);
        name_TV.setText(user.getName());
        email_TV.setText(user.getEmail());
    }
    //todo load the list of my events in the listview, make options to delete them from the listview


    //return the user to the home screen
    View.OnClickListener homeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setContentView(R.layout.activityhome);
            Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    };
}