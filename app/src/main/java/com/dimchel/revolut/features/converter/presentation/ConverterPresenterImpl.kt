package com.dimchel.revolut.features.converter.presentation

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.dimchel.revolut.common.timer.TimerJob
import com.dimchel.revolut.data.repositories.RatesListener
import com.dimchel.revolut.data.repositories.RatesRepository
import com.dimchel.revolut.features.converter.ConverterPresenter
import com.dimchel.revolut.features.converter.ConverterScope
import com.dimchel.revolut.features.converter.ConverterView
import com.dimchel.revolut.features.converter.models.RatesModel
import javax.inject.Inject

private const val TAG_LISTENER = "ConverterPresenterImpl.TAG_LISTENER"

@ConverterScope
@InjectViewState
class ConverterPresenterImpl @Inject constructor(
    private val ratesRepository: RatesRepository,
    private val timerJob: TimerJob
): MvpPresenter<ConverterView>(), ConverterPresenter, RatesListener {

    override fun attachView(view: ConverterView?) {
        super.attachView(view)

        ratesRepository.subscribeRates(TAG_LISTENER, this)

        timerJob.start {
            ratesRepository.requestRates()
        }
    }

    override fun detachView(view: ConverterView?) {
        ratesRepository.unsubscribe(TAG_LISTENER)
        timerJob.stop()

        super.detachView(view)
    }

    // ===========================================================
    // RatesListener
    // ===========================================================

    override fun onRatesReceive(ratesModel: RatesModel) {
        ratesModel.rates.keys.forEach {
            Log.v("123", it)
        }
    }

    override fun onFailure() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}