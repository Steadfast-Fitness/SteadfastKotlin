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


public class NewPassword extends AppCompatActivity {

    private TextInputEditText enterPass;
    private TextInputEditText reEnterPass;
    private Button changePass;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        enterPass = findViewById(R.id.enterpassword);
        reEnterPass = findViewById(R.id.reenterpassword);
        changePass = findViewById(R.id.submitpassword);
        progressBar = findViewById(R.id.progress);

        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password;
                password = String.valueOf(enterPass.getText());

                if(!password.equals("")) {
                    if (!reEnterPass.equals(password)) {
                        Toast.makeText(getApplicationContext(), "Passwords Do Not Match", Toast.LENGTH_SHORT).show();

                    } else {
                        progressBar.setVisibility(View.VISIBLE);
                        //Start ProgressBar first (Set visibility VISIBLE)
                        Handler handler = new Handler();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //Starting Write and Read data with URL
                                //Creating array for parameters
                                String[] field = new String[1];
                                field[0] = "password";
                                //Creating array for data
                                String[] data = new String[1];
                                data[0] = password;
                                PutData putData = new PutData("https://steadfastfitness.online/signup.php", "POST", field, data);
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        progressBar.setVisibility(View.GONE);
                                        String result = putData.getResult();
                                        //End ProgressBar (Set visibility to GONE)
                                        Log.i("PutData", result);
                                        if (result.equals("Registration Successful. Check your inbox to verify your email address.")) {
                                            Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                                            startActivity(intent);
                                            finish();
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
    }
}