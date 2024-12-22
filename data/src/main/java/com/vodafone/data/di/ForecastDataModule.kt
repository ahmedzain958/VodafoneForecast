package com.vodafone.data.di

import android.content.Context
import android.content.SharedPreferences
import com.vodafone.data.local.LocalDataSource
import com.vodafone.data.remote.ForecastApiService
import com.vodafone.data.remote.RemoteDataSource
import com.vodafone.data.repository.ForecastRepositoryImpl
import com.vodafone.domain.repository.ForecastRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ForecastDataModule {
    @Provides
    @Singleton
    fun provideCitiesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    fun provideApiService(retrofit: Retrofit): ForecastApiService {
        return retrofit.create(ForecastApiService::class.java)
    }

    @Provides
    fun provideRemoteDataSource(
        apiService: ForecastApiService,
    ): RemoteDataSource =
        RemoteDataSource(apiService)

    @Provides
    fun provideLocalDataSource(
        sharedPreferences: SharedPreferences,
    ): LocalDataSource =
        LocalDataSource(sharedPreferences)

    @Provides
    fun provideForecastRepository(
        localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource,
    ): ForecastRepository {
        return ForecastRepositoryImpl(remoteDataSource, localDataSource)
    }


    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("weather_prefs", Context.MODE_PRIVATE)
    }


}

object Constants {
    const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
}