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
    Button addEventButton;
    ListView allEventsLV;
    LinkedList alleventslist;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityhome);
        TextView dateTextView = findViewById(R.id.textViewDate);


        //set date
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy", Locale.getDefault());
        String formattedDate = sdf.format(new Date());

        dateTextView.setText(formattedDate);
        addEventButton = findViewById(R.id.addEventButton);
        addEventButton.setOnClickListener(addEventListener);
        allEventsLV = findViewById(R.id.alleventsLV);
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


// Set an onItemClickListener for the listview.
        allEventsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Event event = (Event) parent.getItemAtPosition(position);
                String evName = event.getName();
                String evDate = event.getDate();
                String evLocation = event.getLocation();
                String evDescription = event.getDescription();


                // Create an Intent to open the EventActivity.
                Intent intent = new Intent(HomeActivity.this, EventActivity.class);
                // Add the event object to the Intent.
                intent.putExtra("eventName", evName);
                intent.putExtra("eventDate", evDate);
                intent.putExtra("eventLocation", evLocation);
                intent.putExtra("eventDescription", evDescription);

                // Start the Intent.
                startActivity(intent);
            }
        });

    }



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

    }