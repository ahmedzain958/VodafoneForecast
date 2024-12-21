package com.vodafone.data.remote.model


import com.google.gson.annotations.SerializedName

data class Day(
    @SerializedName("dt")
    val dt: Int?,
    @SerializedName("sunrise")
    val sunrise: Int?,
    @SerializedName("sunset")
    val sunset: Int?,
    @SerializedName("temp")
    val temp: Temp?,
    @SerializedName("pressure")
    val pressure: Int?,
    @SerializedName("humidity")
    val humidity: Int?,
    @SerializedName("weather")
    val weather: List<ForecastWeather?>?,
    @SerializedName("speed")
    val speed: Double?,
    @SerializedName("deg")
    val deg: Int?,
    @SerializedName("gust")
    val gust: Double?,
    @SerializedName("clouds")
    val clouds: Int?,
    @SerializedName("pop")
    val pop: Int?
)

data class City(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("population")
    val population: Int?,
    @SerializedName("timezone")
    val timezone: Int?
)
data class ForecastResponse(
    @SerializedName("city")
    val city: City?,
    @SerializedName("list")
    val list: List<Day?>?
)
data class ForecastWeather(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("main")
    val main: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("icon")
    val icon: String?
)
data class Temp(
    @SerializedName("day")
    val day: Double?,
    @SerializedName("min")
    val min: Double?,
    @SerializedName("max")
    val max: Double?,
    @SerializedName("night")
    val night: Double?,
    @SerializedName("eve")
    val eve: Double?,
    @SerializedName("morn")
    val morn: Double?
)