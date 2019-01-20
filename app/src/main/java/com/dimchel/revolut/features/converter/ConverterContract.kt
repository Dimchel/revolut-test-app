package com.dimchel.revolut.features.converter

import com.arellomobile.mvp.MvpView
import com.dimchel.revolut.features.converter.models.RatesModel

interface ConverterView: MvpView {

    fun setRatesVisible(isVisible: Boolean)
    fun updateRates(ratesModel: RatesModel)

    fun setRetryVisibility(isVisible: Boolean)
    fun setLoadingVisibility(isVisible: Boolean)

}

interface ConverterPresenter {

}