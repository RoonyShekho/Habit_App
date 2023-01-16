package com.example.habitsapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector


sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(
        route = "home_screen/HOME",
        title = "HOME",
        icon = Icons.Default.Home
    )

    object Completed : BottomBarScreen(
        route = "completed_screen/completed",
        title = "COMPLETED",
        icon = Icons.Default.Done
    )
}