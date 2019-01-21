package com.dimchel.revolut.data.repositories

import com.dimchel.revolut.features.converter.models.RatesModel

interface RatesListener {

    fun onRatesReceive(ratesModel: RatesModel)
    fun onFailure()

}

interface RatesRepository {

    fun subscribeRates(tag: String, listener: RatesListener)
    fun unsubscribe(tag: String)

    fun requestRates(baseCurrency: String)

}