package io.mega.megahub

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.mega.megahub.bean.LoadState
import io.mega.megahub.bean.WeatherResult
import io.mega.megahub.network.NetWorkService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class WeatherViewModel: ViewModel() {
    val weatherData = MutableLiveData<WeatherResult>()
    val loadState = MutableLiveData<LoadState>()
    fun getData() {
        selfLaunch(
            {
                loadState.value = LoadState.Loading()
                weatherData.value = async { NetWorkService.apiService.getWeather(2151849) }.await()
                loadState.value = LoadState.Success()
            },
            {
                loadState.value = LoadState.Fail(it.message + " Load Failed.")
            }
        )
//        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
//            loadState.value = LoadState.Fail(throwable.message + " 加载失败")
//        }) {
//            loadState.value = LoadState.Loading()
//            weatherData.value = async { NetWorkService.apiService.getWeather(2151849) }.await()
//            loadState.value = LoadState.Success()
//        }
    }
}