package com.rital.climatenow.navigation

import com.rital.core.utils.NavKeys

sealed class AppRoutes(
    val route: String
) {
    data object Home : AppRoutes("home")
//    data object Home : AppRoutes(NavKeys.HOME_ROUTE)
    data object Details : AppRoutes(
        "${NavKeys.DETAIL_SCREEN_ROUTE}/{${NavKeys.CITY_NAME}}/{${NavKeys.LATITUDE}}/{${NavKeys.LONGITUDE}}"
    ) {
        fun createRoute(
            city: String,
            lat: Double,
            lon: Double
        ): String {
            return "${NavKeys.DETAIL_SCREEN_ROUTE}/$city/$lat/$lon"
        }
    }
}