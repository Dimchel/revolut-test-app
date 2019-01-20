package com.dimchel.revolut.features.converter

import com.dimchel.revolut.data.repositories.RatesRepository
import com.dimchel.revolut.data.repositories.RatesRepositoryImpl
import com.dimchel.revolut.features.converter.presentation.ConverterActivity
import com.dimchel.revolut.features.converter.presentation.ConverterPresenterImpl
import dagger.Binds
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

    @ConverterScope
    @Binds
    abstract fun provideConverterPresenter(converterPresenter: ConverterPresenterImpl): ConverterPresenter

    @ConverterScope
    @Binds
    abstract fun provideRatesRepository(ratesRepository: RatesRepositoryImpl): RatesRepository

}