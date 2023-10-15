package com.example.vktestapplication.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.vktestapplication.data.GifClass
import com.example.vktestapplication.data.GifsPageSource
import com.example.vktestapplication.data.GifsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

class MainViewModel (private val gifsRepository: GifsRepository): ViewModel() {


    private val _query:MutableStateFlow<String?> = MutableStateFlow(null)
    val query: StateFlow<String?> = _query.asStateFlow()



    @OptIn(ExperimentalCoroutinesApi::class)
    val gifs: StateFlow<PagingData<GifClass>> = query
        .map(::newPager)
        .flatMapLatest { pager -> pager.flow }
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())


    private fun newPager(query: String?): Pager<Int, GifClass> {
        return Pager(PagingConfig(25, enablePlaceholders = true), pagingSourceFactory = {
            GifsPageSource(query, gifsRepository)
        })
    }


    fun setQuery(query: String?) {
        val message = query ?: "Null query"
        _query.tryEmit(query)
        Log.d("query in vm", query ?: "null")
    }

}