package com.vodafone.forecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.vodafone.forecast.ui.theme.VodafoneForecastTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VodafoneForecastTheme {
                ForecastScreen2(
                    cityInputViewModel
                    = koinViewModel(),
                    forecastListViewModel
                    = koinViewModel(),
                )
//                ForecastScreen(viewModel = koinViewModel())

            }
        }
    }
}