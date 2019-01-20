package com.dimchel.revolut.common.schedulers

import io.reactivex.Scheduler


interface AppSchedulers {

    fun computation(): Scheduler
    fun io(): Scheduler
    fun ui(): Scheduler

}