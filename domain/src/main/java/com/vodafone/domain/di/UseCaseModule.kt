package com.vodafone.domain.di

import com.vodafone.domain.usecases.FetchForecastUseCase
import com.vodafone.domain.usecases.GetLastCityUseCase
import com.vodafone.domain.usecases.SaveCityUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { FetchForecastUseCase(get()) }
    factory { GetLastCityUseCase(get()) }
    factory { SaveCityUseCase(get()) }
}