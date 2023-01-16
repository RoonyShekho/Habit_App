package com.example.habitsapp.ui.presentation.screen.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.habitsapp.ui.navigation.BottomBar
import com.example.habitsapp.ui.navigation.Screen
import com.example.habitsapp.ui.presentation.screen.components.TodoItem
import com.example.habitsapp.util.Utility.navigateTo


@Composable
fun HomeScreen(
    vm: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val scaffoldState = rememberScaffoldState()

    val state = vm.state.value


    Scaffold(
        modifier = Modifier.fillMaxSize()
            .background(Color.White)
        ,
        topBar = {
            HomeTopBar(modifier = Modifier.fillMaxWidth()) {
                navigateTo(Screen.Search.route, navController)
            }
        },
        bottomBar = {
            BottomBar(navController = navController)
        },
        floatingActionButton = {
            LargeFloatingActionButton(
                onClick = {
                    navigateTo(Screen.AddTodo.route, navController)
                },
                modifier = Modifier
                    .padding(16.dp),
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                contentColor = MaterialTheme.colorScheme.onTertiaryContainer
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add",
                    modifier = Modifier.size(28.dp)
                )
            }
        },
        scaffoldState = scaffoldState,
    ) { habit ->

        if (state.habits.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = "NO HABITS ADDED!",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(habit)
            ) {
                items(state.habits) {
                    TodoItem(
                        todo = it,
                        onDeleteClick = { vm.deleteTodo(it.id!!) },
                        navController
                    )
                }
            }
        }
    }
}