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
interface QuestionDao {
    @Query("SELECT * FROM Question WHERE category = :category")
    suspend fun getQuestionsByCategory(category: Int): List<Response.Question?>?

    @Query("SELECT * FROM Question WHERE id = :id")
    suspend fun getQuestionById(id: Long): Response.Question?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: Response.Question?): Long
}