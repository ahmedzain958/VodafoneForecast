package com.baims.dailyforecast.domain.model

data class WeatherEntity(
    val temperature: Double,
    val humidity: Int,
    val dateTime: String,
    val description: String,
    val cityName: String,
)