package io.mega.megahub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import io.mega.megahub.bean.LoadState
import kotlinx.android.synthetic.main.fragment_main.*
import timber.log.Timber

class FirstFragment : Fragment() {
    private val args: FirstFragmentArgs by navArgs()
    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var foreverObserver: Observer<Int>

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
        }


        foreverObserver = Observer<Int> {
            Timber.d("FirstFragment get count $it")
        }
        CounterManager.count.observeForever(foreverObserver)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Timber.d("taskId: %s, title: %s", args.taskId, args.title)

        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_fetch_weather.setOnClickListener {
            weatherViewModel.getData()
        }
    }

    override fun onDestroy() {
        CounterManager.count.removeObserver(foreverObserver)
        super.onDestroy()
    }

}
