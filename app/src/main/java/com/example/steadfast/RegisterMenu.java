package com.example.steadfast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class RegisterMenu extends AppCompatActivity {

    TextInputEditText textInputEditTextUsername, textInputEditTextEmail, textInputEditTextPassword, textInputEditTextReEnterPassword;
    ProgressBar progressBar;
    Button buttonRegister;
    TextView signInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
        signInBtn = findViewById(R.id.signInText);
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

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginMenu.class);
                startActivity(intent);
                finish();
            }
        });

        //Show and hide passwords
        //Show and hide passwords
        ImageView imageViewShowHidePwd = findViewById(R.id.hide_pwd);
        imageViewShowHidePwd.setImageResource(R.drawable.ic_hide_ped);
        ImageView imageViewShowHideRePwd = findViewById(R.id.hide_repwd);
        imageViewShowHideRePwd.setImageResource(R.drawable.ic_hide_ped);

        imageViewShowHidePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textInputEditTextPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    // If is visible then hide it
                    textInputEditTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    //Change Icon
                    imageViewShowHidePwd.setImageResource(R.drawable.ic_hide_ped);
                } else {
                    textInputEditTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imageViewShowHidePwd.setImageResource(R.drawable.ic_show_pwd);
                }
            }
        });

        imageViewShowHideRePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textInputEditTextReEnterPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    // If is visible then hide it
                    textInputEditTextReEnterPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    //Change Icon
                    imageViewShowHideRePwd.setImageResource(R.drawable.ic_hide_ped);
                } else {
                    textInputEditTextReEnterPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imageViewShowHideRePwd.setImageResource(R.drawable.ic_show_pwd);
                }
            }
        });

    }
}
