package com.rital.core.ui.extensions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cloud
import androidx.compose.material.icons.rounded.Thunderstorm
import androidx.compose.material.icons.rounded.WaterDrop
import androidx.compose.material.icons.rounded.WbSunny
import androidx.compose.ui.graphics.vector.ImageVector
import com.rital.domain.enums.WeatherCondition

//todo: handle all the other condition and optimizes rhis as per the code
fun WeatherCondition.toWeatherIcon(): ImageVector {

    return when (this) {

        WeatherCondition.CLEAR_SKY ->
            Icons.Rounded.WbSunny

        WeatherCondition.PARTLY_CLOUDY ->
            Icons.Rounded.Cloud

        WeatherCondition.MODERATE_RAIN ->
            Icons.Rounded.WaterDrop

        WeatherCondition.THUNDERSTORM ->
            Icons.Rounded.Thunderstorm
        else ->
            Icons.Rounded.Cloud
    }
}