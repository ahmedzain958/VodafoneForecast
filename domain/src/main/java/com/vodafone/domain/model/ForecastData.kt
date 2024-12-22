package com.vodafone.domain.model

data class ForecastData(
    val date: String,
    val temperature: Double,
    val condition: WeatherCondition
)

enum class WeatherCondition {
    CLOUDS,
    RAIN,
    CLEAR,
    SNOW
}
