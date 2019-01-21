package com.dimchel.revolut.data.api

import com.dimchel.revolut.AppConstants
import com.dimchel.revolut.data.api.schemes.RatesResponseScheme
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RevolutApi {

    @GET(AppConstants.REQUEST_QUOTES_URL)
    fun fetchRates(@Query("base") baseCurrency: String): Call<RatesResponseScheme>

}