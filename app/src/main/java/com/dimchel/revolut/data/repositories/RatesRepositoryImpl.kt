package com.dimchel.revolut.data.repositories

import com.dimchel.revolut.data.api.RevolutApi
import com.dimchel.revolut.data.api.mapToRatesModel
import com.dimchel.revolut.data.api.schemes.RatesResponseScheme
import com.dimchel.revolut.features.converter.ConverterScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@ConverterScope
class RatesRepositoryImpl @Inject constructor(
    private val revolutApi: RevolutApi
): RatesRepository {

    private val ratesListenersMap = mutableMapOf<String, RatesListener>()

    override fun requestRates(baseCurrency: String) {
        revolutApi.fetchRates(baseCurrency).enqueue(object : Callback<RatesResponseScheme> {
            override fun onFailure(call: Call<RatesResponseScheme>, t: Throwable) {
                ratesListenersMap.values.forEach {
                    it.onFailure()
                }
            }

            override fun onResponse(call: Call<RatesResponseScheme>, response: Response<RatesResponseScheme>) {
                ratesListenersMap.values.forEach {
                    it.onRatesReceive(response.body()!!.mapToRatesModel())
                }
            }

        })
    }

    override fun subscribeRates(tag: String, listener: RatesListener) {
        ratesListenersMap[tag] = listener
    }

    override fun unsubscribe(tag: String) {
        ratesListenersMap.remove(tag)
    }
}