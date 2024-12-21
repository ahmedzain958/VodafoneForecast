package com.vodafone.domain.repository

import com.vodafone.domain.model.ForecastData

interface ForecastRepository {
    suspend fun getForecastData(cityName: String): ForecastData
    /*fun saveLastCity(cityName: String)
    fun getLastCity(): String?*/
}