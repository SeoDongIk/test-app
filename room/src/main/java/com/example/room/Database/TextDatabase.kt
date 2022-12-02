package com.example.room.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.room.Dao.TextDao
import com.example.room.Entity.TextEntity

@Database(entities = [TextEntity::class], version = 1, exportSchema = false)
abstract class TextDatabase : RoomDatabase() {
    abstract fun textDao() : TextDao
    companion object {
        @Volatile
        private var INSTANCE : TextDatabase? = null
        fun getDatabase(
            context : Context
        ) : TextDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TextDatabase::class.java,
                    "text_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}