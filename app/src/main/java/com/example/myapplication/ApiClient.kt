package com.example.myapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    fun getClient(): ApiInterfaces{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com")
            .addConverterFactory(GsonConverterFactory.create()).build()

        return retrofit.create(ApiInterfaces::class.java)
    }
}