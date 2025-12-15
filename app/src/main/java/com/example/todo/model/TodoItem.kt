package com.example.todo.model

data class TodoItem (
    var isDone: Boolean = false,
    var text: String = ""
)

var list = listOf<TodoItem>(
    TodoItem(false, "Something 1"),
    TodoItem(false, "Something 2"),
    TodoItem(false, "Something else 1"),
    TodoItem(false, "Something else 2"),
    TodoItem( false, "Nothing")
)