package com.example.vktestapplication.di

import com.example.vktestapplication.MainActivity
import com.example.vktestapplication.di.modules.NetworkModule
import com.example.vktestapplication.ui.main.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(mainFragment: MainFragment)

    //fun mainViewModelFactory(gifsRepository: GifsRepository): MainViewModelFactory

    //fun mainViewModel(): MainViewModel
}