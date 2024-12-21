package com.vodafone.data.di

import com.vodafone.data.repository.ForecastRepositoryImpl
import com.vodafone.domain.repository.ForecastRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ForecastRepository> { ForecastRepositoryImpl(get(), get()) }
}