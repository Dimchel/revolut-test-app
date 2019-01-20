package com.dimchel.revolut.di

import com.dimchel.revolut.AppConstants
import com.dimchel.revolut.data.api.RevolutApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



@AppScope
@Module
class NetworkModule {

    @AppScope
    @Provides
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(AppConstants.API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @AppScope
    @Provides
    fun provideRevolutApi(retrofit: Retrofit): RevolutApi {
        return retrofit.create(RevolutApi::class.java)
    }

}