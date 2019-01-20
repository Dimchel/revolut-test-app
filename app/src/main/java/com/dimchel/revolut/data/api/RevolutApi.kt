package com.dimchel.revolut.data.api

import com.dimchel.revolut.AppConstants
import com.dimchel.revolut.data.api.schemes.RatesResponseScheme
import io.reactivex.Observable
import retrofit2.http.GET

interface RevolutApi {

    @GET(AppConstants.REQUEST_QUOTES_URL)
    fun fetchRates(): Observable<RatesResponseScheme>

}