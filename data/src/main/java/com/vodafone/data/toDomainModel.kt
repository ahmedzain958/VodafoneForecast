package com.vodafone.data

import com.vodafone.data.remote.model.ForecastResponse
import com.vodafone.data.remote.model.WeatherResponse
import com.vodafone.domain.model.ForecastData
import com.vodafone.domain.model.WeatherCondition
import java.text.SimpleDateFormat
import java.util.Locale

fun WeatherResponse.toDomainModel(): ForecastData {
    val condition = when (weather?.firstOrNull()?.main?.uppercase()) {
        "CLOUDS" -> WeatherCondition.CLOUDS
        "RAIN" -> WeatherCondition.RAIN
        "CLEAR" -> WeatherCondition.CLEAR
        else -> WeatherCondition.UNKNOWN
    }

    return ForecastData(
        temperature = main?.temp ?: 0.0,
        condition = condition
    )
}

fun String.toWeatherCondition(): WeatherCondition {
    return when (this.uppercase()) {
        "CLOUDS" -> WeatherCondition.CLOUDS
        "RAIN" -> WeatherCondition.RAIN
        "CLEAR" -> WeatherCondition.CLEAR
        else -> WeatherCondition.UNKNOWN
    }
}

fun ForecastResponse.toDomainModel(): List<ForecastData> {
    return this.list?.mapNotNull { day ->
        day?.let {
            ForecastData(
                date = it.dt?.toFormattedDate() ?: "Unknown Date",
                temperature = it.temp?.day ?: 0.0,
                condition = it.weather?.firstOrNull()?.main?.toWeatherCondition()
                    ?: WeatherCondition.UNKNOWN
            )
        }
    } ?: emptyList()
}

fun Int.toFormattedDate(): String {
    val sdf = SimpleDateFormat("EEE, MMM d", Locale.getDefault())
    return sdf.format(this * 1000L)
}