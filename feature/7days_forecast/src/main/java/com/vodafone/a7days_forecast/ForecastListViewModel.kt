package com.vodafone.a7days_forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vodafone.domain.model.ForecastData
import com.vodafone.domain.usecases.FetchForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class ForecastListIntent {
    data class LoadForecast(val cityName: String) : ForecastListIntent()
}

sealed class ForecastListState {
    data object Loading : ForecastListState()
    data class Success(val forecastList: List<ForecastData>) : ForecastListState()
    data class Error(val message: String) : ForecastListState()
}

@HiltViewModel
class ForecastListViewModel @Inject constructor(
    private val fetchForecastUseCase: FetchForecastUseCase,
) : ViewModel() {

    private val _forecastListState = MutableStateFlow<ForecastListState>(ForecastListState.Loading)
    val forecastListState: StateFlow<ForecastListState> get() = _forecastListState

    fun handleIntent(intent: ForecastListIntent) {
        when (intent) {
            is ForecastListIntent.LoadForecast -> {
                viewModelScope.launch {
                    _forecastListState.value = ForecastListState.Loading
                    try {
                        val forecastList = fetchForecastUseCase.getWeeklyForecast(intent.cityName)
                        _forecastListState.value = ForecastListState.Success(forecastList)
                    } catch (e: Exception) {
                        _forecastListState.value = ForecastListState.Error(e.message ?: "Error")
                    }
                }
            }
        }
    }
}
