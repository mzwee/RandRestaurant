package com.example.mzwee.randrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

/**
 * Created by mzwee on 2/20/16.
 */
public class UserAuth extends AppCompatActivity{
    Firebase ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_auth);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);
        final Button login = (Button) findViewById(R.id.login);
        final TextView forgot_password = (TextView) findViewById(R.id.forgot_password);
        final Button register = (Button) findViewById(R.id.register);

        // Set up Firebase
        Firebase.setAndroidContext(this);
        ref = new Firebase("https://hackillinois16.firebaseio.com/");

        // Login function
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.authWithPassword(email.getText().toString(), password.getText().toString(), new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) { // login
                        // if our login is true we should go to the main page
                        Toast.makeText(getApplicationContext(), "Log in approved!",
                                Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(UserAuth.this, GridView.class);
                        startActivity(intent);

                        System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        Toast.makeText(getApplicationContext(), "Log in failed:( " + firebaseError.getMessage(),
                                Toast.LENGTH_LONG).show();
                        Log.e("User", "Login failed: " + firebaseError.getMessage());
                    }
                });
            }
        });

        //Registration function
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.createUser(email.getText().toString(), password.getText().toString(), new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> stringObjectMap) {
                        Toast.makeText(getApplicationContext(), "You have been registered!",
                                Toast.LENGTH_LONG).show();
                        System.out.println("Successfully created user account with uid: " + stringObjectMap.get("uid"));
                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        Toast.makeText(getApplicationContext(), "There was an error in your registration. " + firebaseError.getMessage(),
                                Toast.LENGTH_LONG).show();
                        Log.e("User", "Registration failed: " + firebaseError.getMessage());
                    }
                });
            }
        });

        //Forgot password function
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.resetPassword(email.getText().toString(), new Firebase.ResultHandler() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {

                    }
                });
            }
        });
    }
}

