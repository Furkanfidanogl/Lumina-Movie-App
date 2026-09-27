package com.example.cleanmovieapp.di

import android.app.Application
import com.example.cleanmovieapp.di.module.apiModule
import com.example.cleanmovieapp.di.module.repositoryModule
import com.example.cleanmovieapp.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(apiModule, repositoryModule, viewModelModule)
        }
    }

}