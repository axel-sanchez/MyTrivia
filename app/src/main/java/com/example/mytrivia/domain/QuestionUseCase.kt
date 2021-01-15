package com.example.mytrivia.domain

import com.example.mytrivia.data.models.Response
import com.example.mytrivia.data.room.Database
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

/**
 * Caso de uso para
 * @author Axel Sanchez
 */
class QuestionUseCase : KoinComponent {
    private val room: Database by inject()

    /**
     *
     * @return devuelve un any
     */
    suspend fun getQuestionFromId(id: Long): Response.Question? {
        return room.productDao().getQuestionById(id)
    }
}