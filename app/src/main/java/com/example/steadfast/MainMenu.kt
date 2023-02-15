package com.example.steadfast

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var mSupplementsButton = findViewById<Button>(R.id.Supplements_btn)
        var mProgressionButton = findViewById<Button>(R.id.Progression_btn)
        var mGroupButton = findViewById<Button>(R.id.Online_btn)
        mSupplementsButton.setOnClickListener {
            val intent = Intent(applicationContext, SupplementView::class.java)
            startActivity(intent)
            finish()
        }
        mProgressionButton.setOnClickListener {
            val progression = Intent(this@MainMenu, Progression::class.java)
            startActivity(progression)
            finish()
        }
        mGroupButton.setOnClickListener {
            Toast.makeText(applicationContext, "This feature is coming soon!", Toast.LENGTH_SHORT).show()
        }
    }
}