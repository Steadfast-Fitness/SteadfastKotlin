package com.example.steadfast;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity{
    Button mSupplementsButton;
    Button mProgressionButton;
    Button mGroupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSupplementsButton = findViewById(R.id.Supplements_btn);
        mProgressionButton = findViewById(R.id.Progression_btn);
        mGroupButton = findViewById(R.id.Online_btn);

        mSupplementsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SupplementView.class);
                startActivity(intent);
                finish();
            }
        });

        mProgressionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent progression = new Intent(MainMenu.this, Progression.class);
                startActivity(progression);
                finish();
            }
        });

        mGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "This feature is coming soon!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
