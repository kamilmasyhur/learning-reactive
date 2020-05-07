package com.bootcamp.learningreactive.executors.data

import com.bootcamp.learningreactive.executors.Callback
import com.bootcamp.learningreactive.model.WeatherResponse
import com.bootcamp.learningreactive.service.OpenWeatherService
import com.nhaarman.mockitokotlin2.*
import load
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import retrofit2.Call
import retrofit2.Response

@RunWith(JUnitPlatform::class)
class MainRepositoryImplTest : Spek({
    Feature("Main Repository") {
        Scenario("Get Weather Data") {
            val weatherService: OpenWeatherService = mock()
            val callback: Callback = mock()
            val retrofitCall: Call<WeatherResponse> = mock()

            val mainRepository: MainRepository = MainRepositoryImpl(weatherService)
            val cityName = "jakarta"

            val result = load(WeatherResponse::class.java, "weather_response.json")

            Given("Main Repository") {
                given(retrofitCall.enqueue(any()))
                    .willAnswer { invocation ->
                        (invocation.arguments[0] as retrofit2.Callback<WeatherResponse>).onResponse(
                            retrofitCall,
                            Response.success(result)
                        )
                    }

                given(
                    weatherService.fetchWeatherByCityNameWithoutRx(
                        "cxxxxxxxxxxxxxxxxxxxbvcsbvasd",
                        cityName
                    )
                ).willReturn(retrofitCall)
            }

            When("Calling Open Weather Service") {
                mainRepository.getWeather(cityName, callback)
            }

            Then("It should return weather data") {
                verify(weatherService).fetchWeatherByCityNameWithoutRx(
                    "cxxxxxxxxxxxxxxxxxxxbvcsbvasd",
                    cityName
                )
                verify(callback).onSuccess(result)
                verifyNoMoreInteractions(weatherService, callback)
            }
        }
    }
})
