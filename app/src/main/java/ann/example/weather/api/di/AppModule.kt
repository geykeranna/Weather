package ann.example.weather.api.di

import ann.example.weather.api.service.WeatherService
import ann.example.weather.api.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) //сообщает Hilt контейнеры, в которых доступны привязки с указанным компонентом Hilt
object AppModule {

    //указывает как предоставлять типы, которые не могут быть введены конструктором
    //будет выполняться каждый раз, когда Hilt необходимо предоставить экземпляр этого типа
    @Provides
    fun baseUrl() = BASE_URL

    //Для отображения в консоли данных о работе retrofit
    @Provides
    fun logging() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun okHttpClient() = OkHttpClient.Builder()
        .addInterceptor(logging())
        .build()

    @Provides
    @Singleton //Аннотациея, которая привязывает экземпляр к контейнеру приложения
    //заставит контейнер приложения всегда предоставлять один и тот же экземпляр независимо от того,
    //используется ли тип как зависимость другого типа или его нужно вводить в поле.
    fun provideRetrofit(): WeatherService =
        Retrofit.Builder()
            .baseUrl(baseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
            .build()
            .create(WeatherService::class.java)
}