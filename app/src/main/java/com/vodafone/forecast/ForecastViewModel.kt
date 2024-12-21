package com.vodafone.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vodafone.domain.usecases.FetchForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val fetchForecastUseCase: FetchForecastUseCase,
) : ViewModel() {

    private val _forecastState = MutableStateFlow<ForecastState>(ForecastState.Loading)
    val forecastState: StateFlow<ForecastState> get() = _forecastState

    suspend fun fetchForecast(cityName: String) {
        viewModelScope.launch {
            _forecastState.value = ForecastState.Loading
            try {
                val forecastData = fetchForecastUseCase(cityName)
                _forecastState.value = ForecastState.Success(forecastData)
            } catch (e: Exception) {
                _forecastState.value = ForecastState.Error(e.localizedMessage ?: "Unknown Error")
            }
        }
    }
}
