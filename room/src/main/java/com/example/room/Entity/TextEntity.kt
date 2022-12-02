package com.example.room.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "text_table")
data class TextEntity (
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var text : String
)