package com.vodafone.domain.model

import io.mockk.clearAllMocks
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class ForecastDataTest {
    private lateinit var forecastData: ForecastData
    private lateinit var weatherCondition: WeatherCondition

    @Before
    fun setUp() {
        forecastData = ForecastData(
            date = "Sun, Dec 23",
            temperature = 28.5,
            condition = WeatherCondition.CLEAR
        )

        weatherCondition = WeatherCondition.CLEAR
    }

    @Test
    fun `test ForecastData getters`() {
        assertEquals("Sun, Dec 23", forecastData.date)
        assertEquals(28.5.toString(), forecastData.temperature.toString())
        assertEquals(WeatherCondition.CLEAR, forecastData.condition)
    }

    @Test
    fun `test ForecastData toString`() {
        val expectedString = "ForecastData(date=Sun, Dec 23, temperature=28.5, condition=CLEAR)"
        assertEquals(expectedString, forecastData.toString())
    }

    @Test
    fun `test WeatherCondition enum values`() {
        assertEquals(WeatherCondition.CLOUDS, WeatherCondition.valueOf("CLOUDS"))
        assertEquals(WeatherCondition.RAIN, WeatherCondition.valueOf("RAIN"))
        assertEquals(WeatherCondition.CLEAR, WeatherCondition.valueOf("CLEAR"))
        assertEquals(WeatherCondition.SNOW, WeatherCondition.valueOf("SNOW"))
    }

    @Test
    fun `test WeatherCondition name`() {
        assertEquals("CLEAR", weatherCondition.name)
        assertEquals("SNOW", WeatherCondition.SNOW.name)
    }

    @Test
    fun `test WeatherCondition toString`() {
        assertEquals("CLEAR", weatherCondition.toString())
    }

    @Test
    fun `test ForecastData copy function`() {
        val copiedData = forecastData.copy(date = "Mon, Dec 24")
        assertEquals("Mon, Dec 24", copiedData.date)
        assertEquals(forecastData.temperature.toString(), copiedData.temperature.toString())
        assertEquals(forecastData.condition, copiedData.condition)
    }

    @Test
    fun `test ForecastData equals`() {
        val anotherForecastData = ForecastData(
            date = "Sun, Dec 23",
            temperature = 28.5,
            condition = WeatherCondition.CLEAR
        )
        assertEquals(forecastData, anotherForecastData)

        val differentForecastData = ForecastData(
            date = "Mon, Dec 24",
            temperature = 30.0,
            condition = WeatherCondition.RAIN
        )
        assertNotNull(differentForecastData)
        assertNotEquals(forecastData, differentForecastData)
    }

    @After
    fun clear() {
        clearAllMocks()
    }
}