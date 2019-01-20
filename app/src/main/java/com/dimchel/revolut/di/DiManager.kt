package com.dimchel.revolut.di

import android.app.Application
import com.dimchel.revolut.features.converter.ConverterComponent

class DiManager(private val application: Application) {

    private var appComponent: AppComponent? = null

    // ===========================================================
    // Features
    // ===========================================================

    private var converterComponent: ConverterComponent? = null

    private fun getAppComponent(): AppComponent {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                .application(application)
                .build()
        }

        return appComponent as AppComponent
    }

    public fun getConverterComponent(): ConverterComponent {
        if (converterComponent == null) {
            converterComponent = getAppComponent().getConverterComponent()
        }

        return converterComponent as ConverterComponent
    }

    public fun releaseConverterComponent() {
        converterComponent = null
    }

}