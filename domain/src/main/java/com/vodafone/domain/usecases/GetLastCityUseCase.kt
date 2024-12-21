package com.vodafone.domain.usecases

import com.vodafone.domain.repository.ForecastRepository
import javax.inject.Inject
import javax.inject.Singleton

class GetLastCityUseCase @Inject constructor(
) {
     operator fun invoke(): String? {
        return " forecastRepository.getForecastData()"
    }
}
