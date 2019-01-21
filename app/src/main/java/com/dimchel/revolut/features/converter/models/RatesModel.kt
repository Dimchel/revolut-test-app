package com.dimchel.revolut.features.converter.models

class RatesModel(
    val base: String,
    val data: String,
    val ratesList: MutableList<RateModel>
)