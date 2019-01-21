package com.dimchel.revolut.features.converter.presentation

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.dimchel.revolut.R
import com.dimchel.revolut.RevolutApp
import com.dimchel.revolut.common.utils.visible
import com.dimchel.revolut.features.converter.ConverterView
import com.dimchel.revolut.features.converter.models.RateModel
import javax.inject.Inject


class ConverterActivity : MvpAppCompatActivity(), ConverterView, ConverterListListener {

    private lateinit var converterAdapter: ConverterAdapter

    private lateinit var ratesRecyclerView: RecyclerView
    private lateinit var loadingProgressBar: ProgressBar

    @Inject
    @InjectPresenter
    lateinit var presenter: ConverterPresenterImpl

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        RevolutApp.getDiManager().getConverterComponent().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)

        initViews()
    }

    private fun initViews() {
        loadingProgressBar = findViewById(R.id.converter_progressbar)

        ratesRecyclerView = findViewById(R.id.converter_recyclerview)
        ratesRecyclerView.layoutManager = LinearLayoutManager(this)
        ratesRecyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        ratesRecyclerView.itemAnimator = null
        ratesRecyclerView.setHasFixedSize(true)
    }

    // ===========================================================
    // ConverterView
    // ===========================================================

    override fun setRatesVisible(isVisible: Boolean) {
        ratesRecyclerView.visible = isVisible
    }

    override fun initRatesList(inputValue: Double) {
        converterAdapter = ConverterAdapter(this, inputValue)
        ratesRecyclerView.adapter = converterAdapter
    }

    override fun updateRates(ratesList: List<RateModel>) {
        converterAdapter.updateRates(ratesList)
    }

    override fun updateSelectedRate(rateModel: RateModel) {
        converterAdapter.selectRate(rateModel)
    }

    override fun updateInputValue(inputValue: Double) {
        converterAdapter.setInputValue(inputValue)
    }

    override fun setLoadingVisibility(isVisible: Boolean) {
        loadingProgressBar.visible = isVisible
    }

    // ===========================================================
    // ConverterListListener
    // ===========================================================

    override fun onItemSelected(rateModel: RateModel) {
        presenter.onItemSelected(rateModel)
    }

    override fun onInputValueChanged(inputValue: Double) {
        presenter.onInputValueChanged(inputValue)
    }
}
