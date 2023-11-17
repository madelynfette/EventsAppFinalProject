package com.example.eventsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    EditText nameInput;
    EditText emailInput;
    EditText passwordInput;
    Button loginButton;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        auth = FirebaseAuth.getInstance();
        nameInput = findViewById(R.id.name_input);
        emailInput = findViewById(R.id.editTextUsername);
        passwordInput = findViewById(R.id.editTextPassword);
        signUpButton = findViewById(R.id.signup_button);
        loginButton = findViewById(R.id.login_button);
        signUpButton.setOnClickListener(signUpListener);
    }

    //signs the user up, logs them in, and takes user to home screen. If there is an error logging the user in it notifies the user
    View.OnClickListener signUpListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name = nameInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString();

            User user = new User(email, password);
            user.setName(name);
            auth.createUserWithEmailAndPassword(email, password);
            //todo verify that their account is created and they are logged in

            //if(user.loggedIn == true) {
                setContentView(R.layout.activityhome);
                Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
            //}else {
            //    Toast.makeText(getApplicationContext(), "We are having trouble loggin you in.",Toast.LENGTH_SHORT).show();
           // }
        }
    };

    //return to login screen
    View.OnClickListener loginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setContentView(R.layout.activity_login);
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);

        }
    };


}