package com.example.steadfast

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.vishnusivadas.advanced_httpurlconnection.FetchData
import com.vishnusivadas.advanced_httpurlconnection.PutData


class SupplementView : AppCompatActivity() {

    private lateinit var listView: ListView

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supplement_view)
        var addSup = findViewById<ImageView>(R.id.addSupplement)
        listView = findViewById(R.id.supplementList)

        addSup.setOnClickListener{ view -> // inflate the layout of the popup window
            val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView = inflater.inflate(R.layout.activity_add_supplement, null)

            // create the popup window
            val width = LinearLayout.LayoutParams.MATCH_PARENT
            val height = LinearLayout.LayoutParams.WRAP_CONTENT
            val focusable = true // lets taps outside the popup also dismiss it
            val popupWindow = PopupWindow(popupView, width, height, focusable)

            // show the popup window
            // which view you pass in doesn't matter, it is only used for the window tolken
            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)

            var addSupplements = popupView.findViewById<Button>(R.id.addSupBtn)
            var supplementInput = popupView.findViewById<TextInputEditText>(R.id.addSupplementTxt)
            var dosageInput = popupView.findViewById<TextInputEditText>(R.id.addDosage)
            var gOrMG = popupView.findViewById<Spinner>(R.id.dropDown)
            var amount = ""
            var progressBar = popupView.findViewById<ProgressBar>(R.id.progress);

            gOrMG.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    amount = parent.getItemAtPosition(position) as String
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Do nothing
                }
            }

            addSupplements.setOnClickListener{
                var supplement: String = supplementInput.text.toString()
                var dosage: String = dosageInput.text.toString()
                val email = LoginMenu.passEmail

                if(supplement != "" && dosage != ""){

                    progressBar.visibility = View.VISIBLE
                    //Start ProgressBar first (Set visibility VISIBLE)

                    val handler = Handler()
                    handler.post {
                        //Starting Write and Read data with URL
                        //Creating array for parameters
                        val field = arrayOfNulls<String>(4)
                        field[0] = "email"
                        field[1] = "supplement"
                        field[2] = "dosage"
                        field[3] = "amount"
                        //Creating array for data
                        val data = arrayOfNulls<String>(4)
                        data[0] = email
                        data[1] = supplement
                        data[2] = dosage
                        data[3] = amount
                        val putData = PutData("https://steadfastfitness.online/supplements/supplementstable.php", "POST", field, data)
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                progressBar.visibility = View.GONE
                                val result = putData.result
                                //End ProgressBar (Set visibility to GONE)
                                Log.i("PutData", result)
                                if (result == "Code Correct") {
                                    val intent = Intent(applicationContext, SupplementView::class.java)
                                    startActivity(intent)
                                } else {
                                    Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }

                }else {
                    Toast.makeText(applicationContext, "Enter the Supplement and Dosage", Toast.LENGTH_SHORT).show()
                }
            }
1

            // dismiss the popup window when touched
            popupView.setOnTouchListener { v, event ->
                popupWindow.dismiss()
                true
            }
        }

        // Creating the view of the list


        val url = "https://steadfastfitness.online/supplements/getsupplements.php"
        val email = LoginMenu.passEmail

        val request = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->
                // Response received successfully
                val responseString = response // Store the response in a variable
                Log.d("TAG", "Response: $responseString")
              /*  data class Supplement(
                    @SerializedName("supplement") val name: String,
                    @SerializedName("dosage") val dosage: String,
                    @SerializedName("amount") val amount: String
                ) */

              /*  val json = responseString
                val supplements = Gson().fromJson(json, Array<Supplement>::class.java).toList()

                for (supplement in supplements) {
                    Log.d("TAG", "Supplement Name: ${supplement.name}")
                    Log.d("TAG", "Supplement Dosage: ${supplement.dosage}")
                    Log.d("TAG", "Supplement Amount: ${supplement.amount}")
                } */

            },
            Response.ErrorListener { error ->
                // Error occurred while making the request
                Log.e("TAG", "Error: ${error.message}")
            }) {

            // Add the POST parameters to the request
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["email"] = email
                return params
            }

        }

// Add the request to the Volley request queue
        Volley.newRequestQueue(applicationContext).add(request)

    }
    override fun onBackPressed() {
        val intent = Intent(this, MainMenu::class.java)
        startActivity(intent)
        finish()
    }
}