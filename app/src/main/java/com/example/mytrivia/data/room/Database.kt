package com.example.mytrivia.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mytrivia.data.models.Response

/**
 * @author Axel Sanchez
 */

@Database(
    entities = [Response.Question::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class Database: RoomDatabase() {
    abstract fun productDao(): QuestionDao
}