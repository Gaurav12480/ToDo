package com.example.todo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.database.TodoEntity
import com.example.todo.database.TodoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class TodoViewModel(
    private val repository: TodoRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    val todos = repository.allTodos

    fun addTodo(todo: String) =
        viewModelScope.launch(ioDispatcher) { repository.insert(TodoEntity(task = todo)) }

    fun deleteTodo(todoEntity: TodoEntity) =
        viewModelScope.launch(ioDispatcher) { repository.delete(todoEntity) }

    fun toggleTodo(todoEntity: TodoEntity) =
        viewModelScope.launch(ioDispatcher) { repository.insert(todoEntity.copy(isDone = !todoEntity.isDone)) }

    fun clearAll() {
        viewModelScope.launch(ioDispatcher) { repository.deleteAll() }
    }
}