package com.vodafone.data.repository

import com.vodafone.data.local.LocalDataSource
import com.vodafone.data.remote.RemoteDataSource
import com.vodafone.data.toDomainModel
import com.vodafone.domain.model.ForecastData
import com.vodafone.domain.repository.ForecastRepository


class ForecastRepositoryImpl (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : ForecastRepository {
    override suspend fun getWeather(cityName: String): ForecastData {
        return remoteDataSource.getWeather(cityName).toDomainModel()
    }

    override suspend fun getWeeklyForecast(cityName: String): List<ForecastData> {
        return remoteDataSource.getForecast(cityName).toDomainModel()
    }

    override fun saveLastCity(cityName: String) {
        localDataSource.saveLastCity(cityName)
    }

    override fun getLastCity(): String? {
        return localDataSource.getLastCity()
    }
}