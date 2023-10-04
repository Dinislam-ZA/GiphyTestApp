package com.example.vktestapplication.data

import android.util.Log
import retrofit2.Response
import javax.inject.Inject

class GifsRepository @Inject constructor(private val retrofitApi: ApiService){

    suspend fun getSearchedGifs(query: String, limit: Int, offset: Int): Response<SearchRespond>{
        val response = retrofitApi.searchGifs(query, limit, offset)
        Log.d("WTF", "WTF")
        return response
    }

}