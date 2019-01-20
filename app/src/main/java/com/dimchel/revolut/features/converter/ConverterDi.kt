package com.dimchel.revolut.features.converter

import dagger.Module
import dagger.Subcomponent
import javax.inject.Scope


@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ConverterScope


@ConverterScope
@Subcomponent(modules = [ConverterModule::class])
interface ConverterComponent {

    // ===========================================================
    // Injects
    // ===========================================================

    fun inject(converterActivity: ConverterActivity)

}

@ConverterScope
@Module
abstract class ConverterModule {

}