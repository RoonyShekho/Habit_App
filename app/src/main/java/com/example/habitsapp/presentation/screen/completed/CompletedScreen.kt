package com.example.habitsapp.presentation.screen.completed


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.habitsapp.domain.model.Completed
import com.example.habitsapp.navigation.BottomBar
import com.example.habitsapp.ui.theme.backgroundColor

@Composable
fun CompletedScreen(
    navController: NavHostController,
    vm: CompletedViewModel = hiltViewModel()
) {

    val completedItems = vm.state.value

    Scaffold(bottomBar = {
        BottomBar(navController = navController)
    }) {

        LazyColumn {
            items(completedItems.completed) {
                CompletedItem(
                    todo = Completed(
                        id = it.id,
                        title = it.title,
                        duration = it.duration
                    )
                ){
                    it.id?.let { id -> vm.deleteCompleted(id) }
                }
            }
        }
    }

}


@Composable
fun CompletedItem(
    todo: Completed,
    onDeleteClick:()->Unit
) {

    Surface(color = MaterialTheme.colors.backgroundColor) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        )
        {
            Text(text = todo.title, fontSize = 20.sp)


            Text(text = todo.duration)

            IconButton(
                onClick = onDeleteClick,
                modifier = Modifier.align(End)
            ) {

                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "delete item",
                    Modifier
                        .background(Color.Transparent)
                )
            }
        }
    }
}