package com.rital.data.repository

import com.rital.data.mapper.toDomain
import com.rital.data.mapper.toWeatherInfo
import com.rital.data.remote.api.WeatherApiService
import com.rital.domain.model.City
import com.rital.domain.model.Weather
import com.rital.domain.model.WeatherInfo
import com.rital.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: WeatherApiService
) : WeatherRepository {

    override suspend fun getWeather(
        city: City
    ): Weather {

        val response = apiService.getWeather(
            latitude = city.latitude,
            longitude = city.longitude,
            current = "temperature_2m,weather_code"
        )

        return response.toDomain(city)
    }

    override suspend fun getWeatherInfo(name:String, latitude:Double, longitude:Double): WeatherInfo {
        val response = apiService.getWeatherInfo(
            latitude = latitude,
            longitude = longitude
        )
        return response.toWeatherInfo(name)
    }
}