package com.dimchel.revolut

import com.dimchel.revolut.common.timer.TimerJob
import com.dimchel.revolut.data.repositories.RatesRepository
import com.dimchel.revolut.features.converter.ConverterView
import com.dimchel.revolut.features.converter.`ConverterView$$State`
import com.dimchel.revolut.features.converter.models.RateModel
import com.dimchel.revolut.features.converter.models.RatesModel
import com.dimchel.revolut.features.converter.presentation.ConverterPresenterImpl
import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock


class ConverterTest {

    private val ratesRepository = mock(RatesRepository::class.java)
    private val timeJob = mock(TimerJob::class.java)
    private val view =  mock(ConverterView::class.java)

    private lateinit var viewState: `ConverterView$$State`
    private lateinit var converterPresenter: ConverterPresenterImpl

    @BeforeEach
    fun setUp() {
        viewState = mock(`ConverterView$$State`::class.java)

        converterPresenter = ConverterPresenterImpl(ratesRepository, timeJob)
        converterPresenter.setViewState(viewState)
    }

    @Test
    fun `Should initialize loader state after attach`() {
        converterPresenter.attachView(view)

        verify(viewState).setRatesVisible(false)
        verify(viewState).setLoadingVisibility(true)
    }

    @Test
    fun `Should start timer after attach`() {
        converterPresenter.attachView(view)

        verify(timeJob).start(anyOrNull())
    }

    @Test
    fun `Should stop timer after detach`() {
        converterPresenter.detachView(view)

        verify(timeJob).stop()
    }

    @Test
    fun `Should request new rate and update selected rate`() {
        val rateModel = RateModel("rate", 12.02)

        converterPresenter.onItemSelected(rateModel)

        verify(ratesRepository).requestRates(rateModel.name)

        verify(viewState).updateSelectedRate(rateModel)
    }

    @Test
    fun `Should update input value`() {
        val inputValue = 12.66

        converterPresenter.onInputValueChanged(inputValue)

        verify(viewState).updateInputValue(inputValue)
    }

    @Test
    fun `Should initialize loading state and update rate list`() {
        val baseRate = "USD"
        val baseRateValue = 1.0
        val inputRatesList = mutableListOf<RateModel>().apply {
            add(RateModel("RUB", 42.3))
            add(RateModel("EUR", 13.5))
            add(RateModel("CHF", 52.8))
        }

        converterPresenter.onRatesReceive(RatesModel(baseRate, "23.01.19", inputRatesList))

        verify(viewState).setLoadingVisibility(false)
        verify(viewState).setRatesVisible(true)

        val captor = argumentCaptor<List<RateModel>>()

        verify(viewState).updateRates(captor.capture())

        assertEquals(captor.firstValue[0].name, baseRate)
        assertEquals(captor.firstValue[0].value, baseRateValue)
    }

    @Test
    fun `Should initialize loading state after network error`() {
        converterPresenter.onFailure()

        verify(viewState).setRatesVisible(false)
        verify(viewState).setLoadingVisibility(true)
    }
}
