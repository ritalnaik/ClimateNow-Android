package com.rital.domain.usecase

import com.rital.domain.model.City
import com.rital.domain.model.Weather
import com.rital.domain.model.WeatherInfo
import com.rital.domain.repository.WeatherRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.time.delay
//import kotlin.collections.map
import javax.inject.Inject

class GetWeatherDetailsUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(
        name:String, latitude:Double, longitude:Double
    ): WeatherInfo {
        return repository.getWeatherInfo(
            name = name,
            latitude = latitude,
            longitude = longitude
        )
    }
}