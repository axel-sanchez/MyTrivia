package com.example.mytrivia.di

import androidx.room.Room
import com.example.mytrivia.data.room.Database
import com.example.mytrivia.data.service.ApiService
import com.example.mytrivia.data.service.BASE_URL
import com.example.mytrivia.data.service.ConnectToApi
import com.example.mytrivia.domain.HomeUseCase
import com.example.mytrivia.domain.QuestionUseCase
import com.example.mytrivia.viewmodel.HomeViewModel
import com.example.mytrivia.viewmodel.QuestionViewModel
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * [Module] que crea las instancias singleton utilizando Koin
 * @author Axel Sanchez
 */
val moduleApp = module{
    single{ Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build() }
    single { (get() as Retrofit).create(ApiService::class.java) }
    single { Room
        .databaseBuilder(androidContext(), Database::class.java, "myTriviaDB.db3")
        .build() }
    single { HomeUseCase() }
    single { QuestionUseCase() }
    single { ConnectToApi() }
    single { Gson() }
}