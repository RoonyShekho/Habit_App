package com.example.habitsapp.ui.navigation


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.habitsapp.ui.theme.bottomBarContainerColor
import com.example.habitsapp.ui.theme.selectedIndicatorColor
import com.example.habitsapp.util.Utility.navigateTo


@Composable
fun BottomBar(
    navController: NavHostController
) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Completed,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colors.bottomBarContainerColor
    ) {
        screens.forEach { screen ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any {
                    it.route == screen.route
                } == true,
                onClick = {
                    navigateTo(screen.route, navController)
                },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = "Navigation Icon"
                    )
                },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(selectedIconColor = MaterialTheme.colors.selectedIndicatorColor)
            )
        }
    }
}