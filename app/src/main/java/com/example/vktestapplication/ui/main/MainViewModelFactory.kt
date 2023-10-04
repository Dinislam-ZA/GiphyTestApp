package com.example.vktestapplication.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vktestapplication.data.GifsRepository
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val gifsRepository: GifsRepository):
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(gifsRepository) as T
    }
}