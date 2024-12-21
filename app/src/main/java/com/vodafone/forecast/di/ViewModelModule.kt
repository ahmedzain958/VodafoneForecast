package com.vodafone.forecast.di
import com.vodafone.forecast.ForecastViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        ForecastViewModel(get(), get(), get())
    }
}