package com.dimchel.revolut.features.converter

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.dimchel.revolut.features.converter.models.RateModel


@StateStrategyType(AddToEndSingleStrategy::class)
interface ConverterView: MvpView {

    fun setRatesVisible(isVisible: Boolean)

    fun updateRates(ratesList: List<RateModel>)
    fun updateInputValue(inputValue: Double)

    @StateStrategyType(SkipStrategy::class)
    fun updateSelectedRate(rateModel: RateModel)

    fun setLoadingVisibility(isVisible: Boolean)

}

interface ConverterPresenter {

    fun onItemSelected(rateModel: RateModel)
    fun onInputValueChanged(inputValue: Double)

}