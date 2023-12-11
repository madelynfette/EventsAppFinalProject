package com.example.eventsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
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
    String eventName;
    String  eventDate;
    String eventLocation;
    String eventDescription;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        homeButton = findViewById(R.id.homebutton2);
        addEventButton = findViewById(R.id.addEventButton2);
        homeButton.setOnClickListener(homeListener);
        addEventButton.setOnClickListener(addEventListener);
        auth = FirebaseAuth.getInstance();
        eName = findViewById(R.id.eNameEditText);
        eDate = findViewById(R.id.editTextEventDate);
        eLocation = findViewById(R.id.editTextEventLocation);
        eDescription = findViewById(R.id.editTextEventDescription);


    }

    //returns user to the homepage
    View.OnClickListener homeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //set layout for home activity
            setContentView(R.layout.activityhome);
            //start home activity
            Intent intent = new Intent(AddEventActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    };


    View.OnClickListener addEventListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //get inputs
            eventName = eName.getText().toString();
            //check if null
            if(eventName == null){
                Toast.makeText(getApplicationContext(), "Please enter an event name", Toast.LENGTH_SHORT).show();
            }
            eventDate = eDate.getText().toString();
            //check if null
            if(eventDate == null){
                Toast.makeText(getApplicationContext(), "Please enter an event date", Toast.LENGTH_SHORT).show();
            }
            eventDescription = eDescription.getText().toString();
            //check if null
            if(eventDescription == null){
                Toast.makeText(getApplicationContext(), "Please enter an event description", Toast.LENGTH_SHORT).show();
            }
            eventLocation = eLocation.getText().toString();
            //check if null
            if(eventLocation == null){
                Toast.makeText(getApplicationContext(), "Please enter an event location", Toast.LENGTH_SHORT).show();
            }
            //Store values in db
            ContentValues mNewValues = new ContentValues();

            mNewValues.put(EventContentProvider.FIRSTCOLUMN, eventName);
            mNewValues.put(EventContentProvider.SECONDCOLUMN, eventDate);
            mNewValues.put(EventContentProvider.THIRDCOLUMN, eventLocation);
            mNewValues.put(EventContentProvider.FOURTHCOLUMN, eventDescription);

            getContentResolver().insert(EventContentProvider.CONTENT_URI, mNewValues);

            clear();
        }
    };

    //clears all text inputs
    public void clear(){
        eName.setText("");
        eDate.setText("");
        eLocation.setText("");
        eDescription.setText("");
    }
}