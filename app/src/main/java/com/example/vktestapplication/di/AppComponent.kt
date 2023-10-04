package com.example.vktestapplication.di

import com.example.vktestapplication.MainActivity
import com.example.vktestapplication.data.GifsRepository
import com.example.vktestapplication.di.modules.DataModule
import com.example.vktestapplication.di.modules.NetworkModule
import com.example.vktestapplication.ui.main.MainViewModel
import com.example.vktestapplication.ui.main.MainViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DataModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun mainViewModelFactory(gifsRepository: GifsRepository): MainViewModelFactory

    fun mainViewModel(): MainViewModel
}