package com.example.habitsapp.ui.presentation.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.habitsapp.domain.model.Habit
import com.example.habitsapp.ui.navigation.Screen
import com.example.habitsapp.util.Utility.navigateTo


@Composable
fun TodoItem(
    todo: Habit,
    onDeleteClick: () -> Unit,
    navController: NavHostController
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
                .padding(8.dp)
                .clip(shape = RoundedCornerShape(8.dp)),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = CenterVertically
        ) {
            Text(
                text = todo.title,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(8.dp)
                    .weight(0.7f),
                color = Color.Black,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.width(8.dp))


            IconButton(onClick = {
                navigateTo(
                    Screen.StartTodo.getItemId(todo.id!!),
                    navController
                )
            }) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "play",
                )
            }


            IconButton(onClick = onDeleteClick) {
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