package com.vodafone.forecast

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ForecastScreen(viewModel: ForecastViewModel, savedCity: String?) {
    val state by viewModel.forecastState.collectAsState()
    var cityName by remember { mutableStateOf(savedCity ?: "") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        TextField(
            value = cityName,
            onValueChange = { cityName = it },
            label = { Text("Enter city name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { viewModel.fetchForecast(cityName) }) {
            Text("Search")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (state) {
            is ForecastState.Loading -> CircularProgressIndicator()
            is ForecastState.Success -> {
                val forecast = (state as ForecastState.Success).data
                Text("Temperature: ${forecast.temperature}°C")
                Text("Condition: ${forecast.condition}")
            }

            is ForecastState.Error -> {
                Text("Error: ${(state as ForecastState.Error).message}", color = Color.Red)
            }
        }
    }
}


