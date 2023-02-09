package com.example.steadfast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;


public class LoginRegMenu extends AppCompatActivity {
    private ListView mSupplementListView;
    private SupplementAdapter mSupplementAdapter;
    Button buttonRegister;
    Button buttonReg;
    Button buttonRegisters;
    Button loginbtn;
    EditText email;
    EditText username;
    EditText password;
    TextView welcomename;
    TextInputEditText textInputEditTextUsername, textInputEditTextEmail, textInputEditTextPassword, textInputEditTextReEnterPassword;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        setContentView(R.layout.login_page);

        //buttonRegister = findViewById(R.id.registerbtn);
        buttonReg = findViewById(R.id.registerbutton);
        loginbtn = findViewById(R.id.loginbtn);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);


        //Login Info
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_main);
                welcomename = findViewById(R.id.welcomename);
                String User = username.getText().toString();
                welcomename.setText("Welcome, "+User);
            }


        });
        //Register Button
        buttonReg.setOnClickListener(new View.OnClickListener() {
                    @Override
            public void onClick(View view) {
                setContentView(R.layout.register_page);
                buttonRegister = findViewById(R.id.signinbtn);
                buttonRegisters = findViewById(R.id.registerbtn);
                textInputEditTextUsername = findViewById(R.id.username);
                textInputEditTextEmail = findViewById(R.id.email);
                textInputEditTextPassword = findViewById(R.id.password);
                textInputEditTextReEnterPassword = findViewById(R.id.reenter_password);
                progressBar = findViewById(R.id.progress);

                buttonRegisters.setOnClickListener(new View.OnClickListener() {
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
                                        PutData putData = new PutData("http://steadfastfitness.online/signup.php", "POST", field, data);
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



                buttonRegister.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        setContentView(R.layout.login_page);

                        //Refreshes that pages to allow the user to click Login and Register Multiple Times
                        Intent intent = new Intent(LoginRegMenu.this, LoginRegMenu.class);
                        startActivity(intent);


                    }
                });
            }


        });






        //TODO: These Commented out Functions need to be moved or figure out how they work

        //Initialize the ListView and set the adapter
      /*  mSupplementListView = findViewById(R.id.supplement_list_view);
        mSupplementAdapter = new SupplementAdapter(this, getSupplements());
        mSupplementListView.setAdapter(mSupplementAdapter);

        //Initialize the "Supps" button and set the onClickListener
        Button suppsButton = findViewById(R.id.supps_button);
        suppsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Implement the action to be taken when the button is clicked
                
            }
        });*/
    }


/* private ArrayList<Supplement> getSupplements() {
        //Initialize the list of supplements and add some data
        ArrayList<Supplement> supplements = new ArrayList<>();
        supplements.add(new Supplement("Vitamin C", " 1000mg"));
        supplements.add(new Supplement("Fish Oil", " 2000mg"));
        supplements.add(new Supplement("Turmeric", " 500mg"));
        return supplements;
    }*/
}