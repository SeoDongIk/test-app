package com.example.room.Repository

import com.example.room.Dao.TextDao
import com.example.room.Entity.TextEntity
import kotlinx.coroutines.flow.Flow

class Repository(private val textDao: TextDao) {
    val readAllData : Flow<List<TextEntity>> = textDao.readAllData()
    suspend fun addText(text: TextEntity){
        textDao.addText(text)
    }
}