package com.vodafone.domain.usecases

import com.vodafone.domain.repository.ForecastRepository
import javax.inject.Inject

class GetLastCityUseCase @Inject constructor(
    private val forecastRepository: ForecastRepository,
) {
     operator fun invoke(): String? {
        return forecastRepository.getLastCity()
    }
}
