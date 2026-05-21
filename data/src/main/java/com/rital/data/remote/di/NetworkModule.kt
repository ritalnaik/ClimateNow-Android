package com.rital.data.remote.di


import com.rital.data.remote.api.WeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")//todo Use the one from gradle buildconfig
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(
        retrofit: Retrofit
    ): WeatherApiService {

        return retrofit.create(
            WeatherApiService::class.java
        )
    }
}