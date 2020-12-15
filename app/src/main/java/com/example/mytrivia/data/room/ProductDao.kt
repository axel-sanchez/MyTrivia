package com.example.mytrivia.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mytrivia.data.models.Response

/**
 * @author Axel Sanchez
 */

@Dao
interface ProductDao {
    /**
     * Get the questions of a specific category
     * @param [category] is the category of the question
     * @return list of questions
     * */
    @Query("SELECT * FROM Question WHERE category = :category")
    suspend fun getQuestions(category: Int): List<Response.Question?>?

    /**
     * Get the question of a specific id
     * @param [id] is the id of the question
     * @return a question
     * */
    @Query("SELECT * FROM Question WHERE id = :id")
    suspend fun getQuestionFromId(id: Long): Response.Question?

    /**
     * We insert a question in the local database
     * @param [question] that i want to insert
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: Response.Question?): Long
}