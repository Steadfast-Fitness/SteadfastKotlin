package com.example.steadfast

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.vishnusivadas.advanced_httpurlconnection.PutData

//push
class ResetCode : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_code)
        var signin = findViewById<TextView>(R.id.signInText)
        var progressBar = findViewById<ProgressBar>(R.id.progress)
        var submitCode = findViewById<Button>(R.id.submitcode)
        var enterCode = findViewById<TextInputEditText>(R.id.entercode)
        submitCode.setOnClickListener(View.OnClickListener {
            val code: String = enterCode.text.toString()
            if (code == "") {
                Toast.makeText(applicationContext, "Enter the reset code.", Toast.LENGTH_SHORT).show()
            } else {
                progressBar.visibility = View.VISIBLE
                //Start ProgressBar first (Set visibility VISIBLE)
                val handler = Handler()
                handler.post {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    val field = arrayOfNulls<String>(1)
                    field[0] = "code"
                    //Creating array for data
                    val data = arrayOfNulls<String>(1)
                    data[0] = code
                    val putData = PutData("https://steadfastfitness.online/checkresetcode.php", "POST", field, data)
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            progressBar.visibility = View.GONE
                            val result = putData.result
                            //End ProgressBar (Set visibility to GONE)
                            Log.i("PutData", result)
                            if (result == "Code Correct") {
                                val intent = Intent(applicationContext, NewPassword::class.java)
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

        // Takes the user back to the SignIn page
        signin.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, LoginMenu::class.java)
            startActivity(intent)
            finish()
        })
    }
}