package com.vodafone.forecast

sealed class ForecastState {
    data object Loading : ForecastState()
    data class Success(val forecast: ForecastData) : ForecastState()
    data class Error(val message: String) : ForecastState()
}
