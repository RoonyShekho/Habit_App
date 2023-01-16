package com.example.habitsapp.ui.presentation.screen.completed


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.habitsapp.domain.model.Completed
import com.example.habitsapp.ui.navigation.BottomBar


@ExperimentalFoundationApi
@Composable
fun CompletedScreen(
    navController: NavHostController,
    vm: CompletedViewModel = hiltViewModel()
) {

    val completedItems = vm.state.value
    val scaffoldState = rememberScaffoldState()


    val grouped = completedItems.completed.groupBy { it.date }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        bottomBar = {
            BottomBar(
                navController = navController
            )
        },
        scaffoldState = scaffoldState
    )
    {
        if (completedItems.completed.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = "COMPLETE A HABIT",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.padding(it),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                grouped.forEach { (initial, completed) ->
                    stickyHeader {
                        Surface(modifier = Modifier.fillMaxWidth(), color = LightGray) {
                            Text(text = initial, modifier = Modifier.padding(4.dp), textAlign = TextAlign.Center)
                        }
                    }

                    items(completed) { item ->
                        CompletedItem(
                            todo = Completed(
                                id = item.id,
                                title = item.title,
                                duration = item.duration,
                            )
                        ) {
                            item.id?.let { id -> vm.deleteCompleted(id) }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CompletedItem(
    todo: Completed,
    onDeleteClick: () -> Unit
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(shape = RoundedCornerShape(8.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.8f),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = todo.title, style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
                )

                Text(
                    text = todo.duration, style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            }

            FloatingActionButton(onClick = onDeleteClick) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "delete item",
                    Modifier
                        .background(Color.Transparent)
                        .size(25.dp)
                )
            }
        }
    }
}