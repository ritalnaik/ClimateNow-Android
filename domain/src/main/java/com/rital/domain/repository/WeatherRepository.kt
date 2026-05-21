package com.rital.domain.repository

import com.rital.domain.model.City
import com.rital.domain.model.Weather
import com.rital.domain.model.WeatherInfo

interface WeatherRepository {
    suspend fun getWeather(city: City): Weather
    suspend fun getWeatherInfo(name:String, latitude:Double, longitude:Double): WeatherInfo
}