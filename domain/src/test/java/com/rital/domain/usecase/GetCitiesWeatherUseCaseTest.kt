package com.rital.domain.usecase

import com.rital.domain.model.City
import com.rital.domain.model.Weather
import com.rital.domain.repository.WeatherRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetCitiesWeatherUseCaseTest {

    private lateinit var repository: WeatherRepository
    private lateinit var useCase: GetCitiesWeatherUseCase

    @Before
    fun setup() {
        repository = mockk()
        useCase = GetCitiesWeatherUseCase( repository)
    }

    @Test
    fun `invoke should return weather list for all cities`() =
        runTest {

            val cities = listOf(
                City("Mumbai", 19.07, 72.87),
                City("Delhi", 28.61, 77.20),
                City("Pune", 18.52, 73.85)
            )

            val mumbaiWeather = Weather(cityName = "Mumbai", temperature = 32.0, weatherCode = 91)
            val puneWeather = Weather(cityName = "Pune", temperature = 28.0, weatherCode = 96)
            val delhiWeather = Weather(cityName = "Delhi", temperature = 35.0, weatherCode = 95)

            coEvery {
                repository.getWeather(cities[0])
            } returns mumbaiWeather

            coEvery {
                repository.getWeather(cities[1] )
            } returns puneWeather

            coEvery {
                repository.getWeather(cities[2])
            } returns delhiWeather

            val result = useCase(cities)
            assertEquals(3, result.size)

            assertEquals(listOf(
                    mumbaiWeather,
                    puneWeather,
                    delhiWeather
                ), result)

            coVerify(exactly = 3) { repository.getWeather(any()) }
        }
}