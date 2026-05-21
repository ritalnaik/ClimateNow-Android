package com.rital.data.mapper

import com.rital.data.remote.dto.WeatherResponseDto
import com.rital.domain.model.WeatherInfo

fun WeatherResponseDto.toWeatherInfo(
    cityName: String
): WeatherInfo {

    return WeatherInfo(
        city = cityName,
        temperature = current.temperature,
        maxTemperature = daily.maxTemps.first(),
        minTemperature = daily.minTemps.first(),
        feelsLike = current.feelsLike,
        humidity = current.humidity,
        pressure = current.pressure,
        windSpeed = current.windSpeed,
        visibility = current.visibility,
        uvIndex = daily.uvIndexes.first(),
        sunrise = daily.sunrise.first(),
        sunset = daily.sunset.first(),
        condition = current.weatherCode.toWeatherCondition().name
    )
}