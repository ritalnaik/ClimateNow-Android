package com.rital.data.remote.dto

import com.google.gson.annotations.SerializedName

data class DailyDto(

    @SerializedName("temperature_2m_max")
    val maxTemps: List<Double>,

    @SerializedName("temperature_2m_min")
    val minTemps: List<Double>,

    @SerializedName("uv_index_max")
    val uvIndexes: List<Double>,

    @SerializedName("sunrise")
    val sunrise: List<String>,

    @SerializedName("sunset")
    val sunset: List<String>
)