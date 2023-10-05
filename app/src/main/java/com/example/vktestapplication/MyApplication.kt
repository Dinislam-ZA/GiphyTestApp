package com.example.vktestapplication

import android.app.Application
import android.content.Context
import com.example.vktestapplication.di.AppComponent
import com.example.vktestapplication.di.DaggerAppComponent

open class MyApplication: Application() {

    val appComponent: AppComponent by lazy {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        DaggerAppComponent.builder().build()
    }


}

val Context.appComponent: AppComponent
    get() = when (this) {
        is MyApplication -> appComponent
        else -> applicationContext.appComponent
    }