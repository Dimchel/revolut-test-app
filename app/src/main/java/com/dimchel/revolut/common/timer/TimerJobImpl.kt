package com.dimchel.revolut.common.timer

import android.os.Handler
import android.os.Looper
import java.util.*
import javax.inject.Inject

class TimerJobImpl @Inject constructor(): TimerJob {

    private val handler = Handler(Looper.getMainLooper())

    private var timer: Timer? = null

    override fun start(tick: () -> Unit) {
        timer = Timer()
        timer!!.schedule(object : TimerTask() {
            override fun run() {
                handler.post(tick)
            }
        }, 0, 1000L)
    }

    override fun stop() {
        timer?.cancel()
    }

}