package com.rital.data.mapper

import com.rital.data.remote.dto.WeatherDto
import com.rital.domain.model.City
import com.rital.domain.model.Weather


fun WeatherDto.toDomain(
    city: City
): Weather {
    return Weather(
        cityName = city.name,
        temperature = current.temperature,
        weatherCode = current.weatherCode
    )
}