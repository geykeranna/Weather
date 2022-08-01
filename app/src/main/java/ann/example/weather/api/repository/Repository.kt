package ann.example.weather.api.repository

import ann.example.weather.api.service.WeatherService
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: WeatherService){
    suspend fun getAll(lat: Double, lon: Double) = apiService.getWeather(lat, lon)
}