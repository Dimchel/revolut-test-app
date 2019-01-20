package com.dimchel.revolut.data.api.deserializers

import com.dimchel.revolut.data.api.schemes.RatesScheme
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type



class RatesDeserializer: JsonDeserializer<RatesScheme> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): RatesScheme {
        val jsonObject = json!!.asJsonObject

        val valuesMap = hashMapOf<String, Double>()

        jsonObject.keySet().forEach {
            valuesMap[it] = jsonObject.get(it).asDouble
        }

        return RatesScheme(valuesMap)
    }
}