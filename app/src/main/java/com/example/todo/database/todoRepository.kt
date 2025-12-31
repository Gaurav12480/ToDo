package com.example.todo.database

import kotlinx.coroutines.flow.Flow


class TodoRepository(private val todoDao: TodoDao) {
    val allTodos: Flow<List<TodoEntity>> = todoDao.getAllTodos()

    suspend fun insert(todo: TodoEntity) {
        todoDao.insert(todo)
    }

    suspend fun delete(todo: TodoEntity) {
        todoDao.delete(todo)
    }
    suspend fun deleteAll() {
        todoDao.deleteAll()
    }
}