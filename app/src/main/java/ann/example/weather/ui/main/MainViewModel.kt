package ann.example.weather.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ann.example.weather.api.model.OpenApiWeather
import ann.example.weather.api.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: Repository) : ViewModel() {

    var lat: Double = 55.7522
    var lon: Double = 37.6156

    private val _all = MutableLiveData<OpenApiWeather>()
    val all: LiveData<OpenApiWeather>
        get() = _all

    init {
        getAll()
    }

    fun getAll() = viewModelScope.launch{
        repo.getAll(lat, lon).let {
            if (it.isSuccessful)
                _all.postValue(it.body())
            else Log.d("AAA", "False again ${it.errorBody()}")
        }

        Log.d("CCC", _all.value.toString())
        Log.d("CCC", _all.value?.current?.temp.toString())
        Log.d("CCC", _all.value?.current?.weather?.get(0)?.description.toString())
        Log.d("CCC", _all.value?.current?.wind_speed.toString() + "м/с")
    }

    var city = _all.value?.timezone.toString()

    fun getTemp() = _all.value?.current?.temp.toString()

    fun getDescription() = _all.value?.current?.weather?.get(0)?.description.toString()

    fun getWindSpeed() = _all.value?.current?.wind_speed.toString() + "м/с"
}