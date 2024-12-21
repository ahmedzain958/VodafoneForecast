package com.vodafone.data.remote

import com.vodafone.data.remote.model.ForecastResponse
import com.vodafone.data.remote.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApiService {
    @GET("forecast?appid=816ad9e0c3cb984afbe68550fe4f0a06")
    suspend fun getWeather(
        @Query("q") cityName: String
    ): WeatherResponse

    @GET("forecast/daily?appid=816ad9e0c3cb984afbe68550fe4f0a06&units=metric&cnt=7")
    suspend fun getWeeklyForecast(
        @Query("q") cityName: String
    ): ForecastResponse
}