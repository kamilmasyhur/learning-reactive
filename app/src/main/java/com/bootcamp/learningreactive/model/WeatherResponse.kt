package com.bootcamp.learningreactive.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("name") val name: String,
    @SerializedName("weather") val weatherData: List<ListWeatherData>,
    @SerializedName("main") val weatherTemperature: WeatherTemperature
)

data class ListWeatherData(
    @SerializedName("main") val main: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("icon") val icon: String = ""
)

data class WeatherTemperature(
    @SerializedName("temp") val temperature: Double
)

