package com.bootcamp.learningreactive.executors.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bootcamp.learningreactive.executors.Callback
import com.bootcamp.learningreactive.executors.domain.MainUseCase
import com.bootcamp.learningreactive.model.WeatherResponse

class MainViewModel(
    private val useCase: MainUseCase
) : ViewModel() {

    private var _state = MutableLiveData<MainState>()
    val state: LiveData<MainState> = _state

    fun getWeather(cityName: String) {
        _state.value = MainState.ShowLoading
        useCase.getWeather(cityName, object : Callback {
            override fun onSuccess(weatherResponse: WeatherResponse) {
                _state.value = MainState.DismissLoading
                _state.value = MainState.LoadSuccess(weatherResponse)
            }

            override fun onFailure(throwable: Throwable) {
                _state.value = MainState.DismissLoading
                _state.value = MainState.LoadFailed
            }
        })
    }
}
