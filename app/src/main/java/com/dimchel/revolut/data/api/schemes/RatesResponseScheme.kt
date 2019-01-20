package com.dimchel.revolut.data.api.schemes

import com.google.gson.annotations.SerializedName

class RatesResponseScheme(

    @SerializedName("base")
    val base: String,

    @SerializedName("date")
    val data: String,

    @SerializedName("rates")
    val rates: List<Double>

)