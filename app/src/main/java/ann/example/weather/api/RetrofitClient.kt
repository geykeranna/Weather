package ann.example.weather.api

import ann.example.weather.api.service.WeatherService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val baseUrl: String = "https://api.openweathermap.org/"
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiService: WeatherService by lazy {
        retrofit.create(WeatherService::class.java)
    }

    /*
    fun getClient(): RetrofitServices {

        /*
        if (apiService == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            apiService = retrofit.create(RetrofitServieces::class.java)
        }
         */
        return apiService!!
    }
     */
}