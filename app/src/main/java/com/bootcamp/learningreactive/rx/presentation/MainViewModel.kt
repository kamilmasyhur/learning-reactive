package com.bootcamp.learningreactive.rx.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bootcamp.learningreactive.rx.domain.MainUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class MainViewModel(
    private val useCase: MainUseCase
) : ViewModel() {

    private val disposable by lazy {
        CompositeDisposable()
    }

    override fun onCleared() {
        super.onCleared()
        dispose()
    }

    private fun addDisposable(subscription: Disposable) {
        disposable.add(subscription)
    }

    private var _state = MutableLiveData<MainState>()
    val state : LiveData<MainState> = _state

    fun getWeather(cityName: String) {
        val disposable = useCase.getWeather(cityName)
            .doOnSubscribe {
                _state.value = MainState.ShowLoading
            }
            .subscribe({
                _state.value = MainState.DismissLoading
                _state.value = MainState.LoadSuccess(it)
            }, {
                _state.value = MainState.DismissLoading
                _state.value = MainState.LoadFailed
            })
        addDisposable(disposable)
    }

    private fun dispose() {
        if (disposable.size() > 0) {
            disposable.clear()
        }
    }
}
