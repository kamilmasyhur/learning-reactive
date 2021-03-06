package com.bootcamp.learningreactive.rx.data

import com.bootcamp.learningreactive.service.OpenWeatherService
import com.bootcamp.learningreactive.model.WeatherResponse
import io.reactivex.Single

interface MainRepository {
    fun getWeather(cityName: String): Single<WeatherResponse>
}

class MainRepositoryImpl(private val weatherService: OpenWeatherService): MainRepository {
    override fun getWeather(cityName: String) = weatherService.fetchWeatherByCityName(
        appId = "cxxxxxxxxxxxxxxxxxxxbvcsbvasd",
        cityName = cityName
    )
}
