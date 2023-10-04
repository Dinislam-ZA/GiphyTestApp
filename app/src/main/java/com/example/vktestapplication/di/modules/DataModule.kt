package com.example.vktestapplication.di.modules

import com.example.vktestapplication.data.ApiService
import com.example.vktestapplication.data.GifsRepository
import dagger.Module
import dagger.Provides


@Module
class DataModule {

    @Provides
    fun provideGifsRepository(apiService: ApiService):GifsRepository{
        return GifsRepository(apiService)
    }
}