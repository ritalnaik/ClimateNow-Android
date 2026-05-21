package com.rital.data.remote.dto

import com.google.gson.annotations.SerializedName

data class WeatherDto(
    @SerializedName("current")
    val current: CurrentDto
)
