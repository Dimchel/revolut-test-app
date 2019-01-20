package com.dimchel.revolut.features.converter.presentation

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.dimchel.revolut.R
import com.dimchel.revolut.RevolutApp
import com.dimchel.revolut.features.converter.ConverterView
import javax.inject.Inject


class ConverterActivity : MvpAppCompatActivity(), ConverterView {

    private lateinit var converterRecyclerView: RecyclerView

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
        converterRecyclerView = findViewById(R.id.converter_recyclerview)
    }

    // ===========================================================
    // ConverterView
    // ===========================================================


}
