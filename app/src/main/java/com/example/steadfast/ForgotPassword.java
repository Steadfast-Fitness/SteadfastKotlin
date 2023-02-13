package com.example.steadfast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class ForgotPassword extends AppCompatActivity {

    private Button resetPass;
    public TextInputEditText textEmail;
    public static String passEmail;
    private ProgressBar progressBar;
    private TextView signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        signin = findViewById(R.id.signInText);
        textEmail = findViewById(R.id.email);
        resetPass = findViewById(R.id.resetbtn);
        progressBar = findViewById(R.id.progress);


        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email;
                email = String.valueOf(textEmail.getText());
                passEmail = email;

                Intent sendEmail = new Intent(ForgotPassword.this, NewPassword.class);
                sendEmail.putExtra("key", email);
                startActivity(sendEmail);

                if(email.equals("")){
                    Toast.makeText(getApplicationContext(), "Enter your email address.", Toast.LENGTH_SHORT).show();
                }else {
                    progressBar.setVisibility(View.VISIBLE);
                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[1];
                            field[0] = "email";
                            //Creating array for data
                            String[] data = new String[1];
                            data[0] = email;
                            PutData putData = new PutData("https://steadfastfitness.online/forgetpass.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    //End ProgressBar (Set visibility to GONE)
                                    Log.i("PutData", result);
                                    if (result.equals("Reset Code Sent to Email")) {
                                        Intent intent = new Intent(getApplicationContext(), ResetCode.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                }
            }
        });




        // Takes the user back to the SignIn page
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginMenu.class);
                startActivity(intent);
                finish();
            }
        });
    }
}