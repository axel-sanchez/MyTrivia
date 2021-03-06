package com.example.mytrivia.application

import android.app.Application
import com.example.mytrivia.di.moduleApp
import org.koin.android.ext.android.startKoin

/**
 * Application que inicializa la inyección de dependencias de Koin
 * @author Axel Sanchez
 */
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(applicationContext, listOf(moduleApp))
    }
}