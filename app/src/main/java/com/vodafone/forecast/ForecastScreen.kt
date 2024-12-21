package com.vodafone.forecast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
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

@Composable
fun ForecastScreen(viewModel: ForecastViewModel) {
    val forecastState by viewModel.forecastState.collectAsState()
    val lastCity by viewModel.lastCity.collectAsState()
    var cityName by remember { mutableStateOf(lastCity ?: "") }

    Scaffold(
        /*topBar = {
            AppB(title = { Text("Forecast App") })
        }*/
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // Search Input
            TextField(
                value = cityName,
                onValueChange = { cityName = it },
                label = { Text("Enter city name") },
                modifier = Modifier.fillMaxWidth()
            )

            // Search Button
            Button(
                onClick = { viewModel.fetchForecast(cityName) },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Search")
            }

            // State Handling
            when (forecastState) {
                is ForecastState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }

                is ForecastState.Success -> {
                    val state = forecastState as ForecastState.Success

                    // Display Current Weather
                    Text(
                        text = "Current Weather:",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    WeatherInfo(
                        temperature = state.currentWeather.temperature,
                        condition = state.currentWeather.condition.toString()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Display 7-Day Forecast
                    Text(
                        text = "7-Day Forecast:",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    LazyColumn {
                        items(state.forecastList.size) { index ->
                            val forecast = state.forecastList[index]
                            WeatherInfo(
                                temperature = forecast.temperature,
                                condition = forecast.condition.toString()
                            )
                        }
                    }
                }

                is ForecastState.Error -> {
                    Text(
                        text = (forecastState as ForecastState.Error).message,
                        color = Color.Red
                    )
                }
            }
        }
    }
}

@Composable
fun WeatherInfo(temperature: Double, condition: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Temperature: $temperature°C", style = MaterialTheme.typography.bodySmall)
        Text(text = "Condition: $condition", style = MaterialTheme.typography.bodySmall)
    }
}
