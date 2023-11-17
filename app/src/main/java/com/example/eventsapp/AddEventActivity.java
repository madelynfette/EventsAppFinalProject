package com.example.eventsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.LinkedList;

public class AddEventActivity extends AppCompatActivity {
    Button homeButton;
    Button addEventButton;
    EditText eName;
    EditText eLocation;
    EditText eDate;
    EditText eDescription;
    FirebaseDatabase db;
    DatabaseReference ref;
    String eventName;
    String  eventDate;
    String eventLocation;
    String eventDescription;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        homeButton = findViewById(R.id.homebutton2);
        addEventButton = findViewById(R.id.addEventButton2);
        homeButton.setOnClickListener(homeListener);
        addEventButton.setOnClickListener(addEventListener);
        eName = findViewById(eNameET);
        eLocation = findViewById(editTextEventLocation);
        eDescription = findViewById(editTextEventDescription);
        eDate = findViewById(editTextEventDate);
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("event");
        eventName = eName.getText().toString();
        eventDate = eDate.getText().toString();
        eventLocation = eLocation.getText().toString();
        eventDescription = eDescription.getText().toString();




    }

    //returns user to the homepage
    View.OnClickListener homeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setContentView(R.layout.activityhome);
            Intent intent = new Intent(AddEventActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    };
    //adds event to a linkedList
    View.OnClickListener addEventListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            User user;
            Event event = new Event(eventName, eventDate, eventLocation, eventDescription);
            String key = ref.child(eventName).push().getKey();
            ref.child(eventName).child(key).setValue(event);
            eName.setText("");
            eLocation.setText("");
            eDate.setText("");
            eDescription.setText("");
        }
    };
}