package com.example.mytrivia.data.service

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mytrivia.data.models.Response
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

const val BASE_URL = "https://opentdb.com/"

/**
 * Esta clase es la encargada de conectarse a la api
 * @author Axel Sanchez
 */
class ConnectToApi : KoinComponent {
    private val service: ApiService by inject()

    /**
     * Esta función es la encargada de retornar la búsqueda de productos
     * @param [query] es la búsqueda
     * @return devuelve un mutableLiveData que contiene un listado de [Product]
     */
    suspend fun getQuestions(category: Int, difficulty: String, type: String): MutableLiveData<List<Response.Question?>?> {
        val mutableLiveData = MutableLiveData<List<Response.Question?>?>()
        try {
            val response = service.getQuestions(category, difficulty, type)
            if (response.isSuccessful) {
                Log.i("Successful Response", response.body()
                    ?.let { it.toString() } ?: "")
                mutableLiveData.value = response.body()
                    ?.let { it.results } ?: listOf()
            } else {
                Log.i("Error Response", response.errorBody().toString())
                mutableLiveData.value = listOf()
            }

        } catch (e: Exception) {
            mutableLiveData.value = listOf()
            Log.e("ConnectToApi", "Error getting the questions and saving them in the livedata")
            Log.e("Category", category.toString())
            Log.e("Difficulty", difficulty)
            Log.e("Type", type)
            e.printStackTrace()
        }
        return mutableLiveData
    }
}