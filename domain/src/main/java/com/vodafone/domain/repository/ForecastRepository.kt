package com.vodafone.domain.repository

interface ForecastRepository {
    suspend fun getForecastData(cityName: String)
            : ForecastData
}