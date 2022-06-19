package com.example.habitsapp.presentation.screen.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.habitsapp.domain.model.Habit
import com.example.habitsapp.navigation.BottomBar
import com.example.habitsapp.navigation.Screen
import com.example.habitsapp.ui.theme.backgroundColor
import com.example.habitsapp.ui.theme.itemBackgroundColor
import com.example.habitsapp.ui.theme.textColor


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
    ){

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


@Composable
fun TodoItem(
    todo: Habit,
    onDeleteClick: () -> Unit,
    navController: NavHostController
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(MaterialTheme.colors.itemBackgroundColor),
        horizontalArrangement = Arrangement.SpaceAround
    ) {


        Text(
            text = todo.title,
            style = MaterialTheme.typography.h3,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.textColor,
            modifier = Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = {
                navController.navigate(Screen.StartTodo.getItemId(todo.id!!))
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "GO")
        }


        Column(horizontalAlignment = End) {
            IconButton(
                onClick = onDeleteClick
            ) {

                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "delete item",
                    Modifier
                        .background(Color.Transparent)
                        .padding(8.dp)
                )
            }
        }

    }
}