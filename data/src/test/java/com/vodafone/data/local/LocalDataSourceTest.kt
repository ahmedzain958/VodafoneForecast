package com.vodafone.data.local

import android.content.SharedPreferences
import androidx.test.runner.AndroidJUnit4
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocalDataSourceTest {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var localDataSource: LocalDataSource

    @Before
    fun setup() {
        sharedPreferences = mockk()
        editor = mockk()

        every { sharedPreferences.edit() } returns editor
        every { editor.putString(any(), any()) } returns editor
        every { editor.apply() } just runs
        every { editor.clear().apply() } just runs

        localDataSource = LocalDataSource(sharedPreferences)
    }

    @Test
    fun `saveLastCity should save city name to SharedPreferences`() {
        val cityName = "Cairo"

        localDataSource.saveLastCity(cityName)

        verify {
            editor.putString("last_city", cityName)
            editor.apply()
        }
    }

    @Test
    fun `getLastCity should return city name from SharedPreferences`() {
        val cityName = "Cairo"
        every { sharedPreferences.getString("last_city", null) } returns cityName

        val result = localDataSource.getLastCity()

        assertEquals(cityName, result)
        verify { sharedPreferences.getString("last_city", null) }
    }

    @Test
    fun `getLastCity should return null if no city is saved`() {
        every { sharedPreferences.getString("last_city", null) } returns null

        val result = localDataSource.getLastCity()

        assertEquals(null, result)
        verify { sharedPreferences.getString("last_city", null) }
    }
    @After
    fun tearDown() {
        editor.clear().apply()
        clearAllMocks()
    }
}
