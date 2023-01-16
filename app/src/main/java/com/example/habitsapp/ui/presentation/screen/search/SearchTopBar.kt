package com.example.habitsapp.ui.presentation.screen.search

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@ExperimentalMaterial3Api
@Composable
fun SearchTopBar(
    value: String,
    onValueChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    onDismissClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(0.93f)
                .align(CenterVertically),
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = "Search..") },
            singleLine = true,
            trailingIcon = {
                IconButton(
                    onClick =
                    onDismissClick
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "clear icon",
                        tint = Color.LightGray
                    )
                }
            }
        )

        IconButton(
            onClick = { onSearchClick() },
            modifier = Modifier
                .align(CenterVertically)
                .padding(2.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "search icon",
                tint = Color.LightGray
            )
        }
    }
}