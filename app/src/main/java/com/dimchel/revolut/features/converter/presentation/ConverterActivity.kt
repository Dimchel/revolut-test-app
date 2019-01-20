package com.dimchel.revolut.features.converter.presentation

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.ProgressBar
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.dimchel.revolut.R
import com.dimchel.revolut.RevolutApp
import com.dimchel.revolut.common.utils.visible
import com.dimchel.revolut.features.converter.ConverterView
import com.dimchel.revolut.features.converter.models.RatesModel
import javax.inject.Inject


class ConverterActivity : MvpAppCompatActivity(), ConverterView {

    private lateinit var ratesRecyclerView: RecyclerView
    private lateinit var retryButton: Button
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
        ratesRecyclerView = findViewById(R.id.converter_recyclerview)
        retryButton = findViewById(R.id.converter_retry_button)
        loadingProgressBar = findViewById(R.id.converter_progressbar)
    }

    // ===========================================================
    // ConverterView
    // ===========================================================

    override fun setRatesVisible(isVisible: Boolean) {
        ratesRecyclerView.visible = isVisible
    }

    override fun updateRates(ratesModel: RatesModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setRetryVisibility(isVisible: Boolean) {
        retryButton.visible = isVisible
    }

    override fun setLoadingVisibility(isVisible: Boolean) {
        loadingProgressBar.visible = isVisible
    }
}
