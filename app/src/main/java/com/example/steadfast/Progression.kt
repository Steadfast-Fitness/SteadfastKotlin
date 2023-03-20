package com.example.steadfast

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Progression : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progression)
    }
    override fun onBackPressed() {
        val intent = Intent(this, MainMenu::class.java)
        startActivity(intent)
        finish()
    }
}