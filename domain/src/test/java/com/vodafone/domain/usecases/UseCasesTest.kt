package com.vodafone.domain.usecases

import com.vodafone.domain.model.ForecastData
import com.vodafone.domain.model.WeatherCondition
import com.vodafone.domain.repository.ForecastRepository
import org.junit.Assert.*
import io.mockk.*
import kelvinToCelsius
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test


class UseCasesTest {

    private lateinit var forecastRepository: ForecastRepository
    private lateinit var fetchForecastUseCase: FetchForecastUseCase
    private lateinit var getLastCityUseCase: GetLastCityUseCase
    private lateinit var saveCityUseCase: SaveCityUseCase

    @Before
    fun setUp() {
        forecastRepository = mockk()
        fetchForecastUseCase = FetchForecastUseCase(forecastRepository)
        getLastCityUseCase = GetLastCityUseCase(forecastRepository)
        saveCityUseCase = SaveCityUseCase(forecastRepository)
    }

    @Test
    fun `test FetchForecastUseCase returns correct ForecastData`() = runBlocking {
        val mockForecastData = ForecastData(
            date = "Mon, Dec 24",
            temperature = 25.0,
            condition = WeatherCondition.CLEAR
        )
        coEvery { forecastRepository.getWeather("Cairo") } returns mockForecastData

        val forecastData = fetchForecastUseCase("Cairo")

        assertEquals("Mon, Dec 24", forecastData.date)
        assertEquals(25.0.toString(), forecastData.temperature.toString())
        assertEquals(WeatherCondition.CLEAR, forecastData.condition)

        coVerify { forecastRepository.getWeather("Cairo") }
    }

    @Test
    fun `test FetchForecastUseCase converts Kelvin to Celsius correctly`() {
        val kelvinTemperature = 300.0
        val celsiusTemperature = kelvinToCelsius(kelvinTemperature)

        assertEquals(26.85, celsiusTemperature, 0.01)
    }

    @Test
    fun `test GetLastCityUseCase returns correct city`() {
        every { forecastRepository.getLastCity() } returns "Cairo"

        val city = getLastCityUseCase()

        assertEquals("Cairo", city)

        verify { forecastRepository.getLastCity() }
    }

    @Test
    fun `test GetLastCityUseCase returns null if no city is saved`() {
        every { forecastRepository.getLastCity() } returns null

        val city = getLastCityUseCase()

        assertNull(city)

        verify { forecastRepository.getLastCity() }
    }

    @Test
    fun `test SaveCityUseCase saves the city correctly`() {
        every { forecastRepository.saveLastCity("Cairo") } just runs
        saveCityUseCase("Cairo")

        verify { forecastRepository.saveLastCity("Cairo") }
    }

    @Test
    fun `test FetchForecastUseCase returns weekly forecast correctly`() = runBlocking {
        val mockWeeklyForecast = listOf(
            ForecastData(
                date = "Mon, Dec 24",
                temperature = 25.0,
                condition = WeatherCondition.CLEAR
            ),
            ForecastData(
                date = "Tue, Dec 25",
                temperature = 22.0,
                condition = WeatherCondition.CLOUDS
            )
        )
        coEvery { forecastRepository.getWeeklyForecast("Cairo") } returns mockWeeklyForecast

        val weeklyForecast = fetchForecastUseCase.getWeeklyForecast("Cairo")

        assertEquals(2, weeklyForecast.size)
        assertEquals("Mon, Dec 24", weeklyForecast[0].date)
        assertEquals(25.0.toString(), weeklyForecast[0].temperature.toString())
        assertEquals(WeatherCondition.CLEAR, weeklyForecast[0].condition)

        coVerify { forecastRepository.getWeeklyForecast("Cairo") }
    }
    @After
    fun clear() {
        clearAllMocks()
    }
}
