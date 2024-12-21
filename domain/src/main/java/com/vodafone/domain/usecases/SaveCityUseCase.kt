package com.vodafone.domain.usecases

import com.vodafone.domain.repository.ForecastRepository
import javax.inject.Inject

class SaveCityUseCase @Inject constructor(
) {
    operator fun invoke(cityName: String) {
        val x= cityName
    }
}
