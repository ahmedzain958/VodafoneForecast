package com.vodafone.weather

import com.vodafone.domain.model.ForecastData

sealed class ForecastState {
    data object Loading : ForecastState()
    data class Success(val currentWeather: ForecastData, val forecastList: List<ForecastData>) :
        ForecastState()

    data class Error(val message: String) : ForecastState()
}

