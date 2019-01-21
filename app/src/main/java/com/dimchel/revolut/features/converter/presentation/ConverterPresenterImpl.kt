package com.dimchel.revolut.features.converter.presentation

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.dimchel.revolut.common.timer.TimerJob
import com.dimchel.revolut.data.repositories.RatesListener
import com.dimchel.revolut.data.repositories.RatesRepository
import com.dimchel.revolut.features.converter.ConverterPresenter
import com.dimchel.revolut.features.converter.ConverterScope
import com.dimchel.revolut.features.converter.ConverterView
import com.dimchel.revolut.features.converter.models.RateModel
import com.dimchel.revolut.features.converter.models.RatesModel
import javax.inject.Inject


private const val TAG_LISTENER = "ConverterPresenterImpl.TAG_LISTENER"
private const val DEFAULT_SELECTED_RATE = "USD"
private const val DEFAULT_INPUT_VALUE = 100.0

@ConverterScope
@InjectViewState
class ConverterPresenterImpl @Inject constructor(
    private val ratesRepository: RatesRepository,
    private val timerJob: TimerJob
): MvpPresenter<ConverterView>(), ConverterPresenter, RatesListener {

    private var selectedRate = DEFAULT_SELECTED_RATE

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.setRatesVisible(false)
        viewState.setLoadingVisibility(true)

        viewState.initRatesList(DEFAULT_INPUT_VALUE)
    }

    override fun attachView(view: ConverterView?) {
        super.attachView(view)

        ratesRepository.subscribeRates(TAG_LISTENER, this)

        timerJob.start {
            ratesRepository.requestRates(selectedRate)
        }
    }

    override fun detachView(view: ConverterView?) {
        ratesRepository.unsubscribe(TAG_LISTENER)
        timerJob.stop()

        super.detachView(view)
    }

    // ===========================================================
    // ConverterPresenter
    // ===========================================================

    override fun onItemSelected(rateModel: RateModel) {
        viewState.updateSelectedRate(rateModel)

        selectedRate = rateModel.name

        ratesRepository.requestRates(selectedRate)
    }

    override fun onInputValueChanged(inputValue: Double) {
        viewState.updateInputValue(inputValue)
    }

    // ===========================================================
    // RatesListener
    // ===========================================================

    override fun onRatesReceive(ratesModel: RatesModel) {
        viewState.setLoadingVisibility(false)
        viewState.setRatesVisible(true)

        viewState.updateRates(getRatesListWithBaseRate(ratesModel))
    }

    override fun onFailure() {
        viewState.setRatesVisible(false)
        viewState.setLoadingVisibility(true)
    }

    // ===========================================================
    // Common
    // ===========================================================

    private fun getRatesListWithBaseRate(ratesModel: RatesModel): List<RateModel> =
        ratesModel.ratesList.apply { add(0, RateModel(ratesModel.base, 1.0)) }
}