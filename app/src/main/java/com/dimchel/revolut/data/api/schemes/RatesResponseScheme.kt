package com.dimchel.revolut.data.api.schemes

import com.dimchel.revolut.data.api.deserializers.RatesDeserializer
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

class RatesResponseScheme(

    @SerializedName("base")
    val base: String,

    @SerializedName("date")
    val date: String,

    @SerializedName("rates")
    val ratesScheme: RatesScheme

)

@JsonAdapter(RatesDeserializer::class)
class RatesScheme(
    val rates: Map<String, Double>
)