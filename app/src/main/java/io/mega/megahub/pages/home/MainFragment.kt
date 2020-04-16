package io.mega.megahub.pages.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import io.mega.megahub.*
import io.mega.megahub.bean.LoadState
import kotlinx.android.synthetic.main.fragment_main.*
import timber.log.Timber
import java.util.*
import kotlin.random.Random

private const val TAG = "MainFragment"
class MainFragment : Fragment() {
//    private val args: MainFragmentArgs by navArgs()

    private lateinit var nameViewModel: NameViewModel
    private lateinit var weatherViewModel: WeatherViewModel
    private val timer: Timer = Timer()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Timber.d("onActivityCreated")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        nameViewModel = ViewModelProvider(this).get(NameViewModel::class.java)
        Timber.d("Got a QuizViewModel: $nameViewModel")
//
//        val nameObserver = Observer<String> {newName ->
//            tv_name.text = newName
//        }
//
//        model.currentName.observe(this, nameObserver)

        val counterObserver = Observer<Int> { count ->
            Timber.d("get count $count")
            tv_name.text = "counter: $count"
        }

        CounterManager.count.observe(this, counterObserver)

        // weather viewModel
        weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        // test room db

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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated")

        btn_fetch_weather.setOnClickListener {
            weatherViewModel.getData()
        }

        btn_go_first_fragment.setOnClickListener {
            val action =
                MainFragmentDirections.actionMainFragmentToFirstFragment(
                    "111",
                    "title_111"
                )
            findNavController().navigate(action)
        }

        btn_go_weather_fragment.setOnClickListener {
            val action =
                MainFragmentDirections.actionMainFragmentToWeatherFragment()
            findNavController().navigate(action)
        }

        btn_count.setOnClickListener {
            timer.scheduleAtFixedRate(object : TimerTask() {
                override fun run() {
                    val a = Random.nextInt(0, 100)
                    Timber.d("set count $a")
                    CounterManager.count.postValue(a)
                }
            }, 0, 1000)
        }
    }

    override fun onStop() {
        super.onStop()
        Timber.d("onStop")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.d("onSaveInstanceState")
    }

    override fun onDestroy() {
        timer.cancel()
        super.onDestroy()
    }
}
