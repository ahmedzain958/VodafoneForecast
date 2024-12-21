package com.vodafone.domain.usecases

import com.vodafone.domain.model.ForecastData
import com.vodafone.domain.model.WeatherCondition
import com.vodafone.domain.repository.ForecastRepository
import javax.inject.Inject

class FetchForecastUseCase @Inject constructor(
    private val forecastRepository: ForecastRepository,
) {
    suspend operator fun invoke(cityName: String): ForecastData {
        return ForecastData(0.0, WeatherCondition.RAIN)
    }
}
