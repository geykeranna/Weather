package ann.example.weather.api.repository

import ann.example.weather.api.service.WeatherService
import javax.inject.Inject

class GetWeatherRepository @Inject constructor(private val apiService: WeatherService){
    suspend fun getAll(q: String) = apiService.getWeather(q)
}