package com.vodafone.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vodafone.core.kelvinToCelsius
import com.vodafone.domain.usecases.FetchForecastUseCase
import com.vodafone.domain.usecases.GetLastCityUseCase
import com.vodafone.domain.usecases.SaveCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityInputViewModel @Inject constructor(
    private val fetchForecastUseCase: FetchForecastUseCase,
    private val saveCityUseCase: SaveCityUseCase,
    private val getLastCityUseCase: GetLastCityUseCase,
) : ViewModel() {

    private val _cityInputState = MutableStateFlow<ForecastState>(ForecastState.Loading)
    val cityInputState: StateFlow<ForecastState> get() = _cityInputState

    private val _lastCity = MutableStateFlow<String?>(null)
    val lastCity: StateFlow<String?> get() = _lastCity

    init {
        loadLastCity()
    }

    private fun loadLastCity() {
        _lastCity.value = getLastCityUseCase()
        _lastCity.value?.let { fetchCurrentWeather(it) }
    }

    private fun saveCity(cityName: String) {
        saveCityUseCase(cityName)
    }

    fun fetchCurrentWeather(cityName: String) {
        viewModelScope.launch {
            _cityInputState.value = ForecastState.Loading
            try {
                val currentWeather = fetchForecastUseCase(cityName)
                _cityInputState.value = ForecastState.Success(
                    currentWeather.copy(temperature = kelvinToCelsius(currentWeather.temperature)),
                    emptyList()
                )
                saveCity(cityName)
            } catch (e: Exception) {
                _cityInputState.value = ForecastState.Error(e.message ?: "Unknown Error")
            }
        }
    }
}
