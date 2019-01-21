package com.dimchel.revolut.data.api

import com.dimchel.revolut.data.api.schemes.RatesResponseScheme
import com.dimchel.revolut.features.converter.models.RateModel
import com.dimchel.revolut.features.converter.models.RatesModel


fun RatesResponseScheme.mapToRatesModel(): RatesModel {
    val ratesList: MutableList<RateModel> = mutableListOf()

    ratesScheme.rates.keys.forEach {
        ratesList.add(RateModel(it, ratesScheme.rates[it]!!))
    }

    return RatesModel(
        base, date, ratesList
    )
}