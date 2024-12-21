package com.vodafone.domain.repository

import com.baims.dailyforecast.domain.model.WeatherEntity

interface ForecastRepository {
    suspend fun getForecastList(cityName: String)
            : List<WeatherEntity>
}