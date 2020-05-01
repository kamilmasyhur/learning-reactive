package com.bootcamp.learningreactive.executors.data

import com.bootcamp.learningreactive.executors.Callback
import com.bootcamp.learningreactive.model.WeatherResponse
import com.bootcamp.learningreactive.service.OpenWeatherService
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

interface MainRepository {
    fun getWeather(cityName: String, callback: Callback)
}

class MainRepositoryImpl(private val weatherService: OpenWeatherService): MainRepository {
    override fun getWeather(cityName: String, callback: Callback) {
        val request = Request.Builder()
            .url("https://api.openweathermap.org/data/2.5/weather?appid=cxxxxxxxxxxxxxxxxxxxbvcsbvasd&q=$cityName")
            .build()
        OkHttpClient().newCall(request).enqueue(object :okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                callback.onFailure(e)
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                if (response.isSuccessful) {
                    val responseJson = response.body()?.string()
                    val responseObj = Gson().fromJson(responseJson, WeatherResponse::class.java)
                    callback.onSuccess(responseObj)
                } else {
                    callback.onFailure(Throwable(response.message()))
                }
            }
        })
    }
}
