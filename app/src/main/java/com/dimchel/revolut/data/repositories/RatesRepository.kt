package com.dimchel.revolut.data.repositories

import com.dimchel.revolut.features.converter.models.RatesModel
import io.reactivex.Observable

interface RatesRepository {

    fun getRates(): Observable<RatesModel>

}