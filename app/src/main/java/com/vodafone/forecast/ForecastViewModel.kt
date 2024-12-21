package com.vodafone.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vodafone.domain.model.ForecastData
import com.vodafone.domain.usecases.FetchForecastUseCase
import com.vodafone.domain.usecases.GetLastCityUseCase
import com.vodafone.domain.usecases.SaveCityUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class ForecastState {
    data object Loading : ForecastState()
    data class Success(val currentWeather: ForecastData, val forecastList: List<ForecastData>) :
        ForecastState()

    data class Error(val message: String) : ForecastState()
}

class ForecastViewModel(
    private val fetchForecastUseCase: FetchForecastUseCase,
    private val saveCityUseCase: SaveCityUseCase,
    private val getLastCityUseCase: GetLastCityUseCase,
) : ViewModel() {

    private val _forecastState = MutableStateFlow<ForecastState>(ForecastState.Loading)
    val forecastState: StateFlow<ForecastState> get() = _forecastState

    private val _lastCity = MutableStateFlow<String?>(null)
    val lastCity: StateFlow<String?> get() = _lastCity

    init {
        loadLastCity()
    }

    private fun loadLastCity() {
        _lastCity.value = getLastCityUseCase()
        _lastCity.value?.let { fetchForecast(it) }
    }

    private fun saveCity(cityName: String) {
        saveCityUseCase(cityName)
    }

    fun fetchForecast(cityName: String) {
        viewModelScope.launch {
            _forecastState.value = ForecastState.Loading
            try {
                val currentWeather =
                    viewModelScope.async {
                        fetchForecastUseCase(cityName)
                    }
                val forecastList = viewModelScope.async {
                    fetchForecastUseCase.getWeeklyForecast(cityName)
                }
                _forecastState.value =
                    ForecastState.Success(currentWeather.await(), forecastList.await())
                saveCity(cityName)
            } catch (e: Exception) {
                _forecastState.value = ForecastState.Error(e.message ?: "Unknown Error")
            }
        }
    }
}

