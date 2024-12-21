package com.vodafone.data.di

import com.vodafone.data.di.Constants.BASE_URL
import com.vodafone.data.remote.ForecastApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun provideOkHttpClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
}

val networkModule = module {
    single { provideOkHttpClient() }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<ForecastApiService> {
        get<Retrofit>().create(ForecastApiService::class.java)
    }
}

object Constants {
    const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
}