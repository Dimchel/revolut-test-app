package com.dimchel.revolut.features.converter.presentation

import com.arellomobile.mvp.MvpPresenter
import com.dimchel.revolut.features.converter.ConverterPresenter
import com.dimchel.revolut.features.converter.ConverterScope
import com.dimchel.revolut.features.converter.ConverterView
import javax.inject.Inject

@ConverterScope
class ConverterPresenterImpl @Inject constructor(): MvpPresenter<ConverterView>(), ConverterPresenter {



}