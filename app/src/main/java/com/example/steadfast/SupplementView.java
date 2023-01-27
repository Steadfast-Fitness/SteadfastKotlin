package com.example.steadfast;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.CompoundButton;

import java.util.ArrayList;

public class SupplementView extends LinearLayout {
    private TextView mNameTextView;
    private TextView mDosageTextView;
    private CheckBox mTakenCheckBox;
    public SupplementView(Context context) {
        super(context);
        inflate(context, R.layout.view_supplement, this);
        mNameTextView = findViewById(R.id.supplement_name);
        mDosageTextView = findViewById(R.id.supplement_dosage);
        mTakenCheckBox = findViewById(R.id.supplement_taken);
    }

    private ArrayList<Supplement> getSupplements() {
        //Initialize the list of supplements and add some data
        ArrayList<Supplement> supplements = new ArrayList<>();
        supplements.add(new Supplement("Vitamin C", "1000mg"));
        supplements.add(new Supplement("Fish Oil", "2000mg"));
        supplements.add(new Supplement("Turmeric", "500mg"));
        return supplements;
    }
    public void bind(Supplement supplement) {
        mNameTextView.setText(supplement.getSupplementName());
        mDosageTextView.setText(supplement.getSupplementDosage());
        if (supplement != null) {
            mTakenCheckBox.setVisibility(View.VISIBLE);
        } else {
            mTakenCheckBox.setVisibility(View.INVISIBLE);
        }
        mTakenCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                supplement.setTaken(isChecked);
            }
        });
    }
}


