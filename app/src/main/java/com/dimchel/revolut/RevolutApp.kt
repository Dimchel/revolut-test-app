package com.dimchel.revolut

import android.app.Application
import com.dimchel.revolut.di.DiManager


class RevolutApp : Application() {

    override fun onCreate() {
        super.onCreate()

        diManager = DiManager(this)
    }

    companion object {
        private lateinit var diManager: DiManager

        fun getDiManager(): DiManager = diManager
    }

}