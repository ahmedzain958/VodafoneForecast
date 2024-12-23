package com.vodafone.data.remote.model

import com.google.gson.Gson
import io.mockk.clearAllMocks
import org.junit.After
import org.junit.Assert.*

import org.junit.Test

class ForecastWeatherTest {

    private val gson = Gson()

    @Test
    fun `test Day serialization and deserialization`() {
        val temp = Temp(day = 25.5)
        val forecastWeather = ForecastWeather(main = "Cloudy", description = "Partly cloudy", icon = "01d")
        val day = Day(
            dt = 1628160000,
            weather = listOf(forecastWeather),
            temp = temp
        )

        val json = gson.toJson(day)
        val deserializedDay = gson.fromJson(json, Day::class.java)

        assertEquals(day.dt, deserializedDay.dt)
        assertEquals(day.weather?.get(0)?.main, deserializedDay.weather?.get(0)?.main)
        assertEquals(day.temp.day, deserializedDay.temp.day)
    }

    @Test
    fun `test ForecastResponse serialization and deserialization`() {
        val temp = Temp(day = 28.3)
        val forecastWeather = ForecastWeather(main = "Rainy", description = "Light rain", icon = "09d")
        val day = Day(
            dt = 1628250000,
            weather = listOf(forecastWeather),
            temp = temp
        )
        val forecastResponse = ForecastResponse(
            list = listOf(day)
        )

        val json = gson.toJson(forecastResponse)
        val deserializedResponse = gson.fromJson(json, ForecastResponse::class.java)

        assertEquals(forecastResponse.list?.get(0)?.dt, deserializedResponse.list?.get(0)?.dt)
        assertEquals(
            forecastResponse.list?.get(0)?.weather?.get(0)?.main,
            deserializedResponse.list?.get(0)?.weather?.get(0)?.main
        )
        assertEquals(forecastResponse.list?.get(0)?.temp?.day, deserializedResponse.list?.get(0)?.temp?.day)
    }

    @Test
    fun `test Temp serialization and deserialization`() {
        val temp = Temp(day = 22.5)

        val json = gson.toJson(temp)
        val deserializedTemp = gson.fromJson(json, Temp::class.java)

        assertEquals(temp.day, deserializedTemp.day)
    }

    @Test
    fun `test ForecastWeather serialization and deserialization`() {
        val forecastWeather = ForecastWeather(main = "Sunny", description = "Clear sky", icon = "01d")

        val json = gson.toJson(forecastWeather)
        val deserializedWeather = gson.fromJson(json, ForecastWeather::class.java)

        assertEquals(forecastWeather.main, deserializedWeather.main)
        assertEquals(forecastWeather.description, deserializedWeather.description)
        assertEquals(forecastWeather.icon, deserializedWeather.icon)
    }

    @Test
    fun `test Day getters`() {
        val temp = Temp(day = 30.0)
        val forecastWeather = ForecastWeather(
            main = "Sunny",
            description = "Clear sky",
            icon = "01d"
        )
        val day = Day(
            dt = 1628160000,
            weather = listOf(forecastWeather),
            temp = temp
        )

        assertEquals(1628160000, day.dt)
        assertEquals("Sunny", day.weather?.get(0)?.main)
        assertEquals("Clear sky", day.weather?.get(0)?.description)
        assertEquals("01d", day.weather?.get(0)?.icon)
        assertEquals(30.0, day.temp.day)
    }

    @Test
    fun `test Temp getter`() {
        val temp = Temp(day = 22.5)
        assertEquals(22.5, temp.day)
    }

    @Test
    fun `test ForecastWeather getters`() {
        val forecastWeather = ForecastWeather(
            main = "Rainy",
            description = "Light rain",
            icon = "09d"
        )

        assertEquals("Rainy", forecastWeather.main)
        assertEquals("Light rain", forecastWeather.description)
        assertEquals("09d", forecastWeather.icon)
    }

    @Test
    fun `test ForecastResponse getters`() {
        val temp = Temp(day = 28.3)
        val forecastWeather = ForecastWeather(main = "Rainy", description = "Light rain", icon = "09d")
        val day = Day(
            dt = 1628250000,
            weather = listOf(forecastWeather),
            temp = temp
        )
        val forecastResponse = ForecastResponse(
            list = listOf(day)
        )

        assertEquals(1628250000, forecastResponse.list?.get(0)?.dt)
        assertEquals("Rainy", forecastResponse.list?.get(0)?.weather?.get(0)?.main)
        assertEquals(28.3, forecastResponse.list?.get(0)?.temp?.day)
    }
    @After
    fun tearDown() {
        clearAllMocks()
    }
}