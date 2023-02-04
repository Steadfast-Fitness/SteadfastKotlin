package com.example.steadfast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;


public class LoginRegMenu extends AppCompatActivity {
    private ListView mSupplementListView;
    private SupplementAdapter mSupplementAdapter;
    Button buttonRegister;
    Button buttonReg;

    TextView Username;
    TextView Password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        setContentView(R.layout.login_page);

        //buttonRegister = findViewById(R.id.registerbtn);
        buttonReg = findViewById(R.id.registerbutton);

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.register_page);
                buttonRegister = findViewById(R.id.signinbtn);

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