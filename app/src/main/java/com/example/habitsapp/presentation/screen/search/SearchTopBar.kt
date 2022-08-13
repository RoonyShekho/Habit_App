package com.example.habitsapp.presentation.screen.search

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchTopBar(
    value: String,
    onValueChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    onDismissClick: ()->Unit
) {

    Row(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(0.93f),
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = "Search..")},
            singleLine = true
        )

        IconButton(onClick = {onSearchClick()}) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "search icon")
        }

        IconButton(onClick = onDismissClick) {
            Icon(imageVector = Icons.Default.Clear, contentDescription = "search icon")
        }

    }
}