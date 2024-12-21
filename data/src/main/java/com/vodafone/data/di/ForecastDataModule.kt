package com.vodafone.data.di

import android.content.Context
import com.baims.dailyforecast.data.remote.ForecastApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ForecastDataModule {

    @Provides
    @Singleton
    fun provideCitiesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
    }


    @Provides
    fun provideApiService(retrofit: Retrofit): ForecastApiService =
        retrofit.create(ForecastApiService::class.java)

    @Provides
    fun provideForecastRepository(
        @ApplicationContext context: Context,
        apiService: ForecastApiService,
    ): ForecastRepository =
        ForecastRepositoryImpl(context, apiService, weatherDao, dispatcher)

}

object Constants {
    const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
}