package ann.example.weather.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ann.example.weather.api.model.WeatherEntity
import ann.example.weather.api.repository.GetWeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: GetWeatherRepository) : ViewModel() {

    var lat: Double = 55.7522
    var lon: Double = 37.6156

    private val _all = MutableLiveData<WeatherEntity>()
    val all: LiveData<WeatherEntity>
        get() = _all

    fun getWeatherByCity(city: String) = viewModelScope.launch{
        repo.getAll(city).let {
            if (it.isSuccessful)
                _all.postValue(it.body())
            else Log.d("AAA", "False again ${it.errorBody()}")
        }
    }

}