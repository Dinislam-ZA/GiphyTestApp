package com.example.vktestapplication.data

import retrofit2.Response
import javax.inject.Inject

class GifsRepository @Inject constructor(private val retrofitApi: ApiService){

    suspend fun getSearchedGifs(query: String?, limit: Int, offset: Int): Response<SearchRespond>{
        return if (!query.isNullOrEmpty()) retrofitApi.searchGifs(query, limit, offset) else retrofitApi.getAllGifs(limit, offset)
    }

}