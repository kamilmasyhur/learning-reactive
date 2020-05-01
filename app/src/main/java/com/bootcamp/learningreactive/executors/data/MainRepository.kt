package com.bootcamp.learningreactive.executors.data

import com.bootcamp.learningreactive.executors.Callback
import com.bootcamp.learningreactive.model.WeatherResponse
import com.bootcamp.learningreactive.service.OpenWeatherService
import retrofit2.Call
import retrofit2.Response

interface MainRepository {
    fun getWeather(cityName: String, callback: Callback)
}

class MainRepositoryImpl(private val weatherService: OpenWeatherService): MainRepository {
    override fun getWeather(cityName: String, callback: Callback) {
        weatherService.fetchWeatherByCityNameWithoutRx("xxxxxxxxxxxxxxxxxxxx", cityName)
            .enqueue(object: retrofit2.Callback<WeatherResponse> {
                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    callback.onFailure(t)
                }

                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    response.body()?.let { weatherResponse ->
                        callback.onSuccess(weatherResponse)
                    } ?: run {
                        callback.onFailure(Throwable("Failed getting response from Weather Repository"))
                    }
                }
            })
    }
}
