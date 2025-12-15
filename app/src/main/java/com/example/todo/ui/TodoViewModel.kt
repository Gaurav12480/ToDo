package com.example.todo.ui

import androidx.lifecycle.ViewModel
import com.example.todo.model.TodoItem
import com.example.todo.model.list
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TodoViewModel: ViewModel() {
    private var _todoList = MutableStateFlow(list)
    val todoList: StateFlow<List<TodoItem>> = _todoList.asStateFlow()


    fun getList(): List<TodoItem> {
        return _todoList.value
    }
    fun addTodo(text: String) {
        if (text != "") {
            _todoList.value += TodoItem(text = text)
        }
    }
}