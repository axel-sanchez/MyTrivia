package com.example.mytrivia.data.room

import androidx.room.TypeConverter
import com.example.mytrivia.data.models.Response
import com.google.gson.Gson
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

/**
 * @author Axel Sanchez
 */
class Converters: KoinComponent{
    private val gson: Gson by inject()

    @TypeConverter
    fun fromQuestion(question: Response.Question?): String? {
        question?.let {
            return gson.toJson(it)
        } ?: return null
    }

    @TypeConverter
    fun toQuestion(resultString: String?): Response.Question? {
        resultString?.let {
            return gson.fromJson(it, Response.Question::class.java)
        } ?: return null
    }

    @TypeConverter
    fun fromIncorrectAnswers(incorrectAnswers: List<String?>?): String? {
        var response = ""
        incorrectAnswers?.let {
            for (i in incorrectAnswers.indices) {
                response += if (i == 0) incorrectAnswers[i]
                else ";${incorrectAnswers[i]}"
            }
        } ?: return null
        return response
    }

    @TypeConverter
    fun toIncorrectAnswers(concat: String?): List<String?>? {
        val list = concat?.split(";")
        list?.let {
            return it.map { str -> if (str != "null") str else null }
        } ?: return null
    }
}