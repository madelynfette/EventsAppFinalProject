package com.example.eventsapp;

import com.google.firebase.auth.FirebaseUser;

public class Event {
    String name;
    String date;
    String owner;
    String location;
    String description;
    FirebaseUser user;

    public Event(String name, String date, String location, String description){
        name = this.name;
        date = this.date;
        owner = user.getEmail();
        location = this.location;
        description = this.description;

    }
    public String getName(){
        return name;
    }
    public String getDate(){
        return date;
    }
    public String getOwner(){
        return owner;
    }
    public String getLocation(){
        return location;
    }
    public String getDescription(){
        return description;
    }

    //todo make a toString method to show what it will be returning on the listView


}
