package com.vodafone.forecast.di

import org.koin.dsl.module

val presentationModule = module {
    includes(viewModelModule)
}