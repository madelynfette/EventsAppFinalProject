package com.example.eventsapp;

import static androidx.core.content.ContextCompat.startActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {
    Button profileButton;
    Button addEventButton;
    ListView allEventsLV;
    LinkedList alleventslist;
    ListView forYouLV;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityhome);
        TextView dateTextView = findViewById(R.id.textViewDate);
        //todo make listview of for you events


        //set date
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy", Locale.getDefault());
        String formattedDate = sdf.format(new Date());

        dateTextView.setText(formattedDate);
        profileButton = findViewById(R.id.profile_button);
//        profileButton.setOnClickListener(profileListener);
        addEventButton = findViewById(R.id.addEventButton);
  //      addEventButton.setOnClickListener(addEventListener);
        allEventsLV = findViewById(R.id.alleventsLV);
        forYouLV = findViewById(R.id.foryouLV);
        cursor = getContentResolver().query(
                EventContentProvider.CONTENT_URI,
                null,
                null,
                null,
                null
        );

        //add events to allEventsListView
        alleventslist = new LinkedList<>();
        ArrayAdapter<Event> adapter = null;
        if (cursor != null && !cursor.isClosed()) {
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                while (!cursor.isAfterLast()) {
                    String eventName = cursor.getString(1);
                    String eventDate = cursor.getString(2);
                    String eventLocation = cursor.getString(3);
                    String eventDescription = cursor.getString(4);
                    Event event = new Event(eventName, eventDate, eventLocation, eventDescription);
                    alleventslist.add(event);

                    cursor.moveToNext();
                }
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alleventslist);
            allEventsLV.setAdapter(adapter);
        }
        adapter.notifyDataSetChanged();

        //add events to forYouListView
        LinkedList<Event> foryoulist = new LinkedList<>();
        ArrayAdapter<Event> adapter2 = null;
        if (cursor != null && !cursor.isClosed()) {
            cursor.moveToPosition(8);
            if (cursor.getCount() > 0) {
                while (cursor.getPosition() < 3) {
                    String eventName = cursor.getString(1);
                    String eventDate = cursor.getString(2);
                    String eventLocation = cursor.getString(3);
                    String eventDescription = cursor.getString(4);
                    Event event = new Event(eventName, eventDate, eventLocation, eventDescription);
                    foryoulist.add(event);

                    cursor.moveToNext();
                }
            }

            adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, foryoulist);
            forYouLV.setAdapter(adapter2);
        }
        adapter2.notifyDataSetChanged();

/*// Set an onItemClickListener for the listview.
        allEventsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Get the position of the selected event.
                position = allEventsLV.getPositionForView(view);

                // Get the event object from the listview.
                com.google.android.datatransport.Event event = (com.google.android.datatransport.Event) allEventsLV.getItemAtPosition(position);

                // Create an Intent to open the EventActivity.
                Intent intent = new Intent(HomeActivity.this, EventActivity.class);

                // Add the event object to the Intent.
                intent.putExtra("event", String.valueOf(event));

                // Start the Intent.
                startActivity(intent);
            }
        });


    }*/


        //brings the user to the profile activity
        View.OnClickListener profileListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sets layout for profile activity
                setContentView(R.layout.activity_profile);
                //starts profile activity
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        };
        View.OnClickListener addEventListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sets layout for addEventActivity
                setContentView(R.layout.activity_add_event);
                //Starts addEventActivity
                Intent intent = new Intent(HomeActivity.this, AddEventActivity.class);
                startActivity(intent);
            }
        };

    }}