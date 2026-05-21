package com.rital.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CurrentDto (
    @SerializedName("temperature_2m")
    val temperature: Double,

    @SerializedName("weather_code")
    val weatherCode: Int,

@SerializedName("relative_humidity_2m")
val humidity: Int,

@SerializedName("apparent_temperature")
val feelsLike: Double,

@SerializedName("pressure_msl")
val pressure: Double,

@SerializedName("visibility")
val visibility: Double,

@SerializedName("wind_speed_10m")
val windSpeed: Double
)
