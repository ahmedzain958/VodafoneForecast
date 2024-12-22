package com.vodafone.domain.usecases

import com.vodafone.domain.repository.ForecastRepository
import javax.inject.Inject


class SaveCityUseCase @Inject constructor(
    private val forecastRepository: ForecastRepository,
) {
    operator fun invoke(cityName: String) {
        forecastRepository.saveLastCity(cityName)
    }
}
