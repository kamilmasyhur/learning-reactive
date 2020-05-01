package com.bootcamp.learningreactive.executors.domain

import com.bootcamp.learningreactive.executors.Callback
import com.bootcamp.learningreactive.executors.data.MainRepository

interface MainUseCase {
    fun getWeather(cityName: String, callback: Callback)
}

class MainUseCaseImpl(
    private val mainRepository: MainRepository
) : MainUseCase {
    override fun getWeather(cityName: String, callback: Callback) {
        mainRepository.getWeather(cityName, callback)
    }
}
