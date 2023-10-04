package com.example.vktestapplication.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.vktestapplication.data.GifClass
import com.example.vktestapplication.data.GifsPageSource
import com.example.vktestapplication.data.GifsRepository
import com.example.vktestapplication.data.RetrofitClient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel (private val gifsRepository: GifsRepository): ViewModel() {


    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()



    @OptIn(ExperimentalCoroutinesApi::class)
    val gifs: StateFlow<PagingData<GifClass>> = query
        .map(::newPager)
        .flatMapLatest { pager -> pager.flow }
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())


    private fun newPager(query: String): Pager<Int, GifClass> {
        return Pager(PagingConfig(25, enablePlaceholders = false), pagingSourceFactory = {
            GifsPageSource(query, gifsRepository)
        })
    }

    fun checkFun(){
        viewModelScope.launch {
            val response = RetrofitClient.apiService.searchGifs("sd", 5, 0)
            Log.d("WTF", "${response.body()?.data}")
        }
    }

    fun setQuery(query: String) {
        _query.tryEmit(query)
    }

}