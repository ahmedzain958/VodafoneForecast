package com.vodafone.data.remote.model
import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("weather")
    val weather: List<Weather?>?,
    @SerializedName("main")
    val main: Main?,
    @SerializedName("dt")
    val dt: Int?,
)

data class Main(
    @SerializedName("temp")
    val temp: Double?,
)
data class Weather(
    @SerializedName("main")
    val main: String?,
    @SerializedName("icon")
    val icon: String?
)