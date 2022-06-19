package com.example.habitsapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector


sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(
        route = "home_screen",
        title = "HOME",
        icon = Icons.Default.Home
    )

    object Completed : BottomBarScreen(
        route = "completed_screen",
        title = "COMPLETED",
        icon = Icons.Default.Info
    )

}