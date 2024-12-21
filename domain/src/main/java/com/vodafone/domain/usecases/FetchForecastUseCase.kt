package com.vodafone.domain.usecases

import com.vodafone.domain.model.ForecastData
import com.vodafone.domain.repository.ForecastRepository

class FetchForecastUseCase (
    private val forecastRepository: ForecastRepository,
) {
    suspend operator fun invoke(cityName: String): ForecastData {
        return forecastRepository.getForecastData(cityName)
    }
    suspend fun getWeeklyForecast(cityName: String): List<ForecastData> {
        return forecastRepository.getWeeklyForecast(cityName)
    }
}
