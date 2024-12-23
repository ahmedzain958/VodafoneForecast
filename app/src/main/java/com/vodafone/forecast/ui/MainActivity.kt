package com.vodafone.forecast.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import com.vodafone.forecast.ui.theme.VodafoneForecastTheme
import com.vodafone.weather.ForecastScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VodafoneForecastTheme {
               ForecastScreen(
                    cityInputViewModel
                    = hiltViewModel(),
                    forecastListViewModel
                    = hiltViewModel(),
                )
            }
        }
    }
}