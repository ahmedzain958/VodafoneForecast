package com.vodafone.domain.usecases

import com.vodafone.domain.repository.ForecastRepository

class GetLastCityUseCase (
    private val forecastRepository: ForecastRepository,
) {
     operator fun invoke(): String? {
        return forecastRepository.getLastCity()
    }
}
