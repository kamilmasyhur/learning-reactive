package com.bootcamp.learningreactive.executors

import com.bootcamp.learningreactive.model.WeatherResponse

interface Callback {
    fun onSuccess(weatherResponse: WeatherResponse)
    fun onFailure(throwable: Throwable)
}
