package com.example.room.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.room.Entity.TextEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TextDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addText(text : TextEntity)
    @Query("SELECT * FROM text_table")
    fun readAllData() : Flow<List<TextEntity>>
}