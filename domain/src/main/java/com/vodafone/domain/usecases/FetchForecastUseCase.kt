package com.vodafone.domain.usecases

import com.vodafone.domain.model.ForecastData
import com.vodafone.domain.repository.ForecastRepository
import javax.inject.Inject

class FetchForecastUseCase @Inject constructor(
    private val forecastRepository: ForecastRepository,
) {
    suspend operator fun invoke(cityName: String): ForecastData {
        val forecastData = forecastRepository.getWeather(cityName)
        return forecastData
    }

    suspend fun getWeeklyForecast(cityName: String): List<ForecastData> {
        return forecastRepository.getWeeklyForecast(cityName)
    }
}
