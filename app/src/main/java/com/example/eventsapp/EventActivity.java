package com.example.eventsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EventActivity extends AppCompatActivity {
    TextView date;
    TextView eventName;
    TextView eventDate;
    TextView eventLocation;
    TextView eventDescription;
    TextView location;
    TextView description;
    Event event;
    Button addToMyEventsButton;
    Button returnHomeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        date = findViewById(R.id.textViewDatePosted);
        location = findViewById(R.id.textViewLocation);
        description = findViewById(R.id.textViewDescription);
        addToMyEventsButton = findViewById(R.id.buttonAddToMyEvents);
        returnHomeButton = findViewById(R.id.buttonReturnHome);
        eventName = findViewById(R.id.nameTextView);
        eventDate = findViewById(R.id.textViewDatePosted);
        eventLocation = findViewById(R.id.textViewLocation);
        eventDescription = findViewById(R.id.textViewDescription);


        event = (Event) getIntent().getSerializableExtra("event");
        String name = event.getName();
        String date = event.getDate();
        String location = event.getLocation();
        String description = event.getDescription();

        //show name of event
        eventName.setText(name);
        //show date of event
        eventDate.setText(date);
        //show location of event
        eventLocation.setText(location);
        //show description of event
        eventDescription.setText(description);

    }
//pressing button returns home
    View.OnClickListener homeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setContentView(R.layout.activityhome);
            Intent intent = new Intent(EventActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    };


}