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
//push
public class ResetCode extends AppCompatActivity {

    private TextView signin;
    private ProgressBar progressBar;
    private Button submitCode;
    private TextInputEditText enterCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_code);
        signin = findViewById(R.id.signInText);
        progressBar = findViewById(R.id.progress);
        submitCode = findViewById(R.id.submitcode);
        enterCode = findViewById(R.id.entercode);


        submitCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code;
                code = String.valueOf(enterCode.getText());

                if(code.equals("")){
                    Toast.makeText(getApplicationContext(), "Enter the reset code.", Toast.LENGTH_SHORT).show();
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
                            field[0] = "code";
                            //Creating array for data
                            String[] data = new String[1];
                            data[0] = code;
                            PutData putData = new PutData("https://steadfastfitness.online/checkresetcode.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    //End ProgressBar (Set visibility to GONE)
                                    Log.i("PutData", result);
                                    if (result.equals("Code Correct")) {
                                        Intent intent = new Intent(getApplicationContext(), NewPassword.class);
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