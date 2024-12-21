package com.vodafone.domain.usecases

import com.vodafone.domain.repository.ForecastRepository
import javax.inject.Inject

class FetchForecastUseCase @Inject constructor(
    private val forecastRepository: ForecastRepository,
) {
    suspend operator fun invoke(cityName: String): ForecastData {
        val currentForecast = forecastRepository.getForecastData(cityName)
        return currentForecast
    }
}
