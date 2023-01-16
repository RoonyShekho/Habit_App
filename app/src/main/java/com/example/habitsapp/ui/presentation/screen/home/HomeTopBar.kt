package com.example.habitsapp.ui.presentation.screen.home


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeTopBar(
    modifier: Modifier,
    onSearchClick: () -> Unit
) {


    Row(modifier = modifier, horizontalArrangement = Arrangement.End) {
        IconButton(onClick = onSearchClick) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "search",
                modifier = Modifier.padding(start = 16.dp),
                tint = MaterialTheme.colorScheme.outline
            )
        }
    }
}




