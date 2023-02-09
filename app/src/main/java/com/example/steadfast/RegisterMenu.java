package com.example.steadfast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class RegisterMenu extends AppCompatActivity {

    TextInputEditText textInputEditTextUsername, textInputEditTextEmail, textInputEditTextPassword, textInputEditTextReEnterPassword;
    ProgressBar progressBar;
    Button buttonRegister;
    Button signInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
        signInBtn = findViewById(R.id.signinbtn);
        buttonRegister = findViewById(R.id.registerbtn);
        textInputEditTextUsername = findViewById(R.id.username);
        textInputEditTextEmail = findViewById(R.id.email);
        textInputEditTextPassword = findViewById(R.id.password);
        textInputEditTextReEnterPassword = findViewById(R.id.reenter_password);
        progressBar = findViewById(R.id.progress);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username, email, password, reEnter;
                username = String.valueOf(textInputEditTextUsername.getText());
                email = String.valueOf(textInputEditTextEmail.getText());
                password = String.valueOf(textInputEditTextPassword.getText());
                reEnter = String.valueOf(textInputEditTextReEnterPassword.getText());

                if(!username.equals("") && !email.equals("") && !password.equals("")) {
                    if (!reEnter.equals(password)) {
                        Toast.makeText(getApplicationContext(), "Passwords Don't Match", Toast.LENGTH_SHORT).show();

                    } else {
                        progressBar.setVisibility(View.VISIBLE);
                        //Start ProgressBar first (Set visibility VISIBLE)
                        Handler handler = new Handler();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //Starting Write and Read data with URL
                                //Creating array for parameters
                                String[] field = new String[3];
                                field[0] = "username";
                                field[1] = "email";
                                field[2] = "password";
                                //Creating array for data
                                String[] data = new String[3];
                                data[0] = username;
                                data[1] = email;
                                data[2] = password;
                                PutData putData = new PutData("https://steadfastfitness.online/signup.php", "POST", field, data);
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        progressBar.setVisibility(View.GONE);
                                        String result = putData.getResult();
                                        //End ProgressBar (Set visibility to GONE)
                                        Log.i("PutData", result);
                                        if (result.equals("Registration Successful")) {
                                            setContentView(R.layout.activity_main);
                                        } else {
                                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                                //End Write and Read data with URL
                            }
                        });
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "All Fields Are Required", Toast.LENGTH_SHORT).show();
                }

            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginMenu.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
