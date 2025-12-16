package com.example.todo.ui

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todo.R
import com.example.todo.model.TodoItem

@Composable
fun TodoListPage(modifier: Modifier = Modifier, todoViewModel: TodoViewModel = viewModel()) {
    val todoList by todoViewModel.todoListState.collectAsState()
    Scaffold(
        modifier = modifier.safeDrawingPadding(),
        topBar = { TodoTopAppBar() },
        bottomBar = {
            TodoAddItem(
                onAddTodo = { todoViewModel.addTodo(it) }

            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            TodoClearAllButton(
                clearAll = {
                    todoViewModel.clearAll()
                },
                color = if (todoList.isNotEmpty()) {
                    Color(0, 150, 255)
                } else {
                    Color(192, 192, 192)
                }
            )
            TodoList(
                list = todoList,
                delete = { todoViewModel.deleteTodo(id = it) }
            )
        }
    }
}


@Composable
fun TodoAddItem(modifier: Modifier = Modifier, onAddTodo: (String) -> Unit) {
    var text by rememberSaveable { mutableStateOf("") }

    BottomAppBar(
        containerColor = Color.Transparent,
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            trailingIcon = {
                IconButton(
                    onClick = {
                        onAddTodo(text)
                        text = ""
                    }

                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Icon"
                    )
                }
            },
            label = {
                Text(
                    text = "Add task..."
                )
            },
            singleLine = true,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun TodoList(modifier: Modifier = Modifier, list: List<TodoItem>, delete: (Int) -> Unit) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        list.forEach { ele ->
            Box(modifier = Modifier.fillMaxWidth()) {
                var check: Boolean by rememberSaveable{mutableStateOf(ele.isDone)}
                Checkbox(
                    checked = check,
                    onCheckedChange = {check = !check},
                    modifier = Modifier.align(Alignment.CenterStart)
                )
                Text(
                    text = ele.text,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .padding(horizontal = 44.dp)
                        .align(Alignment.CenterStart),
                    textDecoration = if (check) TextDecoration.LineThrough else TextDecoration.None
                )
                IconButton(
                    onClick = {delete(ele.id)},
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.Gray,
                    )
                }
            }
        }
    }
}

@Composable
fun TodoClearAllButton(modifier: Modifier = Modifier, clearAll: () -> Unit, color: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        TextButton(onClick = {clearAll()}) {
            Text(
                text = "Clear All",
                color = color
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(54, 69, 79)),
        title = {
            Row (
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = Color(50, 205, 50)
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = stringResource(R.string.todo),
                    fontSize = 48.sp,
                    color = Color.White
                )
            }
        }
    )
}

@Preview
@Composable
private fun TodoListPagePreview() {
    TodoListPage()
}