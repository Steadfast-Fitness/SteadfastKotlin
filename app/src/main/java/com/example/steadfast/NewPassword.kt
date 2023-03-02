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
import kotlin.reflect.KMutableProperty1


// push
class NewPassword : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_password)
        var enterPass = findViewById<TextInputEditText>(R.id.enterpassword)
        var reEnterPass = findViewById<TextInputEditText>(R.id.reenterpassword)
        var changePass = findViewById<Button>(R.id.submitpassword)
        var progressBar = findViewById<ProgressBar>(R.id.progress)

        changePass.setOnClickListener(View.OnClickListener {

            val reEnter: String = reEnterPass.text.toString()
            val password: String = enterPass.text.toString()
            val email = ForgotPassword.passEmail

            Log.i("test", email)
            if (password != "") {
                if (reEnter != password) {
                     Toast.makeText(applicationContext, "Passwords Do Not Match", Toast.LENGTH_SHORT).show()
                } else {
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
                        val putData = PutData(
                            "https://steadfastfitness.online/changepass.php",
                            "POST",
                            field,
                            data
                        )
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                progressBar.visibility = View.GONE
                                val result = putData.result
                                //End ProgressBar (Set visibility to GONE)
                                Log.i("PutData", result)
                                if (result == "Password Reset. Please Login.") {
                                    Toast.makeText(applicationContext, result, Toast.LENGTH_LONG)
                                        .show()
                                    val intent = Intent(applicationContext, LoginMenu::class.java)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(applicationContext, result, Toast.LENGTH_LONG)
                                        .show()
                                }
                            }
                        }
                        //End Write and Read data with URL
                    }
                }
            } else {
                Toast.makeText(applicationContext, "All Fields Are Required", Toast.LENGTH_SHORT)
                    .show()
            }

        })
        val imageViewShowHidePwd = findViewById<ImageView>(R.id.hide_pwd)
        imageViewShowHidePwd.setImageResource(R.drawable.ic_hide_ped)
        val imageViewShowHideRePwd = findViewById<ImageView>(R.id.hide_repwd)
        imageViewShowHideRePwd.setImageResource(R.drawable.ic_hide_ped)
        imageViewShowHideRePwd.setOnClickListener {
            if (enterPass.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
                // If is visible then hide it
                enterPass.transformationMethod = PasswordTransformationMethod.getInstance()
                //Change Icon
                imageViewShowHideRePwd.setImageResource(R.drawable.ic_hide_ped)
            } else {
                enterPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
                imageViewShowHideRePwd.setImageResource(R.drawable.ic_show_pwd)
            }
        }
        imageViewShowHidePwd.setOnClickListener {
            if (reEnterPass.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
                // If is visible then hide it
                reEnterPass.transformationMethod = PasswordTransformationMethod.getInstance()
                //Change Icon
                imageViewShowHidePwd.setImageResource(R.drawable.ic_hide_ped)
            } else {
                reEnterPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
                imageViewShowHidePwd.setImageResource(R.drawable.ic_show_pwd)
            }
        }
    }
}