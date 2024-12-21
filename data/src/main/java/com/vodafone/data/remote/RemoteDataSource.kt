package com.vodafone.data.remote

import com.vodafone.data.remote.model.ForecastResponse
import com.vodafone.data.remote.model.WeatherResponse

class RemoteDataSource(private val forecastApiService: ForecastApiService) {
    suspend fun getWeather(cityName: String): WeatherResponse {
        return forecastApiService.getWeather(cityName)
    }

    suspend fun getForecast(cityName: String): ForecastResponse {
        return forecastApiService.getWeeklyForecast(cityName)
    }
}