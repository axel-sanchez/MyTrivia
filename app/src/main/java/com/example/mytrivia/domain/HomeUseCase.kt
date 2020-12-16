package com.example.mytrivia.domain

import com.example.mytrivia.data.service.ConnectToApi
import com.example.mytrivia.data.models.Response
import com.example.mytrivia.data.room.Database
import com.example.mytrivia.helpers.Constants
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.lang.Exception
import java.util.*

/**
 * Caso de uso para
 * @author Axel Sanchez
 */
class HomeUseCase : KoinComponent {
    private val api: ConnectToApi by inject()
    private val room: Database by inject()

    /**
     * @param [category] of the question that i want to search it
     * @return a question
     */
    suspend fun getQuestion(category: Int, difficulty: String, type: String): Response.Question? {
        try {
            var questions = room.productDao().getQuestions(category)
            questions?.let {
                if (questions.isNotEmpty()) {
                    val randomNum = Random().nextInt(questions.size)
                    return questions[randomNum]
                } else {
                    val newQuestions = api.getQuestions(category, difficulty, type)
                    newQuestions.value?.let {
                        if(it.isEmpty()) return null
                        val randomNum = Random().nextInt(it.size)
                        addToDB(it)
                        return it[randomNum]
                    }
                }
            } ?: return null
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    private suspend fun addToDB(questions: List<Response.Question?>) {
        for (question in questions) {
            try {
                question?.category = Constants.categoryConverterToNumber(question?.category)
                question?.id = room.productDao().insertQuestion(question)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}