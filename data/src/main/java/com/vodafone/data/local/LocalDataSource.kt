package com.vodafone.data.local

import android.content.SharedPreferences

class LocalDataSource(private val sharedPreferences: SharedPreferences) {

    companion object {
        private const val LAST_CITY_KEY = "last_city"
    }

    fun saveLastCity(cityName: String) {
        sharedPreferences.edit().putString(LAST_CITY_KEY, cityName).apply()
    }

    fun getLastCity(): String? {
        return sharedPreferences.getString(LAST_CITY_KEY, null)
    }
}
