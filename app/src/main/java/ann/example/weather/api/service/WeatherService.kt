package ann.example.weather.api.service

import ann.example.weather.api.model.WeatherEntity
import ann.example.weather.api.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("/data/2.5/weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") appid: String = API_KEY,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "ru"
    ): Response<WeatherEntity>
}