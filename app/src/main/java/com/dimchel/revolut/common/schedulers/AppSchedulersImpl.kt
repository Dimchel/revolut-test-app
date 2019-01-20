package com.dimchel.revolut.common.schedulers

import com.dimchel.revolut.di.AppScope
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


@AppScope
class AppSchedulersImpl @Inject constructor() : AppSchedulers {

    override fun computation(): Scheduler = Schedulers.computation()

    override fun io(): Scheduler = Schedulers.io()

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()

}