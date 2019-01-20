package com.dimchel.revolut.common.timer

interface TimerJob {

    fun start(tick: () -> Unit)
    fun stop()

}