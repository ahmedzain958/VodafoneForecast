package com.vodafone.forecast.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import com.vodafone.forecast.viewmodels.ForecastListIntent
import com.vodafone.forecast.viewmodels.ForecastListState
import com.vodafone.forecast.viewmodels.ForecastListViewModel

@Composable
fun ForecastListScreen(viewModel: ForecastListViewModel, cityName: String) {
    val forecastListState by viewModel.forecastListState.collectAsState()

    LaunchedEffect(key1 = cityName) {
        viewModel.handleIntent(ForecastListIntent.LoadForecast(cityName))
    }

    when (forecastListState) {
        is ForecastListState.Loading -> {
            CircularProgressIndicator()
        }

        is ForecastListState.Success -> {
            val state = forecastListState as ForecastListState.Success
            val size = state.forecastList.size
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

        is ForecastListState.Error -> {
            Text(
                text = (forecastListState as ForecastListState.Error).message,
                color = Color.Red
            )
        }
    }
}
