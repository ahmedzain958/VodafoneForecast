package com.vodafone.data.di

import com.vodafone.data.local.LocalDataSource
import com.vodafone.data.remote.RemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {

    single { LocalDataSource(get()) }

    single { RemoteDataSource(get()) }
}
