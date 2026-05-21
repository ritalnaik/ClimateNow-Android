package com.rital.data.remote.dto

import com.google.gson.annotations.SerializedName

data class WeatherResponseDto(

    @SerializedName("current")
    val current: CurrentDto,

    @SerializedName("daily")
    val daily: DailyDto
)