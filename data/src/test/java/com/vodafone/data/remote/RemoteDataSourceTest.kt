package com.vodafone.data.remote

import com.vodafone.data.remote.model.Day
import com.vodafone.data.remote.model.ForecastResponse
import com.vodafone.data.remote.model.ForecastWeather
import com.vodafone.data.remote.model.Main
import com.vodafone.data.remote.model.Temp
import com.vodafone.data.remote.model.Weather
import com.vodafone.data.remote.model.WeatherResponse
import org.junit.Assert.*

import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class RemoteDataSourceTest {

    private lateinit var forecastApiService: ForecastApiService
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setUp() {
        forecastApiService = mockk()
        remoteDataSource = RemoteDataSource(forecastApiService)
    }

    @Test
    fun `test getWeather returns correct response`() = runBlocking {
        // Prepare mock response
        val mockWeatherResponse = WeatherResponse(
            weather = listOf(Weather(main = "Clear", icon = "01d")),
            main = Main(temp = 25.5),
            dt = 1628160000
        )

        coEvery { forecastApiService.getWeather("Cairo") } returns mockWeatherResponse

        val response = remoteDataSource.getWeather("Cairo")

        assertEquals(25.5, response.main?.temp)
        assertEquals("Clear", response.weather?.get(0)?.main)
        assertEquals("01d", response.weather?.get(0)?.icon)
        assertEquals(1628160000, response.dt)

        coVerify { forecastApiService.getWeather("Cairo") }
    }

    @Test
    fun `test getForecast returns correct response`() = runBlocking {
        // Prepare mock response
        val mockForecastResponse = ForecastResponse(
            list = listOf(
                Day(
                    dt = 1628160000,
                    weather = listOf(ForecastWeather(main = "Clear", description = "clear sky", icon = "01d")),
                    temp = Temp(day = 25.5)
                )
            )
        )

        coEvery { forecastApiService.getWeeklyForecast("Cairo") } returns mockForecastResponse

        val response = remoteDataSource.getForecast("Cairo")

        val day = response.list?.get(0)
        assertEquals(1628160000, day?.dt)
        assertEquals(25.5, day?.temp?.day)
        assertEquals("Clear", day?.weather?.get(0)?.main)

        coVerify { forecastApiService.getWeeklyForecast("Cairo") }
    }
}
