package com.vodafone.forecast.di

import com.vodafone.forecast.ForecastViewModel
import com.vodafone.forecast.viewmodels.CityInputViewModel
import com.vodafone.forecast.viewmodels.ForecastListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        ForecastViewModel(get(), get(), get())
    }
    viewModel {
        CityInputViewModel(get(), get(), get())
    }
    viewModel {
        ForecastListViewModel(get())
    }
}