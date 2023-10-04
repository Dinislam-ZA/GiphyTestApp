package com.example.vktestapplication

import android.app.Application
import com.example.vktestapplication.di.AppComponent
import com.example.vktestapplication.di.DaggerAppComponent
import com.example.vktestapplication.di.modules.NetworkModule

open class MyApplication: Application() {

    val appComponent: AppComponent by lazy {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        DaggerAppComponent.builder().build()
    }


}