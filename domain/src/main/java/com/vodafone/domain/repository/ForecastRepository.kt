package com.vodafone.domain.repository

import com.vodafone.domain.model.ForecastData

interface ForecastRepository {
    suspend fun getWeather(cityName: String): ForecastData
    suspend fun getWeeklyForecast(cityName: String): List<ForecastData>

    fun saveLastCity(cityName: String)
    fun getLastCity(): String?
}