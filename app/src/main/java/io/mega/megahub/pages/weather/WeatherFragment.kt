package io.mega.megahub.pages.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import io.mega.megahub.R
import io.mega.megahub.WeatherViewModel
import io.mega.megahub.bean.LoadState
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.btn_fetch_weather
import kotlinx.android.synthetic.main.fragment_weather.*
import timber.log.Timber

class WeatherFragment: Fragment() {
    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        weatherViewModel.loadState.observe(this) {
            when (it) {
                is LoadState.Success -> {
                    btn_fetch_weather.isEnabled = true
                }
                is LoadState.Loading -> btn_fetch_weather.isEnabled = false
                is LoadState.Fail -> {
                    btn_fetch_weather.isEnabled = true
                    Toast.makeText(activity, it.msg, Toast.LENGTH_SHORT).show()
                }
            }
        }

        weatherViewModel.weatherData.observe(this) {
            Timber.d(it.toString())
            Toast.makeText(activity, "Shanghai Today min temp is ${it.consolidated_weather[0].min_temp}",
                Toast.LENGTH_SHORT).show()

            rcv_weathers.layoutManager = LinearLayoutManager(activity)
            val adapter = WeatherAdapter(it.consolidated_weather)
            rcv_weathers.adapter = adapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_fetch_weather.setOnClickListener {
            weatherViewModel.getData()
        }
    }

}