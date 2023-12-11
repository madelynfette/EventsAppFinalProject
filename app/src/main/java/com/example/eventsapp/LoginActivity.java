package com.example.eventsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    Button signupbutton;
    Button loginbutton;
    EditText emailInput;
    EditText passwordInput;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signupbutton = findViewById(R.id.signup_button);
        signupbutton.setOnClickListener(signUpListener);
        passwordInput = findViewById(R.id.editTextPassword);
        emailInput = findViewById(R.id.editTextUsername);
        auth = FirebaseAuth.getInstance();
        loginbutton = findViewById(R.id.buttonLogin);
        loginbutton.setOnClickListener(loginListener);

    }

    //signs the user up
    View.OnClickListener signUpListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //takes the email from the text input
            String email = emailInput.getText().toString().trim();
            //takes the password from the text input
            String password = passwordInput.getText().toString().trim();
            Boolean valid = true;
            //checks if the email is null
            if (email == null) {
                Toast.makeText(getApplicationContext(), "Please enter an email", Toast.LENGTH_SHORT).show();
                valid = false;
            }
            //checks if the password is null
            if (password == null) {
                Toast.makeText(getApplicationContext(), "Please enter a password", Toast.LENGTH_SHORT).show();
                valid = false;
            }
            //if valid, it creates a new user
            if (valid) {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //alerts the user they have successfully signed in
                        Toast.makeText(getApplicationContext(), "Signed in", Toast.LENGTH_SHORT).show();
                        //sets the home activity
                        setContentView(R.layout.activityhome);
                        //starts the home activity
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);


                    }
                });
            }
        }
    };

    //logs the user in and bring them to the home page. If the login input is wrong, it notifies the user
    View.OnClickListener loginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //gets the email input
            String email = emailInput.getText().toString().trim();
            //gets the password input
            String password = passwordInput.getText().toString().trim();
            Boolean valid = true;
            //checks that that email is not null
            if (email == null) {
                Toast.makeText(getApplicationContext(), "Please enter an email", Toast.LENGTH_SHORT).show();
                valid = false;
            }
            //checks if the password is null
            if (password == null) {
                Toast.makeText(getApplicationContext(), "Please enter a password", Toast.LENGTH_SHORT).show();
                valid = false;
            }
            //if valid, signs the listener into the app
            if (valid) {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    //alerts the user that they have successfully signed in
                    Toast.makeText(getApplicationContext(), "Signed in", Toast.LENGTH_SHORT).show();
                    //sets the home layout
                    setContentView(R.layout.activityhome);
                    //starts the home activity
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);



                }
            });}
        }
    };

}