package com.example.steadfast

import com.google.gson.annotations.SerializedName

data class Supplement(
    @SerializedName("supplement") val supplement: String,
    @SerializedName("dosage") val dosage: String,
    @SerializedName("amount") val amount: String
)