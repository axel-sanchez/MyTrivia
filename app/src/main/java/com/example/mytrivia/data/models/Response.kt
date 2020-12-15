package com.example.mytrivia.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity data class Response(
    var response_code: Int? = null,
    var results: List<Question?>? = null
) {
    @Entity data class Question(
        @PrimaryKey(autoGenerate = true) var id: Long,
        var category: String? = null,
        var correct_answer: String? = null,
        var difficulty: String? = null,
        var incorrect_answers: List<String?>? = null,
        var question: String? = null,
        var type: String? = null
    )
}