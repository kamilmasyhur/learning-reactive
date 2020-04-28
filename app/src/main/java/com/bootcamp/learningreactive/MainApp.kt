package com.bootcamp.learningreactive

import android.app.Application
import com.bootcamp.learningreactive.data.OpenWeatherService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainApp : Application() {
    companion object {
        private lateinit var openWeatherService: OpenWeatherService

        fun getWeatherService(): OpenWeatherService {
            return if (::openWeatherService.isInitialized) {
                openWeatherService
            } else {
                openWeatherService = Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(OpenWeatherService::class.java)
                openWeatherService
            }
        }
    }
}
