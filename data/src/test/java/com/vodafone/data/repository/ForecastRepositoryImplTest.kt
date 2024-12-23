package com.vodafone.data.repository

import com.vodafone.data.local.LocalDataSource
import com.vodafone.data.remote.RemoteDataSource
import com.vodafone.data.remote.model.Day
import com.vodafone.data.remote.model.ForecastResponse
import com.vodafone.data.remote.model.ForecastWeather
import com.vodafone.data.remote.model.Main
import com.vodafone.data.remote.model.Temp
import com.vodafone.data.remote.model.Weather
import com.vodafone.data.remote.model.WeatherResponse
import com.vodafone.domain.model.WeatherCondition
import org.junit.Assert.*

import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ForecastRepositoryImplTest {

    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var localDataSource: LocalDataSource
    private lateinit var forecastRepository: ForecastRepositoryImpl

    @Before
    fun setUp() {
        remoteDataSource = mockk()
        localDataSource = mockk()
        forecastRepository = ForecastRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun `test getWeather returns correct ForecastData`() = runBlocking {
        val mockWeatherResponse = WeatherResponse(
            weather = listOf(Weather(main = "Clear", icon = "01d")),
            main = Main(temp = 25.5),
            dt = 1628160000
        )
        coEvery { remoteDataSource.getWeather("Cairo") } returns mockWeatherResponse

        val forecastData = forecastRepository.getWeather("Cairo")

        assertEquals("Thu, Aug 5", forecastData.date)  // Assuming the date is correctly formatted
        assertEquals(25.5.toString(), forecastData.temperature.toString())
        assertEquals(WeatherCondition.CLEAR, forecastData.condition)

        coVerify { remoteDataSource.getWeather("Cairo") }
    }

    @Test
    fun `test getWeeklyForecast returns correct list of ForecastData`() = runBlocking {
        val mockForecastResponse = ForecastResponse(
            list = listOf(
                Day(
                    dt = 1628160000,
                    weather = listOf(ForecastWeather(main = "Clear", description = "clear sky", icon = "01d")),
                    temp = Temp(day = 25.5)
                )
            )
        )
        coEvery { remoteDataSource.getForecast("Cairo") } returns mockForecastResponse

        val forecastDataList = forecastRepository.getWeeklyForecast("Cairo")

        assertEquals(1, forecastDataList.size)
        assertEquals("Thu, Aug 5", forecastDataList[0].date)  // Assuming the date is correctly formatted
        assertEquals(25.5.toString(), forecastDataList[0].temperature.toString())
        assertEquals(WeatherCondition.CLEAR, forecastDataList[0].condition)

        coVerify { remoteDataSource.getForecast("Cairo") }
    }

    @Test
    fun `test saveLastCity saves city correctly`() {
        every { forecastRepository.saveLastCity(any()) } just runs
        forecastRepository.saveLastCity("Cairo")

        verify { localDataSource.saveLastCity("Cairo") }
    }

    @Test
    fun `test getLastCity returns last city correctly`() {
        every { localDataSource.getLastCity() } returns "Cairo"

        val cityName = forecastRepository.getLastCity()

        assertEquals("Cairo", cityName)
        verify { localDataSource.getLastCity() }
    }

    @Test
    fun `test getLastCity returns null if no city is saved`() {
        every { localDataSource.getLastCity() } returns null

        val cityName = forecastRepository.getLastCity()

        assertNull(cityName)

        verify { localDataSource.getLastCity() }
    }
}
