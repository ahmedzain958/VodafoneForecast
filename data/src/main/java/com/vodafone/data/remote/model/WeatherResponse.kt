package com.baims.dailyforecast.data.remote.model


import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("list")
    val dailyWeatherItems: List<DailyWeatherItem>,
)
data class DailyWeatherItem(
    @SerializedName("dt_txt") val dtTxt: String?,
    @SerializedName("main") val main: Main?,
    @SerializedName("visibility") val visibility: Int?,
    @SerializedName("weather") val weather: List<Weather>?,
)
data class Main(
    @SerializedName("humidity")
    val humidity: Int?,
    @SerializedName("temp")
    val temp: Double?
)
data class Weather(
    @SerializedName("description")
    val description: String?,
)