package com.example.vktestapplication.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/gifs/search?api_key=kBTgPxYoFXwFyykfYxs8WqZ8ZjgSQvAr")
    suspend fun searchGifs(@Query("q") query: String,
                           @Query("limit") limit: Int,
                           @Query("offset") offset: Int):Response<SearchRespond>

    @GET("v1/gifs/trending?api_key=kBTgPxYoFXwFyykfYxs8WqZ8ZjgSQvAr")
    suspend fun getAllGifs(@Query("limit") limit: Int,
                           @Query("offset") offset: Int):Response<SearchRespond>
}