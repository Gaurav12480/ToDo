package com.example.todo.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mytodos")      //@Entity tells room to treat this class as DB table
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)    //id is marked with PrimaryKey so it acts as unique identifier for each item
    val id: Int = 0,
    val task: String,
    var isDone: Boolean = false

)
