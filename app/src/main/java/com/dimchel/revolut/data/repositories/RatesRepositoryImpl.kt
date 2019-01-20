package com.dimchel.revolut.data.repositories

import com.dimchel.revolut.common.schedulers.AppSchedulers
import com.dimchel.revolut.data.api.RevolutApi
import com.dimchel.revolut.data.api.mapToRatesModel
import com.dimchel.revolut.features.converter.ConverterScope
import com.dimchel.revolut.features.converter.models.RatesModel
import io.reactivex.Observable
import javax.inject.Inject


@ConverterScope
class RatesRepositoryImpl @Inject constructor(
    private val revolutApi: RevolutApi,
    private val appSchedulers: AppSchedulers
): RatesRepository {

    override fun getRates(): Observable<RatesModel> {
        return revolutApi.fetchRates()
            .subscribeOn(appSchedulers.io())
            .observeOn(appSchedulers.ui())
            .map {
                it.mapToRatesModel()
            }
    }

}