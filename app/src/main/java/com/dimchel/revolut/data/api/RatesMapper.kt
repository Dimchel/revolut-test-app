package com.dimchel.revolut.data.api

import android.os.Looper
import android.util.Log
import com.dimchel.revolut.data.api.schemes.RatesResponseScheme
import com.dimchel.revolut.features.converter.models.RatesModel


fun RatesResponseScheme.mapToRatesModel(): RatesModel {
    Log.v("123", "main: " + (Looper.getMainLooper() == Looper.myLooper()))
    return RatesModel(
        base, date, ratesScheme.rates
    )
}