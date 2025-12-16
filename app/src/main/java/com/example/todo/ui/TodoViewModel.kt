package com.example.todo.ui

import androidx.lifecycle.ViewModel
import com.example.todo.model.TodoItem
import com.example.todo.model.todoList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TodoViewModel: ViewModel() {
    private var _todoListState = MutableStateFlow(todoList)
    val todoListState: StateFlow<List<TodoItem>> = _todoListState.asStateFlow()


    fun addTodo(text: String) {
        if (text != "") {
            _todoListState.value += TodoItem(text = text)
        }
    }

    fun deleteTodo(id: Int) {
        _todoListState.value = _todoListState.value.filterNot { it.id == id }
    }

    fun clearAll() {
        _todoListState.value = _todoListState.value.dropWhile { todoListState.value.isNotEmpty() }
    }
}