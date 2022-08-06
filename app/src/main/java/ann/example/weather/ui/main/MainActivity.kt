package ann.example.weather.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import ann.example.weather.R
import ann.example.weather.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val editText = binding.city
        val help = this

        editText.setOnEditorActionListener{ _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE &&
                !editText.text.isNullOrEmpty()) {

                viewModel.getWeatherByCity(editText.text.toString())

                viewModel.all.observe(help){
                    it.let {
                        val city = it.name
                        val correctTemp = it.main.temp.toString() + " °C"
                        val windSpeed = it.wind.speed.toString() + " м/с"
                        val feelsTemp = "чувствуется как " + it.main.feels_like.toString() + " °C"
                        val imgCode = it.weather[0].icon

                        editText.setText(city)
                        binding.temp.text = correctTemp
                        binding.description.text = it.weather[0].description
                        binding.feelsLike.text = feelsTemp
                        binding.wind.text = "Ветер"
                        binding.imgWind.setImageResource(R.drawable.wind)
                        binding.windValue.text = windSpeed

                        when(imgCode){
                            "01d" -> binding.imgStateWeather.setImageResource(R.drawable.day)
                            "01n" -> binding.imgStateWeather.setImageResource(R.drawable.night)
                            "02d" -> binding.imgStateWeather.setImageResource(R.drawable.cloudy)
                            "02n" -> binding.imgStateWeather.setImageResource(R.drawable.cloudy_night)
                            "04d" -> binding.imgStateWeather.setImageResource(R.drawable.cloud)
                            "04n" -> binding.imgStateWeather.setImageResource(R.drawable.cloud)
                            "10d" -> binding.imgStateWeather.setImageResource(R.drawable.rain)
                            "10n" -> binding.imgStateWeather.setImageResource(R.drawable.rain)
                        }
                    }
                }
                true;
            }
            false;
        }

        binding.description.text = "Ждём загрузки..."
    }
}