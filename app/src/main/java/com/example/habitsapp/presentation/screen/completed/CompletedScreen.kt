package com.example.habitsapp.presentation.screen.completed


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.habitsapp.domain.model.Completed
import com.example.habitsapp.navigation.BottomBar
import com.example.habitsapp.ui.theme.itemBackgroundColor

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
                ) {
                    it.id?.let { id -> vm.deleteCompleted(id) }
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
        color = MaterialTheme.colors.itemBackgroundColor, modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(shape = RoundedCornerShape(8.dp))
    ) {


        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
            )
            {
                Text(
                    text = todo.title, style = MaterialTheme.typography.h3,
                    textAlign = TextAlign.Center,
                )

                Text(
                    text = todo.duration, style = MaterialTheme.typography.h3,
                    textAlign = TextAlign.Center,
                )
            }


            Spacer(modifier = Modifier.width(80.dp))

            IconButton(
                onClick = onDeleteClick,
                modifier = Modifier.align(CenterVertically)
            ) {

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