package com.example.vktestapplication.di.modules

import com.example.vktestapplication.data.ApiService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit():Retrofit{
        val gsonConverter = GsonBuilder().serializeNulls().create()

        return Retrofit.Builder().baseUrl("https://api.giphy.com/").addConverterFactory(
            GsonConverterFactory.create(gsonConverter)).build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit):ApiService{

        return retrofit.create(ApiService::class.java)
    }
}