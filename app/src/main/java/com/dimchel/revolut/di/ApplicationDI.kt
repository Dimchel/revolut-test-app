package com.dimchel.revolut.di

import android.app.Application
import android.content.Context
import com.dimchel.revolut.common.schedulers.AppSchedulers
import com.dimchel.revolut.common.schedulers.AppSchedulersImpl
import com.dimchel.revolut.features.converter.ConverterComponent
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Scope


@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope


@AppScope
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    // ===========================================================
    // Subcomponents
    // ===========================================================

    fun getConverterComponent(): ConverterComponent

}

@AppScope
@Module
abstract class AppModule {

    @AppScope
    @Binds
    abstract fun provideContext(application: Application): Context

    @AppScope
    @Binds
    abstract fun provideAppSchedulers(appSchedulers: AppSchedulersImpl): AppSchedulers

}