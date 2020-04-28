package com.bootcamp.learningreactive.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bootcamp.learningreactive.MainApp
import com.bootcamp.learningreactive.R
import com.bootcamp.learningreactive.data.MainRepositoryImpl
import com.bootcamp.learningreactive.domain.MainUseCaseImpl
import com.bootcamp.learningreactive.model.WeatherResponse
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        MainViewModel(MainUseCaseImpl(MainRepositoryImpl(MainApp.getWeatherService())))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observeState()
        viewModel.getWeather("Jakarta")
    }

    private fun observeState() {
        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is MainState.LoadSuccess -> {
                    render(state.result)
                    Toast.makeText(this, "Load Success", Toast.LENGTH_LONG).show()
                }
                MainState.LoadFailed -> {
                    Toast.makeText(this, "Load Failed", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun render(result: WeatherResponse) {
        tvTemp.text = "${result.weatherTemperature.temperature} °F"
    }
}
