package com.example.steadfast

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.vishnusivadas.advanced_httpurlconnection.PutData

//
class RegisterMenu : AppCompatActivity() {

    var passEmail = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_page)
        var signInBtn = findViewById<TextView>(R.id.signInText)
        var buttonRegister = findViewById<Button>(R.id.registerbtn)
        var textInputEditTextUsername = findViewById<TextInputEditText>(R.id.username)
        var textInputEditTextEmail = findViewById<TextInputEditText>(R.id.email)
        var textInputEditTextPassword = findViewById<TextInputEditText>(R.id.password)
        var textInputEditTextReEnterPassword = findViewById<TextInputEditText>(R.id.reenter_password)
        var progressBar = findViewById<ProgressBar>(R.id.progress)
        buttonRegister.setOnClickListener(View.OnClickListener {
            val username: String = textInputEditTextUsername.text.toString()
            val email: String = textInputEditTextEmail.text.toString()
            val password: String = textInputEditTextPassword.text.toString()
            val reEnter: String = textInputEditTextReEnterPassword.text.toString()
            passEmail = email

            if (username != "" && email != "" && password != "") {
                if (reEnter != password) {
                    Toast.makeText(applicationContext, "Passwords Don't Match", Toast.LENGTH_SHORT).show()
                } else {
                    progressBar.visibility = View.VISIBLE
                    //Start ProgressBar first (Set visibility VISIBLE)
                    val handler = Handler()
                    handler.post {
                        //Starting Write and Read data with URL
                        //Creating array for parameters
                        val field = arrayOfNulls<String>(3)
                        field[0] = "username"
                        field[1] = "email"
                        field[2] = "password"
                        //Creating array for data
                        val data = arrayOfNulls<String>(3)
                        data[0] = username
                        data[1] = email
                        data[2] = password
                        val putData = PutData("https://steadfastfitness.online/signup.php", "POST", field, data)
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                progressBar.visibility = View.GONE
                                val result = putData.result
                                //End ProgressBar (Set visibility to GONE)
                                Log.i("PutData", result)
                                if (result == "Registration Successful. Check your inbox to for a code to verify your email address.") {
                                    val intent = Intent(applicationContext, AccountAuthorization::class.java)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                        //End Write and Read data with URL
                    }
                }
            } else {
                Toast.makeText(applicationContext, "All Fields Are Required", Toast.LENGTH_SHORT).show()
            }
        })
        signInBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, LoginMenu::class.java)
            startActivity(intent)
            finish()
        })

        //Show and hide passwords
        //Show and hide passwords
        val imageViewShowHidePwd = findViewById<ImageView>(R.id.hide_pwd)
        imageViewShowHidePwd.setImageResource(R.drawable.ic_hide_ped)
        val imageViewShowHideRePwd = findViewById<ImageView>(R.id.hide_repwd)
        imageViewShowHideRePwd.setImageResource(R.drawable.ic_hide_ped)
        imageViewShowHidePwd.setOnClickListener {
            if (textInputEditTextPassword.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
                // If is visible then hide it
                textInputEditTextPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                //Change Icon
                imageViewShowHidePwd.setImageResource(R.drawable.ic_hide_ped)
            } else {
                textInputEditTextPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                imageViewShowHidePwd.setImageResource(R.drawable.ic_show_pwd)
            }
        }
        imageViewShowHideRePwd.setOnClickListener {
            if (textInputEditTextReEnterPassword.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
                // If is visible then hide it
                textInputEditTextReEnterPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                //Change Icon
                imageViewShowHideRePwd.setImageResource(R.drawable.ic_hide_ped)
            } else {
                textInputEditTextReEnterPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                imageViewShowHideRePwd.setImageResource(R.drawable.ic_show_pwd)
            }
        }
    }

}