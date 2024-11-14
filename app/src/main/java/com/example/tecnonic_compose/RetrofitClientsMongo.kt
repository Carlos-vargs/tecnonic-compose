package com.example.tecnonic_compose

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientMongo {
    private const val BASE_URL = "http://miapienpythontecnonic.eastus.azurecontainer.io:5000/TecnoNic/"

    val apiServiceMongo: ApiServiceMongo by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceMongo::class.java)
    }
}
