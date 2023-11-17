package com.example.eventsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EventActivity extends AppCompatActivity {
    TextView date;
    TextView eventName;
    TextView owner;
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
        owner = findViewById(R.id.textViewEventOwner);
        location = findViewById(R.id.textViewLocation);
        description = findViewById(R.id.textViewDescription);
        addToMyEventsButton = findViewById(R.id.buttonAddToMyEvents);
        returnHomeButton = findViewById(R.id.buttonReturnHome);
        eventName = findViewById(R.id.nameTextView);

        //show name of event
        eventName.setText(event.getName());
        //show date of event
        date.setText("Date: " + event.getDate());
        //show owner of event
        owner.setText("Posted By: "+ event.getOwner());
        //show location of event
        location.setText("Location: "+ event.getLocation());
        //show description of event
        location.setText("Description: "+ event.getDescription());


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
    //pressing button adds event to myEvents
    //todo: make this add to a myEvents list which is registered to the user
    View.OnClickListener myEventsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), eventName + " was added to MyEvents", Toast.LENGTH_SHORT ).show();
        }
    };


}