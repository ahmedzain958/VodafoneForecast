package com.vodafone.data.remote.model
import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("weather")
    val weather: List<Weather?>?,
    @SerializedName("base")
    val base: String?,
    @SerializedName("main")
    val main: Main?,
    @SerializedName("visibility")
    val visibility: Int?,
    @SerializedName("dt")
    val dt: Int?,
    @SerializedName("sys")
    val sys: Sys?,
    @SerializedName("timezone")
    val timezone: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("cod")
    val cod: Int?
)

data class Main(
    @SerializedName("temp")
    val temp: Double?,
    @SerializedName("feels_like")
    val feelsLike: Double?,
    @SerializedName("temp_min")
    val tempMin: Double?,
    @SerializedName("temp_max")
    val tempMax: Double?,
    @SerializedName("pressure")
    val pressure: Int?,
    @SerializedName("humidity")
    val humidity: Int?,
    @SerializedName("sea_level")
    val seaLevel: Int?,
    @SerializedName("grnd_level")
    val grndLevel: Int?
)

data class Sys(
    @SerializedName("type")
    val type: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("sunrise")
    val sunrise: Int?,
    @SerializedName("sunset")
    val sunset: Int?
)
data class Weather(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("main")
    val main: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("icon")
    val icon: String?
)