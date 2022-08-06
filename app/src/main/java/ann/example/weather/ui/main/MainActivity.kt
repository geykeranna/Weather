package ann.example.weather.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import ann.example.weather.R
import ann.example.weather.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

   /*
    lateinit var recycleView: RecyclerView
    lateinit var adaptor: DataItemAdaptor

    private val weatherRequest: MutableLiveData<WeatherResponse> = MutableLiveData()
    private lateinit var apiService: RetrofitServices
    private lateinit var mJob: Job
    override val coroutineContext: CoroutineContext
        get() = mJob + Dispatchers.Main
        */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        var editText = binding.city

        /*
        binding.city.setOnKeyListener(View.OnKeyListener { view, i, keyEvent ->
            if (i == KeyEvent.KEYCODE_ENTER && keyEvent.action == KeyEvent.ACTION_UP){
                Log.d("ENTER KEY", "ENTER KEY WAS PUSHED")
                return@OnKeyListener true
            }
            false
        })*/


        viewModel.all.observe(this){
            it.let {
                val city = it.name
                editText.setText(city)
                val correctTemp = it.main.temp.toString() + " C°"
                binding.temp.text = correctTemp
                binding.description.text = it.weather[0].description
                binding.wind.text = "Ветер"
                binding.imgWind.setImageResource(R.drawable.wind)
                val windSpeed = it.wind.speed.toString() + " м/с"
                binding.windValue.text = windSpeed
            }
        }

    }
}