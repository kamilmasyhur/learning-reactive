package com.bootcamp.learningreactive.rx.presentation

import com.bootcamp.learningreactive.model.WeatherResponse

sealed class MainState {
    object ShowLoading : MainState()
    object DismissLoading : MainState()
    object LoadFailed : MainState()
    data class LoadSuccess(val result: WeatherResponse) : MainState()
}
