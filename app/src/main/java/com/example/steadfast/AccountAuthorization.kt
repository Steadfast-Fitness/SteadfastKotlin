package com.example.steadfast

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.vishnusivadas.advanced_httpurlconnection.PutData

class AccountAuthorization : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_authorization)
        var authorize = findViewById<Button>(R.id.authorize)
        var entercode = findViewById<TextInputEditText>(R.id.entercode)
        var progressBar = findViewById<ProgressBar>(R.id.progress)
        authorize.setOnClickListener(View.OnClickListener {
            val code: String = entercode.text.toString()
            val email: String = RegisterMenu :: passEmail.toString()
            if (code == "") {
                Toast.makeText(applicationContext, "Enter the authorization code.", Toast.LENGTH_SHORT).show()
            } else {
                progressBar.visibility = View.VISIBLE
                //Start ProgressBar first (Set visibility VISIBLE)
                val handler = Handler()
                handler.post {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    val field = arrayOfNulls<String>(2)
                    field[0] = "email"
                    field[1] = "code"
                    //Creating array for data
                    val data = arrayOfNulls<String>(2)
                    data[0] = email
                    data[1] = code
                    val putData = PutData("https://steadfastfitness.online/authentication/checkauthcode.php", "POST", field, data)
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            progressBar.visibility = View.GONE
                            val result = putData.result
                            //End ProgressBar (Set visibility to GONE)
                            Log.i("PutData", result)
                            if (result == "Authentication Code Correct") {
                                val intent = Intent(applicationContext, MainMenu::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        })
    }
}