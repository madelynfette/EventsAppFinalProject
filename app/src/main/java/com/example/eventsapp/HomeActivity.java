package com.example.eventsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {
    Button profileButton;
    Button addEventButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityhome);
        TextView dateTextView = findViewById(R.id.textViewDate);
        //todo make listview of all events
        //todo make listview of for you events


        //set date
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy", Locale.getDefault());
        String formattedDate = sdf.format(new Date());

        dateTextView.setText(formattedDate);
        profileButton = findViewById(R.id.profile_button);
        profileButton.setOnClickListener(profileListener);
        addEventButton = findViewById(R.id.addEventButton);
        addEventButton.setOnClickListener(addEventListener);

    }

    //brings the user to the profile activity
    View.OnClickListener profileListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setContentView(R.layout.activity_profile);
            Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
            startActivity(intent);
        }
    };
    View.OnClickListener addEventListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setContentView(R.layout.activity_add_event);
            Intent intent = new Intent(HomeActivity.this, AddEventActivity.class);
            startActivity(intent);
        }
    };
}