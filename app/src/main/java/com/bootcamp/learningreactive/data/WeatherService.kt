package com.bootcamp.learningreactive.data

import com.bootcamp.learningreactive.model.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {
    @GET("data/2.5/weather")
    fun fetchWeatherByCityName(
        @Query("appid") appId: String,
        @Query("q") cityName: String
    ): Single<WeatherResponse>
}
