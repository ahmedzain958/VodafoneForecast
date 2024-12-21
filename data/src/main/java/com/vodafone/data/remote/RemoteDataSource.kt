package com.vodafone.data.remote

import com.vodafone.data.remote.model.WeatherResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val forecastApiService: ForecastApiService) {
    suspend fun getWeather(cityName: String): WeatherResponse {
        return forecastApiService.getWeather(cityName)
    }
}