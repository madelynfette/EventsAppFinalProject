package com.example.eventsapp;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class User {
    private FirebaseAuth auth;
    String name;
    String email;
    String password;
    Boolean loggedIn;

    public User(String email, String password){

        email = this.email;
        password = this.password;
    }

    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public void setName(String name){
        name = this.name;
    }

    public void createUser(){
        //creates a user
        auth.createUserWithEmailAndPassword(email, password);
        //todo make this verify that it was created like the one below
    }

}
