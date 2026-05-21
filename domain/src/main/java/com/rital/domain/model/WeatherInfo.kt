package com.rital.domain.model

import com.rital.domain.enums.WeatherCondition

data class WeatherInfo(
    val city: String,
    val temperature: Double,
    val maxTemperature: Double,
    val minTemperature: Double,
    val feelsLike: Double,
    val humidity: Int,
    val pressure: Double,
    val windSpeed: Double,
    val visibility: Double,
    val uvIndex: Double,
    val sunrise: String,
    val sunset: String,
    val condition: String
)
