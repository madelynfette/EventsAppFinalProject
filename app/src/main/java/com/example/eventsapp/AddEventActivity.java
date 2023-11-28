package com.example.eventsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    FirebaseDatabase db;
    DatabaseReference ref;
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
            ContentValues mNewValues = new ContentValues();
            String userEmail = auth.getCurrentUser().getEmail();

            mNewValues.put(EventContentProvider.FIRSTCOLUMN, eventName);
            mNewValues.put(EventContentProvider.SECONDCOLUMN, eventDate);
            mNewValues.put(EventContentProvider.THIRDCOLUMN, userEmail);
            mNewValues.put(EventContentProvider.FOURTHCOLUMN, eventLocation);
            mNewValues.put(EventContentProvider.FIFTHCOLUMN, eventDescription);


            getContentResolver().insert(EventContentProvider.CONTENT_URI, mNewValues);

            clear();
        }
    };

    public void clear(){
        eName.setText("");
        eDate.setText("");
        eLocation.setText("");
        eDescription.setText("");
    }
}