package com.example.steadfast

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import com.vishnusivadas.advanced_httpurlconnection.PutData


//
class LoginMenu : AppCompatActivity() {

    companion object{
        var passEmail: String = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)
        var login = findViewById<Button>(R.id.loginbtn)
        var buttonRegister = findViewById<TextView>(R.id.signUpText)
        var textInputEditTextEmail = findViewById<TextInputEditText>(R.id.email)
        var textInputEditTextPassword = findViewById<TextInputEditText>(R.id.password)
        var progressBar = findViewById<ProgressBar>(R.id.progress)
        var forgotPass = findViewById<TextView>(R.id.forgotpass)

        val sharedPreferences = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("email", "")
        val password = sharedPreferences.getString("password", "")
        if (email != null) {
            passEmail = email
        }

        // Set email and password fields if they exist
        if (!email.isNullOrEmpty()) {
            textInputEditTextEmail.setText(email)
        }
        if (!password.isNullOrEmpty()) {
            textInputEditTextPassword.setText(password)
        }

        // If both email and password are available, automatically log in the user
        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
            progressBar.visibility = View.VISIBLE
            val handler = Handler()
            handler.post {
                val field = arrayOfNulls<String>(2)
                field[0] = "email"
                field[1] = "password"
                val data = arrayOfNulls<String>(2)
                data[0] = email
                data[1] = password
                val putData = PutData("https://steadfastfitness.online/login.php", "POST", field, data)
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        progressBar.visibility = View.GONE
                        val result = putData.result
                        Log.i("PutData", result)
                        if (result == "Login Success") {
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

        // If user clicks "Login"
        login.setOnClickListener(View.OnClickListener {
            val email: String = textInputEditTextEmail.text.toString()
            val password: String = textInputEditTextPassword.text.toString()
            passEmail = email
            if (email != "" && password != "") {
                progressBar.visibility = View.VISIBLE
                //Start ProgressBar first (Set visibility VISIBLE)
                val handler = Handler()
                handler.post {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    val field = arrayOfNulls<String>(2)
                    field[0] = "email"
                    field[1] = "password"
                    //Creating array for data
                    val data = arrayOfNulls<String>(2)
                    data[0] = email
                    data[1] = password
                    val putData = PutData("https://steadfastfitness.online/login.php", "POST", field, data)
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            progressBar.visibility = View.GONE
                            val result = putData.result
                            //End ProgressBar (Set visibility to GONE)
                            Log.i("PutData", result)
                            if (result == "Login Success") {
                                val intent = Intent(applicationContext, MainMenu::class.java)
                                startActivity(intent)
                                finish()
                                // Save login information
                                val sharedPreferences = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE)
                                val editor = sharedPreferences.edit()
                                editor.putString("email", email)
                                editor.putString("password", password)
                                editor.apply()
                            } else {
                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    //End Write and Read data with URL
                }
            } else {
                Toast.makeText(applicationContext, "Email Or Password Incorrect", Toast.LENGTH_SHORT).show()
            }
        })


        // If user clicks "Forgot Password?"
        forgotPass.setOnClickListener(View.OnClickListener {
            Toast.makeText(this@LoginMenu, "Enter email to reset password.", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@LoginMenu, ForgotPassword::class.java))
        })


        // If user clicks "Sign Up"
        buttonRegister.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, RegisterMenu::class.java)
            startActivity(intent)
            finish()
        })

        //Show and hide passwords
        val imageViewShowHidePwd = findViewById<ImageView>(R.id.ic_hide_ped)
        imageViewShowHidePwd.setImageResource(R.drawable.ic_hide_ped)
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
    }
}