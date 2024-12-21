package com.vodafone.data

import com.vodafone.data.remote.model.WeatherResponse
import com.vodafone.domain.model.ForecastData
import com.vodafone.domain.model.WeatherCondition

fun WeatherResponse.toDomainModel(): ForecastData {
    val condition = when (weather?.firstOrNull()?.main?.uppercase()) {
        "CLOUDS" -> WeatherCondition.CLOUDS
        "RAIN" -> WeatherCondition.RAIN
        "CLEAR" -> WeatherCondition.CLEAR
        else -> WeatherCondition.UNKNOWN
    }

    return ForecastData(
        temperature = main?.temp ?: 0.0,
        condition = condition
    )
}
