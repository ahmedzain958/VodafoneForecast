package com.vodafone.forecast

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vodafone.forecast.ui.CityInputScreen
import com.vodafone.forecast.ui.ForecastListScreen
import com.vodafone.forecast.viewmodels.CityInputViewModel
import com.vodafone.forecast.viewmodels.ForecastListIntent
import com.vodafone.forecast.viewmodels.ForecastListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForecastScreen2(
    cityInputViewModel: CityInputViewModel,
    forecastListViewModel: ForecastListViewModel,
) {
    val lastCity by cityInputViewModel.lastCity.collectAsState()
    var previousCity by remember { mutableStateOf<String?>(null) }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Forecast App") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            CityInputScreen(cityInputViewModel, onClick = { cityName ->
                if (previousCity != cityName) { // Only call when city changes
                    previousCity = cityName
                    cityInputViewModel.fetchCurrentWeather(cityName)
                    forecastListViewModel.handleIntent(ForecastListIntent.LoadForecast(cityName))
                }})

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "7-Day Forecast:",
                style = MaterialTheme.typography.headlineSmall
            )
            lastCity?.let {
                ForecastListScreen(forecastListViewModel, it)
            }
        }
    }
}