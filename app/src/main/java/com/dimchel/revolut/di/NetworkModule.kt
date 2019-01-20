package com.dimchel.revolut.di

import com.dimchel.revolut.AppConstants
import com.dimchel.revolut.data.api.RevolutApi
import dagger.Module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@AppScope
@Module
abstract class NetworkModule {

    companion object {

        fun provideRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(AppConstants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }

        fun provideRevolutApi(retrofit: Retrofit): RevolutApi {
            return retrofit.create(RevolutApi::class.java)
        }
    }

}