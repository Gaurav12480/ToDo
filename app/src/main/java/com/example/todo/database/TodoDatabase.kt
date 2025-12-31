package com.example.todo.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TodoEntity::class], version = 1, exportSchema = false)        // ::class → class as a value (Kotlin class reference)
abstract class TodoDatabase: RoomDatabase() {       //abstract: You can't create objects directly, only inherit abstract
     abstract fun todoDao(): TodoDao
}
