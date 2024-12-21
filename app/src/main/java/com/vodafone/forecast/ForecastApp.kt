package com.vodafone.forecast

import android.app.Application
import com.vodafone.data.di.dataModule
import com.vodafone.domain.di.domainModule
import com.vodafone.forecast.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ForecastApp : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ForecastApp)
            modules(listOf(
                presentationModule,
                dataModule,
                domainModule
            ))
        }
    }
}