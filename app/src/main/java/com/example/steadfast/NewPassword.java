package com.example.steadfast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
                String password, email, reEnter;

                reEnter = String.valueOf(reEnterPass.getText());
                password = String.valueOf(enterPass.getText());
                email = ForgotPassword.passEmail;

                if(!password.equals("")) {
                    if (!reEnter.equals(password)) {
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
                                String[] field = new String[2];
                                field[0] = "email";
                                field[1] = "password";
                                //Creating array for data
                                String[] data = new String[2];
                                data[0] = email;
                                data[1] = password;
                                PutData putData = new PutData("https://steadfastfitness.online/changepass.php", "POST", field, data);
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        progressBar.setVisibility(View.GONE);
                                        String result = putData.getResult();
                                        //End ProgressBar (Set visibility to GONE)
                                        Log.i("PutData", result);
                                        if (result.equals("Password Reset. Please Login.")) {
                                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(getApplicationContext(), LoginMenu.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
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

        ImageView imageViewShowHidePwd = findViewById(R.id.hide_pwd);
        imageViewShowHidePwd.setImageResource(R.drawable.ic_hide_ped);
        ImageView imageViewShowHideRePwd = findViewById(R.id.hide_repwd);
        imageViewShowHideRePwd.setImageResource(R.drawable.ic_hide_ped);

        imageViewShowHidePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(enterPass.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    // If is visible then hide it
                    enterPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    //Change Icon
                    imageViewShowHideRePwd.setImageResource(R.drawable.ic_hide_ped);
                } else {
                    enterPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imageViewShowHideRePwd.setImageResource(R.drawable.ic_show_pwd);
                }
            }
        });

        imageViewShowHideRePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(reEnterPass.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    // If is visible then hide it
                    reEnterPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    //Change Icon
                    imageViewShowHidePwd.setImageResource(R.drawable.ic_hide_ped);
                } else {
                    reEnterPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imageViewShowHidePwd.setImageResource(R.drawable.ic_show_pwd);
                }
            }
        });



    }
}