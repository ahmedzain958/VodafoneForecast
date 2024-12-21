package com.vodafone.data.repository

import com.baims.dailyforecast.domain.model.WeatherEntity
import com.vodafone.domain.repository.ForecastRepository

class ForecastRepositoryImpl: ForecastRepository {
    override suspend fun getForecastList(cityName: String): List<WeatherEntity> {
        TODO("Not yet implemented")
    }
}