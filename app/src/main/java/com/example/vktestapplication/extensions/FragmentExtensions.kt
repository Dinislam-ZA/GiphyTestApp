package com.example.vktestapplication.extensions

import androidx.fragment.app.Fragment
import com.example.vktestapplication.MyApplication
import com.example.vktestapplication.di.AppComponent

fun Fragment.getAppComponent():AppComponent = (requireContext() as MyApplication).appComponent