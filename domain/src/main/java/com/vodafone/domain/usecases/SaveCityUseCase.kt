package com.vodafone.domain.usecases

import com.vodafone.domain.repository.ForecastRepository


class SaveCityUseCase(
    private val forecastRepository: ForecastRepository,
) {
    operator fun invoke(cityName: String) {
        forecastRepository.saveLastCity(cityName)
    }
}
