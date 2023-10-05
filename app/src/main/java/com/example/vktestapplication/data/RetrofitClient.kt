package com.example.vktestapplication.data

import com.example.vktestapplication.utils.Constants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {


    private val retrofit by lazy {
        val gsonConverter = GsonBuilder().serializeNulls().create()
        Retrofit.Builder().baseUrl(Constants.baseUrl).addConverterFactory(GsonConverterFactory.create(gsonConverter)).build()
    }

    val apiService: ApiService by lazy {

        retrofit.create(ApiService::class.java)

    }


}