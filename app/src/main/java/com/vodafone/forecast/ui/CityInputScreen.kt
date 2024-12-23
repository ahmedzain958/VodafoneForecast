package com.vodafone.forecast.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vodafone.forecast.viewmodels.CityInputViewModel

@Composable
fun CityInputScreen(viewModel: CityInputViewModel, onClick: (String) -> Unit) {
    val cityInputState by viewModel.cityInputState.collectAsState()
    val lastCity by viewModel.lastCity.collectAsState()
    var cityName by remember(lastCity) { mutableStateOf(lastCity ?: "") }

    Column(
        modifier = Modifier
            .height(300.dp)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            value = cityName,
            onValueChange = { cityName = it },
            label = { Text("Enter city name") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                onClick(cityName) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Search")
        }

        when (cityInputState) {
            is ForecastState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            is ForecastState.Success -> {
                val state = cityInputState as ForecastState.Success
                Text(
                    text = "Current Weather:",
                    style = MaterialTheme.typography.headlineLarge
                )
                WeatherInfo(
                    temperature = state.currentWeather.temperature,
                    condition = state.currentWeather.condition.toString()
                )
            }

            is ForecastState.Error -> {
                Text(
                    text = (cityInputState as ForecastState.Error).message,
                    color = Color.Red
                )
            }
        }
    }
}
