package com.mary.starwars.core

import android.app.Application
import com.mary.starwars.core.di.baseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StarWarsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@StarWarsApp)
            modules(baseModule)
        }
    }
}