package com.vodafone.data.remote.model


import com.google.gson.annotations.SerializedName

data class Day(
    @SerializedName("dt")
    val dt: Int?,
    @SerializedName("weather")
    val weather: List<ForecastWeather?>?,
    @SerializedName("temp")
    val temp: Temp,
)

data class ForecastResponse(
    @SerializedName("list")
    val list: List<Day?>?,
)

data class ForecastWeather(
    @SerializedName("main")
    val main: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("icon")
    val icon: String?,
)

data class Temp(
    @SerializedName("day")
    val day: Double?
)