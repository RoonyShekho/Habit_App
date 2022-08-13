package com.example.habitsapp.presentation.screen.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.habitsapp.navigation.BottomBar
import com.example.habitsapp.navigation.Screen
import com.example.habitsapp.presentation.screen.components.TodoItem
import com.example.habitsapp.ui.theme.backgroundColor


@Composable
fun HomeScreen(
    vm: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val scaffoldState = rememberScaffoldState()

    val state = vm.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.backgroundColor),
    ) {

        Scaffold(
            Modifier.background(MaterialTheme.colors.backgroundColor),
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    navController.navigate(Screen.AddTodo.route)
                }
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "add todo")
                }
            },
            topBar = {
                HomeTopBar {
                    navController.navigate(Screen.Search.route)
                }
            },
            bottomBar = {
                BottomBar(navController = navController)
            },
            scaffoldState = scaffoldState
        ) {
            LazyColumn {
                items(state.todos, key = { todo ->
                    todo.id!!
                }) {
                    TodoItem(
                        todo = it,
                        onDeleteClick = { vm.onAction(TodoAction.DeleteTodo(it.id!!)) },
                        navController
                    )
                }
            }
        }
    }
}

