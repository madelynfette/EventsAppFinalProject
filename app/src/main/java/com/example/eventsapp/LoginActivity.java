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

    //opens up the sign up activity
    View.OnClickListener signUpListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setContentView(R.layout.activity_sign_up);
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        }
    };
    //logs the user in and bring them to the home page. If the login input is wrong, it notifies the user
    View.OnClickListener loginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();
            User user = new User(email, password);
            auth.signInWithEmailAndPassword(email,password);
            //todo make this verify that the user is signed in
            //if(user.loggedIn == true){
                setContentView(R.layout.activityhome);
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
           // }
            //else{
           //     Toast.makeText(getApplicationContext(), "Your username or password is incorrect", Toast.LENGTH_SHORT).show();
            //}*/
        }
    };

}