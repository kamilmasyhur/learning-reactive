package com.bootcamp.learningreactive.rx.data

import com.bootcamp.learningreactive.model.WeatherResponse
import com.bootcamp.learningreactive.service.OpenWeatherService
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import load
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

@RunWith(JUnitPlatform::class)
class MainRepositoryImplTest: Spek({
    Feature("Main Repository") {
        Scenario("Get Weather Data") {
            val weatherService: OpenWeatherService = mock()
            val mainRepository: MainRepository = MainRepositoryImpl(weatherService)
            val cityName = "jakarta"

            val result = load(WeatherResponse::class.java, "weather_response.json")
            lateinit var response: TestObserver<WeatherResponse>

            Given("Main Repository") {
                given(weatherService.fetchWeatherByCityName("cxxxxxxxxxxxxxxxxxxxbvcsbvasd", cityName)).
                        willReturn(Single.just(result))
            }

            When("Calling Open Weather Service") {
                response = mainRepository.getWeather(cityName).test()
            }

            Then("It should return weather data") {
                response.assertValue {
                    it == result
                }
            }
        }
    }
})
