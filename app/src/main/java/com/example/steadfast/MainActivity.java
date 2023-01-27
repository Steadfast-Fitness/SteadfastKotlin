package com.example.steadfast;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Button;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ListView mSupplementListView;
    private SupplementAdapter mSupplementAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        setContentView(R.layout.activity_main);

        //Initialize the ListView and set the adapter
        mSupplementListView = findViewById(R.id.supplement_list_view);
        mSupplementAdapter = new SupplementAdapter(this, getSupplements());
        mSupplementListView.setAdapter(mSupplementAdapter);

        //Initialize the "Supps" button and set the onClickListener
        Button suppsButton = findViewById(R.id.supps_button);
        suppsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Implement the action to be taken when the button is clicked
            }
        });
    }

    private ArrayList<Supplement> getSupplements() {
        //Initialize the list of supplements and add some data
        ArrayList<Supplement> supplements = new ArrayList<>();
        supplements.add(new Supplement("Vitamin C", "1000mg"));
        supplements.add(new Supplement("Fish Oil", "2000mg"));
        supplements.add(new Supplement("Turmeric", "500mg"));
        return supplements;
    }
}