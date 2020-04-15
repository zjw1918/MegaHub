package io.mega.megahub.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object NetWorkService {
    private val retrofit = Retrofit.Builder()
        .client(OkHttpClient.Builder().callTimeout(10, TimeUnit.SECONDS).build())
        .baseUrl("https://www.metaweather.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create<ApiService>()
}