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


public class AccountAuthorization extends AppCompatActivity {

    private Button authorize;
    private TextInputEditText entercode;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_authorization);
        authorize = findViewById(R.id.authorize);
        entercode = findViewById(R.id.entercode);
        progressBar = findViewById(R.id.progress);

        authorize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code, email;
                code = String.valueOf(entercode.getText());
                email = RegisterMenu.passEmail;

                if(code.equals("")){
                    Toast.makeText(getApplicationContext(), "Enter the authorization code.", Toast.LENGTH_SHORT).show();
                }else {
                    progressBar.setVisibility(View.VISIBLE);
                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];
                            field[0] = "email";
                            field[1] = "code";
                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = email;
                            data[1] = code;
                            PutData putData = new PutData("https://steadfastfitness.online/authentication/checkauthcode.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    //End ProgressBar (Set visibility to GONE)
                                    Log.i("PutData", result);
                                    if (result.equals("Authentication Code Correct")) {
                                        Intent intent = new Intent(getApplicationContext(), MainMenu.class);
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




    }
}