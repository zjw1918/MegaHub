package io.mega.megahub.bean

data class Weather(
    var id: Number,

    var weather_state_name: String,
    var weather_state_abbr: String,
    var wind_direction_compass: String,
    var created: String,
    var applicable_date: String,

    var min_temp: Number,
    var max_temp: Number,
    var the_temp: Number,
    var wind_speed: Number,
    var wind_direction: Number,
    var air_pressure: Number,
    var humidity: Number,
    var visibility: Number,
    var predictability: Number
)

data class WeatherResult(
    var consolidated_weather: List<Weather>,
    var time: String,
    var title: String,
    var woeid: Number
)