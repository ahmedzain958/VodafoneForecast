package com.vodafone.data.repository

import com.vodafone.data.remote.ForecastApiService
import com.vodafone.data.remote.RemoteDataSource
import com.vodafone.data.toDomainModel
import com.vodafone.domain.model.ForecastData
import com.vodafone.domain.repository.ForecastRepository
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val forecastApiService: ForecastApiService,
) : ForecastRepository {
    override suspend fun getForecastData(cityName: String): ForecastData {
        return forecastApiService.getWeather(cityName).toDomainModel()
    }
  /*  override fun saveLastCity(cityName: String) {
        ForecastRepository.saveLastCity(cityName)
    }

    override fun getLastCity(): String? {
        return ForecastRepository.getLastCity()
    }*/
}