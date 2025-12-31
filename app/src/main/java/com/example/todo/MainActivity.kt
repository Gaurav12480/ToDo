package com.example.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.example.todo.database.TodoDatabase
import com.example.todo.database.TodoRepository
import com.example.todo.ui.TodoListPage
import com.example.todo.ui.TodoViewModel
import com.example.todo.ui.theme.ToDoTheme
import kotlinx.coroutines.Dispatchers

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room
            .databaseBuilder(applicationContext, TodoDatabase::class.java, "todo-db")
        val todoViewModel = TodoViewModel(TodoRepository(db.build().todoDao()), Dispatchers.IO)

        enableEdgeToEdge()
        setContent {
            ToDoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TodoListPage(
                        addTodo = todoViewModel::addTodo,
                        deleteTodo = todoViewModel::deleteTodo,
                        todoItemsFlow = todoViewModel.todos,
                        clearAll = todoViewModel::clearAll,
                        onCheckedChange = todoViewModel::toggleTodo
                    )
                }
            }
        }
    }
}
