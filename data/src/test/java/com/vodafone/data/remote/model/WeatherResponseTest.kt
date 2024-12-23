package com.vodafone.data.remote.model

import org.junit.Assert.*
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import io.mockk.clearAllMocks
import org.junit.After
import org.junit.Test


class WeatherResponseTest {

    private val gson = Gson()

    @Test
    fun `test deserialization of WeatherResponse`() {
        val json = """
            {
                "weather": [
                    {
                        "main": "Clear",
                        "icon": "01d"
                    }
                ],
                "main": {
                    "temp": 23.5
                },
                "dt": 1628160000
            }
        """
        val response = gson.fromJson(json, WeatherResponse::class.java)

        assertEquals(1628160000, response.dt)
        assertEquals(23.5, response.main?.temp)
        assertEquals("Clear", response.weather?.get(0)?.main)
        assertEquals("01d", response.weather?.get(0)?.icon)
    }

    @Test
    fun `test serialization of WeatherResponse`() {
        val weather = Weather(main = "Clear", icon = "01d")
        val main = Main(temp = 23.5)
        val response = WeatherResponse(
            weather = listOf(weather),
            main = main,
            dt = 1628160000
        )

        val json = gson.toJson(response)

        val expectedJson = """
            {"weather":[{"main":"Clear","icon":"01d"}],"main":{"temp":23.5},"dt":1628160000}
        """.trimIndent()

        assertEquals(expectedJson, json)
    }

    @Test
    fun `test WeatherResponse getters`() {
        val weather = Weather(main = "Clear", icon = "01d")
        val main = Main(temp = 23.5)
        val response = WeatherResponse(
            weather = listOf(weather),
            main = main,
            dt = 1628160000
        )

        assertEquals(1628160000, response.dt)
        assertEquals(23.5, response.main?.temp)
        assertEquals("Clear", response.weather?.get(0)?.main)
        assertEquals("01d", response.weather?.get(0)?.icon)
    }
    @After
    fun tearDown() {
        clearAllMocks()
    }
}
