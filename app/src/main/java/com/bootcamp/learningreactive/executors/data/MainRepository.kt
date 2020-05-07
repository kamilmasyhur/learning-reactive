package com.bootcamp.learningreactive.executors.data

import com.bootcamp.learningreactive.executors.Callback
import com.bootcamp.learningreactive.model.WeatherResponse
import com.bootcamp.learningreactive.service.OpenWeatherService
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Response
import java.io.IOException
import java.lang.NullPointerException

interface MainRepository {
    fun getWeather(cityName: String, callback: Callback)
}

class MainRepositoryImpl(private val weatherService: OpenWeatherService): MainRepository {
    override fun getWeather(cityName: String, callback: Callback) {
        weatherService.fetchWeatherByCityNameWithoutRx("cxxxxxxxxxxxxxxxxxxxbvcsbvasd", cityName)
            .enqueue(object : retrofit2.Callback<WeatherResponse> {
                override fun onFailure(call: retrofit2.Call<WeatherResponse>, t: Throwable) {
                    callback.onFailure(t)
                }

                override fun onResponse(
                    call: retrofit2.Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.body() == null) {
                            callback.onFailure(NullPointerException())
                        } else {
                            callback.onSuccess(response.body()!!)
                        }
                    } else {
                        callback.onFailure(Throwable(response.message()))
                    }
                }

            })
    }
}
