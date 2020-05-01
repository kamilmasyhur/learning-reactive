package com.bootcamp.learningreactive.rx.domain

import com.bootcamp.learningreactive.model.WeatherResponse
import com.bootcamp.learningreactive.rx.data.MainRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface MainUseCase {
    fun getWeather(cityName: String): Single<WeatherResponse>
}

class MainUseCaseImpl(
    private val mainRepository: MainRepository
): MainUseCase {
    override fun getWeather(cityName: String): Single<WeatherResponse> = mainRepository
        .getWeather(cityName)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}
