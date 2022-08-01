package ann.example.weather.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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


        /*
        binding.city.setOnKeyListener(View.OnKeyListener { view, i, keyEvent ->
            if (i == KeyEvent.KEYCODE_ENTER && keyEvent.action == KeyEvent.ACTION_UP){
                Log.d("ENTER KEY", "ENTER KEY WAS PUSHED")
                return@OnKeyListener true
            }
            false
        })*/

        Log.d("AAA", viewModel.getTemp())
        Log.d("AAA", viewModel.getDescription())
        Log.d("AAA", viewModel.getWindSpeed())

        if(!viewModel.getTemp().equals(null) && viewModel.getTemp() != "") {
            binding.temp.text = viewModel.getTemp()
            binding.description.text = viewModel.getDescription()
            binding.wind.text = "Ветер"
            binding.imgWind.setImageResource(R.drawable.wind)
            binding.windValue.text = viewModel.getWindSpeed()
            Log.d("OOO", "WHAT THE HELL")
        }

        Log.d("Connection retrofit AAA", viewModel.getAll().toString())
    }


}