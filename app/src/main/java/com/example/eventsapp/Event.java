package com.example.eventsapp;

import static android.content.Intent.getIntent;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseUser;

public class Event {
    String name;
    String date;
    String location;
    String description;

    public Event(String name, String date, String location, String description){
       this.name = name;
       this.date = date;
       this.location = location;
       this.description = description;

    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }
    public String getLocation(){
        return location;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    @NonNull
    @Override
    public String toString(){
        return date + "      " + name;
    }


}
