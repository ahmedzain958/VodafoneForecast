package com.vodafone.data.di

import android.content.Context
import android.content.SharedPreferences
import org.koin.dsl.module

val preferencesModule = module {

    single<SharedPreferences> {
        get<Context>().getSharedPreferences("forecast_prefs", Context.MODE_PRIVATE)
    }
}
