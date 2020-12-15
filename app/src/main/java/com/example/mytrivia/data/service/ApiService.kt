package com.example.mytrivia.data.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface para generar las peticiones a la api
 * @author Axel Sanchez
 */
interface ApiService {
    /**
     * Api que devuelve los productos que correspondan con la búsqueda ingresada
     * @param [query] búsqueda ingresada
     * @return devuelve un objeto Response (de retrofit) que contiene [MyResponse]
     */
    @GET("api.php?amount=10")
    suspend fun getQuestions(@Query("category") category: Int, @Query("difficulty") difficulty: String, @Query("type") type: String): Response<com.example.mytrivia.data.models.Response?>
}