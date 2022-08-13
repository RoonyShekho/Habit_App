package com.example.habitsapp.presentation.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.habitsapp.domain.model.Habit
import com.example.habitsapp.navigation.Screen
import com.example.habitsapp.ui.theme.itemBackgroundColor
import com.example.habitsapp.ui.theme.textColor


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
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(text = "GO")
        }


        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {
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