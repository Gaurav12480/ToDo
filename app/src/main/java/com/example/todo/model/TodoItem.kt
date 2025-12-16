package com.example.todo.model

data class TodoItem (
    var isDone: Boolean = false,
    var text: String = "",
    var id: Int = generateId()
)
var nextId = 0

fun generateId(): Int {
    return nextId++
}


var todoList = listOf<TodoItem>(
    TodoItem(false, "Something", -3),
    TodoItem(false, "Something else", -2),
    TodoItem( false, "Nothing", -1)
)